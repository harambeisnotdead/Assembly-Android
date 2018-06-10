package org.assembly.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.assembly.R;
import org.assembly.tasks.RegisterTask;
import org.assembly.utils.InputUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {
    private Button bttnRegister;
    private Button bttnLogin;
    private EditText username;
    private EditText nationalId;
    private EditText email;
    private EditText password;

    private View.OnClickListener registerListener = v -> {
        ArrayList<EditText> fields = new ArrayList<>(
                Arrays.asList(new EditText[]{username, nationalId, email, password}));

        if (InputUtils.checkRequiredFields(fields))
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
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        });
    }
}
