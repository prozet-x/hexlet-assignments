package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
// BEGIN
import static org.junit.jupiter.api.Assertions.*;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void testAll() {
        final String defValue = "defValue";
        FileKV fbd = new FileKV(filepath.toString(), Map.of());
        assertEquals(fbd.toMap().isEmpty(), true);
        fbd.set("key1", "value1");
        assertEquals(fbd.toMap().size(), 1);
        assertEquals(fbd.get("key1", defValue), "value1");
        assertEquals(fbd.get("key2", defValue), defValue);
        fbd.set("key2", "value2");
        assertEquals(fbd.toMap().size(), 2);
        assertEquals(fbd.get("key2", defValue), "value2");
        fbd.set("key1", "value3");
        assertEquals(fbd.toMap().size(), 2);
        assertEquals(fbd.get("key1", defValue), "value3");
        fbd.unset("key1");
        assertEquals(fbd.toMap().size(), 1);
        assertEquals(fbd.get("key1", defValue), defValue);
    }
    // END
}
