package org.assembly.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.ImageView;

import org.assembly.APIHandler;
import org.assembly.R;
import org.assembly.models.Proposal;

import java.util.List;

public class RetrieveProposalTask extends AsyncTask<String, Void, Proposal> {
    APIHandler api;
    List<Proposal> proposals;
    ImageView image;
    TextView title, close_date, comments, votes;

    public RetrieveProposalTask(APIHandler api, List<Proposal> proposals, ImageView image,
                                TextView title, TextView close_date, TextView comments, TextView votes) {
        this.api = api;
        this.proposals = proposals;
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
        proposals.add(proposal);
        if (proposal.getImage() != null && !"".equals(proposal.getImage()))
            new LoadImageTask(image).execute(proposal.getImage());
        title.setText(proposal.getTitle());
        close_date.setText(proposal.getClose_date());
        comments.setText(String.valueOf(proposal.getComment_count()));
        if (votes != null)
            votes.setText(String.valueOf(proposal.getVote_votes_count()));
    }
};

