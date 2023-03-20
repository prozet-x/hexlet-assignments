package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start();
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateUserPos() {
        HttpResponse respCreate = Unirest.post(baseUrl + "/users")
                .field("firstName", "New")
                .field("lastName", "User")
                .field("email", "a@b.com")
                .field("password", "1234")
                .asString();

        assertThat(respCreate.getStatus()).isEqualTo(302);

        User createdUser = new QUser()
                .firstName.eq("New")
                .lastName.eq("User")
                .email.eq("a@b.com")
                .password.eq("1234")
                .findOne();
        assertThat(createdUser).isNotNull();
    }

    @Test
    void testCreateUserNeg() {
        HttpResponse respCreate = Unirest.post(baseUrl + "/users")
                .field("firstName", "")
                .field("lastName", "User")
                .field("email", "a@b")
                .field("password", "123")
                .asString();

        assertThat(respCreate.getStatus()).isEqualTo(422);
        String body = respCreate.getBody().toString();
        assertThat(body).contains("User");
        assertThat(body).contains("a@b");
        assertThat(body).contains("123");
        assertThat(body).contains("User");
        assertThat(body).contains("User");
        assertThat(body).contains("Имя не должно быть пустым");
        assertThat(body).contains("Должно быть валидным email");
        assertThat(body).contains("Пароль должен содержать не менее 4 символов");

        User createdUser = new QUser()
                .lastName.eq("User")
                .email.eq("a@b")
                .password.eq("123")
                .findOne();
        assertThat(createdUser).isNull();
    }

    public static @AfterAll
    void afterAll() {
        app.stop();
    }
    // END
}
