package gui.bagWindow;

import fighter.Player;
import fighter.items.BagOfItems;

import javax.swing.*;
import java.awt.*;

public class BagFrame extends JFrame {

    private Player player;
    private BagPanel bagPanel;

    public BagFrame(Player player, BagOfItems bagOfItems) {
        this.player = player;
        this.bagPanel = new BagPanel(player, bagOfItems);

        setTitle("Мешок предметов");
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        getContentPane().add(bagPanel);
    }
}
