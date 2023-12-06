package tr.com.cemre.generatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConverterActivity extends AppCompatActivity {

    String[] decArr = {"Seçiniz", "İkilik", "Sekizlik", "On Altılık"};
    String[] mbArr = {"Seçiniz", "KiloByte", "Byte", "KibiByte", "Bit"};
    EditText et_dec, et_mb, et_cels;
    TextView tv_dec_res, tv_mb_res, tv_cels_res;
    RadioGroup radio_group;
    Spinner spinner_dec, spinner_mb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        et_dec = findViewById(R.id.et_dec);
        et_mb = findViewById(R.id.et_mb);
        et_cels = findViewById(R.id.et_cels);

        tv_dec_res = findViewById(R.id.tv_dec_res);
        tv_mb_res = findViewById(R.id.tv_mb_res);
        tv_cels_res = findViewById(R.id.tv_cels_res);

        radio_group = findViewById(R.id.radio_group);

        spinner_dec = findViewById(R.id.spinner_dec);
        spinner_mb = findViewById(R.id.spinner_mb);

        ArrayAdapter adapterDec = new ArrayAdapter(this, android.R.layout.simple_spinner_item, decArr);
        adapterDec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dec.setAdapter(adapterDec);

        spinner_dec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String decStr = et_dec.getText().toString().trim();
                if(!decStr.isEmpty()) {
                    try {
                        Integer dec = Integer.parseInt(decStr);
                        switch (position) {
                            case 0:
                                tv_dec_res.setText("Sonuç");
                                break;
                            case 1:
                                int binaryNum[] = new int[40];
                                int index = 0;
                                String binary = "";
                                while (dec > 0) {
                                    binaryNum[index++] = dec % 2;
                                    dec = dec / 2;
                                }
                                for (int i = index - 1; i >= 0; i--) {
                                    binary += (binaryNum[i]);
                                }
                                tv_dec_res.setText("Sonuç: " + binary);
                                break;
                            case 2:
                                int rem;
                                String octal = "";
                                char octalchars[] = {'0', '1', '2', '3', '4', '5', '6', '7'};
                                while (dec > 0) {
                                    rem = dec % 8;
                                    octal = octalchars[rem] + octal;
                                    dec = dec / 8;
                                }
                                tv_dec_res.setText("Sonuç: " + octal);
                                break;
                            case 3:
                                int remainder;
                                String hex = "";
                                char hexchars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                                while (dec > 0) {
                                    remainder = dec % 16;
                                    hex = hexchars[remainder] + hex;
                                    dec = dec / 16;
                                }
                                tv_dec_res.setText("Sonuç: " + hex);
                                break;
                        }
                    } catch (NumberFormatException e) {
                        tv_dec_res.setText("Geçersiz giriş");
                        e.printStackTrace();
                        }
                }
                else {
                    tv_dec_res.setText("Bir değer giriniz.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter adapterMb = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mbArr);
        adapterMb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mb.setAdapter(adapterMb);

        spinner_mb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String mbStr = et_mb.getText().toString().trim();
                if (!mbStr.isEmpty()) {
                    try {
                        double mb = Double.parseDouble(mbStr);
                        switch (position) {
                            case 0:
                                tv_mb_res.setText("Sonuç");
                                break;
                            case 1:
                                double kb = mb * 1000;
                                tv_mb_res.setText("Sonuç: " + (int)kb + " kilobyte");
                                break;
                            case 2:
                                double _byte = mb * 1000000;
                                tv_mb_res.setText("Sonuç: " + (int)_byte  + " byte");
                                break;
                            case 3:
                                double kib = mb * 1024;
                                tv_mb_res.setText("Sonuç: " + (int)kib + " kibibyte");
                                break;
                            case 4:
                                double bit = mb * 8000000;
                                tv_mb_res.setText("Sonuç: " + (int)bit + " bit");
                                break;
                        }
                    } catch (NumberFormatException e) {
                        tv_mb_res.setText("Geçersiz giriş.");
                        e.printStackTrace();
                    }
                } else {
                    tv_mb_res.setText("Bir değer giriniz.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        radio_group.setOnCheckedChangeListener((group, checkedId) -> {
            double celsius;
            try {
                celsius = Double.parseDouble(et_cels.getText().toString());
            } catch (NumberFormatException e) {
                celsius = 0.0;
                e.printStackTrace();
            }
            if (checkedId == R.id.radioFahrenheit) {
                double fahrenheit = (celsius * 9/5) + 32;
                tv_cels_res.setText("Sonuç: " + fahrenheit + " F");
            } else if (checkedId == R.id.radioKelvin) {
                double kelvin = celsius + 273.15;
                tv_cels_res.setText("Sonuç: " + kelvin + " K");
            } else {
                tv_cels_res.setText("Bir değer seçiniz.");
            }
        });
    }
}