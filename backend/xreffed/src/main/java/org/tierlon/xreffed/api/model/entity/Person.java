package org.tierlon.xreffed.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.tierlon.xreffed.api.model.component.NameComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justinanddiana on 7/28/14.
 */
public class Person {

    @Id
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
                .add("name",name.getName())
                .add("roles",rolesAsString)
                .toString();
    }

    public boolean equals(Object other) {
        if (( other == null )  || ( getClass() !=other.getClass() )) {
            return false;
        }

        Person otherPerson = (Person) other;
        return Objects.equal(otherPerson.id,id) &&
               Objects.equal(otherPerson.name, name) &&
               Objects.equal(otherPerson.roles, roles);
    }
    // ==============================================================
    // PUBLIC -- GETTERS
    // ==============================================================


    public String getId() {
        return id;
    }

    @JsonIgnore
    public String getName() {
        return this.name.getName();
    }

    public NameComponent getNameComponent() {
        return new NameComponent(this.name);
    }

    public List<String> getRoles() {
        return new ArrayList<String>(roles);
    }

    // ==============================================================
    // Serialization Methods  (NON-PUBLIC)
    // ==============================================================
    public void setId(String id) {
        this.id = id;
    }

    void setRoles(List<String> roles) {
        this.roles = roles;
    }

    void setNameComponent(NameComponent name) {
        this.name = name;
    }
}
