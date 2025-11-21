package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InvestmentRepository {

    private static final Path FILE_PATH = Path.of("resources/portfolio.csv");

    public List<String> readAllLines() {
        try {
            return Files.readAllLines(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeAllLines(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH.toFile()))) {
            for (String string : lines) {
                writer.write(string + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
