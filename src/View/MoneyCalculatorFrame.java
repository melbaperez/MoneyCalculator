package View;

import Model.Currency;
import Controller.ControllerMoneyCalculator;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MoneyCalculatorFrame extends JFrame{
    private DialogSwing dialogSwing;
    private DisplaySwing displaySwing;
    private final List<Currency> listCurrencies;
    private final ControllerMoneyCalculator controllerMoneyCalculator;
         
    
    public MoneyCalculatorFrame(List<Currency> listCurrencies, 
            ControllerMoneyCalculator controllerMoneyCalculator){
        this.listCurrencies = listCurrencies;
        this.controllerMoneyCalculator = controllerMoneyCalculator;
        
    }

    public void createGUI(){
        dialogSwing = new DialogSwing(listCurrencies);
        displaySwing = new  DisplaySwing();

        setTitle("Convertidor de divisas");
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(dialogSwing.createPanelDialog());
        pane.add(displaySwing.createPanelDisplay());
    
        
        dialogSwing.getChoiceCurrencyFrom().addActionListener((ActionEvent e) -> {
            controllerMoneyCalculator.actualizarDisplay(dialogSwing, displaySwing);
        });
        
        dialogSwing.getChoiceCurrencyTo().addActionListener((ActionEvent e) -> {
            controllerMoneyCalculator.actualizarDisplay(dialogSwing, displaySwing);
        }); 
        
        dialogSwing.getJTextFieldAmount().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                controllerMoneyCalculator.actualizarDisplay(dialogSwing, displaySwing);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                controllerMoneyCalculator.actualizarDisplay(dialogSwing, displaySwing);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                controllerMoneyCalculator.actualizarDisplay(dialogSwing, displaySwing);
            }
        });
        
        setContentPane(pane);
        setLocation(300,300);
        setSize(800,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
}
