package org.example;
import java.util.HashMap;
public class App {
    public static void main(String[] args) {
        MontyHallGame game = new MontyHallGame();
        int trials = 1000;
        HashMap<String, Integer> results = game.runSimulation(trials);

        System.out.println("Simulation Results:");
        System.out.println("Total Trials: " + results.get("Total trials"));
        System.out.println("Wins when switching: " + results.get("Wins when switching"));
        System.out.println("Wins when staying: " + results.get("Wins when staying"));

        System.out.println("Switching Win Rate: " + (double) results.get("Wins when switching") / trials * 100 + "%");
        System.out.println("Staying Win Rate: " + (double) results.get("Wins when staying") / trials * 100 + "%");
    }
}
// Запуск:
// mvn clean package
// java -cp target/mvn-test-project-1.0-SNAPSHOT.jar org.example.App