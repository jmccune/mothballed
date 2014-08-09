package org.tierlon.xreffed.api.model.entity;


import org.tierlon.xreffed.api.model.component.NameComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by justinanddiana on 7/28/14.
 */
public class PersonBuilder {

    /** Package scope intentionally */
    String id;
    NameComponent name;
    List<String> roles;

    // ==============================================================
    // CONSTRUCTION
    // ==============================================================
    private void init(String displayName) {

    }

    public PersonBuilder(String name, String role) {
        this.id = UUID.randomUUID().toString();
        this.name = new NameComponent(name);
        this.roles = new ArrayList<String>();
        if (role!=null) {
            roles.add(role);
        }
    }

    public PersonBuilder(NameComponent name, String role) {
        this.id = UUID.randomUUID().toString();
        this.name = new NameComponent(name);
        this.roles = new ArrayList<String>();
        if (role!=null) {
            roles.add(role);
        }
    }

    public PersonBuilder(String id,
                         NameComponent name, List<String> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Person create() {
        return new Person(this);
    }


    // ==============================================================
    // PUBLIC Methods
    // ==============================================================
    public void setId(String id) {
        this.id = id;
    }

    public PersonBuilder set(NameComponent.SingleNameType type, String value) {
        name.set(type, value);
        return this;
    }

    public PersonBuilder set(NameComponent.MultiNameType type,
                            List<String> values) {
        name.set(type, values);
        return this;
    }

    public PersonBuilder clear(NameComponent.NameType type) {
        name.clear(type);
        return this;
    }

    public PersonBuilder addRole(String role) {
        roles.add(role);
        return this;
    }

    public PersonBuilder addRoles(List<String> roles) {
        roles.addAll(roles);
        return this;
    }
}
