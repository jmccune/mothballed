package org.tierlon.xreffed.api.model.wrappers;

import org.tierlon.xreffed.api.model.entity.Person;

import java.util.List;

/**
 * Created by justinanddiana on 8/18/14.
 */
public class EmberPeopleWrapper {

    private List<Person> persons;

    public EmberPeopleWrapper(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPeople() {
        return persons;
    }
}
