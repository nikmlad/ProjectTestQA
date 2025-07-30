package api;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.*;

@Epic("Booking API")
@Feature("Получение информации о бронировании")
public class FirstApiTest {

    @Test
    @Story("Получение бронирования по ID")
    @Description("Тест проверяет получение информации о конкретном бронировании")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET /booking/{id} - успешное получение бронирования")
    public void getBookingByIdTest() {
        step("Отправка GET запроса к /booking/1", () -> {
            RestAssured
                    .given()
                    .filter(new AllureRestAssured()) // Добавляем фильтр для Allure
                    .when()
                    .get("https://restful-booker.herokuapp.com/booking/1")
                    .then()
                    .statusCode(200)
                    .body("firstname", notNullValue())
                    .body("lastname", notNullValue())
                    .body("totalprice", greaterThan(0));
        });
    }
}
