package com.bank.domain.finance;

import com.sun.tools.javac.util.Pair;

import java.util.Currency;

/**
 * Created by pafanasenko on 29.09.15.
 */
public interface IExchangeRateSource {
    IMoneyExchangeCalculator get (Currency from, Currency to);
}
