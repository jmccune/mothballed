package org.tierlon.xreffed.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.tierlon.xreffed.api.model.DataReferenceV1;
import org.tierlon.xreffed.api.model.DataReferenceV1Builder;
import org.tierlon.xreffed.api.repositories.IDataReferenceRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/xref")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 * Created by justinanddiana on 7/3/14.
 */
public class DataRefEndpointResource {


    IDataReferenceRepository dataRepo;

    @Autowired
    public DataRefEndpointResource(IDataReferenceRepository dataRepo) {
        this.dataRepo = dataRepo;
        System.out.println(">>>  DataRepo: "+dataRepo);
    }

    @GET
    @Path("list/all")
    public List<DataReferenceV1> listAll() {
        return dataRepo.findAll();
    }


    @PUT
    @Path("add/xref")
    public String addXRef(DataReferenceV1 dataRef) {

        System.out.println("Received DataRef: "+dataRef);
        return "Successfully added dataref";
    }

    @GET
    @Path("add/newdata")
    public String addNewData() {

        DataReferenceV1 dataRef =
                new DataReferenceV1Builder("This is a test with GUID: "+ UUID.randomUUID())
                .setAuthors("Justin McCune","TestDataSet")
                .createDataReference();

        dataRepo.save(dataRef);

        return "Successfully added some data";
    }

}
