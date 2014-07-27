package org.tierlon.xreffed.api.repositories;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tierlon.xreffed.api.model.AutocompleteReference;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by justinanddiana on 7/26/14.
 */
public class IAutocompleteRepositoryTest {

    private static ApplicationContext applicationContext;
    private static IAutocompleteRepository dataRepo;
    @BeforeClass
    static public void loadApplicationContext()
    {
        applicationContext = new ClassPathXmlApplicationContext("RepositoryTestApplicationContext.xml");
        dataRepo  = (IAutocompleteRepository) applicationContext.getBean("IAutocompleteRepository");

        //basic Connectivity Tests
        dataRepo.deleteAll();
        long count = dataRepo.count();
        System.out.println("FOUND "+count+" results on startup!");

        String[] titleTokens = {"alice","wonderland","in"};
        String[] authorTokens = {"lewis","carroll"};
        String[] friendTokens = {"alexander","the","great"};
        dataRepo.save(new AutocompleteReference(titleTokens,"book","0123456","{}"));
        dataRepo.save(new AutocompleteReference(authorTokens,"author","0123456","{}"));
        dataRepo.save(new AutocompleteReference(friendTokens,"friend","0123456","{}"));

    }



    @Test
    public void basicSaveTest() {
        String[] tokens = {"john","doe","middlename"};

        long count = dataRepo.count();
        dataRepo.save(new AutocompleteReference(tokens,"author","0123456","{}"));
        long count2 = dataRepo.count();

        assertTrue(count+1 == count2);
    }

    @Test
    public void findByTokensTest() {

        List<AutocompleteReference> foundReferences = dataRepo.findByTokensAndEntityType("alice","book");
        assertTrue(foundReferences.size() == 1);
    }


    @Test
    public void customFindTest() {

        List<AutocompleteReference> foundReferences = dataRepo.findCompletions("al","book");
        System.err.println(">>>>>> "+foundReferences.size());
        //assertTrue(foundReferences.size() == 1);
    }


}
