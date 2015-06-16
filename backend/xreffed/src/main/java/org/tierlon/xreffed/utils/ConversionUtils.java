package org.tierlon.xreffed.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by justinanddiana on 7/24/14.
 */
public class ConversionUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Parsing error for object: "+object.getClass();
        }
    }
}
