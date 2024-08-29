package gui.fightWindow;

import javax.swing.*;
import java.awt.*;

public class FrameFight extends JFrame {

    public FrameFight(int locationCount, String playerName){
        FightPanel panel = new FightPanel(locationCount, playerName);
        setTitle("Бой");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setSize(new Dimension(1200,800));
        setLocationRelativeTo(null);

        getContentPane().add(panel);
        setVisible(true);
        setResizable(true);
    }
}
