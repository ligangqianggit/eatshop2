package com.stateunion.eatshop.view.mainfrment;

import android.content.Context;
import android.view.View;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.view.basefrment.BaseFragment;

/**
 * Created by admini on 2017/11/28.
 */

public class CouPonFragment  extends BaseFragment {
    private Context context=null;

    @Override
    public int getLayoutId() {
        return R.layout.frag_fuli;
    }

    @Override
    public void createView(View rootView) {
        context=rootView.getContext();
    }

    @Override
    public void refreshData(View rootView) {

    }
}
