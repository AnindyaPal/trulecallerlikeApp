package com.example.welcome.truecallerlikeapp.views.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.welcome.truecallerlikeapp.R;

public class OverlayDialog extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay_dialog);
        if (getSupportActionBar()!=null)
            getSupportActionBar().hide();
        if (getIntent().getExtras()!=null){
            ((TextView)findViewById(R.id.tvPhoneNo)).setText(getIntent().getStringExtra("number"));
            ((TextView)findViewById(R.id.tvDate)).setText(getIntent().getStringExtra("date"));

        }
    }

    public void finishActivity(View view) {
        finish();
    }
}
