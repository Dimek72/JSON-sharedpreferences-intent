package com.example.kol;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView oceny;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button akt2 = findViewById(R.id.przyca2);
        oceny = findViewById(R.id.et1);

        // Rejestracja ActivityResultLauncher
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            // Odczyt SharedPreferences po zakończeniu drugiej aktywności
                            SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                            String jsonData = sharedPreferences.getString("user_data", null);
                            if (jsonData != null) {
                                try {
                                    // Parsowanie danych JSON
                                    JSONObject jsonObject = new JSONObject(jsonData);
                                    String oc1 = jsonObject.getString("ocena1");
                                    String oc2 = jsonObject.getString("ocena2");
                                    oceny.setText(oc1 + ", " + oc2);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
        );

        // Obsługa kliknięcia przycisku
        akt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uruchomienie drugiej aktywności
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                activityResultLauncher.launch(intent);
            }
        });
    }
}
