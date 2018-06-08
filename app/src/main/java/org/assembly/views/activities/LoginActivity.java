package org.assembly.views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.tasks.LoginTask;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private Button bttnLogin;
    private Button bttnRegister;
    private TextView username;
    private TextView password;

    private View.OnClickListener loginListener = v -> {
        boolean requiredFields = true;
        ArrayList<TextView> fields = new ArrayList<>(
                Arrays.asList(new TextView[]{username, password}));

        for (TextView f : fields) {
            if (f.getText().toString().trim().equals("")) {
                f.setError(f.getHint() + " is required");
                requiredFields = false;
            }
        }

        if (!requiredFields)
            return;

        new LoginTask(this, username.getText().toString(), password.getText().toString()).execute();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sp.getString("token", "").isEmpty())
            startActivity(new Intent(getBaseContext(), MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
        else
            findViewById(R.id.loading_animation).setVisibility(View.GONE);

        bttnLogin = findViewById(R.id.bttn_login);
        bttnRegister = findViewById(R.id.bttn_register);
        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
        bttnLogin.setOnClickListener(loginListener);
        bttnRegister.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), RegisterActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
        });
    }
}
