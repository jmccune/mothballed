package org.tierlon.xreffed.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tierlon.xreffed.api.model.DataReferenceV1;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by justinanddiana on 7/26/14.
 */
@Path("/autocomplete")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutocompleteEndpointResource {
    Logger logger = LoggerFactory.getLogger(AutocompleteEndpointResource.class);

    @GET
    @Path("{data-type}/{query}")
    public String getById(@PathParam("data-type") String dataType,
                         @PathParam("query") String queryTerm) {
        logger.info("DATA TYPE>>>> "+dataType);
        logger.info("QUERY: "+queryTerm);
        return "[ { \"value\":\"This is a test\"}, {\"value\":\"Test2\"} ]";
    }

}
