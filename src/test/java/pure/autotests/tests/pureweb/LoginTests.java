package pure.autotests.tests.pureweb;

import pure.autotests.tests.TestBase;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Story("Login tests")
public class LoginTests extends TestBase {

    @BeforeAll
    static void configureBaseUrl() {
        RestAssured.baseURI = TestData.getApiUrl();
        Configuration.baseUrl = TestData.getWebUrl();
    }

    @Test
    @Tag("ui")
    @DisplayName("Checking open of the main page")
    void loginTest() {
        step("Open page", () -> {
            open("");
            $(".page-title").shouldHave(text("Welcome, Please Sign In!"));
        });

        step("Fill in login form", () -> {
            $("#Email").val(TestData.getUserLogin());
            $("#Password").val(TestData.getUserPassword())
                    .pressEnter();
        });

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(TestData.getUserLogin())));
    }

//    @Test
//    @Tag("api")
//    @Disabled("Example test code for further test development")
//    @DisplayName("Successful authorization with set cookie, received by API")
//    void loginWithCookieTest() {
//        step("Get cookie by api and set it to browser", () -> {
//            String authorizationCookie =
//                    given()
//                            .filter(filters().withCustomTemplates())
//                            .log().uri()
//                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                            .formParam("Email", TestData.getUserLogin())
//                            .formParam("Password", TestData.getUserPassword())
//                    .when()
//                            .post("/login")
//                    .then()
//                            .statusCode(302)
//                            .log().body()
//                            .extract()
//                            .cookie("NOPCOMMERCE.AUTH");
//
//            step("Open minimal content, because cookie can be set with site opened", () ->
//                    open("/Themes/DefaultClean/Content/images/logo.png"));
//
//            getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", authorizationCookie));
//        });
//
//        step("Open main page", () -> {
//            open("");
//            $(".topic-html-content-header").shouldHave(text("Welcome to our store"));
//        });
//
//        step("Verify successful authorization", () ->
//                $(".account").shouldHave(text(TestData.getUserLogin())));
//    }
}
