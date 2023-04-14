package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class RollerbladingDiceActivity extends AppCompatActivity {

    // Define the options for each die
    private final String[] die1Options = {"Makio", "Fishbrain", "Torque", "Backslide", "Fastslide", "Top Mizu", "Top Soul", "Top Acid", "Top Pstar", "Mistrial", "Frontside", "Backside", "FS Royale", "BS Royale", "FS Fahrv", "BS Fahrv", "Unity", "Savannah", "SoulGrind", "Mizu", "Acid", "Pstar", "Mistrial", "X-Grind"};
    private final String[] die2Options = {"No-spin", "AO", "Truespin", "Half-cab", "Truespin Half-cab"};
    private final String[] die3Options = {"Direction: ----->", "Direction: <-----"};
    // Define the weights for each option
    private final int[] die1Weights = {7, 1, 1, 1, 1, 4, 4, 4, 4, 3, 8, 8, 6, 4, 5, 4, 4, 3, 5, 5, 3, 3, 2, 1};
    private final int[] die2Weights = {10, 3, 2, 1, 1};
    private final int[] die3Weights = {1, 1};

    // Create a random number generator
    private final Random rand = new Random();

    // Create a weighted random generator
    private final WeightedRandomGenerator wrand = new WeightedRandomGenerator();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rollerblading_dice);

        // Get the buttons and text view from the layout
        Button die1Button = findViewById(R.id.die1_button);
        Button die2Button = findViewById(R.id.die2_button);
        Button die3Button = findViewById(R.id.die3_button);
        Button resetButton = findViewById(R.id.reset_button);
        TextView resultTextView = findViewById(R.id.result_text_view);

        // Set the button text to the name of the die
        die1Button.setText("Die 1");
        die2Button.setText("Die 2");
        die3Button.setText("Die 3");

        // Set the on click listener for each die button
        die1Button.setOnClickListener(v -> {
            // Roll the die using a weighted random generator
            rollDie(die1Options, die1Weights);

            // Display the result of the roll in the text view
            resultTextView.setText("Die 1: " + wrand.next());
        });

        die2Button.setOnClickListener(v -> {
            // Roll the die using a weighted random generator
            rollDie(die2Options, die2Weights);

            // Display the result of the roll in the text view
            resultTextView.setText("Die 2: " + wrand.next());
        });

        die3Button.setOnClickListener(v -> {
            // Roll the die using a weighted random generator
            rollDie(die3Options, die3Weights);

            // Display the result of the roll in the text view
            resultTextView.setText("Die 3: " + wrand.next());
        });

        // Set the on click listener for the reset button
        resetButton.setOnClickListener(v -> {
            // Roll the die for each button with the appropriate options and weights
            rollDie(die1Options, die1Weights);
            String die1Result = wrand.next();
            rollDie(die2Options, die2Weights);
            String die2Result = wrand.next();
            rollDie(die3Options, die3Weights);
            String die3Result = wrand.next();

            // Display the result of the roll in the text view
            resultTextView.setText("Die 1: " + die1Result + "\nDie 2: " + die2Result + "\nDie 3: " + die3Result);
        });
    }

    // Method to roll a die using a weighted random generator
    private void rollDie(String[] options, int[] weights) {
        // Clear the weighted random generator
        wrand.clear();

        // Add the options with their weights to the generator
        for (int i = 0; i < options.length; i++) {
            wrand.add(options[i], weights[i]);
        }

        // Roll the die
        wrand.next();
    }

    // Custom class for a weighted random generator
    private class WeightedRandomGenerator {
        private final ArrayList<String> options = new ArrayList<>();
        private final ArrayList<Integer> weights = new ArrayList<>();
        private int totalWeight = 0;

        // Method to add an option and its weight to the generator
        public void add(String option, int weight) {
            options.add(option);
            weights.add(weight);
            totalWeight += weight;
        }

        // Method to clear the generator
        public void clear() {
            options.clear();
            weights.clear();
            totalWeight = 0;
        }

        // Method to generate a weighted random number
        public String next() {
            int random = rand.nextInt(totalWeight);
            int weightSum = 0;

            for (int i = 0; i < options.size(); i++) {
                weightSum += weights.get(i);

                if (random < weightSum) {
                    return options.get(i);
                }
            }

            return null;
        }
    }
}