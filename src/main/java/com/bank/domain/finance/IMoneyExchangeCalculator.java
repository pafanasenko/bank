package com.bank.domain.finance;

import java.util.Currency;

/**
 * Created by pafanasenko on 29.09.15.
 */
public interface IMoneyExchangeCalculator {
    Money calculate (Money value, Currency to);
}
