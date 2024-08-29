package gui.fightWindow;

import fighter.*;
import fighter.items.BagOfItems;
import gui.bagWindow.BagFrame;
import gui.increasingCharacteristics.IncreasingFrame;
import gui.finishTableWindow.FinishTableFrame;
import location.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FightPanel extends JPanel{

    Player player = new Player();
    Fight fight = new Fight();

    private boolean flagDebag = false;

    private BagOfItems bagOfItems = new BagOfItems();

    private String playerName;

    private int locationNumber;
    private int numberDefeatedEnemies = 0; //количество поверженных врагов

    //    Панели нижнего уровня
    private JPanel p_firstLevel = new JPanel();
    private JPanel p_secondLevel = new JPanel();
    private JPanel p_thirdLevel = new JPanel();
    private BorderLayout borderLayout = new BorderLayout();
    // Заполнение верхней панели
    private JLabel title = new JLabel("Fight");
    // Заполнение центральной панели, 2 ряд
    private JPanel p_secondRow = new JPanel();

    private JPanel p_enemyInfo = new JPanel();
    private JLabel l_enemyHp = new JLabel();
    private JProgressBar pr_enemyHp = new JProgressBar();
    private JLabel l_enemyDamage = new JLabel("Damage");

    private JPanel p_pointsExp = new JPanel();
    private JLabel l_points = new JLabel("Очки");
    private JLabel l_experience = new JLabel("Опыт");
    private JLabel l_valuePoints = new JLabel("");
    private JLabel l_valueExperience = new JLabel("");

    private JPanel p_playerInfo = new JPanel();
    private JLabel l_playerHp = new JLabel(player.getCurrentHp() + "/" + player.getMaxHp());
    private JProgressBar pr_playerHp = new JProgressBar();
    private JLabel l_playerDamage = new JLabel("Урон");

    // Заполнение центральной панели, 3 ряд
    private JPanel p_thirdRow = new JPanel();

    private JPanel p_enemyImage = new JPanel();
    private JLabel l_enemyLevel = new JLabel();
    private JLabel l_enemyName = new JLabel();

    private JPanel p_center = new JPanel();

    private JPanel p_action = new JPanel();
    private JLabel l_actionPlayer = new JLabel();
    private JLabel l_actionEnemy = new JLabel();

    private JPanel p_turn = new JPanel();
    private JLabel l_turn = new JLabel();
    private JLabel l_stun = new JLabel();

    private JPanel p_playerImage = new JPanel();
    private JLabel l_playerLevel = new JLabel("уровень " + player.getLevel());
    private JLabel l_playerName;

    // Заполнение нижней панели
    private JPanel p_buttons = new JPanel();
    private JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
    private JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    private JButton b_attack = new JButton("Атака");
    private JButton b_defend = new JButton("Защита");
    private JButton b_debaff = new JButton("Дебафф");
    private JButton b_items = new JButton("Мешок");
    private JButton b_debug = new JButton("Режим отладки");

    private int number_location = 1;
    private Location location = new Location(1);
    private Enemy enemy;

    Font buttonFont = new Font("", Font.BOLD, 22);
    Dimension buttonSize = new Dimension(150, 50);

    private boolean statusStep = true;

    JLabel scaledEnemyImage;
    JLabel scaledPlayerImage = new JLabel(scaleImageIcon(new ImageIcon("src/main/resources/player.jpg"), 300, 400));

    public FightPanel(int locationCount, String playerName) {

        this.playerName = playerName;
        l_playerName = new JLabel(playerName);
        this.locationNumber = locationCount;
        checkLocation();

        changeAllLabels(player, enemy);

        setLayout(borderLayout);
        add(p_firstLevel, BorderLayout.NORTH);
        add(p_secondLevel, BorderLayout.CENTER);
        add(p_thirdLevel, BorderLayout.SOUTH);

        title.setFont(new Font("Serif", Font.BOLD, 42));
        p_firstLevel.add(title);

        p_secondLevel.setLayout(new BoxLayout(p_secondLevel, BoxLayout.Y_AXIS));
        p_secondLevel.add(Box.createVerticalStrut(10));

        p_secondRow.setPreferredSize(new Dimension(p_secondLevel.getWidth(), 100));
        p_secondRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        p_secondRow.setLayout(new GridLayout(1, 3));

        p_enemyInfo.setLayout(new GridLayout(2, 2));
        p_enemyInfo.add(wrapInPanel(l_enemyHp));
        p_enemyInfo.add(wrapInPanel(pr_enemyHp));
        p_enemyInfo.add(wrapInPanel(l_enemyDamage));

        p_pointsExp.setLayout(new GridLayout(2, 2));
        p_pointsExp.add(wrapInPanel(l_points));
        p_pointsExp.add(wrapInPanel(l_experience));
        p_pointsExp.add(wrapInPanel(l_valuePoints));
        p_pointsExp.add(wrapInPanel(l_valueExperience));

        p_playerInfo.setLayout(new GridLayout(2, 2));
        p_playerInfo.add(wrapInPanel(l_playerHp));
        p_playerInfo.add(wrapInPanel(pr_playerHp));
        p_playerInfo.add(wrapInPanel(l_playerDamage));

        p_secondRow.add(p_enemyInfo);
        p_secondRow.add(p_pointsExp);
        p_secondRow.add(p_playerInfo);

        p_thirdRow.setLayout(new GridLayout(1, 3));

        p_enemyImage.setLayout(new BorderLayout());
        p_enemyImage.add(l_enemyLevel, BorderLayout.NORTH);
        p_enemyImage.add(scaledEnemyImage, BorderLayout.CENTER);
        p_enemyImage.add(wrapInPanel(l_enemyName), BorderLayout.SOUTH);

        p_center.setLayout(new GridLayout(2, 1));

        p_action.setLayout(new BoxLayout(p_action, BoxLayout.Y_AXIS));
        p_action.add(wrapInPanel(l_actionPlayer));
        p_action.add(wrapInPanel(l_actionEnemy));

        p_turn.setLayout(new GridLayout(2, 1));
        p_turn.add(wrapInPanel(l_turn));
        p_turn.add(wrapInPanel(l_stun));

        p_center.add(p_action);
        p_center.add(p_turn);

        p_playerImage.setLayout(new BorderLayout());
        p_playerImage.add(rightAlignInPanel(l_playerLevel), BorderLayout.NORTH);
        p_playerImage.add(scaledPlayerImage, BorderLayout.CENTER);
        p_playerImage.add(wrapInPanel(l_playerName), BorderLayout.SOUTH);

        p_thirdRow.add(p_enemyImage);
        p_thirdRow.add(p_center);
        p_thirdRow.add(p_playerImage);

        p_secondLevel.add(p_secondRow);
        p_secondLevel.add(p_thirdRow);

        b_attack.setFont(buttonFont);
        b_defend.setFont(buttonFont);
        b_debaff.setFont(buttonFont);
        b_items.setFont(buttonFont);
        b_debug.setFont(buttonFont);

        b_attack.setPreferredSize(buttonSize);
        b_defend.setPreferredSize(buttonSize);
        b_debaff.setPreferredSize(buttonSize);
        b_items.setPreferredSize(buttonSize);
        Dimension debugButtonSize = new Dimension(220, 50);
        b_debug.setPreferredSize(debugButtonSize);

        leftPanel.add(b_debug);
        rightPanel.add(b_items);
        rightPanel.add(b_attack);
        rightPanel.add(b_defend);
        rightPanel.add(b_debaff);

        p_buttons.setLayout(new BorderLayout());
        p_buttons.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 50));
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        leftPanel.add(b_debug);

        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 0));
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.add(b_items);
        rightPanel.add(b_attack);
        rightPanel.add(b_defend);
        rightPanel.add(b_debaff);

        p_buttons.add(leftPanel, BorderLayout.WEST);
        p_buttons.add(rightPanel, BorderLayout.EAST);

        p_thirdLevel.add(p_buttons);

        setLabelFont(new Font("", Font.BOLD, 22));

        b_attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                l_actionPlayer.setText("Атакует игрок");
                player.setMoveStatus(Entity.MoveStatus.ATTACK);
                if(player.getFlagDebaff()){
                    player.setFlagDebaff(false);
                    enemy.setCurrentHp(enemy.getCurrentHp() - (int)(player.getDamage() * 1.15));
                    System.out.println("Дебаф снят! Игрок!");
                }
                fight.doFight(player, enemy, statusStep);
                changeAllLabels(player, enemy);
                if (statusStep) {statusStep = false;} else {statusStep = true;}
                enemyZeroHp();
            }
        });

        b_defend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                player.setMoveStatus(Entity.MoveStatus.DEFENDING);
                l_actionPlayer.setText("Игрок защищается");
                fight.doFight(player, enemy, statusStep);
                changeAllLabels(player, enemy);
                if (statusStep) {statusStep = false;} else {statusStep = true;}
                enemyZeroHp();
            }
        });

        b_debaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                l_actionPlayer.setText("Игрок ослабляет противника");
                player.setMoveStatus(Entity.MoveStatus.DEBAFF);
                fight.doFight(player,  enemy, statusStep);
                changeAllLabels(player,  enemy);
                if (statusStep) {statusStep = false;} else {statusStep = true;}
                enemyZeroHp();
            }
        });

        b_items.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BagFrame(player, bagOfItems);
                changeAllLabels(player, enemy);
            }
        });

        b_debug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagDebag = true;
                player.setMaxHp(5000); player.setCurrentHp(5000); player.setDamage(5000);
                changeAllLabels(player, enemy);
            }
        });
    }

    private void checkLocation(){

        if(location.getListEnemy().size() != 0){
            enemy = location.getListEnemy().get(0);
            enemy.setLevel(player.getLevel());
        }else{
            number_location++;
            if(number_location <=  locationNumber){
                location = new Location(number_location);
                enemy = location.getListEnemy().get(0);
                enemy.setLevel(player.getLevel());
                new IncreasingFrame(player, FightPanel.this);
                player.setCurrentHp(player.getMaxHp());
            }else{
                System.out.println("Всех победил!");
                new FinishTableFrame(playerName, player.getPoints());
                b_attack.setEnabled(false);
                b_debaff.setEnabled(false);
                b_defend.setEnabled(false);
            }
        }
    }

    private void enemyZeroHp() {
        if (enemy.getCurrentHp() <= 0) {
            bagOfItems.dropItem();
            player.calculatePoints(player.getCurrentHp(), player.getMaxHp());
            player.calculateExp(enemy, number_location);
            location.getListEnemy().remove(enemy);
            numberDefeatedEnemies += 1;
            player.calculatePlayerLevel(numberDefeatedEnemies, flagDebag);
            checkLocation();
            changeAllLabels(player, enemy);

        } else if(player.getCurrentHp() <= 0){
            if (bagOfItems.getCrossOfRevivalCount() == 0) {
                b_items.setEnabled(false);
                b_attack.setEnabled(false);
                b_debaff.setEnabled(false);
                b_defend.setEnabled(false);
                changeAllLabels(player, enemy);
                new FinishTableFrame(playerName, player.getPoints());
            } else {
                bagOfItems.autoUsingCross(player);
                changeAllLabels(player, enemy);
                JOptionPane.showMessageDialog(this, "Крест возрождения применен", "Информация", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private JPanel wrapInPanel(JComponent component) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(component);
        return panel;
    }

    private JPanel rightAlignInPanel(JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(component);
        return panel;
    }

    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private void changeAllLabels(Player player, Enemy enemy) {
        changePlayerInfo(player);
        changeEnemyInfo(enemy);
        }

    private void changePlayerInfo(Player player) {
        l_valuePoints.setText(String.valueOf(player.getPoints()));
        l_valueExperience.setText(player.getValueOfExp() + "/" + player.getExpToNextLevel());
        if (player.getCurrentHp() <= 0) {
        l_playerHp.setText(0 + "/" + player.getMaxHp());
        } else {l_playerHp.setText(player.getCurrentHp() + "/" + player.getMaxHp());}
        l_playerDamage.setText("Урон: " + player.getDamage());
        l_playerLevel.setText(player.getLevel() + " уровень");
        if (statusStep) {l_turn.setText("Ваш ход");}
        if (player.getMoveStatus() == Entity.MoveStatus.STUNNING) {l_stun.setText("Вы оглушены");} else {l_stun.setText("");}
        l_stun.setVisible(true);
        pr_playerHp.setMaximum(player.getMaxHp());
        pr_playerHp.setValue(player.getCurrentHp());
        revalidate();
        repaint();
    }

    private void changeEnemyInfo(Enemy enemy) {
        if (enemy.getCurrentHp() <= 0) {
            l_enemyHp.setText(0 + "/" + enemy.getMaxHp());
        } else { l_enemyHp.setText(enemy.getCurrentHp() + "/" + enemy.getMaxHp());}
        l_enemyDamage.setText("Урон: " + enemy.getDamage());
        l_enemyLevel.setText(enemy.getLevel() + " уровень");
        l_enemyName.setText(enemy.getName() + " (" + enemy.getType() + ")");
        if (!statusStep) {l_turn.setText("Ход врага");}
        if (enemy.getMoveStatus() == Entity.MoveStatus.STUNNING) {l_stun.setText("Враг оглушен");} else {l_stun.setText("");}

        // Получение изображения и его масштабирование
        ImageIcon originalIcon = new ImageIcon(enemy.getPhotoPath());
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Обновление или создание нового JLabel с изображением
        if (scaledEnemyImage != null) {
            // Обновляем изображение в существующем JLabel
            scaledEnemyImage.setIcon(scaledIcon);
        } else {
            // Создаем новый JLabel, если он еще не существует
            scaledEnemyImage = new JLabel(scaledIcon);
            add(scaledEnemyImage);
        }

        pr_enemyHp.setMaximum(enemy.getMaxHp());
        pr_enemyHp.setValue(enemy.getCurrentHp());

        revalidate();
        repaint();

        actionEnemy(enemy);
    }

    private void actionEnemy(Enemy enemy) {
        if (enemy.getMoveStatus() == Enemy.MoveStatus.ATTACK) {
            l_actionEnemy.setText("Враг атакует");
        } else if (enemy.getMoveStatus() == Enemy.MoveStatus.DEFENDING) {
            l_actionEnemy.setText("Враг защищается");
        } else if (enemy.getMoveStatus() == Enemy.MoveStatus.DEBAFF) {
            l_actionEnemy.setText("Враг дебаффит");
        }
    }

    private void setLabelFont(Font font) {
        l_enemyHp.setFont(font);
        l_enemyDamage.setFont(font);
        l_points.setFont(font);
        l_experience.setFont(font);
        l_valuePoints.setFont(font);
        l_valueExperience.setFont(font);
        l_playerHp.setFont(font);
        l_playerDamage.setFont(font);
        l_enemyLevel.setFont(font);
        l_enemyName.setFont(font);
        l_actionPlayer.setFont(font);
        l_turn.setFont(font);
        l_stun.setFont(font);
        l_playerLevel.setFont(font);
        l_playerName.setFont(font);
        l_actionEnemy.setFont(font);
    }
}
