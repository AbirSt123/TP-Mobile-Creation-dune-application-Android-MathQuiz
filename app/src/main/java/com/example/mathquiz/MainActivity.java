package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Déclaration des variables pour les vues
    private TextView number1TextView;
    private TextView number2TextView;
    private TextView resultTextView;
    private Button buttonAdd;
    private Button buttonSubtract;
    private Button buttonMultiply;
    private Button buttonGenerate;

    // Variables pour stocker les nombres générés
    private int number1;
    private int number2;

    // Générateur de nombres aléatoires
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation du générateur aléatoire
        random = new Random();

        // Récupération des vues depuis le XML
        number1TextView = findViewById(R.id.number1TextView);
        number2TextView = findViewById(R.id.number2TextView);
        resultTextView = findViewById(R.id.resultTextView);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonGenerate = findViewById(R.id.buttonGenerate);

        // Génération des premiers nombres au démarrage
        generateNumbers();

        // Programmation du bouton Addition
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = number1 + number2;
                resultTextView.setText(String.valueOf(result));
            }
        });

        // Programmation du bouton Soustraction
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = number1 - number2;
                resultTextView.setText(String.valueOf(result));
            }
        });

        // Programmation du bouton Multiplication
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = number1 * number2;
                resultTextView.setText(String.valueOf(result));
            }
        });

        // Programmation du bouton Générer
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNumbers();
                resultTextView.setText("?");
            }
        });
    }

    /**
     * Méthode pour générer deux nombres aléatoires entre 111 et 999
     */
    private void generateNumbers() {
        // Génération de nombres entre 111 et 999
        number1 = random.nextInt(889) + 111; // nextInt(889) donne 0-888, +111 donne 111-999
        number2 = random.nextInt(889) + 111;

        // Affichage des nombres dans les TextView
        number1TextView.setText(String.valueOf(number1));
        number2TextView.setText(String.valueOf(number2));
    }
}
