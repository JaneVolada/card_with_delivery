package ru.netology.card;

import com.codeborne.selenide.Condition;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class cardWithDeliveryTest {
    String meetingDay (int day) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

@Test
    void bookCard () {
    open("http://localhost:9999");

    $("[data-test-id='city'] input").setValue("Калуга");
    $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
    $("[data-test-id='date'] input").setValue(meetingDay(5));
    $("[data-test-id='name'] input").setValue("Петр Петров");
    $("[data-test-id='phone'] input").setValue("+79639633636");
    $("[data-test-id='agreement']").click();
    $("button.button").click();
    $(".notification__content")
            .shouldBe(Condition.visible, Duration.ofSeconds(15))
            .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDay(5)));

}



}
