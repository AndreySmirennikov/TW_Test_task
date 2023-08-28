package org.ui.pages.onboarding;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.ui.pages.utils.JsonReader.getValue;

public class SuccessfulPage extends OnboardingBasePage {

    public static final String SUCCESSFUL_PHRASE = getValue("successCreatedWalletMsg");
    public static final String SUCCESSFUL_PHRASE_WARNING = getValue("successCreatedWalletWarning");

    public SelenideElement successfullMsg() {
        return $("h1[class]");
    }
    public SelenideElement successfullPhrase() {
        return $("h1[class]");
    }

    public SelenideElement successfullWarning() {
        return $("h2[class]");
    }
    public SelenideElement openWallet() {
        return $("[data-testid=open-wallet-button]");
    }
}
