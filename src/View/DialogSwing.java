package View;

import Model.Currency;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Model.Money;

public class DialogSwing extends JPanel implements Dialog{
    private final List<Currency> listCurrencies;
    private JComboBox choiceCurrencyFrom;
    private JComboBox choiceCurrencyTo;
    private JFormattedTextField amount;

    
    public DialogSwing(List<Currency> listCurrencies){
        this.listCurrencies = listCurrencies;
    }
   
    @Override 
    public Money getMoney(){
        return new Money(getAmount(), this.getCurrencyFromComboBox(choiceCurrencyFrom));
    }
    
    @Override
    public Currency getCurrencyTo(){
        return this.getCurrencyFromComboBox(choiceCurrencyTo);
    }
    
    private Currency getCurrencyFromComboBox(JComboBox comboBox){
        String name;
        String currencyToString = comboBox.getSelectedItem().toString();
        String[] result = currencyToString.split(" ");
        String[] result2 = result[1].split("-");
        if(result.length == 3)
            name = result2[1] + " " + result[2];
        else
            name = result2[1];
        return new Currency(result2[0], name, result[0]);
    }
    
    private void createComponents(){
        choiceCurrencyFrom = new JComboBox();
        choiceCurrencyTo = new JComboBox();
        amount = new JFormattedTextField (new Float(00.00));
        amount.setPreferredSize(new Dimension(150,30));
        this.loadToComboBox(listCurrencies);
        
        amount.setText("");
        
        amount.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char caracter = e.getKeyChar();
                if(((caracter < '0') ||
                    (caracter > '9')) &&
                    (caracter != '\b') &&
                    (caracter != ',') && (caracter != '.') ){
                    e.consume(); 
                }
                if (caracter == ',') e.setKeyChar('.');
            }
        });
        
    }
    
    private void loadToComboBox(List<Currency> listCurrencies){
        for (Currency moneda : listCurrencies){
            choiceCurrencyFrom.addItem(moneda);
            choiceCurrencyTo.addItem(moneda);
        }
    }
    
    public JPanel createPanelDialog(){
        JPanel panelDialog = new JPanel();
        this.createComponents();
        panelDialog.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panelDialog.add(new JLabel("Importe:"),c);
        c.gridx = 1;
        panelDialog.add(new JLabel("De:"),c);
        c.gridx = 2;
        panelDialog.add(new JLabel("A:"),c);
        c.gridx = 0;
        c.gridy = 1;
        panelDialog.add(amount, c);
        c.gridx = 1;
        panelDialog.add(choiceCurrencyFrom, c);
        c.gridx = 2;
        panelDialog.add(choiceCurrencyTo, c);
        return panelDialog;
    }
    
    public float getAmount(){
        if ("".equals(amount.getText()) || ".".equals(amount.getText())){
            return 0;
        }
        return Float.parseFloat(amount.getText());
    }
    
    public JComboBox getChoiceCurrencyFrom(){
        return choiceCurrencyFrom;
    }
    
    public JComboBox getChoiceCurrencyTo(){
        return choiceCurrencyTo;
    }
    
    public JTextField getJTextFieldAmount(){
        return amount;
    }
    
}
