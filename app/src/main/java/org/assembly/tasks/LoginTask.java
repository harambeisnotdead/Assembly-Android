package org.assembly.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.assembly.R;
import org.assembly.api.APIClient;
import org.assembly.views.activities.MainActivity;

public class LoginTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private String username;
    private String password;
    private APIClient api;

    public LoginTask(Context context, String username, String password) {
        this.context = context;
        this.username = username;
        this.password = password;
        api = new APIClient(context);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean logged = api.login(username, password);
        if (logged)
            api.getCitizen(username);

        return logged;
    }

    @Override
    protected void onPostExecute(Boolean logged) {
        super.onPostExecute(logged);
        if (logged) {
            context.startActivity(new Intent(context, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        } else
            Toast.makeText(context, context.getText(R.string.toast_login_error),
                    Toast.LENGTH_LONG).show();
    }
}
