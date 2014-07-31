package org.tierlon.xreffed.api.io.serializers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.tierlon.xreffed.api.model.component.NameComponent;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by justinanddiana on 7/31/14.
 */
public class NameComponentSerializerTest {

    static private final Logger logger =
            LoggerFactory.getLogger(NameComponentSerializerTest.class);

    // ENDS an MultiNameType ARRAY
    private final String END="END";
    String[] tests = {
            "John Doe",null,
            "John Adams","DISPLAY_NAME","John Quincy",null,
            "John Adams","ALIASES","John Q","John Quincy","John Quincy Adams","The Adams",END, null,
            "John Adams","DISPLAY_NAME","John Quincy","ALIASES","John Q","John Quincy","John Quincy Adams","The Adams",END, null,
    };

    String[] answers = {
            "{'NAME':'John Doe'}",
            "{\"NAME\":\"John Adams\",\"DISPLAY_NAME\":\"John Quincy\"}",
            "{\"NAME\":\"John Adams\",\"ALIASES\":[\"John Q\",\"John Quincy\",\"John Quincy Adams\",\"The Adams\"]}",
            "{\"NAME\":\"John Adams\",\"ALIASES\":[\"John Q\",\"John Quincy\",\"John Quincy Adams\",\"The Adams\"],\"DISPLAY_NAME\":\"John Quincy\"}"
    };

    @Test
    public void doSerializationTest() {

        int i=0;
        int testCount=0;
        while (i< tests.length) {
            String mainName = tests[i++];
            logger.info("Running test: {}", testCount);

            NameComponent nameComponent = new NameComponent(mainName);
            while (tests[i] != null) {
                NameComponent.NameType type = getType(tests[i++]);

                if (type instanceof NameComponent.SingleNameType) {
                    String value = tests[i++];
                    nameComponent.set((NameComponent.SingleNameType) type, value);
                } else {
                    List<String> values = new ArrayList<>();
                    String newValue = null;
                    while ((newValue = tests[i++]) != END) {
                        values.add(newValue);
                    }
                    nameComponent.set((NameComponent.MultiNameType) type, values);
                }
            }
            i++;
            String expectedAnswer = answers[testCount++].replace("'", "\"");
            runTest(nameComponent,expectedAnswer);
        }
        logger.info("All done with NameComponent Serialization Test!");
    }

    private NameComponent.NameType getType(String value) {
        for (NameComponent.SingleNameType type :
                NameComponent.SingleNameType.values()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        for (NameComponent.MultiNameType type :
                NameComponent.MultiNameType.values()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
                "Value: "+value+" is not a valid type!");
    }

    private void runTest(NameComponent component, String expectedValue) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw,component);
        } catch (IOException e) {
            e.printStackTrace();
        }


        assertTrue(isEquivalent(sw.toString(), expectedValue));
    }

    private boolean isEquivalent(String json1,String json2) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode tree1 = mapper.readTree(new StringReader(json1));
            JsonNode tree2 = mapper.readTree(new StringReader(json2));
            return tree1.equals(tree2);
        } catch (IOException e) {
            e.printStackTrace();
        }
       throw new IllegalStateException("Unable to compare> "+json1+" to "+json2);
    }
}
