package org.tierlon.xreffed.io.converters;

import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.tierlon.xreffed.api.model.component.NameComponent;
import org.tierlon.xreffed.api.model.entity.Person;
import org.tierlon.xreffed.api.model.entity.PersonBuilder;
import org.tierlon.xreffed.io.converters.component.NameComponentConverterHelper;

import java.util.List;

/**
 * Created by justinanddiana on 8/1/14.
 */
public class PersonReadConverter
        implements Converter<DBObject, Person> {

    private NameComponentConverterHelper helper =
            new NameComponentConverterHelper();
    @Override
    public Person convert(DBObject dbo) {
        List<String> roles = (List<String>) dbo.get("roles");
        String id = (String) dbo.get("_id");

        if (roles==null || id==null) {
            throw new NullPointerException();
        }
        NameComponent name = helper.readFromDbObject(dbo);
        PersonBuilder pb = new PersonBuilder(id,name,roles);

        return pb.create();
    }
}
