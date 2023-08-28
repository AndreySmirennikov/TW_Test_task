package org.ui.pages.onboarding;

import java.awt.Toolkit;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.awt.datatransfer.DataFlavor.stringFlavor;
import static org.ui.pages.utils.JsonReader.getValue;

public class ConfirmSecretPhrasePage extends OnboardingBasePage {
    public static final String INCORRECT_PHRASE_WARNING = getValue("incorrectPhrase");

    public SelenideElement phraseWord(String value) {
        return $("input[value=" + value + "]");
    }

    public SelenideElement phraseIncorrectMsg() {
        return $x("//div[h1]//p");
    }

    public void fillSecretPhrase() throws IOException, UnsupportedFlavorException {
        fillSecretPhrase(false);
    }

    public void fillSecretPhrase(boolean reversePhrase) throws IOException, UnsupportedFlavorException {
        String phrase = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(stringFlavor);
        String[] words = phrase.split(" ");
        if (reversePhrase)
            Collections.reverse(Arrays.asList(words));
        for (String word : words) {
            phraseWord(word).click();
        }
    }
}
