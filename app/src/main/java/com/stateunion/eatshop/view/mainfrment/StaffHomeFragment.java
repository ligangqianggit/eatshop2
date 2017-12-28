package com.stateunion.eatshop.view.mainfrment;

import android.view.View;
import android.widget.Button;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.view.basefrment.BaseFragment;

/**
 * Created by zhang on 2017/12/28.
 */

public class StaffHomeFragment extends BaseFragment{
    private Button btOrder,btTake;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mainyuangong;
    }

    @Override
    public void createView(View rootView) {
       btOrder= (Button) rootView.findViewById(R.id.bt_yuangong_dingcan);
        btTake= (Button) rootView.findViewById(R.id.bt_yuangong_qucan);
    }

    @Override
    public void refreshData(View rootView) {
     }
}
