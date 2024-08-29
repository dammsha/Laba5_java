package gui.firstWindow;

import javax.swing.*;
import java.awt.*;

public class FirstFrame extends JFrame {

    JPanel firstPanel = new FirstPanel(this);

    public FirstFrame() {
        setTitle("Mortal Kombat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setSize(new Dimension(900,500));
        setLocationRelativeTo(null);
        getContentPane().add(firstPanel);
    }
}
