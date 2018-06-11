package org.assembly.views.recycler;

import android.view.View;
import android.widget.TextView;

import org.assembly.R;

public class VoteProposalViewHolder extends ProposalViewHolder {
    public TextView votes;
    public TextView comments;

    public VoteProposalViewHolder(View itemView, ProposalViewAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        votes = itemView.findViewById(R.id.proposal_votes);
        comments = itemView.findViewById(R.id.proposal_comments);
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

