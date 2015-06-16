package org.tierlon.xreffed.api.repositories;

import org.tierlon.xreffed.api.model.reference.AutocompleteReference;

import java.util.List;

/**
 * Created by justinanddiana on 7/26/14.
 */
public interface IAutocompleteRepositoryCustom {

    public List<AutocompleteReference> findCompletions(String query, String entityType);
}
