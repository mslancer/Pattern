package Data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Data
@NoArgsConstructor

public class DataConstructor {

    private static Faker faker = new Faker();



    public static String generateData(int days) {
        String data = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return data;
    }

    public static String generateCity(String locale) {
        String city = faker.options().option("Санкт-Петербург", "Москва", "Уфа", "Казань", "Екатеринбург", "Петрозаводск",
                "Сыктывкар", "Пермь", "Самара", "Нальчик", "Магас", "Магадан", "Краснодар", "Красноярск", "Чебоксары",
                "Грозный", "Саранск", "Майкоп", "Анадырь", "Элиста", "Ижевск", "Якутск", "Владивосток", "Хабаровск", "Чита",
                "Кызыл", "Барнаул", "Калининград", "Волгоград", "Ставрополь", "Севастополь");
        return city;
    }

    public static String generateName(String locale) {
        Faker faker1 = new Faker(new Locale(locale));
        String name = faker1.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        String phone = faker.numerify("+79#########");
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(generateCity(locale),generateName(locale),generatePhone(locale));
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }


}
