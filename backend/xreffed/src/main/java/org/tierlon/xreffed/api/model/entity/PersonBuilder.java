package org.tierlon.xreffed.api.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.tierlon.xreffed.api.model.meta.MetaData.*;
/**
 * Created by justinanddiana on 7/28/14.
 */
public class PersonBuilder {
    
    String id;
    String displayName;

    List<String> firstNames;
    List<String> middleNames;
    List<String> lastNames;
    List<String> titles;

    List<String> roles;

    // ==============================================================
    // CONSTRUCTION
    // ==============================================================
    private void init(String displayName,
                      List<String> firstNames, List<String> middleNames,
                      List<String> lastNames) {
        this.id = UUID.randomUUID().toString();
        this.displayName = cleanName(displayName);
        this.firstNames = new ArrayList<String>();
        this.middleNames = new ArrayList<String>();
        this.lastNames= new ArrayList<String>();
        this.titles = new ArrayList<String>();
        this.roles = new ArrayList<String>();

        addNamesToList(firstNames, this.firstNames);
        addNamesToList(lastNames,this.lastNames);
        addNamesToList(middleNames,this.middleNames);

        if (this.firstNames.size()<1) {
            throw new IllegalArgumentException("No first name!");
        }
        if (this.lastNames.size()<1) {
            throw new IllegalArgumentException("No last name!");
        }
    }

    public PersonBuilder(String name) {
        name = cleanName(name);
        String[] res = name.split("\\W");
        if (res.length<2) {
            throw new IllegalArgumentException("Name: "+name+" doesn't work as a person's name--not specific enough!");
        }
        String firstName = res[0];
        String lastName = res[res.length-1];
        List<String> middleNames = new ArrayList<>();

        for (int i=1; i<res.length-1; i++) {
            middleNames.add(res[i]);
        }
        init(name, Arrays.asList(firstName), middleNames, Arrays.asList(lastName));
    }

    public PersonBuilder(String firstName, String middleName, String lastName) {
        init("", Arrays.asList(firstName), Arrays.asList(middleName), Arrays.asList(lastName));
        displayName = buildDefaultName();
    }

    public PersonBuilder(List<String> firstNames, List<String> middleNames, List<String> lastNames) {
        init("",firstNames,middleNames,lastNames);
        displayName = buildDefaultName();
    }


    // ==============================================================
    // PUBLIC Methods
    // ==============================================================
    public PersonBuilder addMiddleName(String name) {
        name = cleanName(name);
        if (name.length()>0) {
            middleNames.add(name);
        }
        return this;
    }

    public PersonBuilder addMiddleNames(List<String> names) {
        for (String name: names) {
            name = cleanName(name);
            if (name.length() > 0) {
                middleNames.add(name);
            }
        }
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

    public PersonBuilder addTitle(String title) {
        titles.add(title);
        return this;
    }

    public PersonBuilder addTitles(List<String> titles) {
        titles.addAll(titles);
        return this;
    }

    // ==============================================================
    // PRIVATE -- Helpers
    // ==============================================================
    private void addNamesToList(List<String> nameList, List<String> target) {
        for (String name: nameList) {
            name = cleanName(name);
            if (name.length()>0) {
                target.add(name);
            }
        }
    }

    private String buildDefaultName() {
        return firstNames.get(0)+ " "+lastNames.get(0);
    }
    static private String cleanName(String name) {
        if (name == null)  {return ""; }
        return name.trim();
    }
}
