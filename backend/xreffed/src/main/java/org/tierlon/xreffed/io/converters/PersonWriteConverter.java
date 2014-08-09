package org.tierlon.xreffed.io.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.tierlon.xreffed.api.model.entity.Person;
import org.tierlon.xreffed.io.converters.component.NameComponentConverterHelper;

/**
 * Created by justinanddiana on 8/1/14.
 */
public class PersonWriteConverter
        implements Converter<Person, DBObject> {

    private NameComponentConverterHelper helper =
            new NameComponentConverterHelper();

    @Override
    public DBObject convert(Person person) {
        DBObject dbo = new BasicDBObject();

        dbo.put("_id", person.getId());
        dbo.put("_class",person.getClass().toString());
        dbo.put("roles",person.getRoles());

        helper.writeToDbObject(person.getNameComponent(),dbo);

        return dbo;
    }
}
