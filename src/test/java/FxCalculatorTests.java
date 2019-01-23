import fxcalculator.Currency;
import fxcalculator.FxCalculator;
import fxcalculator.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class FxCalculatorTests {
    @Test
    public void convertAUDtoUSD() {
        Money aud100 = new Money(Currency.AUD, new BigDecimal(100));
        Money result = FxCalculator.convert(aud100, Currency.USD);
        assertEquals(Currency.USD, result.getCurrency());
        assertEquals("83.71", result.getRoundedAmount().toString());

    }

    @Test
    public void convertAUDtoAUD() {
        Money aud100 = new Money(Currency.AUD, new BigDecimal(100));
        Money result = FxCalculator.convert(aud100, Currency.AUD);
        assertEquals(Currency.AUD, result.getCurrency());
        assertEquals("100.00", result.getRoundedAmount().toString());
    }

    @Test
    public void convertAUDtoDKK() {
        Money aud100 = new Money(Currency.AUD, new BigDecimal(100));
        Money result = FxCalculator.convert(aud100, Currency.DKK);
        assertEquals(Currency.DKK, result.getCurrency());
        assertEquals("505.76", result.getRoundedAmount().toString());
    }

    @Test
    public void convertJPYtoUSD() {
        Money jpy100 = new Money(Currency.JPY, new BigDecimal(100));
        Money result = FxCalculator.convert(jpy100, Currency.USD);
        assertEquals(Currency.USD, result.getCurrency());
        assertEquals("0.83", result.getRoundedAmount().toString());
    }

    @Test
    public void convertJPYtoEUR() {
        Money jpy100 = new Money(Currency.JPY, new BigDecimal(100));
        Money result = FxCalculator.convert(jpy100, Currency.EUR);
        assertEquals(Currency.EUR, result.getCurrency());
        assertEquals("0.68", result.getRoundedAmount().toString());
    }

    @Test
    public void convertAUDtoJPY() {
        Money aud1 = new Money(Currency.AUD, new BigDecimal(1));
        Money result = FxCalculator.convert(aud1, Currency.JPY);
        assertEquals(Currency.JPY, result.getCurrency());
        assertEquals("100", result.getRoundedAmount().toString());
    }

    @Test
    public void convertNOKtoAUD() {
        Money nok100 = new Money(Currency.NOK, new BigDecimal(100));
        Money result = FxCalculator.convert(nok100, Currency.AUD);
        assertEquals(Currency.AUD, result.getCurrency());
        assertEquals("16.98", result.getRoundedAmount().toString());
    }

    @Test
    public void convertGBPtoNOK() {
        Money gbp100 = new Money(Currency.GBP, new BigDecimal(100));
        Money result = FxCalculator.convert(gbp100, Currency.NOK);
        assertEquals(Currency.NOK, result.getCurrency());
        assertEquals("1103.49", result.getRoundedAmount().toString());
    }
}