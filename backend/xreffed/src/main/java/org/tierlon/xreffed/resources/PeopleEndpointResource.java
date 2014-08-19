package org.tierlon.xreffed.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tierlon.xreffed.api.model.entity.Person;
import org.tierlon.xreffed.api.model.wrappers.EmberPeopleWrapper;
import org.tierlon.xreffed.api.model.wrappers.EmberPersonWrapper;
import org.tierlon.xreffed.api.repositories.IPersonRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * Created by justinanddiana on 8/18/14.
 *
 * This class is just to stub out routes to test/develop front
 * end functionality before developing the real back-end api.
 */
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleEndpointResource {

    Logger logger = LoggerFactory.getLogger(DataRefEndpointResource.class);
    IPersonRepository dataRepo;

    @Autowired
    public PeopleEndpointResource(IPersonRepository dataRepo) {
        this.dataRepo = dataRepo;
    }

    @GET
    public EmberPeopleWrapper listAll() {
        List<Person> personList = dataRepo.findAll();
        return new EmberPeopleWrapper(personList);
    }

    @POST
    public EmberPersonWrapper createNewPerson(EmberPersonWrapper personWrapper) {
        Person person = personWrapper.getPerson();
        person.autoSetId();
        logger.info("Saving person: " + person);
        dataRepo.save(person);
        return personWrapper;
    }
}
