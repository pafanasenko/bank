package com.bank.domain.finance;

import com.bank.domain.finance.IExchangeRateSource;
import com.bank.domain.finance.IMoneyExchangeCalculator;
import com.bank.domain.finance.Money;
import com.sun.tools.javac.util.Pair;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class MockExchangeRateSource implements IExchangeRateSource, IMoneyExchangeCalculator {

    private Map<Pair<Currency,Currency>, Pair<BigDecimal,BigDecimal>> map =
            new HashMap<Pair<Currency,Currency>, Pair<BigDecimal,BigDecimal>> ();

    public void set(Currency from, Currency to, BigDecimal rateFrom, BigDecimal rateTo) {
        map.put(new Pair<>(from, to), new Pair<>(rateFrom, rateTo));
    }

    @Override
    public IMoneyExchangeCalculator get(Currency from, Currency to) {
        Pair<BigDecimal,BigDecimal> rate = map.get(new Pair<> (from,to));
        if (rate == null)
            set(from, to, new BigDecimal(1), new BigDecimal(1));
        return this;
    }

    @Override
    public Money calculate(Money value, Currency to) {
        Pair<BigDecimal,BigDecimal> rate = map.get(new Pair<> (value.getCurrency(),to));
        if (rate == null)
            throw new RuntimeException("There is no calculation rate");
        BigDecimal newAmount = new BigDecimal(String.valueOf(value.getAmount())).divide(rate.fst).multiply(rate.snd);
        return new Money(newAmount, to);
    }
}