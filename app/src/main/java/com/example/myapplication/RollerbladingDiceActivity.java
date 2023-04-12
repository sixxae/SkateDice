import com.example.myapplication.R;

public class RollerbladingDiceActivity extends AppCompatActivity {

    // Define the options for each die
    private String[] die1Options = {"One Foot (Left Leading)", "One Foot (Right Leading)", "Two Feet (Left Leading)", "Two Feet (Right Leading)"};
    private String[] die2Options = {"Leading: Groove positive", "Leading: Groove negative", "Leading: Soul positive", "Leading: Soul negative"};
    private String[] die3Options = {"Trailing: Groove positive", "Trailing: Groove negative", "Trailing: Soul positive", "Trailing: Soul negative"};
    private String[] die4Options = {"No-spin", "AO", "Truespin", "Half-cab", "Truespin Half-cab"};

    // Define the weights for each option
    private int[] die1Weights = {1, 1, 1, 1};
    private int[] die2Weights = {2, 2, 1, 1};
    private int[] die3Weights = {2, 2, 1, 1};
    private int[] die4Weights = {3, 2, 1, 1, 1};

    // Create a random number generator
    private Random rand = new Random();

    // Create a weighted random generator
    private WeightedRandomGenerator wrand = new WeightedRandomGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rollerblading_dice);

        // Get the buttons and text view from the layout
        Button die1Button = findViewById(R.id.die1_button);
        Button die2Button = findViewById(R.id.die2_button);
        Button die3Button = findViewById(R.id.die3_button);
        Button die4Button = findViewById(R.id.die4_button);
        Button resetButton = findViewById(R.id.reset_button);
        TextView resultTextView = findViewById(R.id.result_text_view);

        // Set the button text to the name of the die
        die1Button.setText("Die 1");
        die2Button.setText("Die 2");
        die3Button.setText("Die 3");
        die4Button.setText("Die 4");

        // Set the on click listener for each die button
        die1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Roll the die using a weighted random generator
                rollDie(die1Options, die1Weights);

                // Display the result of the roll in the text view
                resultTextView.setText("Die 1: " + wrand.next());
            }
        });

        die2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Roll the die using a weighted random generator
                rollDie(die2Options, die2Weights);

                // Display the result of the roll in the text view
                resultTextView.setText("Die 2: " + wrand.next());
            }
        });

        die3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Roll the die using a weighted random generator
                rollDie(die3Options, die3Weights);

// Display the result of the roll in the text view
                resultTextView.setText("Die 3: " + wrand.next());
            }
        });

        die4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Roll the die using a weighted random generator
                rollDie(die4Options, die4Weights);

                // Display the result of the roll in the text view
                resultTextView.setText("Die 4: " + wrand.next());
            }
        });

        // Set the on click listener for the reset button
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the text view
                resultTextView.setText("");
            }
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
        private ArrayList<String> options = new ArrayList<String>();
        private ArrayList<Integer> weights = new ArrayList<Integer>();
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