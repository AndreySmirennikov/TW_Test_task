package org.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("TW08")
public class TwUiPositiveLocalizationTest extends TwUiPositiveTest {

    //NOTE:
    //This is just an example(but it is working, and tests are running and checking spanish localization) of how you can manage tests through variables,
    // Ideally i would set the value of this variable at the CI level
    @BeforeEach
    @Override protected void setUp(TestInfo testInfo) {
        LOCAL="es";
        super.setUp(testInfo);
    }
}
