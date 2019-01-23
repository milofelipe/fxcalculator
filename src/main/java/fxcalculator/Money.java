package fxcalculator;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private Currency currency;
    private BigDecimal amount;

    public Money(Currency currency, BigDecimal amount) {
        Objects.requireNonNull(currency, "Currency is a required field.");
        Objects.requireNonNull(currency, "Amount is a required field.");

        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getRoundedAmount() {
        return amount.setScale(currency.getPrecision(), BigDecimal.ROUND_HALF_UP);
    }
}