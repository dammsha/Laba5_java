package gui.bagWindow;

import fighter.Player;
import fighter.items.BagOfItems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BagPanel extends JPanel {

    private BagOfItems bagOfItems;
    private Player player;

    private JRadioButton rb_SmallPotion;
    private JRadioButton rb_BigPotion;
    private JRadioButton rb_CrossOfRevival;
    private JButton b_apply;

    public BagPanel(Player player, BagOfItems bagOfItems) {
        this.player = player;
        this.bagOfItems = bagOfItems;
        setLayout(new GridLayout(4, 1));

        rb_SmallPotion = new JRadioButton("Малое зелье лечения: " + bagOfItems.getSmallPotionCount());
        rb_BigPotion = new JRadioButton("Большое зелье лечения: " + bagOfItems.getBigPotionCount());
        rb_CrossOfRevival = new JRadioButton("Крест возрождения: " + bagOfItems.getCrossOfRevivalCount());

        ButtonGroup group = new ButtonGroup();
        group.add(rb_SmallPotion);
        group.add(rb_BigPotion);
        group.add(rb_CrossOfRevival);

        b_apply = new JButton("Применить");
        b_apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_SmallPotion.isSelected()) {
                    if (bagOfItems.getSmallPotionCount() == 0) {
                        JOptionPane.showMessageDialog(BagPanel.this, "У Вас нет малого зелья лечения", "Информация", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        bagOfItems.useItem("Малое зелье лечения", player);
                        rb_SmallPotion.setText("Малое зелье лечения: " + bagOfItems.getSmallPotionCount());
                    }

                } else if (rb_BigPotion.isSelected()) {
                    if (bagOfItems.getBigPotionCount() == 0) {
                        JOptionPane.showMessageDialog(BagPanel.this, "У Вас нет большого зелья лечения", "Информация", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        bagOfItems.useItem("Большое зелье лечения", player);
                        rb_BigPotion.setText("Большое зелье лечения: " + bagOfItems.getBigPotionCount());
                    }
                }
                else if (rb_CrossOfRevival.isSelected()) {
                }
            }
        });

        add(rb_SmallPotion);
        add(rb_BigPotion);
        add(rb_CrossOfRevival);
        add(b_apply);
    }
}
