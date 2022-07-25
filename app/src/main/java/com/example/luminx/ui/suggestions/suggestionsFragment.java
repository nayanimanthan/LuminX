package com.example.luminx.ui.suggestions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.luminx.MainActivity;
import com.example.luminx.R;
import com.example.luminx.apimanager.RetrofitClient;
import com.example.luminx.databinding.FragmentSuggestionsBinding;
import com.example.luminx.model.RecomendedPlacesList;
import com.example.luminx.model.Root3;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class suggestionsFragment extends Fragment {

    private FragmentSuggestionsBinding binding;

    AutoCompleteTextView et_location;
    MapView mMapView;
    private GoogleMap googleMap;
    //    Spinner spin;
    String[] items = new String[]{
            "Nunavut",
            "Québec",
            "Northwest Territories",
            "British Columbia",
            "Ontario",
            "Alberta",
            "Saskatchewan",
            "Manitoba",
            "Yukon",
            "Newfoundland and Labrador",
            "New Brunswick",
            "Nova Scotia",
            "Prince Edward Island",
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SuggestionsViewModel suggestionsViewModel =
                new ViewModelProvider(this).get(SuggestionsViewModel.class);

        binding = FragmentSuggestionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        spin = (Spinner) binding.spinner1;
        et_location = (AutoCompleteTextView) binding.etLocation;

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items);
        et_location.setAdapter(arrayAdapter);
        et_location.setInputType(0);
        et_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                Log.v("item", (String) parent.getItemAtPosition(pos));
                Toast.makeText(getActivity(), "province selected", Toast.LENGTH_LONG).show();
                getRecommendationsbasedonprovince((String) parent.getItemAtPosition(pos));
                Toast.makeText(getContext(), " get place", Toast.LENGTH_SHORT).show();
            }
        });

        et_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_location.showDropDown();
            }
        });
//        initspinnerfooter();

        final TextView textView = binding.textNotifications;
        suggestionsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        setupmap(savedInstanceState);
        return root;
    }

    private void setupmap(Bundle savedInstanceState) {
        mMapView = (MapView) binding.mapView;
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        MapsInitializer.initialize(getActivity().getApplicationContext());

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                googleMap.setMyLocationEnabled(true);

                LatLng latLng = new LatLng(56.13,106.34);
                MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                        .title("Here I am!");
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
                googleMap.addMarker(markerOptions);
            }
        });
    }

//    private void initspinnerfooter() {
//
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner, items);
//        spin.setAdapter(adapter);
//        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.v("item", (String) parent.getItemAtPosition(position));
////                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
//
//                Toast.makeText(getActivity(), "province selected", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });
//    }

    private void getRecommendationsbasedonprovince(String province) {
        Call<Root3> call = RetrofitClient.getInstance().getMyApi().getRecommendationsbasedonprovince(province);
        call.enqueue(new Callback<Root3>() {
            @Override
            public void onResponse(Call<Root3> call, Response<Root3> response) {
                Root3 root = response.body();
                String message = root.getMessage();
                ArrayList<RecomendedPlacesList> recomendedPlacesList=root.getRecomendedPlacesList();

                Toast.makeText(getContext(), " api called ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Root3> call, Throwable t) {
                Log.e("Error in API calling ", "" + t);
                Toast.makeText(getActivity(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}