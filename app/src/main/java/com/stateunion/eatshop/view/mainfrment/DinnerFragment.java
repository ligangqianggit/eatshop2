package com.stateunion.eatshop.view.mainfrment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.ShengRiListAdapter;
import com.stateunion.eatshop.adapter.TongjiOrderAdpter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.TongJiBean;
import com.stateunion.eatshop.retrofit.view.IBaseDialogView;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

/**
 * Created by 青春 on 2017/12/11.
 */

public class DinnerFragment extends Fragment implements IBaseDialogView {

    @BindView(R.id.list_order)
    ListView listOrder;
    @BindView(R.id.tv_tongji_shengrinum)
    TextView tvTongjiShengrinum;
    @BindView(R.id.list_shengri)
    ListView listShengri;
    Unbinder unbinder;
    private boolean isAlin = false;

    TongjiOrderAdpter tongjiOrderAdpter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_lunch, container, false);
        isAlin = true;
        call();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void call() {
        RequestCommand.getTongji(new TongJICallBackW(DinnerFragment.this), "晚餐");
     }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isAlin = false;
        unbinder.unbind();
    }


    public class TongJICallBackW extends DialogCallback<TongJiBean, DinnerFragment> {
        ShengRiListAdapter shengRiListAdapter;

        public TongJICallBackW(DinnerFragment requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(TongJiBean tongJiBean, Call<TongJiBean> call) {
            super.onResponseSuccess(tongJiBean, call);
            shengRiListAdapter = new ShengRiListAdapter(tongJiBean.getBody().getShenglist(), getActivity());
            tongjiOrderAdpter = new TongjiOrderAdpter(tongJiBean.getBody().getGoods(), getActivity());
            listShengri.setAdapter(shengRiListAdapter);
            listOrder.setAdapter(tongjiOrderAdpter);
            tvTongjiShengrinum.setText("今日生日员工:" + tongJiBean.getBody().getSheng_num());
        }
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
}
