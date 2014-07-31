package org.tierlon.xreffed.api.model.entity;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import org.tierlon.xreffed.api.model.component.NameComponent;

import java.util.List;

/**
 * Created by justinanddiana on 7/28/14.
 */
public class Person {

    private String id;
    private NameComponent name;
    private List<String> roles;

    // ==============================================================
    // CONSTRUCTION
    // ==============================================================
    // Serialize/Deserialize usage
    Person() {}

    public Person(PersonBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.roles = builder.roles;
    }

    // ==============================================================
    // OBJECT Methods
    // ==============================================================
    public String toString() {

        String rolesAsString = Joiner.on(" ").join(roles);
        return Objects.toStringHelper(this)
                .add("id",this.id)
                .add("name",name)
                .add("roles",rolesAsString)
                .toString();
    }

    // ==============================================================
    // PUBLIC -- GETTERS
    // ==============================================================


    public String getId() {
        return id;
    }

    public String getName() {
        return this.name.getName();
    }

    public NameComponent getNameComponent() {
        return this.name;
    }


    public List<String> getRoles() {
        return roles;
    }

    void setId(String id) {
        this.id = id;
    }

    void setRoles(List<String> roles) {
        this.roles = roles;
    }

    void setNameComponent(NameComponent name) {
        this.name = name;
    }
}
