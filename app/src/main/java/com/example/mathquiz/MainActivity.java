package com.example.mathquiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView number1TextView, number2TextView, resultTextView, scoreTextView, historyTextView;
    Button buttonGenerate, buttonAdd, buttonSubtract, buttonMultiply;
    Button buttonEasy, buttonMedium, buttonHard;

    Button buttonResetScore;

    Button buttonClearHistory;

    int number1, number2;
    int difficulty = 10;
    int score = 0;

    ArrayList<String> historyList = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1TextView = findViewById(R.id.number1TextView);
        number2TextView = findViewById(R.id.number2TextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        historyTextView = findViewById(R.id.historyTextView);

        buttonGenerate = findViewById(R.id.buttonGenerate);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);

        buttonEasy = findViewById(R.id.buttonEasy);
        buttonMedium = findViewById(R.id.buttonMedium);
        buttonHard = findViewById(R.id.buttonHard);

        buttonResetScore = findViewById(R.id.buttonResetScore);


        Button buttonClearHistory = findViewById(R.id.buttonClearHistory);

        loadScore();
        generateNumbers();

        buttonGenerate.setOnClickListener(v -> generateNumbers());

        buttonAdd.setOnClickListener(v -> checkAnswer(number1 + number2, "+"));
        buttonSubtract.setOnClickListener(v -> checkAnswer(number1 - number2, "-"));
        buttonMultiply.setOnClickListener(v -> checkAnswer(number1 * number2, "×"));

        buttonEasy.setOnClickListener(v -> {
            difficulty = 10;
            updateDifficultyButtons();
        });

        buttonMedium.setOnClickListener(v -> {
            difficulty = 50;
            updateDifficultyButtons();
        });

        buttonHard.setOnClickListener(v -> {
            difficulty = 100;
            updateDifficultyButtons();
        });

        buttonResetScore.setOnClickListener(v -> {
            score = 0;
            scoreTextView.setText("Score : " + score);

            SharedPreferences prefs = getSharedPreferences("quiz", MODE_PRIVATE);
            prefs.edit().putInt("score", score).apply();
        });


        buttonClearHistory.setOnClickListener(v -> {
            historyList.clear();
            historyTextView.setText("");
        });
    }

    private void generateNumbers() {
        number1 = random.nextInt(difficulty);
        number2 = random.nextInt(difficulty);
        number1TextView.setText(String.valueOf(number1));
        number2TextView.setText(String.valueOf(number2));
        resultTextView.setText("?");
    }

    private void checkAnswer(int correctAnswer, String op) {
        resultTextView.setText(String.valueOf(correctAnswer));

        score++;
        scoreTextView.setText("Score : " + score);

        saveScore();

        String historyEntry = number1 + " " + op + " " + number2 + " = " + correctAnswer;
        historyList.add(historyEntry);

        updateHistory();
    }

    private void updateHistory() {
        StringBuilder builder = new StringBuilder();
        for (String item : historyList) {
            builder.append(item).append("\n");
        }
        historyTextView.setText(builder.toString());
    }

    private void saveScore() {
        SharedPreferences prefs = getSharedPreferences("quiz", MODE_PRIVATE);
        prefs.edit().putInt("score", score).apply();
    }

    private void loadScore() {
        SharedPreferences prefs = getSharedPreferences("quiz", MODE_PRIVATE);
        score = prefs.getInt("score", 0);
        scoreTextView.setText("Score : " + score);
    }

    private void updateDifficultyButtons() {
        // Reset all buttons
        buttonEasy.setBackgroundTintList(getColorStateList(R.color.primary_blue));
        buttonMedium.setBackgroundTintList(getColorStateList(R.color.button_subtract));
        buttonHard.setBackgroundTintList(getColorStateList(R.color.button_multiply));

        // Highlight selected difficulty
        if (difficulty == 10) {
            buttonEasy.setBackgroundTintList(getColorStateList(R.color.level_selected));
        } else if (difficulty == 50) {
            buttonMedium.setBackgroundTintList(getColorStateList(R.color.level_selected));
        } else if (difficulty == 100) {
            buttonHard.setBackgroundTintList(getColorStateList(R.color.level_selected));
        }
    }

}
