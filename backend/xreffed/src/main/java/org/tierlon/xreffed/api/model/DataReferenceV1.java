package org.tierlon.xreffed.api.model;

import com.google.common.base.Strings;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by justinanddiana on 7/3/14.
 */
public class DataReferenceV1 {


    @Id
    private String id;

    private String referenceText;

    private List<String> authors;

    private List<String> locationReferences;

    private List<String> tags;

    private List<String> comments;

    private Map<String,Object> propertyMap;

    private List<String> links;


    // ==============================================================
    // CONSTRUCTION
    // ==============================================================
    public DataReferenceV1(DataReferenceV1Builder builder) {
        this.id = builder.id;
        this.referenceText = builder.referenceText;
        this.authors = builder.authors;
        this.locationReferences = builder.locationReferences;
        this.tags = builder.tags;
        this.comments = builder.comments;
        this.propertyMap = builder.propertyMap;
        this.links = builder.links;
    }


    // ==============================================================
    // PUBLIC -- GETTERS
    // ==============================================================

    public String getId() {
        return id;
    }

    public String getReferenceText() {
        return referenceText;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getLocationReferences() {
        return locationReferences;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getComments() {
        return comments;
    }

    public Map<String, Object> getPropertyMap() {
        return propertyMap;
    }

    public List<String> getLinks() {
        return links;
    }



}
