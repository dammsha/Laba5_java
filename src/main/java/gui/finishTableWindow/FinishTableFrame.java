package gui.finishTableWindow;

import javax.swing.*;
import java.awt.*;

public class FinishTableFrame extends JFrame {

    public FinishTableFrame(String playerName, int points) {

        FinishTablePanel panel = new FinishTablePanel(playerName, points);
        setTitle("Окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setSize(new Dimension(700,500));
        setLocationRelativeTo(null);

        getContentPane().add(panel);
        setVisible(true);
        setResizable(true);
    }
}
