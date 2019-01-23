package fxcalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FxCalculator {
    /**
     * Converts a given Money to the given Currency
     */
    public static Money convert(Money money, Currency currency) {
        Objects.requireNonNull(money, "Money is a required field.");
        Objects.requireNonNull(money, "Currency is a required field.");

        // First, try a straight conversion. Return converted money if available.
        Optional<Money> optionalMoney = conversion(money, currency);
        if (optionalMoney.isPresent()) {
            return optionalMoney.get();
        }

        // Second, try converting to a cross currency (USD/EUR)
        optionalMoney = conversion(money, getCrossCurrency(money.getCurrency()));

        // If a conversion is found, run the converted money to the main convert method.
        // This will just loop until a conversion is found. It is assumed that every supported Currency
        // has at least one CurrencyPair available (either paired to USD or EUR).
        return optionalMoney.map(money1 -> convert(money1, currency)).orElse(null);
    }

    /**
     * Returns the cross conversion currency of the given currency. This returns either USD or EUR.
     */
    private static Currency getCrossCurrency(Currency currency) {
        if (Currency.USD.equals(currency)) {
            return Currency.EUR;
        } else if (Currency.EUR.equals(currency)) {
            return Currency.USD;
        }

        List<CurrencyPair> currencyPairs = CurrencyPair.findByCurrency(currency);

        for (CurrencyPair currencyPair : currencyPairs) {
            if (currencyPair.getBase().equals(Currency.USD) || currencyPair.getBase().equals(Currency.EUR)) {
                return currencyPair.getBase();
            } else if (currencyPair.getTerms().equals(Currency.USD) || currencyPair.getTerms().equals(Currency.EUR)) {
                return currencyPair.getTerms();
            }
        }

        return Currency.USD;
    }

    private static Optional<Money> conversion(Money money, Currency currency) {
        // Process if FROM and TO currencies are the same
        if (money.getCurrency().equals(currency)) {
            return Optional.of(new Money(currency, money.getAmount()));
        }

        // Process if straight or inverse conversions
        Optional<Double> rate = findRate(money.getCurrency(), currency);
        if (rate.isPresent()) {
            BigDecimal newAmount = money.getAmount().multiply(BigDecimal.valueOf(rate.get()));
            return Optional.of(new Money(currency, newAmount));
        }

        return Optional.empty();
    }

    private static Optional<Double> findRate(Currency base, Currency terms) {
        // Find straight conversion rate
        Optional<CurrencyPair> currencyPair = CurrencyPair.findByBaseAndTerms(base, terms);
        if (currencyPair.isPresent()) {
            return Optional.of(currencyPair.get().getRate());
        }

        // Find inverse conversion rate
        currencyPair = CurrencyPair.findByBaseAndTerms(terms, base);
        return currencyPair.map(CurrencyPair::getInverseRate);
    }
}