package org.ui.pages.onboarding;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ImproveTWPage extends OnboardingBasePage {
    public SelenideElement privatePolicy() {
        return $("[data-test=privacy-policy-link]");
    }
}
