package org.assembly.views.recycler;

import android.view.View;
import android.widget.TextView;

import org.assembly.R;

public class ReviewProposalViewHolder extends ProposalViewHolder {
    public TextView votes;

    public ReviewProposalViewHolder(View itemView, ProposalViewAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        votes = itemView.findViewById(R.id.proposal_votes);
    }

    @Override
    public void setVotes(String votes) {
        this.votes.setText(votes);
    }
}

