package exercise;

import java.lang.reflect.Field;
// BEGIN
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Validator {
    private static final String CANNOT_BE_NULL = "can not be null";
    private static final String BAD_MIN_LENGTH = "length less than ";

    public static List<String> validate(Address address) {
        List<String> res = new ArrayList<>();

        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!validateNotNull(field, address)) {
                res.add(field.getName());
            }
        }
        return res;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> res = new HashMap<>();

        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            List<String> errorList = new ArrayList<>();
            field.setAccessible(true);
            if (!validateNotNull(field, address)) {
                errorList.add(CANNOT_BE_NULL);
                res.put(field.getName(), errorList);
                continue;
            }
            if (!validateMinLength(field, address)) {
                errorList.add(BAD_MIN_LENGTH + getMinLengthAnnotationValue(field, address));
            }
            if (!errorList.isEmpty()) {
                res.put(field.getName(), errorList);
            }
        }
        return res;
    }

    private static Boolean validateNotNull(Field field, Address obj) {
        if ((field.getAnnotation(NotNull.class) == null)) {
            return true;
        }
        try {
            if (field.get(obj) == null) {
                return false;
            }
        } catch (IllegalAccessException ex) {
            System.out.println(ex.getMessage());
            return true;
        }
        return true;
    }

    private static Boolean validateMinLength(Field field, Address obj) {
        if (field.getAnnotation(MinLength.class) == null) {
            return true;
        }
        try {
            if (((String)field.get(obj)).length() < getMinLengthAnnotationValue(field, obj)) {
                return false;
            }
        } catch (IllegalAccessException ex) {
            System.out.println(ex.getMessage());
            return true;
        }
        return true;
    }

    private static Integer getMinLengthAnnotationValue(Field field, Address obj) {
        return field.getAnnotation(MinLength.class).minLength();
    }
}
// END
