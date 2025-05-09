package models;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Leaderboard {
    private Map<String, Integer> scores = new HashMap<>();

    public void load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                scores.put(data[0], Integer.parseInt(data[1]));
            }
        } catch (IOException e) {
            System.out.println("Leaderboard file not found.");
        }
    }

    public void update(Player p, String filename) {
        scores.put(p.getName(), p.getScore());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Integer> e : scores.entrySet()) {
                bw.write(e.getKey() + "," + e.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing leaderboard: " + e.getMessage());
        }
    }

    public void display() {
        System.out.println("\n--- Leaderboard ---");
        scores.entrySet().stream()
              .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
              .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}


