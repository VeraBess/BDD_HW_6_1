package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import ru.netology.bdd.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@DisplayName("Страница 4. Перевод баланса между картами")

public class TransferPage {
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) { // метод валидный перевод средств, возвращает следующую страницу открывающуюся после успешного перевода
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) { //метод заполения двух поля ввода суммы перевода и номера карты списания, возможны негативные сценарии
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCartNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) { //метод проверяющий сообщение об ошибке
        errorMessage.should(Condition.and("Ошибка!", Condition.text(expectedText), Condition.visible), Duration.ofSeconds(15));
    }
}