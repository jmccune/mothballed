package org.tierlon.xreffed.resources;

import com.yammer.dropwizard.jersey.params.IntParam;
import com.yammer.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by justinanddiana on 6/28/14.
 */

@Path("/{user}/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestResource {
    public TestResource() {
    }

    @GET
    public String fetch(@PathParam("user") LongParam userId,
                                  @QueryParam("count") @DefaultValue("20") IntParam count) {
        return "['this is a test']";
    }

    @POST
    public String add(@PathParam("user") LongParam userId,
                        String notification) {
        System.out.println("RECEIVED: "+userId+">> "+notification);
        return "'notification received'";
    }
}