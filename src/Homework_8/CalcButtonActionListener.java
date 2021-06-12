package Homework_8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcButtonActionListener implements ActionListener {
    private final JTextField textField;

    public CalcButtonActionListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //  String[] operands = textField.getText().split("\\+");
        //  String[] operation = textField.getText().split("[^0-9]");
        //  int sum = 0;
        //  for (int i = 0; i < operands.length; i++) {
        //      sum += Integer.parseInt(operands[i]);
        //  }
        String operands = textField.getText();
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("Nashorn");
        Object expResult = null;
        try {
            expResult = scriptEngine.eval(operands);
        } catch (ScriptException scriptException) {
            scriptException.printStackTrace();
        }

        textField.setText(String.valueOf(expResult));

    }
}
