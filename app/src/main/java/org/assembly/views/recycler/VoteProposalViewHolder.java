package org.assembly.views.recycler;

import android.content.Context;
import android.media.Image;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.models.Proposal;
import org.assembly.tasks.VoteTask;
import org.assembly.utils.Endpoints;
import org.assembly.utils.SharedKeys;

import java.util.ArrayList;

public class VoteProposalViewHolder extends ProposalViewHolder {
    public TextView votes;
    public TextView comments;
    private ImageButton upvote;
    private ImageButton downvote;
    private Context context;
    private ArrayList<Proposal> proposals;

    public VoteProposalViewHolder(View itemView, ProposalViewAdapter.OnItemClickListener listener,
                                  ArrayList<Proposal> proposals) {
        super(itemView, listener);
        votes = itemView.findViewById(R.id.proposal_votes);
        comments = itemView.findViewById(R.id.proposal_comments);
        context = itemView.getContext();
        upvote = itemView.findViewById(R.id.bttn_upvote);
        upvote.setOnClickListener(upvoteListener);
        downvote = itemView.findViewById(R.id.bttn_downvote);
        downvote.setOnClickListener(downvoteListener);
        this.proposals = proposals;
    }

    private View.OnClickListener upvoteListener = v -> {
        int proposal = proposals.get(getAdapterPosition()).getId();
        vote(proposal, true);
    };

    private View.OnClickListener downvoteListener = v -> {
        int proposal = proposals.get(getAdapterPosition()).getId();
        vote(proposal, false);
    };

    private void vote(int proposal, boolean option) {
        new VoteTask(context, 0, Endpoints.Proposals.VOTE, proposal, option).execute();
    }

    @Override
    public void setVotes(String votes) {
        this.votes.setText(votes);
    }

    @Override
    public void setComments(String comments) {
        this.comments.setText(comments);
    }
}

