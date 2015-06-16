package org.tierlon.xreffed.api.model.reference;

import java.util.*;

public class DataReferenceV1Builder {
    String id;
    String referenceText;
    List<String> authors;
    List<String> locationReferences;
    List<String> tags;
    List<String> comments;
    Map<String, Object> propertyMap;
    List<String> links;

    static public String generateNextId() {
        return UUID.randomUUID().toString();
    }
    public DataReferenceV1Builder(String referenceText) {
        this.id = generateNextId();
        this.referenceText = referenceText;
        this.locationReferences = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.propertyMap = new HashMap<String,Object>();
        this.links = new ArrayList<>();
    }

    public DataReferenceV1Builder setAuthors(String... authors) {
        return setAuthors(Arrays.asList(authors));
    }

    public DataReferenceV1Builder setAuthors(List<String> authors) {
        this.authors = authors;
        return this;
    }

    public DataReferenceV1Builder setLocationReferences(List<String> locationReferences) {
        this.locationReferences = locationReferences;
        return this;
    }

    public DataReferenceV1Builder setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public DataReferenceV1Builder setComments(List<String> comments) {
        this.comments = comments;
        return this;
    }

    public DataReferenceV1Builder setPropertyMap(Map<String, Object> propertyMap) {
        this.propertyMap = propertyMap;
        return this;
    }

    public DataReferenceV1Builder setLinks(List<String> links) {
        this.links = links;
        return this;
    }

    public DataReferenceV1 createDataReference() {
        return new DataReferenceV1(this);
    }
}