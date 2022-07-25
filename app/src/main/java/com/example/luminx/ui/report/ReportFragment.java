package com.example.luminx.ui.report;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luminx.apimanager.RetrofitClient;
import com.example.luminx.databinding.FragmentReportBinding;
import com.example.luminx.model.PollutionReport;
import com.example.luminx.model.Root;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ReportFragment extends Fragment {

    private FragmentReportBinding binding;
    public ArrayList<PollutionReport> pollutionReports;
    //   private ProgressDialog progressDialog;

    private ReportListAdapter adapter;
    private RecyclerView rv_reportlist;

    public double latitude = 0.0d;

//    JSONParser jsonparser = new JSONParser();
//    JSONObject jobj = null;

    public double longitude = 0.0d;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReportViewModel dashboardViewModel = new ViewModelProvider(this).get(ReportViewModel.class);

        binding = FragmentReportBinding.inflate(inflater, container, false);
        //      binding = FragmentReportBinding.inflate(inflater, R.layout.fragment_report, container, false);
        View root = binding.getRoot();

        //  final TextView textView = binding.textDashboard;
        // dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ImageView new_report = binding.btnNewReport;
        new_report.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                Intent iNewReport = new Intent(getActivity(), NewReportActivity.class);
                startActivity(iNewReport);

            }
        });

        getReportList();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getReportList() {
        Call<Root> call = RetrofitClient.getInstance().getMyApi().getLightPollutionReport();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root pollutionreport = response.body();
                pollutionReports = pollutionreport.getPollutionReports();

                //    adapter = new ReportListAdapter(ReportListActivity.this, pollutionReports);
                //     adapter.setClickListener(MainActivity().this);
                //    rv_reportlist.setAdapter(adapter);


                Log.d(TAG, "onResponse: ============response" + pollutionReports);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getActivity(), "An error has occured", Toast.LENGTH_LONG).show();
                Log.e("API error" , "" + t);
            }
        });
    }
}