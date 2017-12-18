package com.stateunion.eatshop.view.mainfrment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.view.baseactivity.HistoryActivity;
import com.stateunion.eatshop.view.baseactivity.OpinActivity;
import com.stateunion.eatshop.view.basefrment.BaseFragment;

/**
 * Created by admini on 2017/11/28.
 */

public class PersFragment extends BaseFragment {
    private Context context=null;
    private LinearLayout llyt_preson_fankui,llyt_preson_history;
    @Override
    public int getLayoutId() {
        return R.layout.frag_preson;
    }

    @Override
    public void createView(View rootView) {
        context=rootView.getContext();
        llyt_preson_fankui=(LinearLayout) rootView.findViewById(R.id.llyt_preson_fankui);
        llyt_preson_fankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, OpinActivity.class);
                startActivity(intent);
            }
        });

        llyt_preson_history= (LinearLayout) rootView.findViewById(R.id.llyt_preson_history);
        llyt_preson_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void refreshData(View rootView) {

    }
}
