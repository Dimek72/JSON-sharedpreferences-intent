package com.example.kol;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity2 extends AppCompatActivity {
    EditText ocena1, ocena2;
    Button ustaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ocena1 = findViewById(R.id.ocena1);
        ocena2 = findViewById(R.id.ocena2);
        ustaw = findViewById(R.id.ustaw);

        ustaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oc1 = ocena1.getText().toString();
                String oc2 = ocena2.getText().toString();
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ocena1", oc1);
                    jsonObject.put("ocena2", oc2);

                    SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_data", jsonObject.toString());
                    editor.apply();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
