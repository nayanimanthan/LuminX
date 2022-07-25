package com.example.luminx.ui.report;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.luminx.MainActivity;
import com.example.luminx.R;
import com.example.luminx.databinding.FragmentReportBinding;

public class ReportFragment extends Fragment {

    private FragmentReportBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReportViewModel dashboardViewModel = new ViewModelProvider(this).get(ReportViewModel.class);

        binding = FragmentReportBinding.inflate(inflater, container, false);
  //      binding = FragmentReportBinding.inflate(inflater, R.layout.fragment_report, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button new_report = binding.btnNewReport;
        new_report.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                Intent iNewReport = new Intent(getActivity(), NewReportActivity.class);
                startActivity(iNewReport);

            }
        });

        Button repoty_list = binding.btnReportList;
        repoty_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent iReportList = new Intent(getActivity(), ReportListActivity.class);
                startActivity(iReportList);

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}