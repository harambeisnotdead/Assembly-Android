package org.assembly;

import android.view.View;
import android.widget.TextView;

public class ReviewProposalViewHolder extends ProposalViewHolder {
    public TextView votes;

    public ReviewProposalViewHolder(View itemView, ProposalViewAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        votes = itemView.findViewById(R.id.proposal_upvotes);
    }

    @Override
    public void setVotes(String votes) {
        this.votes.setText(votes);
    }
}

