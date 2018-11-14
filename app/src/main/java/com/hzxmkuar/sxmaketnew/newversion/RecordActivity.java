package com.hzxmkuar.sxmaketnew.newversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.hzxmkuar.sxmaketnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 代收代付记录 和 发票申请记录
 */
public class RecordActivity extends BaseMvpActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.t_name)
    TextView tName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_adv)
    ImageView ivAdv;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @OnClick(R.id.back)
    public void onFinishClicked() {
        finish();
    }


    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_new;
    }

    @Override
    protected void onViewCreated() {
        Bundle bundle = getIntent().getExtras();
        boolean flag = bundle.getBoolean("flag", true);
        ivAdv.setImageResource(flag ? R.mipmap.adv_withdrawal_time : R.mipmap.adv_invoice_record);
        tName.setText(flag ? "代收代付申请记录" : "发票提现申请记录");
    }

    @Override
    protected void doLogicFunc() {

    }
}
