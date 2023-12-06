package tr.com.cemre.generatorapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {
    private int numberOfBars = 0;
    private int min = 0;
    private int max = 100;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        initializeViews();
    }
    private void initializeViews() {
        layout = findViewById(R.id.layout);
        EditText numberOfBarsInput = findViewById(R.id.et_num_bars);
        EditText minInput = findViewById(R.id.et_max);
        EditText maxInput = findViewById(R.id.et_min);

        numberOfBarsInput.addTextChangedListener(createTextWatcher(numberOfBarsInput, () -> {
            numberOfBars = parseInputToInt(numberOfBarsInput);
        }));

        minInput.addTextChangedListener(createTextWatcher(numberOfBarsInput, () -> {
            min = parseInputToInt(minInput);
        }));

        maxInput.addTextChangedListener(createTextWatcher(numberOfBarsInput, () -> {
            max = parseInputToInt(maxInput);
        }));
    }
    private  TextWatcher createTextWatcher(EditText editText, Runnable action) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    action.run();
                    updateProgressBars();
                } catch (NumberFormatException e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
    private int parseInputToInt(EditText editText) {
        return  Integer.parseInt(editText.getText().toString());
    }
    private void updateProgressBars() {
        if(min > max) {
            Toast.makeText(this, "Min değeri max değerinden büyük olamaz!", Toast.LENGTH_SHORT).show();
        } else if (min == max) {
            Toast.makeText(this, "Min ve max değerleri birbirine eşit olamaz!", Toast.LENGTH_SHORT).show();
        }
        layout.removeAllViews();

        Random random = new Random();

        for (int i = 0; i < numberOfBars; i++) {
            int randomNum = random.nextInt(max-min+1) + min;

            LinearLayout progressBarLayout = new LinearLayout(this);
            progressBarLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

            ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

            int progress = (int) (100 * (float) (randomNum - min) / (max - min));
            progressBar.setProgress(progress);

            TextView minTextView = new TextView(this);
            minTextView.setText(String.valueOf(min));

            TextView maxTextView = new TextView(this);
            minTextView.setText(String.valueOf(max));

            LinearLayout.LayoutParams minTextViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            minTextViewParams.setMargins(0,0,16,0);
            minTextView.setLayoutParams(minTextViewParams);

            LinearLayout.LayoutParams maxTextViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            maxTextViewParams.setMargins(16,0,0,0);
            minTextView.setLayoutParams(maxTextViewParams);

            progressBarLayout.addView(minTextView);
            progressBarLayout.addView(progressBar);
            progressBarLayout.addView(maxTextView);

            TextView randomTextView = new TextView(this);
            randomTextView.setText(randomNum + " = % " + progress);

            LinearLayout.LayoutParams randomParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            randomParams.setMargins(450, 0, 0, 16);

            LinearLayout.LayoutParams progressBarLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            progressBarLayoutParams.setMargins(0, 25, 0, 100);

            layout.addView(randomTextView, randomParams);
            layout.addView(progressBarLayout, progressBarLayoutParams);
        }
    }
}
