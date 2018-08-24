package com.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;

import com.common.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by STH on 2018/6/9.
 */

public class BankUtils {
    private int[] bank = {R.mipmap.abc_,R.mipmap.bcm_,R.mipmap.bob_,R.mipmap.boc_,
            R.mipmap.cbc_, R.mipmap.ccb_,R.mipmap.cdb_,R.mipmap.ceb_,
            R.mipmap.cib_,R.mipmap.cmb_,R.mipmap.cmbc_, R.mipmap.eb_,
            R.mipmap.gdb_,R.mipmap.hsbc_,R.mipmap.hxb_,R.mipmap.icbc_,
            R.mipmap.pboc_, R.mipmap.psbc_,R.mipmap.sdb_,R.mipmap.sp_,
            R.mipmap.spdb_};
    private String [] bankdes = {"ABC","BCM","BOB","BOC","CBC","CCB","CDB",
            "CEB","CID","CMB","CMBC","EB","GDB","HSBC","HXB","ICBC","PBOC",
            "PSBC","SDB","SP","SPDB",};
    private Context mContext;
    private long mRgb;

    public BankUtils(Context context) {
        this.mContext = context;
    }

    public Map<String , BankFormBean> getBankFormBeanList(){
        Map <String , BankFormBean> map = new HashMap<>();
        for (int i = 0 ; i<1; i++){
            BankFormBean bankFormBean = new BankFormBean(setPixelsColor(R.mipmap.bg_navigation),bank[i]);
            map.put(bankdes[i],bankFormBean);
        }
        return map;
    }
    private long setPixelsColor(int resourceId){
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), resourceId);
        // Asynchronous
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            private Palette.Swatch mSwatch;
            public void onGenerated(Palette p) {
               mSwatch = p.getVibrantSwatch();
                System.out.println(mSwatch.getRgb()+"---"+mSwatch.toString());
                mRgb = mSwatch.getRgb();
            }
        });
        return mRgb;
    }
}
