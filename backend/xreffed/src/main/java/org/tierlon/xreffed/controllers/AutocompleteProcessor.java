package org.tierlon.xreffed.controllers;

import org.tierlon.xreffed.api.model.reference.DataReferenceV1;
import org.tierlon.xreffed.api.repositories.IAutocompleteRepository;

/**
 * Created by justinanddiana on 7/27/14.
 */
public class AutocompleteProcessor {

    IAutocompleteRepository repository;

    public AutocompleteProcessor() {}

    public void process(DataReferenceV1 xref) {

        for (String author : xref.getAuthors()) {

        }

    }

}
