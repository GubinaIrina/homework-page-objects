package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTransferTest {
    @BeforeEach
    void openWebAndLogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        val dashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        val transferPage = new TransferPage();
        val firstCard = DataHelper.getFirstCard();
        transferPage.secondCard(firstCard, "5000");
        val firstCardBalanceCurrent = dashboardPage.getFirstCardBalance();
        val secondCardBalanceCurrent = dashboardPage.getSecondCardBalance();
        val firstCardBalanceExpected = firstCardBalanceStart - 5000;
        val secondCardBalanceExpected = secondCardBalanceStart + 5000;

        assertEquals(firstCardBalanceExpected, firstCardBalanceCurrent);
        assertEquals(secondCardBalanceExpected, secondCardBalanceCurrent);

        val secondCard = DataHelper.getSecondCard();
        transferPage.firstCard(secondCard, "5000");
    }

    @Test
    void shouldNotTransferMoneyBetweenOwnCards() {
        val transferPage = new TransferPage();
        val invalidCard = DataHelper.getInvalidCard();
        transferPage.invalidCard(invalidCard, "5000");
    }
}
