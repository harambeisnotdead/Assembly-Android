package org.assembly.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import org.assembly.api.APIClient;
import org.assembly.models.Proposal;


public class RetrieveProposalTask extends AsyncTask<String, Void, Proposal> {
    private APIClient api;
    private ImageView image;
    private TextView title, close_date, comments, votes;

    public RetrieveProposalTask(Context context, ImageView image, TextView title,
                                TextView close_date, TextView comments, TextView votes) {
        this.api = new APIClient(context);
        this.image = image;
        this.title = title;
        this.close_date = close_date;
        this.comments = comments;
        this.votes = votes;
    }

    @Override
    protected Proposal doInBackground(String... endpoint) {
        return api.getLastProposal(endpoint[0]);
    }

    @Override
    protected void onPostExecute(Proposal proposal) {
        super.onPostExecute(proposal);
        if (proposal.getImage() != null && !proposal.getImage().isEmpty())
            new LoadImageTask(image).execute(proposal.getImage());
        title.setText(proposal.getTitle());
        close_date.setText(proposal.getClose_date());
        comments.setText(String.valueOf(proposal.getComment_count()));
        if (votes != null)
            votes.setText(String.valueOf(proposal.getVote_votes_count()));
    }
};

