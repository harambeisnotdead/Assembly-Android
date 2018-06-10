package org.assembly.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.assembly.R;
import org.assembly.api.APIClient;
import org.assembly.tasks.PopulateTask;
import org.assembly.views.recycler.ProposalViewAdapter;

public class ReviewFragment extends Fragment {
    private RecyclerView rv;
    private RecyclerView.LayoutManager rvManager;
    private ProposalViewAdapter rvAdapter;
    private APIClient api;
    private Context context;
    private SwipeRefreshLayout refreshLayout;

    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        new PopulateTask(rv, rvAdapter, api,
                R.layout.item_proposal_review, context, refreshLayout).execute();
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        context = getContext();
        api = new APIClient(context);
        rv = view.findViewById(R.id.recycler_view);
        rvManager = new LinearLayoutManager(context);
        rv.setLayoutManager(rvManager);
        refreshLayout = view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(refreshListener);
        new PopulateTask(rv, rvAdapter, api,
                R.layout.item_proposal_review, context, null).execute();
        return view;
    }
}
