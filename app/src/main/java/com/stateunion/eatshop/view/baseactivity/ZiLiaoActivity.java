package com.stateunion.eatshop.view.baseactivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.stateunion.eatshop.R;

import java.util.Calendar;


/**
 * Created by 青春 on 2018/1/1.
 */

public class ZiLiaoActivity extends BaseActivity{
    private TextView tv_ziliao_date;
    // 用来保存年月日：
    private int mYear;
    private int mMonth;
    private int mDay;
    // 声明一个独一无二的标识，来作为要显示DatePicker的Dialog的ID：
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziliao);
        intview();
    }
    public void intview(){
        tv_ziliao_date= (TextView) findViewById(R.id.tv_ziliao_date);
        // 获得当前的日期：
        final Calendar currentDate = Calendar.getInstance();
        mYear = currentDate.get(Calendar.YEAR);
        mMonth = currentDate.get(Calendar.MONTH);
        mDay = currentDate.get(Calendar.DAY_OF_MONTH);
// 设置文本的内容：
        tv_ziliao_date.setText(new StringBuilder().append(mYear).append("年")
                .append(mMonth + 1).append("月")// 得到的月份+1，因为从0开始
                .append(mDay).append("日"));

        tv_ziliao_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }
    // 需要定义弹出的DatePicker对话框的事件监听器：
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            String str1 = new String(new StringBuilder().append(mYear)
                    .append("年").append(mMonth + 1).append("月")// 得到的月份+1，因为从0开始
                    .append(mDay).append("日"));


// 设置文本的内容：
            tv_ziliao_date.setText(str1);
        }
    };


    /**
     * 当Activity调用showDialog函数时会触发该函数的调用：
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }
}
