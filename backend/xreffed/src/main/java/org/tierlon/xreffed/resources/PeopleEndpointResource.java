package org.tierlon.xreffed.resources;

import org.tierlon.xreffed.api.model.entity.Person;
import org.tierlon.xreffed.api.model.entity.PersonBuilder;
import org.tierlon.xreffed.api.model.reference.DataReferenceV1;
import org.tierlon.xreffed.api.model.wrappers.EmberPeopleWrapper;
import org.tierlon.xreffed.api.model.wrappers.EmberXrefsWrapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

        Person person = new PersonBuilder("John Doe","author").create();

        return new EmberPeopleWrapper(Collections.EMPTY_LIST);

    }


}
