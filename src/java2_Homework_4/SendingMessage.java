package java2_Homework_4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendingMessage implements ActionListener {
    private final JTextField inputField;
    private final JTextArea outputField;

    public SendingMessage(JTextField inputField, JTextArea outputField) {
        this.inputField = inputField;
        this.outputField = outputField;
    }

    public void actionPerformed(ActionEvent e) {
        String str = outputField.getText() + inputField.getText() + "\n";
        outputField.setText(str);
        inputField.setText("");
    }
}
