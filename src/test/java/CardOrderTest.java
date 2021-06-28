import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    public void cardOrderSuccessPath() {
        open("http://localhost:9999");
        $("[data-test-id='name'] .input__control")
                .setValue("Иванова Александра");
        $("[data-test-id='phone'] .input__control")
                .setValue("+79378212612");

        $("[data-test-id='agreement'] span")
                .click();
        $("button")
                .click();
        $(byText("Заявка на дебетовую карту")).shouldBe(Condition.visible);
        $(byText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.")).shouldBe(Condition.visible);
    }

    @Test
    public void cardOrderWithoutFio() {
        open("http://localhost:9999");
        $("[data-test-id='phone'] .input__control")
                .setValue("+79378212612");
        $("[data-test-id='agreement'] span")
                .click();
        $("button")
                .click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    public void cardOrderWithoutPhone() {
        open("http://localhost:9999");
        $("[data-test-id='name'] .input__control")
                .setValue("Иванова Александра");
        $("[data-test-id='agreement'] span")
                .click();
        $("button")
                .click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    public void cardOrderWithoutAgreement() {
        open("http://localhost:9999");
        $("[data-test-id='name'] .input__control")
                .setValue("Иванова Александра");
        $("[data-test-id='phone'] .input__control")
                .setValue("+79378212612");
        $("button")
                .click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    public void cardOrderWithoutInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id='name'] .input__control")
                .setValue("Ivan");
        $("[data-test-id='phone'] .input__control")
                .setValue("+79378212612");
        $("[data-test-id='agreement'] span")
                .click();
        $("button")
                .click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void cardOrderWithoutInvalidPhone() {
        open("http://localhost:9999");
        $("[data-test-id='name'] .input__control")
                .setValue("Иванов Петя");
        $("[data-test-id='phone'] .input__control")
                .setValue("9378212612");
        $("[data-test-id='agreement'] span")
                .click();
        $("button")
                .click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }




}
