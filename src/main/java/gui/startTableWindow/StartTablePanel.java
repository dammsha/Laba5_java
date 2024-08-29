package gui.startTableWindow;

import excel.ExcelManager;
import excel.PlayerScore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class StartTablePanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private ExcelManager excelManager;

    public StartTablePanel() {
        excelManager = new ExcelManager();
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

        for (int i = 0; i < Math.min(scores.size(), 10); i++) {
            PlayerScore score = scores.get(i);
            tableModel.addRow(new Object[]{score.getName(), score.getPoints()});
        }
    }
}
