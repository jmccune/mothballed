package org.tierlon.xreffed.resources;

import org.tierlon.xreffed.api.model.reference.DataReferenceV1;
import org.tierlon.xreffed.api.model.wrappers.EmberXrefsWrapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by justinanddiana on 8/18/14.
 *
 * This class is just to stub out routes to test/develop front
 * end functionality before developing the real back-end api.
 */
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StubMeDevResource {

    String authorPrefixList = " { \"authors\": [";
    String authorPostfixList = "]}";




    @GET
    public String listAll() {

        String result=""+ authorPrefixList + authorPostfixList;
        return result;
    }


}
