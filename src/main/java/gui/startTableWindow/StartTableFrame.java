package gui.startTableWindow;


import javax.swing.*;
import java.awt.*;

public class StartTableFrame extends JFrame {

    public StartTableFrame() {
        StartTablePanel panel = new StartTablePanel();
        setTitle("Предыдущие результаты");

        setLocationRelativeTo(null);
        setSize(new Dimension(700,500));
        setLocationRelativeTo(null);

        getContentPane().add(panel);
        setVisible(true);
        setResizable(true);
    }
}
