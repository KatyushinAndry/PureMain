package pure.autotests.tests.pureweb;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pure.autotests.pages.MainPages;
import pure.autotests.tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static pure.autotests.helpers.DriverHelper.getConsoleLogs;


public class MainPageTests extends TestBase {
    MainPages mainPages = new MainPages();

    @BeforeAll
    static void configureBaseUrl(){
        RestAssured.baseURI = TestData.getApiUrl();
        Configuration.baseUrl = TestData.getWebUrl();
    }

    @Test
    @Tag("functional")
    @Tag("smoke")
     @DisplayName("Console log should not have any errors")
    void consoleLogShouldNotHaveErrors(){
        step("Open main page", () -> open(""));

        step("Page should not have errors (SEVERE) in console", () -> {
            String consoleLogs = getConsoleLogs();
            assertThat(consoleLogs, not(containsString("SEVERE")));
        });
    }


    @Test
    @Tag("ui")
    @Tag("smoke")
    @DisplayName("Checking the main page of 'pure.app'")
    void openMainPageTest(){

        step("Open page", () -> {
            open("");
        });

        step("Checking that the main page is opened correctly", () -> {
            $(".intro-title").shouldHave(text("SHAMELESS HOOKUP DATING"));
        });
    }


    @Test
    @Tag("functional")
    @Tag("smoke")
    @DisplayName("Checking Google authorization page")
    void googleLoginPageTest(){

        step("Open page", () -> {
            open("");
        });

        step("Opening google authorization page", () -> {
            $(".android.login-button").click();
        });

        step("Google authorization page check", () -> {
            $(".Y4dIwd").shouldHave(text("pure.app"));
        });
    }

    @Test
    @Tag("functional")
    @Tag("smoke")
    @DisplayName("Checking Apple authorization page")
    void appleLoginPageTest(){

        step("Open page", () -> {
            open("");
        });

        step("Opening Apple authorization page", () -> {
            $(".ios.login-button").click();
        });

        step("Apple authorization page check", () -> {
            switchTo().window(1);
            $(".si-container-title").shouldHave(text("Pure"));
        });
    }

    @Test
    @Tag("functional")
    @Tag("smoke")
    @DisplayName("Checking authorization via header → Apple")
    void headerAppleLoginTest(){

        step("Open page", () -> {
            open("");
        });

        step("Checking that the header will appear", () -> {
            mainPages.opensHeader();
        });

        step("Opening Apple authorization page", () -> {

            $(".navbar-buttons__download.ios").click();
        });

        step("Apple authorization page check", () -> {
            switchTo().window(1);
            $(".si-container-title").shouldHave(text("Pure"));
        });
    }


    @Test
    @Tag("functional")
    @Tag("smoke")
    @DisplayName("Checking authorization via header → Google")
    void headerGoogleLoginTest(){

        step("Open page", () -> {
            open("");
        });

        step("Checking that the header will appear", () -> {
            mainPages.opensHeader();
        });

        step("Opening Google authorization page", () -> {

            $(".navbar-buttons__download.android").click();
        });

        step("Google authorization page check", () -> {
            $(".Y4dIwd").shouldHave(text("pure.app"));
        });
    }


    @Test
    @Tag("functional")
    @Tag("smoke")
    @DisplayName("Checking ads button")
    void adsPostButtonTest(){

        step("Open page", () -> {
            open("");
        });

        step("Click Post button", () -> {
            $(".container > #announcement-form .button-pure").click();
        });

        step("Checking that the onboarding opens", () -> {
            $("#root").shouldHave(text("PURE"));
        });
    }


    @Test
    @Tag("functional")
    @Tag("smoke")
    @DisplayName("Checking ads button")
    void otherAdsButtonTest(){

        step("Open page", () -> {
            open("");
        });

        step("Click 'Check other ads' button", () -> {
            $(byText("Check other ads")).click();
        });

        step("Checking that the onboarding opens", () -> {
            switchTo().window(1);
            $(".sc-fVHxE.eBCLPp").shouldBe(visible);
        });
    }

    @Test
    @Tag("ui")
    @Tag("smoke")
    @DisplayName("Checking FAQ")
    void linkFAQTest(){

        step("Open page", () -> {
            open("");
        });

        step("Click 'FAQ' link", () -> {
            $(byText("FAQ")).click();
        });

        step("Checking that the FAQ' opens", () -> {
            switchTo().window(1);
            $(".wrap").shouldHave(text("About Pure"));
        });
    }

    @Test
    @Tag("ui")
    @Tag("smoke")
    @DisplayName("Checking 'Terms and Conditions'")
    void linkTermsConditionsTest(){

        step("Open page", () -> {
            open("");
        });

        step("Click 'Terms and Conditions' link", () -> {
            $(byText("Terms and Conditions")).click();
        });

        step("Checking that the 'Terms and Conditions' opens", () -> {
            switchTo().window(1);
            $(".terms.load").shouldHave(text("terms  of service"));
        });
    }

    @Test
    @Tag("ui")
    @Tag("smoke")
    @DisplayName("Checking 'About us'")
    void linkAboutUsTest(){

        step("Open page", () -> {
            open("");
        });

        step("Click 'About us' link", () -> {
            $(byText("About us")).click();
        });

        step("Checking that the 'About us' opens", () -> {
            switchTo().window(1);
            $(".Block__Root-sc-1aahnxl-0").shouldHave(text("About us"));
        });
    }

}
