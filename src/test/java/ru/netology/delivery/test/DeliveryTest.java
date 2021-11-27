
package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataConstructor;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataConstructor.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var daysToAddForSecondMeeting = 7;
        // Указываем город
        $("[data-test-id=city] .input__control").setValue(validUser.getCity());
        // Очищаем дату встречи
        $("[data-test-id=date] .input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.chord(Keys.BACK_SPACE));
        // Вводим первую дату
        $("[data-test-id=date] .input__control[placeholder='Дата встречи']").setValue(DataConstructor.generateDate(daysToAddForFirstMeeting));
        // Вводим Имя и Фамилию
        $("[data-test-id=name] .input__control[name='name']").setValue(validUser.getName());
        // Номер телефона
        $("[data-test-id=phone] .input__control[name='phone']").setValue(validUser.getPhone());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(byText("Запланировать")).click();
        // Проверяем
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(10));
        $("[data-test-id=date] .input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.chord(Keys.BACK_SPACE));
        // Вводим вторую дату
        $("[data-test-id=date] .input__control[placeholder='Дата встречи']").setValue(DataConstructor.generateDate(daysToAddForSecondMeeting));
        $(byText("Запланировать")).click();
        $(byText("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(10));
        $("[data-test-id='success-notification'][data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text(DataConstructor.generateDate(daysToAddForSecondMeeting)));
    }
}
