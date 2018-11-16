package com.hzxmkuar.sxmaketnew.newversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.SegmentTabLayout;
import com.hzxmkuar.sxmaketnew.R;
import com.view.pie.AnimatedPieView;
import com.view.pie.AnimatedPieViewConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RevenueActivity extends AppCompatActivity {

    private String[] mTitles = {"最近7天 ", "最近半年"};
    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.pie_view)
    AnimatedPieView pieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_revenue);
        ButterKnife.bind(this);
        tabLayout.setTabData(mTitles);
        AnimatedPieViewConfig config = pieView.getConfig();

    }
}
