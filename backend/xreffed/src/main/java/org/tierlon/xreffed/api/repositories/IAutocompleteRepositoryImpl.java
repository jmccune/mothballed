package org.tierlon.xreffed.api.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.tierlon.xreffed.api.model.reference.AutocompleteReference;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by justinanddiana on 7/26/14.
 */
public class IAutocompleteRepositoryImpl implements IAutocompleteRepositoryCustom {

    private MongoTemplate mongoTemplate;


    @Autowired
    public IAutocompleteRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<AutocompleteReference> findCompletions(String queryTerm, String entityType) {

        Criteria criteria = where("tokens").regex(queryTerm+".*");
        Query query=Query.query(criteria);
        if (entityType!=null && entityType.trim().length()>0) {
            query.addCriteria(where("entityType").is(entityType));
        }

        return mongoTemplate.find(query, AutocompleteReference.class);
    }
}
