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
import android.widget.LinearLayout;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class suggestionsFragment extends Fragment {

    private FragmentSuggestionsBinding binding;
    LinearLayout ll_bottom;
    private SuggestionListAdapter adapter;
    private RecyclerView rv_suggesrionlist;
    public ArrayList<RecomendedPlacesList> recommendedList;

    AutoCompleteTextView et_location;
    MapView mMapView;
    private GoogleMap googleMap;
    String[] items = new String[]{
            "Nunavut",
            //     "Québec",
            "Northwest Territories",
            //     "British Columbia",
            "Ontario",
            "Alberta",
            "Saskatchewan",
            "Manitoba",
            "Yukon",
            "Newfoundland and Labrador",
            "New Brunswick",
            "Nova Scotia",
            //     "Prince Edward Island",
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SuggestionsViewModel suggestionsViewModel =
                new ViewModelProvider(this).get(SuggestionsViewModel.class);

        binding = FragmentSuggestionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        et_location = (AutoCompleteTextView) binding.etLocation;

        ll_bottom = binding.llBottom;

        rv_suggesrionlist = binding.rvSuggestionlist;
        rv_suggesrionlist.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


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
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                // ontario latitude:50.000000 , lontitude:-85.000000
                // alberta latitude:55.000000 , lontitude:-115.000000
                // saskatchewan  latitude:55.000000 , lontitude:-106.000000
                // New Brunswick latitude:46.498390 , lontitude:-66.159668
                // nova scotia latitude:45.000000 , lontitude:-63.000000
                // Newfoundland and Labrador latitude:53.135509 , lontitude:-57.660435
                // Yukon  latitude:64.000000 , lontitude:-135.000000
                // Northwest territories  latitude: 64.8255 , lontitude:-124.8457
                // manitoba  latitude:49.895077 , lontitude:-97.138451
                // nunavut  latitude:63.748611 , lontitude:-68.519722
                googleMap.clear();
                googleMap.setMyLocationEnabled(true);

                LatLng latLng;
                if (pos == 0) {
                    latLng = new LatLng(63.748611, -68.519722);
                } else if (pos == 1) {
                    latLng = new LatLng(64.8255, -124.8457);
                } else if (pos == 2) {
                    latLng = new LatLng(50.000000, -85.000000);
                } else if (pos == 3) {
                    latLng = new LatLng(55.000000, -115.000000);
                } else if (pos == 4) {
                    latLng = new LatLng(55.000000, -106.000000);
                } else if (pos == 5) {
                    latLng = new LatLng(49.895077, -97.138451);
                } else if (pos == 6) {
                    latLng = new LatLng(64.000000, -135.000000);
                } else if (pos == 7) {
                    latLng = new LatLng(53.135509, -57.660435);
                } else if (pos == 8) {
                    latLng = new LatLng(46.498390, -66.159668);
                } else if (pos == 9) {
                    latLng = new LatLng(45.000000, -63.000000);
                } else {
                    latLng = new LatLng(0, 0);
                }

                MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                        .title("Here I am!");
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
                googleMap.addMarker(markerOptions);
            }
        });

        et_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_location.showDropDown();
            }
        });

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

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
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

    private void getRecommendationsbasedonprovince(String province) {
        Call<Root3> call = RetrofitClient.getInstance().getMyApi().getRecommendationsbasedonprovince(province);
        call.enqueue(new Callback<Root3>() {
            @Override
            public void onResponse(Call<Root3> call, Response<Root3> response) {
                Root3 suggestionlist = response.body();
                String message = suggestionlist.getMessage();
                recommendedList = suggestionlist.getRecomendedPlacesList();

                adapter = new SuggestionListAdapter(getContext(), recommendedList);
                rv_suggesrionlist.setAdapter(adapter);

                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                ll_bottom.setVisibility(View.VISIBLE);
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