package org.assembly.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.assembly.R;
import org.assembly.api.APIClient;

public class CreateProposalTask extends AsyncTask<Void, Void, Boolean> {
    private Activity activity;
    private String title;
    private String description;
    private APIClient api;

    public CreateProposalTask(Activity activity, String title, String description) {
        this.activity = activity;
        this.title = title;
        this.description = description;
        api = new APIClient(activity.getBaseContext());

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return api.createProposal(title, description);
    }

    @Override
    protected void onPostExecute(Boolean created) {
        super.onPostExecute(created);
        if (created) {
            activity.finish();
            Toast.makeText(activity, activity.getText(R.string.toast_create_success),
                    Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(activity, activity.getText(R.string.toast_create_error),
                    Toast.LENGTH_LONG).show();
    }
}
