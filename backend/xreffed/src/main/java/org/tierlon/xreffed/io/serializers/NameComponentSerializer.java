package org.tierlon.xreffed.io.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.tierlon.xreffed.api.model.component.NameComponent;

import java.io.IOException;
import java.util.List;

/**
 * Created by justinanddiana on 7/29/14.
 */
public class NameComponentSerializer extends JsonSerializer<NameComponent> {

    @Override
    public void serialize(NameComponent nameComponent,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {

        jsonGenerator.writeStartObject();
        for (NameComponent.NameType type : nameComponent.getUsedNameTypes()) {
            if (type instanceof NameComponent.SingleNameType) {
                String value =
                        nameComponent.getName((NameComponent.SingleNameType) type);
                jsonGenerator.writeStringField("" + type, value);
            } else if (type instanceof NameComponent.MultiNameType) {
                jsonGenerator.writeArrayFieldStart("" + type);

                List<String> values =
                        nameComponent.getNames((NameComponent.MultiNameType) type);
                for (String value : values) {
                    jsonGenerator.writeString(value);
                }
                jsonGenerator.writeEndArray();
            } else {
                throw new IllegalStateException("Unsupported name type!");
            }
        }

    }
}