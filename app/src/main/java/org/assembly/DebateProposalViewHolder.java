package org.assembly;

import android.view.View;
import android.widget.TextView;

public class DebateProposalViewHolder extends ProposalViewHolder {
    public TextView comments;

    public DebateProposalViewHolder(View itemView, ProposalViewAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        comments = itemView.findViewById(R.id.proposal_comments);
    }

    @Override
    public void setComments(String comments) {
        this.comments.setText(comments);
    }
}

