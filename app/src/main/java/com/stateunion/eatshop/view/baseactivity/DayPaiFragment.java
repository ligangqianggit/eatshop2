package com.stateunion.eatshop.view.baseactivity;

import android.app.Dialog;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.ListView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.PaiMingListAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.PaiMingBean;
import com.stateunion.eatshop.retrofit.view.IBaseDialogView;
import com.stateunion.eatshop.util.AppSessionEngine;
import com.stateunion.eatshop.view.basefrment.BaseFragment;

import retrofit2.Call;

/**
 * Created by admini on 2018/3/1.
 */

public class DayPaiFragment extends BaseFragment implements IBaseDialogView {
    private ListView list_paimingtwo;
    private boolean isAlin = false;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_daypai;
    }

    @Override
    public void createView(View rootView) {
        intview(rootView);
    }
    public void intview(View view){
        list_paimingtwo= (ListView) view.findViewById(R.id.list_paimingtwo);
    }
    public void getPaiming(){
        RequestCommand.getPaiming(new PaiMingCallBack(DayPaiFragment.this),"1");

    }
    @Override
    public void refreshData(View rootView) {
        getPaiming();
    }

    @Override
    public Dialog createDialog(@StyleRes int themeResId) {
        Dialog dialog = new Dialog(getActivity(), themeResId);
        return dialog;
    }
    @Override
    public void showError(String message) {
    }
    @Override
    public BaseActivity getBaseActivity() {
        return null;
    }
    @Override
    public boolean isAlive() {
        return isAlin;
    }

    public class PaiMingCallBack extends DialogCallback<PaiMingBean,DayPaiFragment> {
        PaiMingListAdapter paiMingListAdapter;
        public PaiMingCallBack(DayPaiFragment requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(PaiMingBean paiMingBean, Call<PaiMingBean> call) {
            super.onResponseSuccess(paiMingBean, call);
            paiMingListAdapter=new PaiMingListAdapter(paiMingBean.getBody().getList(),getAttachTarget().getBaseActivity());
            list_paimingtwo.setAdapter(paiMingListAdapter);
        }
    }
}
