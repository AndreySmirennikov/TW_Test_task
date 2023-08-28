package org.ui.pages.onboarding;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.ui.pages.utils.JsonReader.getValue;

public class BackUpSecretPhrasePage extends OnboardingBasePage {

    public static final String BACKUP_PHRASE_WARNING = getValue("backUpPhraseWarning");
    public static final String SHOW_BTN_TEXT = getValue("showBtnText");

    public SelenideElement backUpPhraseWarning() {
        return $x(".//div[*[contains(@class,'icon')]]//p");
    }

    public SelenideElement copePhrase() {
        return $("[data-testid=copy-seed-phrase]");
    }

    public void proceedScreenWithCopySecretPhrase() {
        nextBtn().click();
        copePhrase().click();
        nextBtn().click();
    }
}
