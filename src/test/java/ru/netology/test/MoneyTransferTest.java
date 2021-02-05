package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPageV1;
import ru.netology.page.LoginPageV2;
import ru.netology.page.LoginPageV3;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    @BeforeEach
    void openWeb() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button").click();
        $("[data-test-id='amount'] input").setValue("5000");
        $("[data-test-id='from'] input").setValue("5559 0000 0000 0002");
        $("[data-test-id='action-transfer']").click();

        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']")
                .shouldHave(exactText("**** **** **** 0001, баланс: 15000 р. Пополнить"));

        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button").click();
        $("[data-test-id='amount'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='amount'] input").setValue("5000");
        $("[data-test-id='from'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='from'] input").setValue("5559 0000 0000 0001");
        $("[data-test-id='action-transfer']").click();

        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']")
                .shouldHave(exactText("**** **** **** 0002, баланс: 10000 р. Пополнить"));
    }

    @Test
    void shouldNotTransferMoneyBetweenOwnCardsV2() {
        val loginPage = new LoginPageV2();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button").click();
        $("[data-test-id='amount'] input").setValue("5000");
        $("[data-test-id='from'] input").setValue("5559 0000 0000 0000");
        $("[data-test-id='action-transfer']").click();

        $("[data-test-id='error-notification']").shouldBe(visible);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV3() {
        val loginPage = open("http://localhost:9999", LoginPageV3.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button").click();
        $("[data-test-id='amount'] input").setValue("10000");
        $("[data-test-id='from'] input").setValue("5559 0000 0000 0001");
        $("[data-test-id='action-transfer']").click();

        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']")
                .shouldHave(exactText("**** **** **** 0002, баланс: 20000 р. Пополнить"));

        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button").click();
        $("[data-test-id='amount'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='amount'] input").setValue("10000");
        $("[data-test-id='from'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='from'] input").setValue("5559 0000 0000 0002");
        $("[data-test-id='action-transfer']").click();

        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']")
                .shouldHave(exactText("**** **** **** 0001, баланс: 10000 р. Пополнить"));
    }
}
