package Java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Java 8，Date API
 *     -  Clock 类提供了访问当前日期和时间的方法，Clock 是时区敏感的，可以用来取代 System.currentTimeMillis() 来获取当前的微秒数。
 *     某一个特定的时间点也可以使用 Instant 类来表示，Instant 类也可以用来创建旧版本的java.util.Date 对象。
 *
 *     -  在新API中时区使用 ZoneId 来表示。时区可以很方便的使用静态方法of来获取到。
 *     抽象类ZoneId（在java.time包中）表示一个区域标识符。 它有一个名为getAvailableZoneIds的静态方法，它返回所有区域标识符。
 *
 *     - jdk1.8中新增了 LocalDate 与 LocalDateTime等类来解决日期处理方法，同时引入了一个新的类DateTimeFormatter 来解决
 *     日期格式化问题。可以使用Instant代替 Date，LocalDateTime代替 Calendar，DateTimeFormatter 代替 SimpleDateFormat。
 */
public class DateAPI {
    /**
     * Clock类使用
     */
    static void clockDemo(){
        // 获取系统时区,时区敏感
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println("millis:" + millis);
        // 获取Date
        Instant instant = clock.instant();
        System.out.println(instant);
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);
    }

    /**
     * Timezones(时区)
     */
    static void timeZones(){
        // 输出所有区域标识符
        System.out.println(ZoneId.getAvailableZoneIds());
        // 时区转换
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        System.out.println(zone1.getRules());
    }

    /**
     * LocalTime(本地时间)
     */
    static void localTime(){
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalTime now = LocalTime.now(zoneId);
        System.out.println(now);

        // 构造时间，07:00:00，七点零分零秒
        LocalTime time = LocalTime.of(07,00,00);
        System.out.println(time);
    }

    /**
     * LocalDate(本地时间)
     * LocalDate 表示了一个确切的日期，比如 2014-03-11。该对象值是不可变的,给Date对象加减时，操作返回的总是一个新实例
     */
    static void localDate(){
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期：" + today);
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println("明天的日期：" + tomorrow);
        LocalDate yesterday = today.minusDays(1);
        System.out.println("昨天的日期：" + yesterday);
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        System.out.println("今天是周几：" + dayOfWeek);

        // 构造日期,字符串严格按照yyyy-MM-dd
        LocalDate date = LocalDate.parse("2019-05-14");
        System.out.println(date);


    }

    /**
     * DateTimeFormatter，字符串与时间的转化
     */
    static void dateTimeFormatter(){
        // 字符串转LocalDateTime
        String str1 = "2019-05-14 23:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str1, formatter);
        System.out.println(dateTime);

        // LocalDateTime转字符串
        LocalDateTime dateTime1 = LocalDateTime.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter1.format(dateTime1));
    }

    /**
     * LocalDateTime(本地日期时间)
     */
    static void localDateTime(){
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        System.out.println("dayOfWeek: " + dateTime.getDayOfWeek());
        System.out.println("month:" + dateTime.getMonth());
    }


    public static void main(String[] args){
        timeZones();
        localTime();
        localDate();
        dateTimeFormatter();
    }
}
