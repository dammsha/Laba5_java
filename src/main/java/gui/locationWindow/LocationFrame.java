package gui.locationWindow;

import javax.swing.*;
import java.awt.*;

public class LocationFrame extends JFrame {

    LocationPanel locationPanel = new LocationPanel(this);

    public LocationFrame() {
        setTitle("Mortal Kombat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setSize(new Dimension(600,400));
        setLocationRelativeTo(null);
        getContentPane().add(locationPanel);
    }
}
