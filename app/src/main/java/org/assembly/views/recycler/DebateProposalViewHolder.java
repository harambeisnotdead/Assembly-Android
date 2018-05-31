package org.assembly.views.recycler;

import android.view.View;
import android.widget.TextView;

import org.assembly.R;

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

