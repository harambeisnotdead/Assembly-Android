package org.assembly.views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.utils.SharedKeys;

import java.util.HashMap;
import java.util.Map;

public class CitizenActivity extends AppCompatActivity {
    private TextView username;
    private TextView email;
    private TextView nationalId;
    private TextView firstName;
    private TextView lastName;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);
        username = findViewById(R.id.citizen_username);
        email = findViewById(R.id.citizen_email);
        nationalId = findViewById(R.id.citizen_national_id);
        firstName = findViewById(R.id.citizen_first_name);
        lastName = findViewById(R.id.citizen_last_name);
        logout = findViewById(R.id.bttn_logout);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        logout.setOnClickListener(v -> {
            sp.edit().clear().apply();
            startActivity(new Intent(this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        });

        Map<String, String> citizenInfo = (Map<String, String>) sp.getAll();
        Map<TextView, String> fieldsMap = new HashMap<TextView, String>() {{
           put(username, citizenInfo.get(SharedKeys.CITIZEN_USERNAME));
           put(email, citizenInfo.get(SharedKeys.CITIZEN_EMAIL));
           put(nationalId, citizenInfo.get(SharedKeys.CITIZEN_NATIONAL_ID));
           put(firstName, citizenInfo.get(SharedKeys.CITIZEN_FIRST_NAME));
           put(lastName, citizenInfo.get(SharedKeys.CITIZEN_LAST_NAME));
        }};

        for (TextView field : fieldsMap.keySet())
            field.setText(field.getText() +  fieldsMap.get(field));
    }
}
