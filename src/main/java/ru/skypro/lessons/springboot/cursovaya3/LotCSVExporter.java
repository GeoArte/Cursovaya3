package ru.skypro.lessons.springboot.cursovaya3;

import liquibase.util.csv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LotCSVExporter {
    public static void exportLotsToCSV(List<Lot> lots, String csvFileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            // Заголовок CSV
            String[] header = {"ID", "Name", "Start Price", "Current Price", "Status"};
            writer.writeNext(header);

            // Данные
            for (Lot lot : lots) {
                String[] row = {
                        String.valueOf(lot.getId()),
                        lot.getName(),
                        String.valueOf(lot.getStartPrice()),
                        String.valueOf(lot.getCurrentPrice()),
                        lot.getStatus().toString()
                };
                writer.writeNext(row);
            }

            System.out.println("Данные экспортированы в CSV файл.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
