package org.tierlon.xreffed.api.model.entity;


import org.tierlon.xreffed.api.model.component.NameComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static org.tierlon.xreffed.api.model.meta.MetaData.*;
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


    // ==============================================================
    // PUBLIC Methods
    // ==============================================================
    public PersonBuilder addRole(String role) {
        roles.add(role);
        return this;
    }

    public PersonBuilder addRoles(List<String> roles) {
        roles.addAll(roles);
        return this;
    }
}
