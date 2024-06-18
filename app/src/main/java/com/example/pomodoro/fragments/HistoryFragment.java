package com.example.pomodoro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pomodoro.R;
import com.example.pomodoro.Session;
import com.example.pomodoro.viewModels.SessionViewModel;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private ListView historyListView;
    private ArrayAdapter<String> historyAdapter;
    private ArrayList<String> historyList;
    private SessionViewModel sessionViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        historyListView = view.findViewById(R.id.history_list_view);
        historyList = new ArrayList<>();
        historyAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, historyList);
        historyListView.setAdapter(historyAdapter);

        sessionViewModel = new ViewModelProvider(this).get(SessionViewModel.class);
        sessionViewModel.getAllSessions().observe(getViewLifecycleOwner(), sessions -> {
            historyList.clear();
            for (Session session : sessions) {
                historyList.add("Session: " + session.getDescription());
            }
            historyAdapter.notifyDataSetChanged();
        });

        return view;
    }
}
