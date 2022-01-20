package View;

import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import Model.Money;

public class DisplaySwing extends JPanel implements Display{
    private JTextArea data; 
    
    @Override 
    public void display(Money moneyFrom, Money moneyTo, double rate){
        DecimalFormat df = new DecimalFormat("0.###");
        data.setText(df.format( moneyFrom.getAmount()) + " " + moneyFrom.getCurrency().getSymbol() +
                "-" + moneyFrom.getCurrency().getName() + " = " + 
               df.format( moneyTo.getAmount()) + " " + moneyTo.getCurrency().getSymbol() +
                "-" + moneyTo.getCurrency().getName() + "\n 1 " + 
                moneyFrom.getCurrency().getSymbol() + " = " + 
                df.format(rate) + " " + moneyTo.getCurrency().getSymbol());    
    }
    
    private void constructControls(){
        data = new JTextArea();
        data.setEditable(false);
    }
    
    public JPanel createPanelDisplay(){
        JPanel panelDisplay = new JPanel();
        this.constructControls();
        panelDisplay.add(data);
        return panelDisplay;
    }
           
}