package org.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.ui.blocks.TipSection;

import static com.codeborne.selenide.Selenide.$;
import static org.ui.pages.utils.JsonReader.getValue;

public class MainPage{

    public static final String FIRST_ENTRANCE_TIP_HEADLINE_1 = getValue("firstEntranceTipHeadline1");
    public static final String FIRST_ENTRANCE_TIP_TEXT_1 = getValue("firstEntranceTipText1");

    public static final String FIRST_ENTRANCE_TIP_HEADLINE_2 = getValue("firstEntranceTipHeadline2");
    public static final String FIRST_ENTRANCE_TIP_TEXT_2 = getValue("firstEntranceTipText2");
    public static final String ZERO_BALANCE = getValue("zeroBalance");

    public SelenideElement balance() {
        return $("[data-testid=account-balance]");
    }

    public TipSection welcomeTip() {
        return new TipSection();
    }
}
