package gui.locationWindow;

import gui.fightWindow.FrameFight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LocationPanel extends JPanel {

    private JLabel l_text = new JLabel("Введите количество локаций от 1 до 5");
    private JTextField textField = new JTextField();
    private JLabel l_name = new JLabel("Введите Ваше имя");
    private JTextField nameField = new JTextField();
    private JButton b_ok = new JButton("ok");
    private int locationCount;
    private String input;
    private String playerName;

    public LocationPanel(JFrame frame) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font font = new Font("Arial", Font.PLAIN, 20);
        l_text.setFont(font);
        l_name.setFont(font);
        textField.setFont(font);
        nameField.setFont(font);
        b_ok.setFont(font);

        Dimension fieldSize = new Dimension(400, 60);
        textField.setPreferredSize(fieldSize);
        nameField.setPreferredSize(fieldSize);

        Dimension buttonSize = new Dimension(120, 50);
        b_ok.setPreferredSize(buttonSize);

        textField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setHorizontalAlignment(JTextField.CENTER);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(l_text);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        namePanel.add(l_name);

        JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textFieldPanel.add(textField);

        JPanel nameFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nameFieldPanel.add(nameField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));  // Кнопка выравнена по левому краю
        buttonPanel.add(b_ok);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));  // Установлен одинаковый отступ

        add(Box.createVerticalStrut(20));
        add(titlePanel);
        add(Box.createVerticalStrut(10));
        add(textFieldPanel);
        add(Box.createVerticalStrut(10));
        add(namePanel);
        add(Box.createVerticalStrut(10));
        add(nameFieldPanel);
        add(Box.createVerticalStrut(10));
        add(buttonPanel);

        b_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input = textField.getText();
                playerName = nameField.getText();

                try {
                    locationCount = Integer.parseInt(input);
                    if (locationCount >= 1 && locationCount <= 5) {
                        if (playerName.isEmpty()) {
                            JOptionPane.showMessageDialog(LocationPanel.this,
                                    "Введите имя",
                                    "Ошибка ввода",
                                    JOptionPane.ERROR_MESSAGE);
                        } else if (!playerName.matches("[a-zA-Zа-яА-Я]+")) {  // Проверка на наличие только букв
                            JOptionPane.showMessageDialog(LocationPanel.this,
                                    "Имя должно содержать только буквы",
                                    "Ошибка ввода",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            new FrameFight(locationCount, playerName);
                            frame.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(LocationPanel.this,
                                "Введите число от 1 до 5",
                                "Ошибка ввода",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(LocationPanel.this,
                            "Введите корректное число",
                            "Ошибка ввода",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}