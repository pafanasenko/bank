package com.bank.domain.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by pafanasenko on 28.09.15.
 */
public final class Money implements Comparable<Money>, Serializable {

    private MoneyAmount fAmount;
    private final Currency fCurrency;

    private int fHashCode;
    private static final int HASH_SEED = 23;
    private static final int HASH_FACTOR = 37;
    private static final long serialVersionUID = 7526471155622776147L;
    //TODO review
    //private static final int[] cents = new int[] { 1, 10, 100, 1000 };

    //TODO review moving from RuntimeException superclass
    public static final class MismatchedCurrencyException extends RuntimeException {
        MismatchedCurrencyException(String aMessage){
            super(aMessage);
        }
    }

    public Money(Money money) {
        fAmount = money.fAmount;
        fCurrency = money.fCurrency;
        validateState();
    }

    public Money(MoneyAmount aAmount, Currency aCurrency){
        fAmount = aAmount;
        fCurrency = aCurrency;
        validateState();
    }

    public Money(BigDecimal aAmount, Currency aCurrency){
        fAmount = new MoneyAmount (aAmount);
        fCurrency = aCurrency;
        validateState();
    }

    public Money(int aAmount, Currency aCurrency){
        fAmount = new MoneyAmount (aAmount);
        fCurrency = aCurrency;
        validateState();
    }

    public Money(long aAmount, Currency aCurrency){
        fAmount = new MoneyAmount (aAmount);
        fCurrency = aCurrency;
        validateState();
    }

    public Money(double aAmount, Currency aCurrency){
        fAmount = new MoneyAmount (aAmount);
        fCurrency = aCurrency;
        validateState();
    }

    public Money(char[] aAmount, Currency aCurrency){
        fAmount = new MoneyAmount (aAmount);
        fCurrency = aCurrency;
        validateState();
    }

    public Money(String aAmount, Currency aCurrency){
        fAmount = new MoneyAmount (aAmount);
        fCurrency = aCurrency;
        validateState();
    }

    public MoneyAmount getAmount() { return fAmount; }

    public Currency getCurrency() { return fCurrency; }

    public Money plus(Money aThat){
        checkCurrenciesMatch(aThat);
        return new Money(fAmount.add(aThat.fAmount), fCurrency);
    }

    public Money minus(Money aThat){
        checkCurrenciesMatch(aThat);
        return new Money(fAmount.subtract(aThat.fAmount), fCurrency);
    }

    public boolean gt(Money aThat) {
        checkCurrenciesMatch(aThat);
        return compareAmount(aThat) > 0;
    }

    public boolean gteq(Money aThat) {
        checkCurrenciesMatch(aThat);
        return compareAmount(aThat) >= 0;
    }

    public boolean lt(Money aThat) {
        checkCurrenciesMatch(aThat);
        return compareAmount(aThat) < 0;
    }

    public boolean lteq(Money aThat) {
        checkCurrenciesMatch(aThat);
        return compareAmount(aThat) <= 0;
    }

    public String toString(){
        return fAmount.toPlainString() + " " + fCurrency.getSymbol();
    }

    public boolean equals(Object aThat){
        if (this == aThat) return true;
        if (! (aThat instanceof Money) ) return false;
        Money that = (Money)aThat;
        //the object fields are never null :
        boolean result = (this.fAmount.equals(that.fAmount) );
        result = result && (this.fCurrency.equals(that.fCurrency) );
        return result;
    }

    public int hashCode(){
        if ( fHashCode == 0 ) {
            fHashCode = HASH_SEED;
            fHashCode = HASH_FACTOR * fHashCode + fAmount.hashCode();
            fHashCode = HASH_FACTOR * fHashCode + fCurrency.hashCode();
        }
        return fHashCode;
    }

    public int compareTo(Money aThat) {
        final int EQUAL = 0;

        if ( this == aThat ) return EQUAL;

        //the object fields are never null
        int comparison = this.fAmount.compareTo(aThat.fAmount);
        if ( comparison != EQUAL ) return comparison;

        comparison = this.fCurrency.getCurrencyCode().compareTo(
                aThat.fCurrency.getCurrencyCode()
        );
        if ( comparison != EQUAL ) return comparison;


        return EQUAL;
    }

    private void validateState(){
        if( fAmount == null ) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if( fCurrency == null ) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        if ( fAmount.scale() > getNumDecimalsForCurrency() ) {
            throw new IllegalArgumentException(
                    "Number of decimals is " + fAmount.scale() + ", but currency only takes " +
                            getNumDecimalsForCurrency() + " decimals."
            );
        }
    }

    private int getNumDecimalsForCurrency(){
        return fCurrency.getDefaultFractionDigits();
    }

    private void checkCurrenciesMatch(Money aThat){
        if (! this.fCurrency.equals(aThat.getCurrency())) {
            throw new MismatchedCurrencyException(
                    aThat.getCurrency() + " doesn't match the expected currency : " + fCurrency
            );
        }
    }

    private int compareAmount(Money aThat){
        return this.fAmount.compareTo(aThat.fAmount);
    }
}
