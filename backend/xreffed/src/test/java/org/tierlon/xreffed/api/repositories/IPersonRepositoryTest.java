package org.tierlon.xreffed.api.repositories;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.tierlon.xreffed.api.model.component.NameComponent;
import org.tierlon.xreffed.api.model.entity.Person;
import org.tierlon.xreffed.api.model.entity.PersonBuilder;
import org.tierlon.xreffed.utils.ConversionUtils;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Created by justinanddiana on 8/1/14.
 */
public class IPersonRepositoryTest {

    private static ApplicationContext applicationContext;
    private static IPersonRepository dataRepo;
    @BeforeClass
    static public void loadApplicationContext() {
        applicationContext = new ClassPathXmlApplicationContext("RepositoryTestApplicationContext.xml");
        dataRepo = (IPersonRepository) applicationContext.getBean("IPersonRepository");

        //basic Connectivity Tests
        dataRepo.deleteAll();
    }

    @Test
    public void testSave() {
        dataRepo.save(new PersonBuilder("John Q Adams", "author").create());
    }

    @Test
    public void testConversionBackAndForth() {
        Person person2 =
            new PersonBuilder("John Q Adams","author")
                .set(NameComponent.SingleNameType.GIVEN_NAME, "John Quincy Adams")
                .create();

        //Serializes person2
        dataRepo.save(person2);

        //Deserialize into person3.
        String id = person2.getId();
        Person person3 = dataRepo.findOne(id);

        // Compare
        assertFalse(person2==person3);
        assertEquals(person2,person3);

        String[] aliases={"The Boxer","The man with the plan"};

        Person person4 =
            new PersonBuilder("John Doe","author")
            .set(NameComponent.MultiNameType.ALIASES, Arrays.asList(aliases))
            .create();

        dataRepo.save(person4);

        //Deserialize into person5
        id = person4.getId();
        Person person5 = dataRepo.findOne(id);

        assertEquals(person4,person5);
    }


}
