package com.bank.domain.finance;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class MoneyAmount implements Comparable<MoneyAmount> {
    private BigDecimal value = null;
    private final static int PRECISION = 2;

    public MoneyAmount(BigDecimal value) {
        this.value = Rounder.round(value, PRECISION);
    }

    public MoneyAmount(int value) {
        this.value = Rounder.round(new BigDecimal(value), PRECISION);
    }

    public MoneyAmount(long value) {
        this.value = Rounder.round(new BigDecimal(value), PRECISION);
    }

    public MoneyAmount(double value) {
        this.value = Rounder.round(new BigDecimal(value), PRECISION);
    }

    public MoneyAmount(char[] value) {
        this.value = Rounder.round(new BigDecimal(value), PRECISION);
    }

    public MoneyAmount(String value) {
        this.value = Rounder.round(new BigDecimal(value), PRECISION);
    }

    public MoneyAmount add(MoneyAmount added) {
        return new MoneyAmount(value.add (added.get()));
    }

    public MoneyAmount subtract(MoneyAmount  subtrahend) {
        return new MoneyAmount(value.subtract(subtrahend.get()));
    }

    int scale () {
        return value.scale();
    }

    public String toPlainString () {
        return value.toPlainString();
    }

    //TODO to remove
    private BigDecimal get () { return value; }

    @Override
    public  String toString () {
        return value.toString();
    }

    public boolean equals(Object obj) {
        //TODO review
        return obj instanceof MoneyAmount && value.equals(((MoneyAmount)obj).get());
    }

    @Override
    public int compareTo(MoneyAmount o) {
        return value.compareTo(o.get());
    }
}
