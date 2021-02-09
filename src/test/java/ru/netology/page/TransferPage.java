package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement transferHead = $("h1.heading");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage makeTransfer(DataHelper.CardInfo card, String amount) {
        amountInput.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        amountInput.setValue(amount);
        fromInput.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        fromInput.setValue(card.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void getErrorNotification() {
        errorNotification.shouldBe(visible);
    }
}
