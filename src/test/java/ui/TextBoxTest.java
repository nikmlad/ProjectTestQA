package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Epic("DemoQA Tests")
@Feature("Text Box Form")
public class TextBoxTest {

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

        // Добавляем лисенер для Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
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
    @Story("Пользователь может заполнить и отправить форму Text Box")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет заполнение всех полей формы Text Box и отображение введенных данных после отправки")
    void fillTextBoxForm() {
        // Шаг 1: Заполнение полей формы
        fillFormFields(
                "Алексей Петров",
                "alex@example.com",
                "Москва, ул. Примерная, 10",
                "Санкт-Петербург, ул. Тестовая, 20"
        );

        // Шаг 2: Отправка формы
        submitForm();

        // Шаг 3: Проверка результатов
        verifyOutputData(
                "Алексей Петров",
                "alex@example.com",
                "Москва, ул. Примерная, 10",
                "Санкт-Петербург, ул. Тестовая, 20"
        );
    }

    @Step("Заполнение полей формы: имя '{fullName}', email '{email}', текущий адрес '{currentAddress}', постоянный адрес '{permanentAddress}'")
    private void fillFormFields(String fullName, String email, String currentAddress, String permanentAddress) {
        $("#userName").setValue(fullName);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
    }

    @Step("Отправка формы")
    private void submitForm() {
        $("#submit").scrollTo().click();
    }

    @Step("Проверка отображения введенных данных: имя '{expectedName}', email '{expectedEmail}', текущий адрес '{expectedCurrentAddress}', постоянный адрес '{expectedPermanentAddress}'")
    private void verifyOutputData(String expectedName, String expectedEmail,
                                  String expectedCurrentAddress, String expectedPermanentAddress) {
        $("#output").shouldBe(visible);
        $("#name").shouldHave(text(expectedName));
        $("#email").shouldHave(text(expectedEmail));
        $("#currentAddress", 1).shouldHave(text(expectedCurrentAddress));
        $("#permanentAddress", 1).shouldHave(text(expectedPermanentAddress));
    }
}