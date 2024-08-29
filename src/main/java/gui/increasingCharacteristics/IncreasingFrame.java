package gui.increasingCharacteristics;

import fighter.Player;
import gui.fightWindow.FightPanel;

import javax.swing.*;
import java.awt.*;

public class IncreasingFrame extends JFrame {

    private FightPanel fightPanel;

    public IncreasingFrame(Player player, FightPanel fightPanel) {
        this.fightPanel = fightPanel;

        IncreasingPanel panel = new IncreasingPanel(player, this);
        setTitle("Выбор");

        setLocationRelativeTo(null);
        setSize(new Dimension(700, 300));
        setLocationRelativeTo(null);

        getContentPane().add(panel);

        // Скрываем FightPanel при открытии окна
        fightPanel.setVisible(false);

        setVisible(true);
        setResizable(true);

        // Добавляем WindowListener для обработки закрытия окна
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fightPanel.setVisible(true);
            }
        });
    }

    public void restoreFightPanel() {
        fightPanel.setVisible(true);
    }
}

