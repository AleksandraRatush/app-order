import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    public void cardOrderSuccessPath(){
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form
                .$("[type=\"text\"]")
                .setValue("Иванова Александра");
        form
                .$("[type=\"tel\"]")
                .setValue("+79872212713");

        Selenide.executeJavaScript("arguments[0].click();", $(".checkbox__control"));
        form.$(".button__text").click();
        SelenideElement root = $("#root");
        root
                .$("h2").shouldHave(Condition.text("Заявка на дебетовую карту"));
        root
                .$(".Success_successBlock__2L3Cw")
                .$("p")
                .shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void cardOrderWithoutFio(){
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form
                .$("[type=\"tel\"]")
                .setValue("+79872212713");

        Selenide.executeJavaScript("arguments[0].click();", $(".checkbox__control"));
        form.$(".button__text").click();
        form.$(".input_invalid").$(".input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
}
