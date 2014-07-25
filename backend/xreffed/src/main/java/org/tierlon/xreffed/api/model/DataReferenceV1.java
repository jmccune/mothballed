package org.tierlon.xreffed.api.model;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

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
    /** For use in serialization/deserialization. */
    DataReferenceV1() {}
    
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
    // OBJECT Methods
    // ==============================================================
    public String toString() {

        String text = this.referenceText;
        int textLength = text.length();
        if (textLength>30) {
            text = this.referenceText.substring(0,20)+" ... "+
                    this.referenceText.substring(textLength-10,textLength);
        }

        return Objects.toStringHelper(this)
                .add("id",this.id)
                .add("referenceText",text)
                .add("authors", Joiner.on(",").join(this.authors))
                .toString();
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
//
//    public List<String> getLocationReferences() {
//        return locationReferences;
//    }
//
//    public List<String> getTags() {
//        return tags;
//    }
//
//    public List<String> getComments() {
//        return comments;
//    }
//
//    public Map<String, Object> getPropertyMap() {
//        return propertyMap;
//    }
//
//    public List<String> getLinks() {
//        return links;
//    }

    // ==============================================================
    // SETTERS
    // ==============================================================

    public void setId(String id) {
        this.id = id;
    }

    void setReferenceText(String referenceText) {
        this.referenceText = referenceText;
    }

    void setAuthors(List<String> authors) {
        this.authors = authors;
    }
//
//    void setLocationReferences(List<String> locationReferences) {
//        this.locationReferences = locationReferences;
//    }
//
//    void setTags(List<String> tags) {
//        this.tags = tags;
//    }
//
//    void setComments(List<String> comments) {
//        this.comments = comments;
//    }
//
//    void setPropertyMap(Map<String, Object> propertyMap) {
//        this.propertyMap = propertyMap;
//    }
//
//    void setLinks(List<String> links) {
//        this.links = links;
//    }
}
