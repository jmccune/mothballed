package org.tierlon.xreffed.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tierlon.xreffed.api.model.AutocompleteReference;

import java.util.List;

/**
 * Created by justinanddiana on 7/26/14.
 */
public interface IAutocompleteRepository
        extends MongoRepository<AutocompleteReference,String>, IAutocompleteRepositoryCustom {

    List<AutocompleteReference> findByTokensAndEntityType(String token, String entityType);
}
