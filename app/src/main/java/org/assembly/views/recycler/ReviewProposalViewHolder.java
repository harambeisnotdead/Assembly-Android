package org.assembly.views.recycler;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.assembly.R;

public class ReviewProposalViewHolder extends ProposalViewHolder {
    public TextView votes;
    public ImageButton upvote;

    public ReviewProposalViewHolder(View itemView, ProposalViewAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        votes = itemView.findViewById(R.id.proposal_votes);
        upvote = itemView.findViewById(R.id.bttn_upvote);
        upvote.setOnClickListener(upvoteListener);
    }

    private View.OnClickListener upvoteListener = v -> {
        v.setSelected(!v.isSelected());
    };

    @Override
    public void setVotes(String votes) {
        this.votes.setText(votes);
    }
}

