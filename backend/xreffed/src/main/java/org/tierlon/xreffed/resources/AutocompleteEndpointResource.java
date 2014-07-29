package org.tierlon.xreffed.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tierlon.xreffed.api.model.reference.AutocompleteReference;
import org.tierlon.xreffed.api.repositories.IAutocompleteRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by justinanddiana on 7/26/14.
 */
@Path("/autocomplete")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutocompleteEndpointResource {
    Logger logger = LoggerFactory.getLogger(AutocompleteEndpointResource.class);

    private IAutocompleteRepository repository = null;

    @Autowired
    public AutocompleteEndpointResource(IAutocompleteRepository repository) {
        this.repository = repository;
    }

    @GET
    @Path("{data-type}/{query}")
    public String getById(@PathParam("data-type") String dataType,
                         @PathParam("query") String queryTerm) {

        List<AutocompleteReference> autocompleteResults = repository.findCompletions(queryTerm,dataType);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int count=0;
        for (AutocompleteReference reference : autocompleteResults) {
            if (count>0) {
                sb.append(",");
            }
            sb.append(reference.getAutocompletionJSON());
            count++;
        }
        sb.append("]");

        return sb.toString();
    }

    @GET
    @Path("seedData")
    public String seedData() {

        String[] tokenStrings = {
                "lewis,carroll",
                "douglas,adams",
                "jrr,tolkien",
                "orson,scott,card",
                "joshua,mccune",
                "victor,hugo",
                "joshua,bloch",
                "anwar,el,sadat",
                "robert,lynn,aspirin",
                "alexandre,dumas",
                "edgar,allan,poe",
                "j,k,rowling",
                "mark,twain",
                "william,shakespeare",
                "stephen,king",
                "charles,dickens",
                "emily,dickinson",
                "george,orwell",
                "oscar,wilde",
                "fyodor,dostoyevsky",
                "sun,tzu"
        };

        List<AutocompleteReference> refList = new ArrayList<>();
        for (String tokenString : tokenStrings) {
            String id = "id-"+tokenString.replace(",", "-");
            String json = "{ 'id':'"+id+"', 'value':'"+tokenString.replace(",", " ")+"' }";
            json = json.replace("'","\"");
            AutocompleteReference reference =
                    new AutocompleteReference(tokenString.split(","),"author",id,json);
            refList.add(reference);
        }
        repository.save(refList);

        return "Added: "+refList.size()+" entities!";
    }
}
