import javax.swing.*;

public class MainForm {
    private JPanel mainPanel;
    private JButton yesButton;
    private JButton noButton;
    private JLabel question;

    private GameState state = GameState.NEW_GAME;
    private int min, max; // Границы наших догадок
    private int guess = -1; // Текущая наша догадка

    private MainForm() {
        yesButton.addActionListener(e -> {
            switch (state) {
                case GAME_OVER:
                case NEW_GAME:
                    min = 1;
                    max = 100;
                    state = GameState.STARTED;
                    nextQuestion();
                    break;
                case STARTED:
                    min = guess + 1;
                    nextQuestion();
                    break;
            }
        });
        noButton.addActionListener(e -> {
            switch (state) {
                case NEW_GAME:
                    question.setText("Я буду ждать :) Задумали ли число?");
                    break;
                case STARTED:
                    max = guess;
                    nextQuestion();
                    break;
                case GAME_OVER:
                    System.exit(0);
                    break;
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Игра Угадай число");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void nextQuestion() {
        if (min == max) {
            question.setText("Ваше число " + min + ". Ещё раз?");
            state = GameState.GAME_OVER;
            return;
        }
        guess = (min + max) / 2;
        question.setText("Число больше " + guess + "?");
    }
}
