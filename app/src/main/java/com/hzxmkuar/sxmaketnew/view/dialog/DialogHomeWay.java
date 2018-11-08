package com.hzxmkuar.sxmaketnew.view.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzxmkuar.sxmaketnew.R;


public class DialogHomeWay extends BottomSheetDialogFragment {




    public static DialogHomeWay newInstance() {
        DialogHomeWay dialogHomeWay = new DialogHomeWay();
        return dialogHomeWay;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_home_way, container, false);


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
