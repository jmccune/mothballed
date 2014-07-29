package org.tierlon.xreffed.api.model.entity;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;

import java.util.List;

/**
 * Created by justinanddiana on 7/28/14.
 */
public class Person {

    private String id;
    private String displayName;

    private List<String> firstNames;
    private List<String> middleNames;
    private List<String> lastNames;
    private List<String> titles;

    private List<String> roles;

    // ==============================================================
    // CONSTRUCTION
    // ==============================================================
    // Serialize/Deserialize usage
    Person() {}

    public Person(PersonBuilder builder) {
        this.id = builder.id;
        this.displayName = builder.displayName;
        this.firstNames = builder.firstNames;
        this.middleNames = builder.middleNames;
        this.lastNames = builder.lastNames;
        this.titles = builder.titles;
        this.roles = builder.roles;
    }

    // ==============================================================
    // OBJECT Methods
    // ==============================================================
    public String toString() {

        String rolesAsString = Joiner.on(" ").join(roles);
        return Objects.toStringHelper(this)
                .add("id",this.id)
                .add("displayName",displayName)
                .add("roles",rolesAsString)
                .toString();
    }

    // ==============================================================
    // PUBLIC -- GETTERS
    // ==============================================================

    public String getId() {
        return id;
    }

    public List<String> getFirstNames() {
        return firstNames;
    }

    public List<String> getLastNames() {
        return lastNames;
    }

    public String getDisplayName() {
        return displayName;
    }


    public List<String> getMiddleNames() {
        return middleNames;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getRoles() {
        return roles;
    }

    void setId(String id) {
        this.id = id;
    }

    void setFirstNames(List<String> firstNames) {
        this.firstNames = firstNames;
    }

    void setLastNames(List<String> lastNames) {
        this.lastNames = lastNames;
    }

    void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    void setMiddleNames(List<String> middleNames) {
        this.middleNames = middleNames;
    }

    void setTitles(List<String> titles) {
        this.titles = titles;
    }

    void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
