package org.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WelcomePage{

    public SelenideElement createNewWallet() {
        return $("[data-testid=create-new-wallet]");
    }
}