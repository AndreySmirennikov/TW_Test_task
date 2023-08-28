package org.ui.pages.onboarding;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class OnboardingBasePage {
    public SelenideElement backBtn() {
        return $("[data-testid=back-button]");
    }

    public SelenideElement nextBtn() {
        return $("[data-testid=next-button]");
    }
}
