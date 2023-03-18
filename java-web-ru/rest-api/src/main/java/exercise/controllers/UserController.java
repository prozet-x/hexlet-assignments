package exercise.controllers;

import io.javalin.core.validation.BodyValidator;
import io.javalin.core.validation.ValidationError;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;
import java.util.Map;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
// BEGIN
        List<User> users = new QUser().findList();
        String usersAsJSON = DB.json().toJson(users);
        ctx.json(usersAsJSON);
// END
    };

    public void getOne(Context ctx, String id) {

// BEGIN
        User user = new QUser().id.eq(Long.valueOf(id)).findOne();
        String userAsJSON = DB.json().toJson(user);
        ctx.json(userAsJSON);
// END
    };

    public void create(Context ctx) {

// BEGIN

        BodyValidator<User> userValidator =  ctx.bodyValidator(User.class)
                .check(u -> !u.getFirstName().isEmpty(), "Имя не может быть пустым")
                .check(u -> !u.getLastName().isEmpty(), "Фамилия не может быть пустой")
                .check(u -> EmailValidator.getInstance().isValid(u.getEmail()), "Email должен быть валидным")
                .check(u -> u.getPassword().length() > 4, "Пароль должен содержать не менее 4 символов")
                .check(u -> StringUtils.isNumeric(u.getPassword()), "Пароль должен содержать только цифры");
        Map<String, List<ValidationError<User>>> errors = userValidator.errors();
        if (errors.isEmpty()) {
            String body = ctx.body();
            User user = DB.json().toBean(User.class, body);
            user.save();
        }
// END
    };

    public void update(Context ctx, String id) {
// BEGIN
        User updatedUser = DB.json().toBean(User.class, ctx.body());
        updatedUser.setId(id);
        updatedUser.update();
// END
    };

    public void delete(Context ctx, String id) {
// BEGIN
        new QUser()
                .id.equalTo(Long.valueOf(id))
                .delete();
// END
    };
}
