package com.example.luminx.ui.learn;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luminx.R;

import androidx.appcompat.app.AppCompatActivity;

public class LearnActivity extends AppCompatActivity {

    private TextView tv_title,tv_discription;
    private String title;
    private String discription;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        getSupportActionBar().hide();

        title=getIntent().getExtras().getString("title");
        discription=getIntent().getExtras().getString("discription");

        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tv_title=(TextView)findViewById(R.id.tv_title);
        tv_discription=(TextView)findViewById(R.id.tv_discription);

        tv_title.setText(title);
        tv_discription.setText(discription);
    }

}