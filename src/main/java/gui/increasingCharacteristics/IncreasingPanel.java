package gui.increasingCharacteristics;

import fighter.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncreasingPanel extends JPanel {

    JLabel l_choice = new JLabel("Выберите то, что хотите улучшить");
    JButton b_damage = new JButton("Урон");
    JButton b_hp = new JButton("Здоровье");
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private IncreasingFrame increasingFrame;

    public IncreasingPanel(Player player, IncreasingFrame increasingFrame) {
        this.increasingFrame = increasingFrame;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font largerFont = new Font("Arial", Font.BOLD, 24);
        l_choice.setFont(largerFont);
        b_damage.setFont(largerFont);
        b_hp.setFont(largerFont);

        Dimension buttonSize = new Dimension(200, 60);
        b_damage.setPreferredSize(buttonSize);
        b_hp.setPreferredSize(buttonSize);

        l_choice.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(l_choice);

        buttonPanel.add(b_damage);
        buttonPanel.add(b_hp);
        add(Box.createVerticalStrut(20));
        add(buttonPanel);

        b_damage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setDamage(player.getDamage() + 20);
                closePanel();
            }
        });

        b_hp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setMaxHp(player.getMaxHp() + 20);
                closePanel();
            }
        });
    }

    // Метод для закрытия текущего окна
    private void closePanel() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
            increasingFrame.restoreFightPanel();
        }
    }
}