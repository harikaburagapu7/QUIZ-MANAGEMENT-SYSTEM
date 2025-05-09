package models;

public class Question {
    private String question, optionA, optionB, optionC, optionD, correctOption;

    public Question(String q, String a, String b, String c, String d, String correct) {
        this.question = q;
        this.optionA = a;
        this.optionB = b;
        this.optionC = c;
        this.optionD = d;
        this.correctOption = correct;
    }

    public boolean isCorrect(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctOption);
    }

    public String getFormatted() {
        return question + "\nA. " + optionA + "\nB. " + optionB + "\nC. " + optionC + "\nD. " + optionD;
    }

    public String toFileString() {
        return question + "|" + optionA + "|" + optionB + "|" + optionC + "|" + optionD + "|" + correctOption;
    }

    public static Question fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new Question(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
    }
}
