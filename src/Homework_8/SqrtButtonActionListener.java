package Homework_8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SqrtButtonActionListener implements ActionListener {

    private final JTextField inputField;

    public SqrtButtonActionListener(JTextField inputField) {
        this.inputField = inputField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] line = inputField.getText().split("SQRT");
        int number = Integer.parseInt(line[0]);
        double result = Math.sqrt(number);
        inputField.setText(String.valueOf(result));
    }
}

