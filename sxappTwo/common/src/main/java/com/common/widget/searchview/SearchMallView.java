package com.common.widget.searchview;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;
import com.common.utils.EmptyUtils;
import com.common.utils.KeyBoardUtils;
import com.common.utils.RecordSQLiteOpenHelper;
import com.common.utils.StringUtils;
import com.common.utils.ViewUtils;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.linearlayout.flowlayout.FlowLayout;
import com.common.widget.linearlayout.flowlayout.TagAdapter;
import com.common.widget.linearlayout.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Set;

public class SearchMallView extends LinearLayout {

    private Context context;

    /*UI组件*/
    private ImageView iv_clear;
    private DeleteEditText et_search;
    private TextView tv_tip;
    private ImageView iv_search;
    private TextView tvEmpty;

    /*列表及其适配器*/
    private TagFlowLayout listView;

    /*数据库变量*/
    private RecordSQLiteOpenHelper helper ;
    private SQLiteDatabase db;

    private SearchCallback searchCallback;

    /*三个构造函数*/
    //在构造函数里直接对搜索框进行初始化 - init()
    public SearchMallView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SearchMallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public SearchMallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void setEtSearch(DeleteEditText etsearch) {
        this.et_search = etsearch;

        if (EmptyUtils.isEmpty(et_search)) {
            return;
        }
        //搜索框的文本变化实时监听
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  }

            //输入后调用该方法
            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().trim().length() == 0) {
                    //若搜索框为空,则模糊搜索空字符,即显示所有的搜索历史
                    tv_tip.setText("搜索历史");
                } else {
                    tv_tip.setText("搜索结果");
                }

                //每次输入后都查询数据库并显示
                //根据输入的值去模糊查询数据库中有没有数据
                queryData(StringUtils.replaceBtoA(et_search.getText().toString().trim(), "'"));
            }
        });


        // 搜索框的键盘搜索键
        // 点击回调
        et_search.setOnKeyListener(new OnKeyListener() {// 输入完后按键盘上的搜索键


            // 修改回车键功能
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                    KeyBoardUtils.hideKeyboard(et_search);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    boolean hasData = hasData(et_search.getText().toString().trim());
                    if (!hasData) {
                        insertData(et_search.getText().toString().trim());
                        queryData("");
                    }
                    if (EmptyUtils.isNotEmpty(searchCallback)) {
                        searchCallback.onSearchAction(et_search.getText().toString().trim());
                    }
                }
                return false;
            }
        });
    }

    public void setIvSearch(ImageView iv_search) {
        this.iv_search = iv_search;

        if (EmptyUtils.isEmpty(iv_search)) {
            return;
        }

        //点击搜索按钮后的事件
        this.iv_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasData = hasData(et_search.getText().toString().trim());
                if (!hasData) {
                    insertData(et_search.getText().toString().trim());
                    //搜索后显示数据库里所有搜索历史是为了测试
                    queryData("");
                }

                if (EmptyUtils.isNotEmpty(searchCallback)) {
                    searchCallback.onSearchAction(et_search.getText().toString().trim());
                }
            }
        });
    }

    /*初始化搜索框*/
    private void init() {

        //初始化UI组件
        initView();

        //实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(context, "yyyy_mall.db");

        // 第一次进入时查询所有的历史记录
        queryData("");

        //"清空搜索历史"按钮
        iv_clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空数据库
                deleteData();
                queryData("");
            }
        });
    }

    /** 封装的函数 */
    private void initView(){
        LayoutInflater.from(context).inflate(R.layout.base_search_layout,this);
        iv_clear = (ImageView) findViewById(R.id.iv_clear);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        tvEmpty = (TextView) findViewById(R.id.tv_empty);
        listView = (TagFlowLayout) findViewById(R.id.tf_item);
    }

    /*插入数据*/
    private void insertData(String tempName) {
        if (EmptyUtils.isEmpty(tempName)) {
            return;
        }
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /*模糊查询数据 并显示在ListView列表上*/
    private void queryData(String tempName) {

        //模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        final ArrayList<String> item = queryCuror(cursor);

        if (EmptyUtils.isEmpty(item)) {
            tvEmpty.setVisibility(VISIBLE);
            listView.setVisibility(GONE);
        } else {
            tvEmpty.setVisibility(GONE);
            listView.setVisibility(VISIBLE);
            listView.setAdapter(new TagAdapter<String>(item) {
                @Override
                public View getView(FlowLayout parent, int position, String string) {
                    return ViewUtils.creatTextView(context, StringUtils.nullToStr(string));
                }
            });
        }

        listView.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                // 获取到用户点击列表里的文字,并自动填充到搜索框内
                if (EmptyUtils.isNotEmpty(selectPosSet)) {
                    et_search.setText(item.get((int) selectPosSet.toArray()[0]));
                    if (EmptyUtils.isNotEmpty(searchCallback)) {
                        searchCallback.onSearchAction(item.get((int) selectPosSet.toArray()[0]));
                    }
                }
            }
        });
    }

    private ArrayList<String> queryCuror(Cursor cursor) {
        ArrayList<String> list = new ArrayList<>();

        try {
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String data = cursor.getString(cursor.getColumnIndex("name"));
                    list.add(StringUtils.nullToStr(data));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    /*检查数据库中是否已经有该条记录*/
    private boolean hasData(String tempName) {
        //从Record这个表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /*清空数据*/
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }

    public void setSearchCallback(SearchCallback searchCallback) {
        this.searchCallback = searchCallback;
    }

    public interface SearchCallback {
        void onSearchAction(String keyword);
    }
}
