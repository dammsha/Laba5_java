package gui.firstWindow;

import gui.locationWindow.LocationFrame;
import gui.startTableWindow.StartTableFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPanel extends JPanel {

    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JButton showTable = new JButton("Показать таблицу результатов");
    JButton startGame = new JButton("Начать игру");
    JLabel title = new JLabel("Mortal Kombat", JLabel.CENTER);

    public FirstPanel(Frame frame) {

        setLayout(new GridLayout(2, 1));

        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.anchor = GridBagConstraints.CENTER;

        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setForeground(Color.RED);
        topPanel.add(title, gbcTitle);
        add(topPanel);

        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcButton1 = new GridBagConstraints();
        GridBagConstraints gbcButton2 = new GridBagConstraints();
        gbcButton1.insets = new Insets(5, 0, 5, 20); // Отступы вокруг кнопок
        gbcButton2.insets = new Insets(5, 20, 5, 0); // Отступы вокруг кнопок

        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        showTable.setFont(buttonFont);
        startGame.setFont(buttonFont);

        Dimension buttonSize = new Dimension(400, 100);
        showTable.setPreferredSize(buttonSize);
        startGame.setPreferredSize(buttonSize);

        gbcButton1.gridx = 0;
        gbcButton1.gridy = 0;
        centerPanel.add(showTable, gbcButton1);

        gbcButton2.gridx = 1;
        gbcButton2.gridy = 0;
        centerPanel.add(startGame, gbcButton2);

        add(centerPanel);

        showTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartTableFrame();
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LocationFrame();
                frame.dispose();
            }
        });
    }
}



