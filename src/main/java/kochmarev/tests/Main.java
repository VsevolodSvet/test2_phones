package kochmarev.tests;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton search;
    private JTextArea resphones;
    private JTextField find;

    private static final Map<String, String> phonebase = new HashMap<String, String>();

    public Main() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getPhones(find.getText());
            }
        });
    }

    private ArrayList getPhones(String info){
        ArrayList resultPhones = new ArrayList();
        for (Map.Entry entry : phonebase.entrySet()) {
            if (entry.getKey().toString().contains(info) && info.length() > 3){
                String[] buff = entry.getValue().toString().split("\\+");
                resphones.setText(entry.getKey() + "\n\n");
                // от 1 - потому что убирается первый плюс из базы
                for (int i = 1; i < buff.length; i++) {
                    resphones.append("+" + buff[i] + "\n");
                    resultPhones.add(0, "+"+buff[i]);
                }
                return resultPhones;
            }
        }
        resphones.setText("ФИО не найдены!");
        return null;
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Main dialog = new Main();

        phonebase.put("Иванов И.И.", "+8 800 2000 500 +8 800 200 600");
        phonebase.put("Петров П.П.", "+8 800 2000 700");
        phonebase.put("Сидоров С.С.", "+8 800 2000 800 +8 800 2000 900 +8 800 2000 000");

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
