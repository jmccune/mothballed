package org.tierlon.xreffed.io.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.tierlon.xreffed.api.model.component.NameComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by justinanddiana on 7/31/14.
 */
public class NameComponentDeserializer extends JsonDeserializer<NameComponent> {

    @Override
    public NameComponent deserialize(JsonParser jsonParser,
                DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        NameComponent nameComponent = new NameComponent("deserialized temporary");

        Iterator<Map.Entry<String,JsonNode>> entries= node.fields();
        while (entries.hasNext()) {
            Map.Entry<String,JsonNode> entry = entries.next();
            NameComponent.NameType type =
                    NameComponent.getType(entry.getKey());

            if (type instanceof NameComponent.SingleNameType){
                String value =  entry.getValue().textValue();
                nameComponent.set((NameComponent.SingleNameType) type, value);
            }
            else if (type instanceof NameComponent.MultiNameType){
                List<String> values = new ArrayList<String>();

                Iterator<JsonNode> arrayListIt =
                        entry.getValue().elements();

                while (arrayListIt.hasNext()) {
                    values.add(arrayListIt.next().asText());
                }
                nameComponent.set((NameComponent.MultiNameType) type, values);
            }
        }

        return nameComponent;
    }
}
