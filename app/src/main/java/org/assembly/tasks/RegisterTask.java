package org.assembly.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.assembly.APIHandler;
import org.assembly.R;
import org.assembly.views.activities.MainActivity;

public class RegisterTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private String username;
    private String nationalId;
    private String password;
    private String email;
    private APIHandler api;

    public RegisterTask(Context context, String username, String nationalId,
                        String password, String email) {
        this.context = context;
        this.username = username;
        this.nationalId = nationalId;
        this.password = password;
        this.email = email;
        api = new APIHandler(context);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return api.register(username, nationalId, password, email);
    }

    @Override
    protected void onPostExecute(Boolean registered) {
        super.onPostExecute(registered);
        if (registered)
            new LoginTask(context, username,password).execute();
        else
            Toast.makeText(context, context.getText(R.string.toast_register_error),
                    Toast.LENGTH_LONG).show();
    }
}
