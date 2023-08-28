package org.ui;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.ui.pages.WelcomePage;
import org.ui.pages.onboarding.BackUpSecretPhrasePage;
import org.ui.pages.onboarding.ConfirmSecretPhrasePage;
import org.ui.pages.onboarding.SetPasswordPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.ui.pages.Constatns.DFLT_PSWD;
import static org.ui.pages.Constatns.DISABLED_ATR;
import static org.ui.pages.Constatns.EMPTY_COLOR;
import static org.ui.pages.Constatns.GREEN_COLOR;
import static org.ui.pages.onboarding.ConfirmSecretPhrasePage.INCORRECT_PHRASE_WARNING;
import static org.ui.pages.onboarding.SetPasswordPage.ONE_DIGIT;
import static org.ui.pages.onboarding.SetPasswordPage.ONE_SYMBOL;
import static org.ui.pages.onboarding.SetPasswordPage.ONE_UPPER_CASE;
import static org.ui.pages.onboarding.SetPasswordPage.PWD_8_CHARACTERS;
import static org.ui.pages.onboarding.SetPasswordPage.PWD_VALIDATION;

public class TwUiNegativeTest extends AbstractSeleniumTest {

    @Test
    @Tags({@Tag("TW03"), @Tag("TW04"), @Tag("TW05") })
    void passwordScreenNegativeTest(){
        log.info("[Step] 1. Create a new wallet");
        WelcomePage welcome = new WelcomePage();
        welcome.createNewWallet().click();

        log.info("[Step] 2. Set and confirm Password screen");
        log.info("[Step] 2.1. Can not proceed with mismatched passwords");
        SetPasswordPage pwd = new SetPasswordPage();
        pwd.termsCheckbox().click();
        pwd.pswdField().setValue(DFLT_PSWD);
        pwd.confirmPswdField().setValue(DFLT_PSWD+"AAA");
        pwd.pswdValidationMsg().shouldBe(visible).shouldHave(text(PWD_VALIDATION));
        pwd.nextBtn().shouldHave(attribute(DISABLED_ATR));

        log.info("[Step] 2.2. Can not proceed with mismatched requirements passwords");
        pwd.fillPasswordsFields("abc");
        pwd.pwdWarning(PWD_8_CHARACTERS).shouldHave(cssValue("color",EMPTY_COLOR));
        pwd.pwdWarning(ONE_SYMBOL).shouldHave(cssValue("color",EMPTY_COLOR));
        pwd.pwdWarning(ONE_DIGIT).shouldHave(cssValue("color",EMPTY_COLOR));
        pwd.pwdWarning(ONE_UPPER_CASE).shouldHave(cssValue("color",EMPTY_COLOR));
        pwd.nextBtn().shouldHave(attribute(DISABLED_ATR));

        pwd.fillPasswordsFields("ABCD1234");
        pwd.pwdWarning(PWD_8_CHARACTERS).shouldHave(cssValue("color",GREEN_COLOR));
        pwd.pwdWarning(ONE_SYMBOL).shouldHave(cssValue("color",EMPTY_COLOR));
        pwd.pwdWarning(ONE_DIGIT).shouldHave(cssValue("color",GREEN_COLOR));
        pwd.pwdWarning(ONE_UPPER_CASE).shouldHave(cssValue("color",GREEN_COLOR));
        pwd.nextBtn().shouldHave(attribute(DISABLED_ATR));

        pwd.fillPasswordsFields("abcd!@");
        pwd.pwdWarning(PWD_8_CHARACTERS).shouldHave(cssValue("color",EMPTY_COLOR));
        pwd.pwdWarning(ONE_SYMBOL).shouldHave(cssValue("color",GREEN_COLOR));
        pwd.pwdWarning(ONE_DIGIT).shouldHave(cssValue("color",EMPTY_COLOR));
        pwd.pwdWarning(ONE_UPPER_CASE).shouldHave(cssValue("color",EMPTY_COLOR));
        pwd.nextBtn().shouldHave(attribute(DISABLED_ATR));

        log.info("[Step] 2.3.  Can not proceed with mismatched requirements passwords");
        pwd.termsCheckbox().click();
        pwd.fillPasswordsFields(DFLT_PSWD);
        pwd.nextBtn().shouldHave(attribute(DISABLED_ATR));
    }

    @Test
    @Tags({@Tag("TW06")})
    void incorrectSecretPhraseTest() throws IOException, UnsupportedFlavorException {
        log.info("[Step] 1. Open the  Welcome screen, click on the 'Create a new wallet' button.");
        WelcomePage welcome = new WelcomePage();
        welcome.createNewWallet().click();

        log.info("[Step] 2. Set and confirm Password screen");
        SetPasswordPage pwd = new SetPasswordPage();
        pwd.setUpPassword(DFLT_PSWD);
        pwd.nextBtn().click();

        log.info("[Step] 3. Backup your secret phrase");
        BackUpSecretPhrasePage secretPhrasePage = new BackUpSecretPhrasePage();
        secretPhrasePage.proceedScreenWithCopySecretPhrase();

        log.info("[Step] 4. Confirm Your Secret Phrase");
        ConfirmSecretPhrasePage confirmSecretPhrasePage =new ConfirmSecretPhrasePage();
        confirmSecretPhrasePage.fillSecretPhrase(true);

        log.info("[Step] 5. Can not proceed with incorrectly secret phrase");
        confirmSecretPhrasePage.phraseIncorrectMsg().shouldBe(visible).shouldHave(text(INCORRECT_PHRASE_WARNING));
        confirmSecretPhrasePage.nextBtn().shouldHave(attribute(DISABLED_ATR));
    }
}
