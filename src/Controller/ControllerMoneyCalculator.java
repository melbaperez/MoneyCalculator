package Controller;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import Model.Currency;
import Persistence.Archive.CurrencyLoaderArchive;
import View.DialogSwing;
import View.DisplaySwing;
import Model.ExchangeRate;
import Persistence.WebService.ExchangeRateLoaderWebService;
import Model.Money;
import View.MoneyCalculatorFrame;

public class ControllerMoneyCalculator {
    private final ExchangeRateLoaderWebService exchangeRateLoaderWebService;

    public ControllerMoneyCalculator(ExchangeRateLoaderWebService exchangeRateLoaderWebService) {
        this.exchangeRateLoaderWebService = exchangeRateLoaderWebService;
    }
    
    public void actualizarDisplay(DialogSwing dialogSwing, DisplaySwing displaySwing){
        Money moneyFrom = dialogSwing.getMoney();
        Currency currencyTo = dialogSwing.getCurrencyTo();
        ExchangeRate exchangeRate = exchangeRateLoaderWebService.get(
                moneyFrom.getCurrency(), currencyTo);
        double rate = exchangeRate.getRate();
        Money moneyTo = new Money(moneyFrom.getAmount()*rate, currencyTo);
        displaySwing.display(moneyFrom, moneyTo, rate);
    }
    
    public static void main(String[] args) throws IOException, 
            ParserConfigurationException, SAXException {
        CurrencyLoaderArchive currencyLoader = new CurrencyLoaderArchive("currencies.txt"); 
        List<Currency> listCurrencies = currencyLoader.loadAllCurrencies();
        ExchangeRateLoaderWebService exchangeRateLoaderWebService = new ExchangeRateLoaderWebService();
        ControllerMoneyCalculator controllerMoneyCalculator = new ControllerMoneyCalculator(exchangeRateLoaderWebService);
        MoneyCalculatorFrame frame = new MoneyCalculatorFrame(listCurrencies, controllerMoneyCalculator);
        frame.createGUI(); 
    }
    
}
