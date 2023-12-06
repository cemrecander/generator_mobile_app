package tr.com.cemre.generatorapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_converter;
    Button btn_random;
    Button btn_sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_converter = findViewById(R.id.btn_converter);
        btn_random = findViewById(R.id.btn_random);
        btn_sms = findViewById(R.id.btn_sms);

        btn_converter.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ConverterActivity.class);
            startActivityWithTransition(i);
        });

        btn_random.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, RandomActivity.class);
            startActivityWithTransition(i);
        });

        btn_sms.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, SmsActivity.class);
            startActivityWithTransition(i);
        });

    }
    private void startActivityWithTransition(Intent i) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            Fade fade = new Fade();

            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(explode);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(i, options.toBundle());
        } else {
            startActivity(i);
        }
    }
}