package org.assembly.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.assembly.R;
import org.assembly.api.APIClient;

public class RegisterTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private String username;
    private String nationalId;
    private String password;
    private String email;
    private APIClient api;

    public RegisterTask(Context context, String username, String nationalId,
                        String password, String email) {
        this.context = context;
        this.username = username;
        this.nationalId = nationalId;
        this.password = password;
        this.email = email;
        api = new APIClient(context);
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
