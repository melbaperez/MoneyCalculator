package Persistence.WebService;

import Model.Currency;
import Model.ExchangeRate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import Persistence.ExchangeRateLoader;


public class ExchangeRateLoaderWebService implements ExchangeRateLoader {
    
    
    private ExchangeRate ExchangeRateOf(String lineText, Currency from, Currency to) {
        String[] splitedLine = lineText.split(" ");
        return new ExchangeRate(Double.parseDouble(splitedLine[1]), from, to);
    }
    
    @Override
    public ExchangeRate get(Currency from, Currency to) {
        try {
            ExchangeRate exchangeRate = new ExchangeRate(0, from, to);
            URL url= new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" 
                    + from.getCode().toLowerCase() + "/" + to.getCode().toLowerCase() + ".json");
            
            URLConnection urlConnection = url.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()))) {
                in.skip(32);
                String inputLine = in.readLine();
                if (inputLine != null)
                    exchangeRate = this.ExchangeRateOf(inputLine, from, to);
            }
            return exchangeRate;
            
        }   catch (MalformedURLException ex) {
            Logger.getLogger(ExchangeRateLoaderWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExchangeRateLoaderWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
   
}
