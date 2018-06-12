package org.assembly.views.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.assembly.R;
import org.assembly.models.Proposal;

import java.util.ArrayList;

public class ProposalViewAdapter extends RecyclerView.Adapter<ProposalViewHolder> {
    private ArrayList<Proposal> proposals;
    private OnItemClickListener listener;
    private int layoutId;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProposalViewAdapter(int layoutId, ArrayList<Proposal> proposals) {
        this.layoutId = layoutId;
        this.proposals = proposals;
    }

    @Override
    public ProposalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

        switch (layoutId) {
            case R.layout.item_proposal_review:
                return new ReviewProposalViewHolder(view, listener, proposals);
            case R.layout.item_proposal_debate:
                return new DebateProposalViewHolder(view, listener);
            case R.layout.item_proposal_vote:
                return new VoteProposalViewHolder(view, listener, proposals);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ProposalViewHolder holder, int position) {
        Proposal p = proposals.get(position);
        holder.setTitle(p.getTitle());
        holder.setImage(p.getImage());
        holder.setCloseDate(p.getClose_date());
        holder.setComments(String.valueOf(p.getComment_count()));

        switch (layoutId) {
            case R.layout.item_proposal_review:
                holder.setVotes(String.valueOf(p.getReview_votes_count()));
                break;
            case R.layout.item_proposal_vote:
                holder.setVotes(String.valueOf(p.getVote_votes_count()));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return proposals.size();
    }
}
