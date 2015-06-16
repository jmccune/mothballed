package org.tierlon.xreffed.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tierlon.xreffed.api.model.entity.Person;

/**
 * Created by justinanddiana on 8/1/14.
 */
public interface IPersonRepository
        extends MongoRepository<Person,String> {


}
