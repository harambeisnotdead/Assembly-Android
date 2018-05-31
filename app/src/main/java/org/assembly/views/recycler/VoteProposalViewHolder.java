package org.assembly.views.recycler;

import android.view.View;
import android.widget.TextView;

import org.assembly.R;

public class VoteProposalViewHolder extends ProposalViewHolder {
    public TextView upvotes;

    public VoteProposalViewHolder(View itemView, ProposalViewAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        upvotes = itemView.findViewById(R.id.proposal_upvotes);
    }

    @Override
    public void setUpvotes(String upvotes) {
        this.upvotes.setText(upvotes);
    }
}

