package java2_Homework_4;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SendingMessageByEnterKey extends KeyAdapter {
    private final JTextField inputField;
    private final JTextArea outputField;

    public SendingMessageByEnterKey(JTextField inputField, JTextArea outputField) {
        this.inputField = inputField;
        this.outputField = outputField;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String str = outputField.getText() + inputField.getText() + "\n";
            outputField.setText(str);
            inputField.setText("");
        }

    }
}
