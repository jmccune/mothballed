package org.tierlon.xreffed.api.repositories;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tierlon.xreffed.api.model.reference.AutocompleteReference;

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

        String[] bookTokens = {"alice","wonderland","in"};
        String[] authorTokens = {"lewis","carroll"};
        String[] friendTokens = {"alexander","the","great"};
        String[] friendTokens2 = {"john","doe"};
        String[] friendTokens3 = {"joseph","smith"};
        String[] friendTokens4 = {"joey","the","kid"};
        String[] friendTokens5 = {"alfred","james"};
        String[] friendTokens6 = {"jennifer","lopez"};
        String[] friendTokens7 = {"josephine","buonaparte"};

        dataRepo.save(new AutocompleteReference(bookTokens,"book","0123456","{}"));
        dataRepo.save(new AutocompleteReference(authorTokens,"author","0123456","{}"));
        dataRepo.save(new AutocompleteReference(friendTokens,"friend","0123456","{}"));
        dataRepo.save(new AutocompleteReference(friendTokens2,"friend","0123457","{}"));
        dataRepo.save(new AutocompleteReference(friendTokens3,"friend","0123458","{}"));
        dataRepo.save(new AutocompleteReference(friendTokens4,"friend","0123459","{}"));
        dataRepo.save(new AutocompleteReference(friendTokens5,"friend","0123460","{}"));
        dataRepo.save(new AutocompleteReference(friendTokens6,"friend","0123461","{}"));
        dataRepo.save(new AutocompleteReference(friendTokens7,"friend","0123462","{}"));

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

        Object[] tests = {
                "al","",3,  //Should find alexander,alice, alfred
                "al","book",1,
                "alice","",1,
                "albatross","",0,
                "j","friend",6,
                "jo","friend",4,
                "jos","friend",2,
                "joseph","friend",2,
                "josephi","friend",1
        };

        for (int i=0; i<tests.length; i+=3) {
            String queryTerm = (String) tests[i+0];
            String entityType = (String) tests[i+1];
            Integer numExpected = (Integer) tests[i+2];
            List<AutocompleteReference> foundReferences = dataRepo.findCompletions(queryTerm,entityType);
            assertTrue(foundReferences.size() == numExpected);
        }
    }


}
