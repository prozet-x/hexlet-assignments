package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(fn -> !fn.isEmpty(), "Имя не должно быть пустым");
        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(ln -> !ln.isEmpty(), "Фамилия не должна быть пустой");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                .check(e -> EmailValidator.getInstance().isValid(e), "Email должен быть валидным");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(p -> p.length() > 3, "пароль должен быть не короче 4 символов")
                .check(p -> StringUtils.isNumeric(p), "Пароль должен содержать только цифры");

        Map<String, List<ValidationError<?>>> errors = JavalinValidation.collectErrors(
                firstNameValidator,
                lastNameValidator,
                emailValidator,
                passwordValidator
        );
        User user = new User(firstName, lastName, email, password);

        if (!errors.isEmpty()) {
            ctx.attribute("errors", errors);
            ctx.attribute("user", user);
            ctx.status(422);
            ctx.render("/users/new.html");
            return;
        }
        user.save();
        ctx.attribute("flash", "Пользователь успешно добавлен");
        ctx.redirect("/users");
        // END
    };
}
