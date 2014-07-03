package org.tierlon.xreffed.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tierlon.xreffed.api.model.DataReferenceV1;

/**
 * Created by justinanddiana on 7/3/14.
 */
public interface IDataReferenceRepository extends MongoRepository<DataReferenceV1,String> {

    public DataReferenceV1 findById(String id);
}
