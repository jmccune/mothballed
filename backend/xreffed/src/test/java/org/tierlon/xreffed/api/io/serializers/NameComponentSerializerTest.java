package org.tierlon.xreffed.api.io.serializers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import org.tierlon.xreffed.api.model.component.NameComponent;

import java.io.IOException;
import java.io.StringWriter;

import static org.testng.Assert.assertTrue;

/**
 * Created by justinanddiana on 7/31/14.
 */
public class NameComponentSerializerTest {

//    String[] tests = {
//            "John Doe",null,"{'NAME':'John Doe'}",
//            "John Adams","DISPLAY_NAME","John Quincy",null,"{'NAME':'John Doe'}",
//    };
//
    @Test
    public void doSerializationTest() {

        NameComponent component = new NameComponent("John Doe");
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw,component);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String answer="{'NAME':'John Doe'}".replace("'","\"");
        assertTrue(sw.toString().equals(answer));
    }
}
