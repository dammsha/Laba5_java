package excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelManager {

    private String filePath = "src/main/resources/Результаты.xlsx";

    public List<PlayerScore> readScores() {
        List<PlayerScore> scores = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String name = row.getCell(0).getStringCellValue();
                int points = (int) row.getCell(1).getNumericCellValue();
                scores.add(new PlayerScore(name, points));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public boolean writeScore(PlayerScore playerScore) {
        List<PlayerScore> scores = readScores();

        // Проверка наличия игрока с таким же именем
        String originalName = playerScore.getName();
        String newName = originalName;
        int counter = 1;

        while (isNameExist(scores, newName)) {
            counter++;
            newName = originalName + " (" + counter + ")";
        }

        // Обновляем имя игрока, если оно было изменено
        playerScore.setName(newName);
        scores.add(playerScore);

        // Сортируем по числу очков
        scores.sort((s1, s2) -> Integer.compare(s2.getPoints(), s1.getPoints()));

        // Проверяем, попадает ли текущий игрок в топ-10
        boolean isInTop10 = scores.indexOf(playerScore) < 10;

        // Оставляем только топ-10
        if (scores.size() > 10) {
            scores = scores.subList(0, 10);
        }

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.createSheet("Scores");

            int rowIndex = 0;
            for (PlayerScore score : scores) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(score.getName());
                row.createCell(1).setCellValue(score.getPoints());
            }

            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isInTop10;
    }

    private boolean isNameExist(List<PlayerScore> scores, String name) {
        for (PlayerScore score : scores) {
            if (score.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}