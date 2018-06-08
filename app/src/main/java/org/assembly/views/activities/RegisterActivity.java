package org.assembly.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.tasks.RegisterTask;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {
    private Button bttnRegister;
    private Button bttnLogin;
    private TextView username;
    private TextView nationalId;
    private TextView email;
    private TextView password;

    private View.OnClickListener registerListener = v -> {
        boolean requiredFields = true;
        ArrayList<TextView> fields = new ArrayList<>(
                Arrays.asList(new TextView[]{username, nationalId, email, password}));

        for (TextView f : fields) {
            if (f.getText().toString().trim().isEmpty()) {
                f.setError(f.getHint() + " is required");
                requiredFields = false;
            }
        }

        if (!requiredFields)
            return;

        new RegisterTask(this,
                         username.getText().toString(),
                         nationalId.getText().toString(),
                         password.getText().toString(),
                         email.getText().toString()).execute();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bttnRegister = findViewById(R.id.bttn_register);
        bttnLogin = findViewById(R.id.bttn_login);
        username = findViewById(R.id.input_username);
        nationalId = findViewById(R.id.input_national_id);
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        bttnRegister.setOnClickListener(registerListener);
        bttnLogin.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
        });
    }
}
