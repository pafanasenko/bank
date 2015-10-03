package com.bank.domain.finance;


import java.util.Currency;

/**
 * Created by pafanasenko on 28.09.15.
 */
public class MoneyExchanger {
    private static IExchangeRateSource instance = null;

    public static final Currency USD = Currency.getInstance("USD");
    public static final Currency EUR = Currency.getInstance("EUR");
    public static final Currency RUB = Currency.getInstance("RUB");

    public static Money exchangeTo (Money value, Currency to) {
        if (instance != null) {
            IMoneyExchangeCalculator calc = instance.get(value.getCurrency(), to);
            if (calc != null)
                return calc.calculate(value, to);
        }
        throw new RuntimeException ("to implement");
    }

    public static IExchangeRateSource setExchangeRateSource (IExchangeRateSource source) {
        IExchangeRateSource value = instance;
        instance = source;
        return value;
    }
}
