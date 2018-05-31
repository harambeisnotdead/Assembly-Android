package org.assembly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReviewFragment extends Fragment {
    private RecyclerView rv;
    private RecyclerView.LayoutManager rvManager;
    private ProposalViewAdapter rvAdapter;
    private APIHandler api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        api = new APIHandler(getContext());
        rv = view.findViewById(R.id.recycler_view);
        rvManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(rvManager);
        new PopulateTask(rv, rvAdapter, api, R.layout.item_proposal_review).execute();
        return view;
    }
}
