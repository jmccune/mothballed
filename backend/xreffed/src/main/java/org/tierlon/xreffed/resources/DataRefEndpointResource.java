package org.tierlon.xreffed.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tierlon.xreffed.api.model.DataReferenceV1;
import org.tierlon.xreffed.api.model.DataReferenceV1Builder;
import org.tierlon.xreffed.api.model.wrappers.EmberXrefWrapper;
import org.tierlon.xreffed.api.model.wrappers.EmberXrefsWrapper;
import org.tierlon.xreffed.api.repositories.IDataReferenceRepository;
import org.tierlon.xreffed.utils.ConversionUtils;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/xrefs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 * Created by justinanddiana on 7/3/14.
 */
public class DataRefEndpointResource {

    Logger logger = LoggerFactory.getLogger(DataRefEndpointResource.class);
    IDataReferenceRepository dataRepo;

    @Autowired
    public DataRefEndpointResource(IDataReferenceRepository dataRepo) {
        this.dataRepo = dataRepo;
        System.out.println(">>>  DataRepo: "+dataRepo);
    }

    @GET
    public EmberXrefsWrapper listAll() {

        List<DataReferenceV1> answers =  dataRepo.findAll();
        return new EmberXrefsWrapper(answers);
    }


    @GET
    @Path("{id}")
    public DataReferenceV1 getById(@PathParam("id") String xrefId) {
        return dataRepo.findById(xrefId);
    }

    @POST
    public EmberXrefWrapper addXRef(@Valid EmberXrefWrapper dataRef) {
        logger.debug("addXRef() call data: "+ ConversionUtils.toJSON(dataRef));
        DataReferenceV1 xref = dataRef.getXref();
        xref.setId(DataReferenceV1Builder.generateNextId());

        return new EmberXrefWrapper(xref);
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
