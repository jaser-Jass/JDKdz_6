package org.example;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Random;

public class MontyHallGame {
    private final Random random = new Random();

    public boolean simulateGame(boolean switchDoor) {
        // 0 - дверь с машиной, 1 - пустая дверь, 2 - пустая дверь
        int[] doors = {0, 1, 2};
        // Перемешиваем двери
        shuffleArray(doors);

        // Игрок выбирает дверь
        int playerChoice = random.nextInt(3);

        // Гостя открывает одну из оставшихся дверей, которая не содержит машину
        int montyChoice = getMontyChoice(doors, playerChoice);

        // Если игрок решает поменять свой выбор
        if (switchDoor) {
            playerChoice = 3 - playerChoice - montyChoice;
        }

        // Игрок выигрывает, если он выбрал дверь с машиной
        return doors[playerChoice] == 0;
    }

    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private int getMontyChoice(int[] doors, int playerChoice) {
        for (int door : doors) {
            if (door != playerChoice && door != 0) {
                return door; // Открываем неиспользуемую дверь без автомобиля.
            }
        }
        return -1; // Это никогда не должно произойти
    }

    public HashMap<String, Integer> runSimulation(int trials) {
        HashMap<String, Integer> results = new HashMap<>();
        int winsSwitch = 0;
        int winsStay = 0;

        for (int i = 0; i < trials; i++) {
            if (simulateGame(true)) {
                winsSwitch++;
            }
            if (simulateGame(false)) {
                winsStay++;
            }
        }

        results.put("Wins when switching", winsSwitch);
        results.put("Wins when staying", winsStay);
        results.put("Total trials", trials);
        return results;
    }
}
