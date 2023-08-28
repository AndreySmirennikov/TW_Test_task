package org.ui.blocks;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TipSection {
    public SelenideElement nextBtn() {
        return $("[data-testid=next-button]");
    }

    public SelenideElement tipBoxText() {
        return $("[data-testid=first-time-education-box]");
    }

    public SelenideElement tipText() {
        return $("section div[id] div p");
    }

    public SelenideElement tipHeadLine() {
        return $("section div p:nth-last-child(2)");
    }
}
