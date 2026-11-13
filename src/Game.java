public class Game {
    private GameState state = GameState.NEW_GAME;
    private int min, max; // Границы наших догадок
    private int guess = -1; // Текущая наша догадка


    String yes() {
        switch (state) {
            case GAME_OVER:
            case NEW_GAME:
                min = 1;
                max = 100;
                state = GameState.STARTED;
                return nextQuestion();
            case STARTED:
                min = guess + 1;
                return nextQuestion();
        }
        return null;
    }


    String no() {
        switch (state) {
            case NEW_GAME:
                return "Я буду ждать :) Задумали ли число?";
            case STARTED:
                max = guess;
                return nextQuestion();
            case GAME_OVER:
                System.exit(0);
                break;
        }
        return null;
    }

    private String nextQuestion() {
        if (min == max) {
            state = GameState.GAME_OVER;
            return "Ваше число " + min + ". Ещё раз?";
        }
        guess = (min + max) / 2;
        return "Число больше " + guess + "?";
    }
}
