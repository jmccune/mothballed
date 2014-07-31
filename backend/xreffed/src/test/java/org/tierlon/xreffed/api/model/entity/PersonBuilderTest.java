package org.tierlon.xreffed.api.model.entity;


import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by justinanddiana on 7/29/14.
 */
public class PersonBuilderTest {

    @Test
    public void createPersonTests() {

        PersonBuilder pb = new PersonBuilder("John Doe","author");

//        assertTrue(pb.firstNames.size() == 1 && pb.middleNames.size() == 0 && pb.lastNames.size() == 1);
//        assertTrue(pb.firstNames.get(0).equals("John"));
//        assertTrue(pb.lastNames.get(0).equals("Doe"));
//
//        pb = new PersonBuilder("John Alexander Doe");
//        assertTrue(pb.firstNames.size()==1 && pb.middleNames.size()==1 && pb.lastNames.size()==1 );
//        assertTrue(pb.firstNames.get(0).equals("John"));
//        assertTrue(pb.lastNames.get(0).equals("Doe"));
//        assertTrue(pb.middleNames.get(0).equals("Alexander"));
//
//        pb = new PersonBuilder("John Michael Alexander Doe");
//        assertTrue(pb.firstNames.size()==1 && pb.middleNames.size()==2 && pb.lastNames.size()==1 );
//        assertTrue(pb.firstNames.get(0).equals("John"));
//        assertTrue(pb.lastNames.get(0).equals("Doe"));
//        assertTrue(pb.middleNames.get(0).equals("Michael"));
//        assertTrue(pb.middleNames.get(1).equals("Alexander"));


    }
}
