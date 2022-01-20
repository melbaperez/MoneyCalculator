package Persistence;

import Model.ExchangeRate;
import Model.Currency;

public interface ExchangeRateLoader {
    public ExchangeRate get(Currency from, Currency to);
}
