package java2_Homework_4;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame {
    private JFrame mainFrame = new JFrame();
    private JPanel panel;

    public ApplicationFrame() {

        mainFrame.setTitle("Мой чат v.1.0");
        mainFrame.setBounds(new Rectangle(600, 800));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BorderLayout borderLayout = new BorderLayout();
        mainFrame.setLayout(borderLayout);

        JTextArea messageHistoryField = new JTextArea();
        JScrollPane scroll = new JScrollPane(messageHistoryField);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        messageHistoryField.setFont(new Font("MessageLine font", 0, 20));
        messageHistoryField.setEditable(false);
        mainFrame.add(scroll, borderLayout.CENTER);

        panel = new JPanel();
        panel.setLayout(new GridLayout());

        JTextField messageLine = new JTextField();
        messageLine.setFont(new Font("MessageLine font", 0, 20));

        JButton btn = new JButton("Отправить");
        btn.addActionListener(new SendingMessage(messageLine, messageHistoryField));

        messageLine.addKeyListener(new SendingMessageByEnterKey(messageLine, messageHistoryField));

        panel.add(messageLine);
        panel.add(btn);

        mainFrame.add(panel, borderLayout.SOUTH);
        mainFrame.setVisible(true);
    }
}
