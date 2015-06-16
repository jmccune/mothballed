package org.tierlon.xreffed.io.converters.component;

import com.mongodb.DBObject;
import org.tierlon.xreffed.api.model.component.NameComponent;

import java.util.List;


/**
 * Created by justinanddiana on 8/8/14.
 */
public class NameComponentConverterHelper {
    public static final String NC_USED_SINGLE_NAMES = "NC_UsedSingleNames";
    public static final String NC_USED_MULTI_NAMES = "NC_UsedMultiNames";

    public void writeToDbObject(NameComponent component,DBObject dbo) {
        StringBuilder sb = new StringBuilder();

        for (org.tierlon.xreffed.api.model.component.NameComponent.SingleNameType type : component.getUsedSingleNameTypes()) {
            dbo.put(type.toString(), component.getName(type));
            if (sb.length()>0) {
                sb.append(",");
            }
            sb.append(type.toString());
        }
        dbo.put(NC_USED_SINGLE_NAMES,sb.toString());

        sb = new StringBuilder();
        for (org.tierlon.xreffed.api.model.component.NameComponent.MultiNameType type : component.getUsedMultiNameTypes()) {
            dbo.put(type.toString(), component.getNames(type));
            if (sb.length()>0) {
                sb.append(",");
            }
            sb.append(type.toString());
        }
        dbo.put(NC_USED_MULTI_NAMES,sb.toString());
    }

    public NameComponent readFromDbObject(DBObject dbo) {

        String nameKey =NameComponent.SingleNameType.NAME.toString();
        String name = (String) dbo.get(nameKey);

        NameComponent nameComponent = new NameComponent(name);
        String singleNameList = (String)
                dbo.get(NC_USED_SINGLE_NAMES);

        for (String typeAsString: singleNameList.split(",")) {
            if (typeAsString.trim().isEmpty()) {
                continue;
            }
            String value = (String) dbo.get(typeAsString);
            NameComponent.SingleNameType type = (NameComponent.SingleNameType)
                    NameComponent.getType(typeAsString);
            nameComponent.set(type,value);
        }

        String multiNameList = (String)
                dbo.get(NC_USED_MULTI_NAMES);

        for (String typeAsString: multiNameList.split(",")) {
            if (typeAsString.trim().isEmpty()) {
                continue;
            }
            List<String> values = (List<String>) dbo.get(typeAsString);
            NameComponent.MultiNameType type =
                    (NameComponent.MultiNameType)
                    NameComponent.getType(typeAsString);
            nameComponent.set(type,values);
        }

        return nameComponent;
    }

}
