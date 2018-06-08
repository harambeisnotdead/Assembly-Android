package org.assembly.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.assembly.APIHandler;
import org.assembly.R;
import org.assembly.views.activities.MainActivity;

public class LoginTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private String username;
    private String password;
    private APIHandler api;

    public LoginTask(Context context, String username, String password) {
        this.context = context;
        this.username = username;
        this.password = password;
        api = new APIHandler(context);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return api.login(username, password);
    }

    @Override
    protected void onPostExecute(Boolean logged) {
        super.onPostExecute(logged);
        if (logged) {
            context.startActivity(new Intent(context, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
        } else
            Toast.makeText(context, context.getText(R.string.toast_login_error),
                    Toast.LENGTH_LONG).show();
    }
}
