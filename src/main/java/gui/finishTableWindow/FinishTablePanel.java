package gui.finishTableWindow;

import excel.ExcelManager;
import excel.PlayerScore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class FinishTablePanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private ExcelManager excelManager;

    public FinishTablePanel(String playerName, int points) {
        excelManager = new ExcelManager();
        boolean isInTop10 = excelManager.writeScore(new PlayerScore(playerName, points));

        if (!isInTop10) {
            JOptionPane.showMessageDialog(this, "Вы не попали в топ-10", "Информация", JOptionPane.INFORMATION_MESSAGE);
        }

        List<PlayerScore> scores = excelManager.readScores();
        initTable(scores);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initTable(List<PlayerScore> scores) {
        tableModel = new DefaultTableModel(new Object[]{"Имя", "Очки"}, 0);
        table = new JTable(tableModel);

        Font tableFont = new Font("Arial", Font.PLAIN, 20);
        table.setFont(tableFont);
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 22));

        // Ограничиваем вывод 10 строками
        for (int i = 0; i < Math.min(scores.size(), 10); i++) {
            PlayerScore score = scores.get(i);
            tableModel.addRow(new Object[]{score.getName(), score.getPoints()});
        }
    }
}