package org.tierlon.xreffed.api.repositories;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.tierlon.xreffed.api.model.reference.DataReferenceV1;
import org.tierlon.xreffed.api.model.reference.DataReferenceV1Builder;

import static org.testng.Assert.assertTrue;


/**
 * Created by justinanddiana on 7/3/14.
 */
public class IDataReferenceRepositoryTest {

    private static ApplicationContext applicationContext;
    private static IDataReferenceRepository dataRepo;
    @BeforeClass
    static public void loadApplicationContext()
    {
        applicationContext = new ClassPathXmlApplicationContext("RepositoryTestApplicationContext.xml");
        dataRepo  = (IDataReferenceRepository) applicationContext.getBean("IDataReferenceRepository");
    }

    @Test
    public void basicConnectivityTest() {

        long count = dataRepo.count();
        System.out.println("FOUND "+count+" results on startup!");
    }

    @Test
    public void saveRecordTest() {
        long beforeCount = dataRepo.count();
        DataReferenceV1 dataRef =
                new DataReferenceV1Builder("This is a test")
                .setAuthors("Justin McCune")
                .createDataReference();

        dataRepo.save(dataRef);
        long afterCount = dataRepo.count();

        assertTrue(beforeCount+1 == afterCount);
    }
}
