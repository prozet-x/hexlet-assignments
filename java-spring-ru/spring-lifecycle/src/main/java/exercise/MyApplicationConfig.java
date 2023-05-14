package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;

// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationConfig {
    @Bean
    public Daytime getDayTime() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int min = now.getMinute();
        int minsFromMidnight = hour * 60 + min;
        if (minsFromMidnight >= 360 && minsFromMidnight < 720) {
            return new Morning();
        } else if (minsFromMidnight >= 720 && minsFromMidnight < 1080) {
            return new Day();
        } else if (minsFromMidnight >= 1080 && minsFromMidnight < 1380) {
            return new Evening();
        }
        return new Night();
    }
}
// END
