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
 * Serialization & Deserialization tests do the opposite and
 * are therefore logically grouped together.
 *
 * Created by justinanddiana on 7/31/14.
 */
public class NameComponentSerializationTest {

    static private final Logger logger =
            LoggerFactory.getLogger(NameComponentSerializationTest.class);

    // ==============================================================
    // TEST DATA
    // ==============================================================


    // ENDS an MultiNameType ARRAY -- comparison by DIRECT equality
    private final String END="END";

    // Record Structure (used to build an actual component) is:
    //  One Record:
    //      <MainName> [, <otherNameSpec> [,<otherNameSpec>...]], null
    //  Where:
    //    <otherNameSpec>:
    //       <(Single) NameType_ENUM_asString>, <value>
    //       <(Multi) NameType_ENUM_asString>,<value>,<value>...,END
    //

    String[] tests = {
            "John Doe",null,
            "John Adams","DISPLAY_NAME","John Quincy",null,
            "John Adams","ALIASES","John Q","John Quincy","John Quincy Adams","The Adams",END, null,
            "John Adams","DISPLAY_NAME","John Quincy","ALIASES","John Q","John Quincy","John Quincy Adams","The Adams",END, null,
    };

    // Answers are deserialized versions of test i,
    // e.g. answer[i] matches tests[i].
    String[] answers = {
            "{\"NAME\":\"John Doe\"}",
            "{\"NAME\":\"John Adams\",\"DISPLAY_NAME\":\"John Quincy\"}",
            "{\"NAME\":\"John Adams\",\"ALIASES\":[\"John Q\",\"John Quincy\",\"John Quincy Adams\",\"The Adams\"]}",
            "{\"NAME\":\"John Adams\",\"ALIASES\":[\"John Q\",\"John Quincy\",\"John Quincy Adams\",\"The Adams\"],\"DISPLAY_NAME\":\"John Quincy\"}"
    };

    // ==============================================================
    // TESTS
    // ==============================================================
    @Test
    public void doSerializationTest() {
        ITestInvoker serializeTester = new ITestInvoker() {
            public void doTest(NameComponent component,
                               String deserializedVersion) {
                runSerializationTest(component,deserializedVersion);
            }
        };

        iterateTests(serializeTester);
    }

    @Test
    public void doDeserializationTest() {

        ITestInvoker deserializeTester = new ITestInvoker() {
            public void doTest(NameComponent component,
                               String deserializedVersion) {
                runDeserializationTest(deserializedVersion,component);
            }
        };

        iterateTests(deserializeTester);
    }

    // ==============================================================
    // PRIVATE Helper Methods (test harnesses)
    // ==============================================================

    /** Used to invoke the appropriate test.
     */
    private interface ITestInvoker {
        void doTest(NameComponent component,
                    String deserializedVersion);
    };

    /**
     * Iterates over the tests for the serialize & deserialize cases
     */
    private void iterateTests(ITestInvoker invoker) {
        int i=0;
        int testCount=0;
        while (i< tests.length) {
            String mainName = tests[i++];
            logger.info("Running test: {}", testCount);

            NameComponent nameComponent = new NameComponent(mainName);
            while (tests[i] != null) {
                NameComponent.NameType type = NameComponent.getType(tests[i++]);

                if (type instanceof NameComponent.SingleNameType) {
                    String value = tests[i++];
                    nameComponent.set((NameComponent.SingleNameType) type, value);
                } else {
                    List<String> values = new ArrayList<>();
                    String newValue;
                    while ((newValue = tests[i++]) != END) {
                        values.add(newValue);
                    }
                    nameComponent.set((NameComponent.MultiNameType) type, values);
                }
            }
            i++;
            String deserializedVersion = answers[testCount++];

            invoker.doTest(nameComponent, deserializedVersion);
        }
        logger.info("All done with NameComponent Serialization Test!");
    }

    /**
     * A single test of deserialization
     */
    private void runDeserializationTest(String deserializedVersion,
                                        NameComponent component) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            NameComponent nameComponentDeserialized =
                    mapper.readValue(
                            new StringReader(deserializedVersion),
                            NameComponent.class);

            //VERIFICATION STEP HERE
            assertEquals(nameComponentDeserialized,component);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A single test of serialization
     */
    private void runSerializationTest(NameComponent component,
                                      String expectedValue) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw,component);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JsonNode tree1 = mapper.readTree(
                new StringReader(sw.toString()));
            JsonNode tree2 = mapper.readTree(
                new StringReader(expectedValue));

            assertTrue(tree1.equals(tree2));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
