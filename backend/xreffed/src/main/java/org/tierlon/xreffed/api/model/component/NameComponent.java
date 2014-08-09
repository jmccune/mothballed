package org.tierlon.xreffed.api.model.component;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.tierlon.xreffed.io.serializers.NameComponentDeserializer;
import org.tierlon.xreffed.io.serializers.NameComponentSerializer;

import java.util.*;

/**
 * Created by justinanddiana on 7/29/14.
 * Will allow easier merging of records later
 * (assuming two representations of the same person
 *    -- Bill Shakespeare and William Shakespeare
 *    -- The Bard
 *  )
 */
@JsonSerialize(using = NameComponentSerializer.class)
@JsonDeserialize(using = NameComponentDeserializer.class)
public class NameComponent {

    // ==============================================================
    // PUBLIC INFO
    // ==============================================================
    static public interface NameType {};

    static public enum SingleNameType implements NameType {
        // SINGLE ITEM
        NAME,   /** REQUIRED!!! The DEFAULT*/
        DISPLAY_NAME,
        PREFERRED_NAME,
        GIVEN_NAME,
        OFFICIAL_NAME,
        MAIDEN_NAME,
    };

    static public enum MultiNameType implements NameType {
        //LIST EXPECTED
        FORMER_NAMES,
        ALIASES,
        NICKNAMES
    };

    static public NameType getType(String value) {
        NameType result = typeLookupMap.get(value);
        if (result == null) {
            throw new IllegalArgumentException(
                    "Value: " + value + " is not a valid type!");
        }
        return result;
    }
    //TODO -- SUFFIXES, TITLES

    // ==============================================================
    // PRIVATE Fields
    // ==============================================================

    private Map<SingleNameType,String> nameMap;
    private Map<MultiNameType,List<String>> multiNameMap;

    // ==============================================================
    // CONSTRUCTION
    // ==============================================================
    public NameComponent(String name) {
        name = cleanName(name);
        String[] res = name.split("\\W");

        if (res.length<2) {
            throw new IllegalArgumentException("Name: "+name+
            " doesn't work as a person's name--not specific enough!");
        }

        nameMap = new HashMap<SingleNameType,String>();
        nameMap.put(SingleNameType.NAME, name);

        multiNameMap = new HashMap<MultiNameType,List<String>>();
    }

    public NameComponent(NameComponent other) {
        nameMap = new HashMap<SingleNameType,String>();
        nameMap.putAll(other.nameMap);

        multiNameMap = new HashMap<MultiNameType,List<String>>();
        multiNameMap.putAll(other.multiNameMap);
    }

    // ==============================================================
    // OBJECT
    // ==============================================================
    public boolean equals(Object other) {
        if (other == null) { return false; }
        if (!(other instanceof NameComponent)) {
            return false;
        }

        NameComponent otherComponent = (NameComponent) other;
        return this.nameMap.equals(otherComponent.nameMap) &&
                this.multiNameMap.equals(otherComponent.multiNameMap);
    }
    // ==============================================================
    // SETTERS
    // ==============================================================

    /**
     * Sets the value for the given value of the given namecomponent.
     * @param type (non-null) the type of name
     * @param value (non-null) the value to set
     * @return the old value or NULL if no former value.
     */
    public String set(SingleNameType type, String value) {
        if (type == null || value == null) {
            throw new NullPointerException();
        }
        return nameMap.put(type,value);
    }

    /**
     * Sets the value for the given value of the given namecomponent.
     *
     * All values returned or given are defensively copied and
     * may be used/altered without affecting the value set in the
     * NameComponent.
     *
     * @param type (non-null) the type of name
     * @param values (non-null, non-null elements) the list of names
     *               that are given for that type of name.
     *               (e.g. if the type is alias, this is the list of
     *               aliases for the given name.)
     * @return the old list of values or an EMPTY LIST
     *  if no former value.
     */
    public List<String> set(MultiNameType type, List<String> values) {
        if (type == null || values == null) {
            throw new NullPointerException();
        }
        for (String value: values) {
            if (value == null) {
                throw new NullPointerException(
                        "No nulls allowed, not even in list");
            }
        }

        List<String> myList = new ArrayList<String>();
        myList.addAll(values);
        List<String> results =  new ArrayList<String>();
        List<String> foundList = multiNameMap.put(type,myList);
        if ( foundList != null ) {
            results.addAll(foundList);
        }
        return results;
    }


    /**
     * Clears the given parameter, returning the old value (if any)
     *
     * @param type
     * @return the old value, or NULL/EmptyList depending upon the
     * type.
     */
    public Object clear(NameType type) {
        if (type instanceof SingleNameType) {
            return nameMap.remove(type);
        }
        else if (type instanceof MultiNameType) {
            List<String> results = multiNameMap.remove(type);
            if (results == null) {
                return new ArrayList<String>();
            }
            return results;
        }
        else {
            throw new IllegalStateException("Unsupported name type!");
        }
    }



    // ==============================================================
    // GETTERS
    // ==============================================================

    /** Gets all the used name types used by the naem component:
     *  Will return the NAME type for sure-- however, may return
     *  other name types (maiden name, preferred, etc).
     */
    public Set<NameType> getUsedNameTypes() {
        Set<NameType> set = new HashSet<NameType>();
        set.addAll(nameMap.keySet());
        set.addAll(multiNameMap.keySet());
        return set;
    }

    /** Gets all the used name types used that may only have
     * a single value.  (E.g. this includes the required
     * NAME type-- however may include the given/preferred name, etc.)
     */
    public Set<SingleNameType> getUsedSingleNameTypes() {
        Set<SingleNameType> set = new HashSet<SingleNameType>();
        set.addAll(nameMap.keySet());
        return set;
    }

    public Set<MultiNameType> getUsedMultiNameTypes() {
        Set<MultiNameType> set = new HashSet<MultiNameType>();
        set.addAll(multiNameMap.keySet());
        return set;
    }

    public String getName() {
        return (String) nameMap.get(SingleNameType.NAME);
    }
    public String getName(SingleNameType nameType) {
        String result = (String) nameMap.get(nameType);
        if ( result == null ) {
            result = (String) nameMap.get(SingleNameType.NAME);
        }
        return result;
    }

    public List<String> getNames(MultiNameType nameType) {
        List<String> results = multiNameMap.get(nameType);
        return (results == null) ? new ArrayList<String>() : results;
    }

    public Collection<String> getAllNames() {
        HashSet<String> results = new HashSet<String>();
        results.addAll(nameMap.values());
        for (List<String> valueList : multiNameMap.values()) {
            results.addAll(valueList);
        }
        return results;
    }

    // ==============================================================
    // PRIVATE Methods
    // ==============================================================
    static private String cleanName(String name) {
        if (name == null)  {return ""; }
        return name.trim();
    }


    // ==============================================================
    // STATIC PRIVATE INFO
    // ==============================================================

    private static Map<String,NameType> typeLookupMap;

    static {
        typeLookupMap = new HashMap<String,NameType>();
        for (SingleNameType type : SingleNameType.values()) {
            typeLookupMap.put(type.toString(),type);
        }
        for (MultiNameType type : MultiNameType.values()) {
            typeLookupMap.put(type.toString(),type);
        }
    }

}