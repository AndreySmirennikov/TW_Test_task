package org.ui;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.ui.blocks.TipSection;
import org.ui.pages.MainPage;
import org.ui.pages.WelcomePage;
import org.junit.jupiter.api.Tag;
import org.ui.pages.onboarding.BackUpSecretPhrasePage;
import org.ui.pages.onboarding.ConfirmSecretPhrasePage;
import org.ui.pages.onboarding.ImproveTWPage;
import org.ui.pages.onboarding.SetPasswordPage;
import org.ui.pages.onboarding.SuccessfulPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static org.ui.pages.Constatns.DFLT_PSWD;
import static org.ui.pages.Constatns.GREEN_COLOR;
import static org.ui.pages.MainPage.FIRST_ENTRANCE_TIP_HEADLINE_1;
import static org.ui.pages.MainPage.FIRST_ENTRANCE_TIP_HEADLINE_2;
import static org.ui.pages.MainPage.FIRST_ENTRANCE_TIP_TEXT_1;
import static org.ui.pages.MainPage.FIRST_ENTRANCE_TIP_TEXT_2;
import static org.ui.pages.MainPage.ZERO_BALANCE;
import static org.ui.pages.onboarding.BackUpSecretPhrasePage.BACKUP_PHRASE_WARNING;
import static org.ui.pages.onboarding.BackUpSecretPhrasePage.SHOW_BTN_TEXT;
import static org.ui.pages.onboarding.SetPasswordPage.ONE_DIGIT;
import static org.ui.pages.onboarding.SetPasswordPage.ONE_SYMBOL;
import static org.ui.pages.onboarding.SetPasswordPage.ONE_UPPER_CASE;
import static org.ui.pages.onboarding.SetPasswordPage.PWD_8_CHARACTERS;
import static org.ui.pages.onboarding.SuccessfulPage.SUCCESSFUL_PHRASE;
import static org.ui.pages.onboarding.SuccessfulPage.SUCCESSFUL_PHRASE_WARNING;

public class TwUiPositiveTest extends AbstractSeleniumTest {

    @ParameterizedTest
    @Tags({@Tag("TW01"), @Tag("TW02")})
    @ValueSource(booleans = {true, false})
    void createWalletTest(boolean shareUserData) throws IOException, UnsupportedFlavorException {
        log.info("[Step] 1. Open the  Welcome screen, click on the 'Create a new wallet' button.");
        WelcomePage welcome = new WelcomePage();
        welcome.createNewWallet().click();

        log.info("[Step] 2. Set and confirm Password screen");
        log.info("[Step] 2.1. Enter the password in the 'Create new password' field.Check “Show password” btn");
        SetPasswordPage pwd = new SetPasswordPage();
        pwd.setUpPassword(DFLT_PSWD);
        pwd.pswdField().shouldHave(attribute("type","password"));
        pwd.hidePwdBtn().click();
        pwd.pswdField().shouldHave(attribute("type","text"));
        log.info("[Step] 2.2. Enter the password in the 'Confirm new password' field.Check “Show password” btn");
        pwd.confirmPswdField().shouldHave(attribute("type","password"));
        pwd.confirmPswHidePwdBtn().click();
        pwd.pswdField().shouldHave(attribute("type","text"));
        log.info("[Step] 2.3. Check if all password criteria are met (green checkmarks)");
        pwd.pwdWarning(PWD_8_CHARACTERS).shouldHave(cssValue("color",GREEN_COLOR));
        pwd.pwdWarning(ONE_SYMBOL).shouldHave(cssValue("color",GREEN_COLOR));
        pwd.pwdWarning(ONE_DIGIT).shouldHave(cssValue("color",GREEN_COLOR));
        pwd.pwdWarning(ONE_UPPER_CASE).shouldHave(cssValue("color",GREEN_COLOR));
        pwd.nextBtn().click();

        log.info("[Step] 3. Backup your secret phrase");
        BackUpSecretPhrasePage secretPhrasePage = new BackUpSecretPhrasePage();
        secretPhrasePage.backUpPhraseWarning().shouldHave(text(BACKUP_PHRASE_WARNING));
        secretPhrasePage.proceedScreenWithCopySecretPhrase();

        log.info("[Step] 4. Confirm Your Secret Phrase");
        ConfirmSecretPhrasePage confirmSecretPhrasePage =new ConfirmSecretPhrasePage();
        confirmSecretPhrasePage.fillSecretPhrase();
        confirmSecretPhrasePage.nextBtn().click();

        log.info("[Step] 5. Improve Trust Wallet");
        ImproveTWPage improveTWPage = new ImproveTWPage();
        improveTWPage.privatePolicy().shouldBe(visible, DFLT_AWAIT_TIMEOUT);
        if (shareUserData) {
            log.info("[Step] 5.1. Continue with sharing user data");
            improveTWPage.nextBtn().click();
        }
        else {
            log.info("[Step] 5.1. Continue without sharing user data");
            improveTWPage.backBtn().click();
        }

        log.info("[Step] 6. Check Successful page");
        SuccessfulPage successfulPage = new SuccessfulPage();
        successfulPage.successfullMsg().shouldHave(text(SUCCESSFUL_PHRASE));
        successfulPage.successfullWarning().shouldHave(text(SUCCESSFUL_PHRASE_WARNING));
        successfulPage.openWallet().click();

        log.info("[Step] 7. Check tips and main page");
        MainPage mainPage = new MainPage();
        TipSection tip = mainPage.welcomeTip();
        tip.tipHeadLine().shouldHave(text(FIRST_ENTRANCE_TIP_HEADLINE_1));
        tip.tipBoxText().shouldHave(text(FIRST_ENTRANCE_TIP_TEXT_1));
        tip.nextBtn().click();
        tip.tipHeadLine().shouldHave(text(FIRST_ENTRANCE_TIP_HEADLINE_2));
        tip.tipText().shouldHave(text(FIRST_ENTRANCE_TIP_TEXT_2));
        tip.nextBtn().click();
        mainPage.balance().shouldHave(text(ZERO_BALANCE));
    }


