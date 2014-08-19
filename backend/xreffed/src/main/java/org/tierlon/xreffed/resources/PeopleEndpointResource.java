package org.tierlon.xreffed.resources;

import org.tierlon.xreffed.api.model.entity.Person;
import org.tierlon.xreffed.api.model.entity.PersonBuilder;
import org.tierlon.xreffed.api.model.reference.DataReferenceV1;
import org.tierlon.xreffed.api.model.wrappers.EmberPeopleWrapper;
import org.tierlon.xreffed.api.model.wrappers.EmberPersonWrapper;
import org.tierlon.xreffed.api.model.wrappers.EmberXrefsWrapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


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

    String authorPrefixList = " { \"people\": [";
    String authorPostfixList = "]}";

    @GET
    public EmberPeopleWrapper listAll() {

        ArrayList<Person> array = new ArrayList<>();
        Person person = new PersonBuilder("John Doe","author").create();
        Person person2 = new PersonBuilder("Jane Eyre","character").create();

        array.add(person);
        array.add(person2);
        return new EmberPeopleWrapper(array);

    }

    @POST
    public EmberPersonWrapper createNewPerson(EmberPersonWrapper personWrapper) {
        Person person = personWrapper.getPerson();
        person.setId("12345");
        return personWrapper;
    }


}
