package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {
    @Test
    void testEnlargeArrayImageNormal() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"}
        };
        String[][] expected = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"}
        };
        String[][] actual = App.enlargeArrayImage(image);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testEnlargeArrayImageEmpty() {
        String[][] image = {};
        String[][] expected = {};
        String[][] actual = App.enlargeArrayImage(image);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testEnlargeArrayImageRow() {
        String[][] image = {{"*", " ", " ", "*"}};
        String[][] expected = {{"*", "*", " ", " ", " ", " ", "*", "*"}, {"*", "*", " ", " ", " ", " ", "*", "*"}};
        String[][] actual = App.enlargeArrayImage(image);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testEnlargeArrayImageCol() {
        String[][] image = {{"*"}, {" "}, {" "}, {"*"}};
        String[][] expected = {
                {"*", "*"},
                {"*", "*"},
                {" ", " "},
                {" ", " "},
                {" ", " "},
                {" ", " "},
                {"*", "*"},
                {"*", "*"}
        };
        String[][] actual = App.enlargeArrayImage(image);
        assertThat(actual).isEqualTo(expected);
    }
}
// END
