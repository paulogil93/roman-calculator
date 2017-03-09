package roman_calculator;

/*
 * roman-calculator
 *
 * @author      Paulo Gil
 * @date        06/02/2017
 * @email       paulogil93@gmail.com
 * @version      1.0
 */

import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JTabbedPane tabbedPane1;
    private JTextField inputField;
    private JButton convertButton;
    private JLabel resultLabel;
    private JTextField expressionField;
    private JButton calcButton;
    private JLabel calcResult;

    public Calculator() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonClose);

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        });

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Convert();
            }
        });

        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Calc();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onClose() {
        dispose();
    }

    private void Convert() {
        RomanNumber rn;
        if(inputField.getText().length() == 0) {
            resultLabel.setText("Nothing to convert...");
        } else if(isNumber(inputField.getText())) {
            rn = new RomanNumber(Integer.parseInt(inputField.getText()));
            if(rn.getNumeral() == null) {
                resultLabel.setText("Invalid range...");
            } else {
                resultLabel.setText(rn.getNumeral());
            }
        } else {
            rn = new RomanNumber(inputField.getText());
            if(rn.getNumeral() == null) {
                resultLabel.setText("Invalid Numeral...");
            } else {
                resultLabel.setText(Integer.toString(rn.getValue()));
            }
        }
    }

    private void Calc() {
        String str = expressionField.getText();
        RomanNumber rn1;
        RomanNumber rn2;
        if(str.length() == 0) {
            calcResult.setText("Nothing to calc...");
        } else if(str.contains("+")) {
            String[] parts = str.split("\\+");
            rn1 = new RomanNumber(parts[0]);
            try {
                rn2 = new RomanNumber(parts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                rn2 = new RomanNumber(0);
            }

            if (rn1.getNumeral() == null || rn2.getNumeral() == null) {
                calcResult.setText("Invalid numeral(s)...");
            } else {
                int sum = rn1.getValue() + rn2.getValue();
                RomanNumber rn3 = new RomanNumber(sum);
                calcResult.setText(rn3.getNumeral());
            }
        } else if(str.contains("-")) {
            String[] parts = str.split("-");
            rn1 = new RomanNumber(parts[0]);
            try {
                rn2 = new RomanNumber(parts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                rn2 = new RomanNumber(0);
            }

            if (rn1.getNumeral() == null || rn2.getNumeral() == null) {
                calcResult.setText("Invalid numeral(s)...");
            } else {
                int diff = rn1.getValue() - rn2.getValue();
                RomanNumber rn3 = new RomanNumber(diff);
                calcResult.setText(rn3.getNumeral());
            }
        } else if(str.contains("*")) {
            String[] parts = str.split("\\*");
            rn1 = new RomanNumber(parts[0]);
            try {
                rn2 = new RomanNumber(parts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                rn2 = new RomanNumber(0);
            }

            if (rn1.getNumeral() == null || rn2.getNumeral() == null) {
                calcResult.setText("Invalid numeral(s)...");
            } else {
                int mul = rn1.getValue() * rn2.getValue();
                RomanNumber rn3 = new RomanNumber(mul);
                if(rn3.getNumeral() == null) calcResult.setText("Overflow...");
                else calcResult.setText(rn3.getNumeral());
            }
        } else if(str.contains(":")) {
            String[] parts = str.split(":");
            rn1 = new RomanNumber(parts[0]);
            try {
                rn2 = new RomanNumber(parts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                rn2 = new RomanNumber(0);
            }

            if (rn1.getNumeral() == null || rn2.getNumeral() == null) {
                calcResult.setText("Invalid numeral(s)...");
            } else {
                int div = rn1.getValue() / rn2.getValue();
                RomanNumber rn3 = new RomanNumber(div);
                if(rn3.getNumeral() == null) calcResult.setText("Overflow...");
                else calcResult.setText(rn3.getNumeral());
            }
        } else {
            calcResult.setText("Invalid expression...");
        }
    }

    private boolean isNumber(String s) {
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Calculator dialog = new Calculator();
        dialog.setTitle("Roman Calculator/Converter");
        dialog.setResizable(false);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
