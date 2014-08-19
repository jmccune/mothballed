package org.tierlon.xreffed.api.model.wrappers;

import org.tierlon.xreffed.api.model.entity.Person;

/**
 * Created by justinanddiana on 8/18/14.
 */
public class EmberPersonWrapper {

    private Person person;

    public EmberPersonWrapper(Person person) {
        this.person = person;
    }

    public EmberPersonWrapper(){}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
