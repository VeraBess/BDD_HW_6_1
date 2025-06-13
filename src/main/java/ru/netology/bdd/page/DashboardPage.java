package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import ru.netology.bdd.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Страница 3. Список карт + баланс + пополнить")

public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("h2");
    private final ElementsCollection cards = $$(".list__item div");
    private final SelenideElement reloadButton = $("[data-test-id='action-reload']");

    public DashboardPage() {
        heading.should(Condition.visible, Duration.ofSeconds(15));
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) { //метод для получения баланса карты
        var text = getCard(cardInfo).getText();
        return extractBalance(text);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) { //выбор карты для пополнения
        getCard(cardInfo).$("button").click();
        return new TransferPage();
    }

    private SelenideElement getCard(DataHelper.CardInfo cardInfo) { //метод поиска карты в коллекции по тестовой метке
        return cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId()));
    }

    public void reloadDashboardPage() { //метод для обновления страницы
        reloadButton.click();
        heading.shouldBe(visible);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void checkCardBalance(DataHelper.CardInfo cardInfo, int expectedBalance) { //метод проверяет баланс выбранной карты
        getCard(cardInfo).should(visible).should(text(balanceStart + expectedBalance + balanceFinish));
    }
}