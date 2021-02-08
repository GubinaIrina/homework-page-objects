package ru.netology.page;

import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    public DashboardPage firstCard(DataHelper.SecondCard card, String amount) {
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button").click();
        $("[data-test-id='amount'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='amount'] input").setValue(amount);
        $("[data-test-id='from'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='from'] input").setValue(card.getNumber());
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }

    public DashboardPage secondCard(DataHelper.FirstCard card, String amount) {
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button").click();
        $("[data-test-id='amount'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='amount'] input").setValue(amount);
        $("[data-test-id='from'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='from'] input").setValue(card.getNumber());
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }

    public DashboardPage invalidCard(DataHelper.InvalidCard card, String amount) {
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button").click();
        $("[data-test-id='amount'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='amount'] input").setValue(amount);
        $("[data-test-id='from'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='from'] input").setValue(card.getNumber());
        $("[data-test-id='action-transfer']").click();
        $("[data-test-id='error-notification']").shouldBe(visible);
        return new DashboardPage();
    }
}
