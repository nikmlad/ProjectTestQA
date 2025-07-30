package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTest {

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        // Configuration.headless = true; // Раскомментируйте для headless-режима
    }

    @BeforeEach
    void openBrowser() {
        open("/text-box");
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Заполнение формы Text Box")
    void fillTextBoxForm() {
        // Заполняем форму
        $("#userName").setValue("Алексей Петров");
        $("#userEmail").setValue("alex@example.com");
        $("#currentAddress").setValue("Москва, ул. Примерная, 10");
        $("#permanentAddress").setValue("Санкт-Петербург, ул. Тестовая, 20");

        // Прокручиваем к кнопке, чтобы она стала кликабельной
        $("#submit").scrollTo().click();

        // Проверяем результаты
        $("#output").shouldBe(visible);
        $("#name").shouldHave(text("Алексей Петров"));
        $("#email").shouldHave(text("alex@example.com"));
        $("#currentAddress", 1).shouldHave(text("Москва, ул. Примерная, 10"));
        $("#permanentAddress", 1).shouldHave(text("Санкт-Петербург, ул. Тестовая, 20"));
    }
}