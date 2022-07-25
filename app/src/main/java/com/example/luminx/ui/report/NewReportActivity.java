package com.example.luminx.ui.report;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luminx.R;
import com.example.luminx.ui.ColorPickView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class NewReportActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    ImageView iv_clicked;
    ImageView iv_back;

    Boolean img_cliked = false;
    Boolean b_name = false;
    Boolean b_email = false;
    Boolean b_img = false;
    Boolean b_location = false;
    Boolean b_door = false;
    Boolean b_bright = false;

    FrameLayout fl_click_photo;
    FrameLayout fl_indoor;
    FrameLayout fl_outdoor;
    FrameLayout fl_n_shield;
    FrameLayout fl_p_shield;
    FrameLayout fl_f_shield;
    FrameLayout fl_pin;
    FrameLayout fl_street;
    FrameLayout fl_gps;
    FrameLayout fl_dim;
    FrameLayout fl_normal;
    FrameLayout fl_bright;
    FrameLayout fl_submit;

    LinearLayout iv_camera;
    LinearLayout ll_location;

    EditText et_name;
    EditText et_email;
    EditText et_location;

    protected LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report);

        getSupportActionBar().hide();

        this.iv_clicked = (ImageView) this.findViewById(R.id.iv_clicked);
        this.iv_camera = (LinearLayout) this.findViewById(R.id.iv_camera);

        //  new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        this.et_name = findViewById(R.id.et_name);
        String name = et_name.getText().toString();
        et_name.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    b_name = true;
                }

                //    Toast.makeText(NewReportActivity.this, "editable is : " + s, Toast.LENGTH_SHORT).show();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        this.et_email = findViewById(R.id.et_email);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        //  String email = et_email.getText().toString();
        et_email.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (isValidEmail(s.toString())) {
                    b_email = true;
                } else {
                    b_email = false;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        this.fl_click_photo = findViewById(R.id.fl_click_photo);
        fl_click_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewReportActivity.this, "Open Camera", Toast.LENGTH_SHORT).show();

                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        this.ll_location = findViewById(R.id.ll_location);
        this.et_location = findViewById(R.id.et_location);
        this.fl_pin = findViewById(R.id.fl_pin);
        fl_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_pin.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_street.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_gps.setBackgroundResource(R.drawable.selector_op_button_unselected);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ll_location.setVisibility(View.GONE);
                        et_location.setVisibility(View.VISIBLE);

                        et_location.setHint("Enter Pincode");
                    }
                }, 300);

            }
        });

        this.fl_street = findViewById(R.id.fl_street);
        fl_street.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_street.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_pin.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_gps.setBackgroundResource(R.drawable.selector_op_button_unselected);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ll_location.setVisibility(View.GONE);
                        et_location.setVisibility(View.VISIBLE);

                        et_location.setHint("Enter Street Name");
                    }
                }, 300);


            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1,
                1000,
                new MyLocationListener()
        );

        this.fl_gps = findViewById(R.id.fl_gps);
        fl_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_gps.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_pin.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_street.setBackgroundResource(R.drawable.selector_op_button_unselected);

                showCurrentLocation();
            }
        });


        this.fl_indoor = findViewById(R.id.fl_indoor);
        this.fl_outdoor = findViewById(R.id.fl_outdoor);
        this.fl_n_shield = findViewById(R.id.fl_n_shield);
        this.fl_p_shield = findViewById(R.id.fl_p_shield);
        this.fl_f_shield = findViewById(R.id.fl_f_shield);

        fl_indoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_indoor.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_outdoor.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_n_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_p_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_f_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);

                b_door = true;
            }
        });


        fl_outdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_outdoor.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_indoor.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_n_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_p_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_f_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);

                b_door = true;
            }
        });

        fl_n_shield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_n_shield.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_indoor.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_outdoor.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_p_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_f_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);

                b_door = true;
            }
        });

        fl_p_shield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_p_shield.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_indoor.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_outdoor.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_n_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_f_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);

                b_door = true;
            }
        });

        fl_f_shield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_f_shield.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_indoor.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_outdoor.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_n_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_p_shield.setBackgroundResource(R.drawable.selector_op_button_unselected);

                b_door = true;
            }
        });

        this.fl_dim = findViewById(R.id.fl_dim);
        fl_dim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_dim.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_normal.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_bright.setBackgroundResource(R.drawable.selector_op_button_unselected);

                b_bright = true;
            }
        });

        this.fl_normal = findViewById(R.id.fl_normal);
        fl_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_normal.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_dim.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_bright.setBackgroundResource(R.drawable.selector_op_button_unselected);

                b_bright = true;
            }
        });

        this.fl_bright = findViewById(R.id.fl_bright);
        fl_bright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl_bright.setBackgroundResource(R.drawable.selector_op_button_selected);
                fl_dim.setBackgroundResource(R.drawable.selector_op_button_unselected);
                fl_normal.setBackgroundResource(R.drawable.selector_op_button_unselected);

                b_bright = true;
            }
        });


        this.fl_submit = findViewById(R.id.fl_submit);
        fl_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!b_name) {
                    Toast.makeText(NewReportActivity.this, "Please enter valid Name", Toast.LENGTH_SHORT).show();
                } else if (!b_email) {
                    Toast.makeText(NewReportActivity.this, "Please enter valid Email Address", Toast.LENGTH_SHORT).show();
                } else if (!b_img) {
                    Toast.makeText(NewReportActivity.this, "Please capture Image", Toast.LENGTH_SHORT).show();
                } else if (!b_door) {
                    Toast.makeText(NewReportActivity.this, "Please select valid Light Source", Toast.LENGTH_SHORT).show();
                } else if (!b_bright) {
                    Toast.makeText(NewReportActivity.this, "Please select valid Light Source", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewReportActivity.this, "New Report submitted", Toast.LENGTH_SHORT).show();

                    finish();
/*
                    AndroidNetworking.post("https://fierce-cove-29863.herokuapp.com/createAnUser")
                            .addBodyParameter("firstname", "Amit")
                            .addBodyParameter("lastname", "Shekhar")
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // do anything with response
                                }
                                @Override
                                public void onError(ANError error) {
                                    // handle error
                                }
                            });
*/

                }
            }
        });

        this.iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ColorPickView colorPickView = findViewById(R.id.colorpickview);
        colorPickView.setBarListener(new ColorPickView.OnColorBarListener() {
            @Override
            public void moveBar(int color) {

                /*TextView tv = findViewById(R.id.tv_color);
                tv.setTextColor(color);*/

            }
        });
    }

    protected void showCurrentLocation() {

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            String message = String.format(
                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(NewReportActivity.this, message,
                    Toast.LENGTH_LONG).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                ActivityCompat.requestPermissions(NewReportActivity.this, new String[] {Manifest.permission.CAMERA}, requestCode);
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }


        // Location
        /*if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission given write code here
            } else {
                // Permission Denied
                Toast.makeText(this, "Please provide Location Permission ", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img_cliked = true;
            iv_clicked.setImageBitmap(photo);

            if (img_cliked){
                iv_camera.setVisibility(View.GONE);

                b_img = true;
            }

        }
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

class MyLocationListener implements LocationListener {

    public void onLocationChanged(Location location) {
        String message = String.format(
                "New Location \n Longitude: %1$s \n Latitude: %2$s",
                location.getLongitude(), location.getLatitude()
        );
     //   Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void onStatusChanged(String s, int i, Bundle b) {
   //     Toast.makeText(LbsGeocodingActivity.this, "Provider status changed",Toast.LENGTH_LONG).show();
    }

    public void onProviderDisabled(String s) {
     //   Toast.makeText(LbsGeocodingActivity.this,"Provider disabled by the user. GPS turned off", Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String s) {
     //   Toast.makeText(LbsGeocodingActivity.this, "Provider enabled by the user. GPS turned on", Toast.LENGTH_LONG).show();
    }

}


