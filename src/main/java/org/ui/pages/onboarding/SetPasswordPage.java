package org.ui.pages.onboarding;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.ui.pages.Constatns.DFLT_PSWD;
import static org.ui.pages.utils.JsonReader.getValue;

public class SetPasswordPage extends OnboardingBasePage {

    public static final String PWD_8_CHARACTERS = getValue("pwd8Characters");
    public static final String ONE_UPPER_CASE = getValue("pwdOneUpperCase");
    public static final String ONE_SYMBOL = getValue("pwdOneSymbol");
    public static final String ONE_DIGIT = getValue("pwdOneDigit");
    public static final String PWD_VALIDATION = getValue("pwdValidation");

    public SelenideElement pswdField() {
        return $("[data-testid=password]");
    }

    public SelenideElement confirmPswdField() {
        return $("[data-testid=confirm-password]");
    }

    public SelenideElement pswdValidationMsg() {
        return $("[data-testid=password-validation-text]");
    }

    public SelenideElement hidePwdBtn() {
        return $x(".//div[input[@data-testid='password']]//div[contains(@class,'')]");
    }

    public SelenideElement confirmPswHidePwdBtn() {
        return $x(".//div[input[@data-testid='confirm-password']]//div[contains(@class,'')]");
    }

    public SelenideElement pwdWarning(String warning) {
        return $x(".//div[p[text()='" + warning + "']]//*[@d]");
    }

    public SelenideElement termsCheckbox() {
        return $("[data-testid=terms-checkbox] span");
    }

    public void fillPasswordsFields(String pswd) {
        pswdField().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        pswdField().setValue(pswd);

        confirmPswdField().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        confirmPswdField().setValue(pswd);
    }

    public void setUpPassword(String pswd) {
        pswdField().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        pswdField().setValue(pswd);

        confirmPswdField().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        confirmPswdField().setValue(pswd);

        termsCheckbox().click();
    }
}
