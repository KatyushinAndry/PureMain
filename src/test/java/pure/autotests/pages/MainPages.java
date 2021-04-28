package pure.autotests.pages;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPages {

    public MainPages opensHeader(){
        $(".announcements__read-more").scrollTo();
        $("#navbar").shouldBe(visible);
        return this;
    }

}
