package fxcalculator;

import java.util.*;

/**
 * Available Currency Pairs
 */
public enum CurrencyPair {
    AUDUSD(Currency.AUD, Currency.USD, 0.8371),
    CADUSD(Currency.CAD, Currency.USD, 0.8711),
    USDCNY(Currency.USD, Currency.CNY, 6.1715),
    EURUSD(Currency.EUR, Currency.USD, 1.2315),
    GBPUSD(Currency.GBP, Currency.USD, 1.5683),
    NZDUSD(Currency.NZD, Currency.USD, 0.7750),
    USDJPY(Currency.USD, Currency.JPY, 119.95),
    EURCZK(Currency.EUR, Currency.CZK, 27.6028),
    EURDKK(Currency.EUR, Currency.DKK, 7.4405),
    EURNOK(Currency.EUR, Currency.NOK, 8.6651);

    private Currency base;
    private Currency terms;
    private double rate;

    CurrencyPair(Currency base, Currency terms, double rate) {
        this.base = base;
        this.terms = terms;
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public Currency getBase() {
        return base;
    }

    public Currency getTerms() {
        return terms;
    }

    public double getInverseRate() {
        return 1 / rate;
    }

    public static Optional<CurrencyPair> findByBaseAndTerms(Currency base, Currency terms) {
        Objects.requireNonNull(base, "Base Currency is a required field.");
        Objects.requireNonNull(terms, "Terms Currency is a required field.");

        switch (base.toString() + terms.toString()) {
            case "AUDUSD":
                return Optional.of(AUDUSD);
            case "CADUSD":
                return Optional.of(CADUSD);
            case "USDCNY":
                return Optional.of(USDCNY);
            case "EURUSD":
                return Optional.of(EURUSD);
            case "GBPUSD":
                return Optional.of(GBPUSD);
            case "NZDUSD":
                return Optional.of(NZDUSD);
            case "USDJPY":
                return Optional.of(USDJPY);
            case "EURCZK":
                return Optional.of(EURCZK);
            case "EURDKK":
                return Optional.of(EURDKK);
            case "EURNOK":
                return Optional.of(EURNOK);
            default:
                return Optional.empty();
        }
    }

    /**
     * Returns list of CurrencyPairs containing the given Currency, either as a base or terms.
     */
    public static List<CurrencyPair> findByCurrency(Currency currency) {
        Objects.requireNonNull(currency, "Currency is a required field.");

        switch (currency.toString()) {
            case "AUD":
                return Collections.singletonList(AUDUSD);
            case "CAD":
                return Collections.singletonList(CADUSD);
            case "CNY":
                return Collections.singletonList(USDCNY);
            case "CZK":
                return Collections.singletonList(EURCZK);
            case "DKK":
                return Collections.singletonList(EURDKK);
            case "EUR":
                return Arrays.asList(EURUSD, EURCZK, EURDKK, EURNOK);
            case "GBP":
                return Collections.singletonList(GBPUSD);
            case "JPY":
                return Collections.singletonList(USDJPY);
            case "NOK":
                return Collections.singletonList(EURNOK);
            case "NZD":
                return Collections.singletonList(NZDUSD);
            case "USD":
                return Arrays.asList(AUDUSD, CADUSD, USDCNY, EURUSD, GBPUSD, NZDUSD, USDJPY);
            default:
                return Collections.emptyList();
        }
    }
}