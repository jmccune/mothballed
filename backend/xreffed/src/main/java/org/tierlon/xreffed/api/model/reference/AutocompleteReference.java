package org.tierlon.xreffed.api.model.reference;

import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * Created by justinanddiana on 7/26/14.
 */
public class AutocompleteReference {

    @Id
    private String id;

    /** The tokens that are searchable to be autocompleted. */
    private String[] tokens;

    /** The type of the actual entity being
     * autocompleted.  E.g. author, book:title, etc.
     */
    private String   entityType;

    /** The reference to the actual entity */
    private String  entityReferenceId;

    /** The information to be returned for AUTOCOMPLETE */
    private String  autocompletionJSON;

    // ==============================================================
    // CONSTRUCTION
    // ==============================================================
    public AutocompleteReference(String[] tokens, String entityType, String entityReferenceId, String autocompletionJSON) {
        this.id = UUID.randomUUID().toString();
        this.tokens = tokens;
        this.entityType = entityType;
        this.entityReferenceId = entityReferenceId;
        this.autocompletionJSON = autocompletionJSON;
    }

    // ==============================================================
    // GETTERS & SETTERS
    // ==============================================================

    public String getId() {
        return id;
    }

    public String[] getTokens() {
        return tokens;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getEntityReferenceId() {
        return entityReferenceId;
    }

    public String getAutocompletionJSON() {
        return autocompletionJSON;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public void setEntityReferenceId(String entityReferenceId) {
        this.entityReferenceId = entityReferenceId;
    }

    public void setAutocompletionJSON(String autocompletionJSON) {
        this.autocompletionJSON = autocompletionJSON;
    }
}
