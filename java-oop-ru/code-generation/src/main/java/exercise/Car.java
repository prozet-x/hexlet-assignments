package exercise;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@AllArgsConstructor
@Getter
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static Car unserialize(String carAsJSON) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(carAsJSON, Car.class);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    // END
}
