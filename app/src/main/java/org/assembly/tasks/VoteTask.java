package org.assembly.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.assembly.R;
import org.assembly.api.APIClient;
import org.assembly.utils.Endpoints;

public class VoteTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private int user;
    private String phase;
    private int proposal;
    private boolean option;
    private APIClient api;

    public VoteTask(Context context, int user, String phase, int proposal, boolean option) {
        this.context = context;
        this.user = user;
        this.phase = phase;
        this.proposal = proposal;
        this.option = option;
        api = new APIClient(context);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        switch (phase) {
            case Endpoints.Proposals.REVIEW:
                return api.reviewVote(user, proposal);
            case Endpoints.Proposals.VOTE:
                return api.votingVote(option, proposal);
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean voted) {
        super.onPostExecute(voted);
        if (voted)
            Toast.makeText(context, context.getText(R.string.toast_vote_success),
                    Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, context.getText(R.string.toast_vote_error),
                    Toast.LENGTH_LONG).show();
    }
}
