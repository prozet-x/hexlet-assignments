package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        List<Integer> actual = App.take(list, 4);
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        assertThat(actual)
                .isNotEmpty()
                .isEqualTo(expected);

        actual = App.take(list, 0);
        assertThat(actual)
                .isEmpty();

        actual = App.take(list, 10);
        assertThat(actual)
                .isNotEmpty()
                .isEqualTo(list);
        // END
    }
}
