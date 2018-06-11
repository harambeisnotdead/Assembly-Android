package org.assembly.views.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.tasks.LoadImageTask;

public class ProposalViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView image;
    private TextView closeDate;

    public ProposalViewHolder(View itemView, ProposalViewAdapter.OnItemClickListener listener) {
        super(itemView);
        title = itemView.findViewById(R.id.proposal_title);
        image = itemView.findViewById(R.id.proposal_image);
        closeDate = itemView.findViewById(R.id.proposal_close);
        itemView.setOnClickListener(v -> {
            int p = getAdapterPosition();
            if (p != RecyclerView.NO_POSITION)
                listener.onItemClick(p);
        });
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setImage(String image_url) {
        new LoadImageTask(image).execute(image_url);
    }

    public void setCloseDate(String date) {
        this.closeDate.setText(date);
    }

    public void setVotes(String votes) {
    }

    public void setComments(String comments) {
    }
}