    @Test
    @Tag("TW07")
    void navigationTest() throws IOException, UnsupportedFlavorException {
        log.info("[Step] 1. Open the  Welcome screen, click on the 'Create a new wallet' button.");
        WelcomePage welcome = new WelcomePage();
        welcome.createNewWallet().click();

        log.info("[Step] 2. Set and confirm Password screen");
        log.info("[Step] 2.1. Enter the password in the 'Create new password' field.Check “Show password” btn");
        SetPasswordPage pwd = new SetPasswordPage();
        pwd.setUpPassword(DFLT_PSWD);
        log.info("[Step] 2.2. Return back to Welcome screen and click on the 'Create a new wallet' button.");
        pwd.backBtn().click();
        welcome.createNewWallet().click();
        log.info("[Step] 2.2. Check all fields and checkbox are empty");
        pwd.termsCheckbox().shouldNotHave(attribute("data-checked"));
        pwd.pswdField().shouldHave(value(""));
        pwd.confirmPswdField().shouldHave(value(""));

        pwd.setUpPassword(DFLT_PSWD);
        pwd.nextBtn().click();

        log.info("[Step] 3. Backup your secret phrase");
        BackUpSecretPhrasePage secretPhrasePage = new BackUpSecretPhrasePage();
        log.info("[Step] 3.1. Return back to Welcome screen and click on the 'Create a new wallet' button.");
        secretPhrasePage.backBtn().click();
        welcome.createNewWallet().click();
        log.info("[Step] 3.2. Check that we returned to Back Your Secret Phrase screen.");
        secretPhrasePage.backUpPhraseWarning().shouldHave(text(BACKUP_PHRASE_WARNING));
        secretPhrasePage.nextBtn().click();
        log.info("[Step] 3.3. Return back to Welcome screen and click on the 'Create a new wallet' button.");
        secretPhrasePage.backBtn().click();
        welcome.createNewWallet().click();
        log.info("[Step] 3.2. Check that we returned to \"Back Your Secret Phrase\" screen. Show button is visible and clickable");
        secretPhrasePage.backUpPhraseWarning().shouldHave(text(BACKUP_PHRASE_WARNING));
        secretPhrasePage.nextBtn().shouldHave(text(SHOW_BTN_TEXT));
        secretPhrasePage.proceedScreenWithCopySecretPhrase();

        log.info("[Step] 4. Confirm Your Secret Phrase");
        ConfirmSecretPhrasePage confirmSecretPhrasePage =new ConfirmSecretPhrasePage();
        log.info("[Step] 4.1. Return to Back Your Secret Phrase screen. Show button is visible and clickable");
        confirmSecretPhrasePage.backBtn().click();
        secretPhrasePage.nextBtn().shouldHave(text(SHOW_BTN_TEXT));
        secretPhrasePage.proceedScreenWithCopySecretPhrase();
        confirmSecretPhrasePage.fillSecretPhrase();
        confirmSecretPhrasePage.nextBtn().click();

        log.info("[Step] 5. Improve Trust Wallet");
        ImproveTWPage improveTWPage = new ImproveTWPage();
        improveTWPage.privatePolicy().shouldBe(visible, DFLT_AWAIT_TIMEOUT);
        improveTWPage.nextBtn().click();

        log.info("[Step] 6. Check Successful page");
        SuccessfulPage successfulPage = new SuccessfulPage();
        successfulPage.successfullMsg().shouldHave(text(SUCCESSFUL_PHRASE));
        successfulPage.successfullWarning().shouldHave(text(SUCCESSFUL_PHRASE_WARNING));
        successfulPage.openWallet().shouldBe(visible);
    }
}