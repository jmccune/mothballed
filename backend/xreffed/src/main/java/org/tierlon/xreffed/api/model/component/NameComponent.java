package org.tierlon.xreffed.api.model.component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    //TODO -- SUFFIXES, TITLES

    // ==============================================================
    // PRIVATE Fields
    // ==============================================================

    public Map<NameType,Object> nameMap;

    // ==============================================================
    // CONSTRUCTION
    // ==============================================================
    public NameComponent(String name) {
        name = cleanName(name);
        String[] res = name.split("\\W");

        if (res.length<2) {
            throw new IllegalArgumentException("Name: "+name+" doesn't work as a person's name--not specific enough!");
        }

        nameMap = new HashMap<NameType,Object>();
        nameMap.put(SingleNameType.NAME, name);
    }

    public NameComponent(NameComponent other) {
        nameMap = new HashMap<NameType,Object>();
        nameMap.putAll(other.nameMap);
    }

    // ==============================================================
    // GETTERS
    // ==============================================================
    public Set<NameType> getUsedNameTypes() {
        return nameMap.keySet();
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
        List<String> results = (List<String>) nameMap.get(nameType);
        if ( results != null ) {
            return results;
        }
        return new ArrayList<>();
    }

    // ==============================================================
    // PRIVATE Methods
    // ==============================================================
    static private String cleanName(String name) {
        if (name == null)  {return ""; }
        return name.trim();
    }
}