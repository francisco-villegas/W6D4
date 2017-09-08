package com.example.pancho.w6d4;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pie_chart)
    CustomPie pieChart;
    @BindView(R.id.ctvText)
    CustomViewTextView ctvText;
    @BindView(R.id.btnCustom)
    CustomViewButton btnCustom;
    @BindView(R.id.tagLayout)
    TagLayout tagLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        float[] datapoints = {1, 2, 3, 4};
        int[] colors = {
                ContextCompat.getColor(this, R.color.bootstrap_brand_primary),
                ContextCompat.getColor(this, R.color.bootstrap_brand_danger),
                ContextCompat.getColor(this, R.color.bootstrap_brand_success),
                ContextCompat.getColor(this, R.color.bootstrap_brand_warning)};
        pieChart.setDataPoints(datapoints, colors);


        LayoutInflater layoutInflater = getLayoutInflater();
        String tag;
        for (int i = 0; i <= 20; i++) {
            tag = "#tag" + i;
            View tagView = layoutInflater.inflate(R.layout.tag_layout, null, false);

            TextView tagTextView = (TextView) tagView.findViewById(R.id.tagTextView);
            tagTextView.setText(tag);
            tagLayout.addView(tagView);
        }
    }

    @OnClick(R.id.btnCustom)
    public void onViewClicked() {
        Toast.makeText(MainActivity.this, "This is a Custom View extending from Button with ID " + btnCustom.getParentId(), Toast.LENGTH_SHORT).show();
    }
}
