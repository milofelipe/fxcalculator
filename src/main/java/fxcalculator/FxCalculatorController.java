package fxcalculator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

@RestController
public class FxCalculatorController {
    private static final NumberFormat numberFormat = NumberFormat.getInstance();

    @RequestMapping("/convert")
    public String convert(@RequestParam(value = "amount") String amount,
                          @RequestParam(value = "fromCurrency") String fromCurrency,
                          @RequestParam(value = "toCurrency") String toCurrency) {
        String errorMessage = validateInputParameters(amount, fromCurrency, toCurrency);
        if (Objects.nonNull(errorMessage)) {
            return errorMessage;
        }

        Money fromMoney = new Money(Currency.valueOf(fromCurrency), new BigDecimal(amount));
        Money convertedMoney = FxCalculator.convert(fromMoney, Currency.valueOf(toCurrency));

        return Objects.requireNonNull(convertedMoney).getCurrency().toString() + " " + numberFormat.format(convertedMoney.getRoundedAmount().doubleValue());
    }

    private String validateInputParameters(String amount, String fromCurrency, String toCurrency) {
        try {
            new BigDecimal(amount);
        } catch (NumberFormatException e) {
            return "Invalid amount value.";
        }

        try {
            Currency.valueOf(fromCurrency);
            Currency.valueOf(toCurrency);
        } catch (IllegalArgumentException e) {
            return "Unable to find rate for " + fromCurrency + "/" + toCurrency;
        }

        return null;
    }
}