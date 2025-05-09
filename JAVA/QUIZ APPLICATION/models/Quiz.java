package models;

import java.util.*;
import java.io.*;

public class Quiz {
    private ArrayList<Question> questions = new ArrayList<>();
    private int score = 0;
    private Scanner sc = new Scanner(System.in);

    public void loadQuestionsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                questions.add(Question.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading questions: " + e.getMessage());
        }
    }

    public void start(Player player) {
        Collections.shuffle(questions);
        int qNumber = 0;
        for (Question q : questions) {
            qNumber++;
            System.out.println("\nQuestion " + qNumber + ":\n" + q.getFormatted());

            Timer timer = new Timer();
            AnswerTask task = new AnswerTask();
            timer.schedule(task, 15000); // 15 seconds

            String answer = sc.nextLine();
            timer.cancel();

            if (answer.equalsIgnoreCase("exit")) break;

            if (q.isCorrect(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        }
        player.setScore(score);
    }

    static class AnswerTask extends TimerTask {
        public void run() {
            System.out.println("\nTime's up! Moving to next question.");
            System.exit(0); // You can change logic to skip instead
        }
    }
}
