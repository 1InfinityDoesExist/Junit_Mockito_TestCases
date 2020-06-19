package in.patel.javafeature.drastita.util;

import java.io.IOException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StringToJsonConversion {
  public static JsonNode stringToJson(String complexJson) {
    JsonNode jsonNode = null;
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      jsonNode = objectMapper.readTree(complexJson);
    } catch (final IOException ex) {
      ex.printStackTrace();
    }
    return jsonNode;
  }
}
