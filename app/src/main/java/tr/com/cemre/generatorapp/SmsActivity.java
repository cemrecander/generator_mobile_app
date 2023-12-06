package tr.com.cemre.generatorapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SmsActivity extends AppCompatActivity {
    EditText et_tel;
    EditText et_sms;
    String url;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        et_tel = findViewById(R.id.et_tel);
        et_sms = findViewById(R.id.et_sms);
        btn_send = findViewById(R.id.btn_send);

        RequestQueue queue = Volley.newRequestQueue(this);
        url = "https://v2.jokeapi.dev/joke/Programming?blacklistFlags=sexist&type=single";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("Response", response);
                    try {
                        JSONObject jsonResponse = new JSONObject(response);

                        if(jsonResponse.has("joke")) {
                            String joke = jsonResponse.getString("joke");

                            et_sms.setText(joke);
                        } else {
                            et_sms.setText("Joke data not available.");
                        }
                    } catch (JSONException e) {
                        Log.e("JSONError", "Error parsing JSON" + e.getMessage());
                        et_sms.setText("Error parsing joke");
                    }
                },
                error -> {
                    String errorMessage = "Bilinmeyen error";
                    if (error.networkResponse != null && error.networkResponse.data != null) {
                        errorMessage = new String(error.networkResponse.data);
                    }
                    et_sms.setText(errorMessage);
                    Log.e("VolleyError", errorMessage);
                });
        queue.add(stringRequest);

        btn_send.setOnClickListener(v -> {
            String number = et_tel.getText().toString();
            String message = et_sms.getText().toString();

            Intent i = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
            i.putExtra("sms_body", message);
            startActivity(i);
        });
    }
}