import java.util.Scanner;
import models.Leaderboard;
import models.Player;
import models.Quiz;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Quiz quiz = new Quiz();
        Leaderboard leaderboard = new Leaderboard();

        quiz.loadQuestionsFromFile("C:\\Users\\harik\\OneDrive\\Desktop\\JAVA\\QUIZ APPLICATION\\DATA\\questions.txt");
        leaderboard.load("C:\\Users\\harik\\OneDrive\\Desktop\\JAVA\\QUIZ APPLICATION\\DATA\\scores.txt");

        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        Player player = new Player(name);
        quiz.start(player);

        leaderboard.update(player, "C:\\Users\\harik\\OneDrive\\Desktop\\JAVA\\QUIZ APPLICATION\\DATA\\scores.txt");
        leaderboard.display();
    }
}
