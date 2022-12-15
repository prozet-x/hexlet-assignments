package exercise;

import java.util.List;

// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        List<String> freeDomains = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return emails.stream()
                .filter(email -> freeDomains.contains(email.split("@")[1]))
                .count();
    }
}
// END
