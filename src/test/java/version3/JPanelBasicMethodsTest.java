package version3;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.event.ActionEvent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JPanelBasicMethodsTest {

    private static StandardCalculator_v3 c;
    private String number;
    private boolean result;

    @Mock
    ActionEvent ae;

    @BeforeClass
    public static void setup() throws Exception {
        System.setProperty("appName", "JPanelBasicMethodsTest");
        c = new StandardCalculator_v3();
    }

    @Test
    public void switchingFromProgrammerToBasicConvertsTextArea() {
        c.getTextArea().setText("00000100");
        JPanelBasic_v3 p = new JPanelBasic_v3(c);
        p.convertToDecimal();
        assertEquals("Did not convert from Binary to Decimal", "4", c.getTextArea().getText().replaceAll("\n", ""));
    }

    @Test
    public void testingMathPow() {
        double delta = 0.000001d;
        Number num = 8.0;
        assertEquals(num.doubleValue(), Math.pow(2,3), delta);
    }
}
