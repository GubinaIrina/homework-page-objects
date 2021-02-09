package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void openWebAndLogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        val dashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        var transferPage = dashboardPage.chooseSecondCard();
        val firstCard = DataHelper.getFirstCard();
        transferPage.makeTransfer(firstCard, "5000");
        val firstCardBalanceCurrent = dashboardPage.getFirstCardBalance();
        val secondCardBalanceCurrent = dashboardPage.getSecondCardBalance();
        val firstCardBalanceExpected = firstCardBalanceStart - 5000;
        val secondCardBalanceExpected = secondCardBalanceStart + 5000;
        assertEquals(firstCardBalanceExpected, firstCardBalanceCurrent);
        assertEquals(secondCardBalanceExpected, secondCardBalanceCurrent);
        transferPage = dashboardPage.chooseFirstCard();
        val secondCard = DataHelper.getSecondCard();
        transferPage.makeTransfer(secondCard, "5000");
    }

    @Test
    void shouldNotTransferMoneyBetweenOwnCards() {
        val dashboardPage = new DashboardPage();
        val invalidCard = DataHelper.getInvalidCard();
        val transferPage = dashboardPage.chooseSecondCard();
        transferPage.makeTransfer(invalidCard, "5000");
        transferPage.getErrorNotification();
    }
}
