package com.stateunion.eatshop.view.mainfrment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

    @BindView(R.id.rl_tongji_zonglan)
    RelativeLayout rl_tongji_zonglan;

    @BindView(R.id.list_zonglan)
    ListView list_zonglan;

    @BindView(R.id.iv_tongji_jiantou)
    ImageView iv_tongji_jiantou;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_lunch, container, false);
        isAlin = true;
        call();
        call2();
        unbinder = ButterKnife.bind(this, view);
        intview();
        return view;
    }

    public void intview(){
        rl_tongji_zonglan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list_zonglan.getVisibility()==View.GONE){
                    list_zonglan.setVisibility(View.VISIBLE);
                    iv_tongji_jiantou.setImageDrawable(getResources().getDrawable(R.drawable.ic_shang_hui));
                }else if(list_zonglan.getVisibility()==View.VISIBLE){
                    list_zonglan.setVisibility(View.GONE);
                    iv_tongji_jiantou.setImageDrawable(getResources().getDrawable(R.drawable.ic_xia_hui));
                }
            }
        });
    }

    public void call() {
        RequestCommand.getTongji(new TongJICallBackW(DinnerFragment.this), "晚餐");
     }
    public void call2(){
        RequestCommand.getTongji(new TongJICallBack1(DinnerFragment.this), "全部");
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


    public class TongJICallBack1 extends DialogCallback<TongJiBean, DinnerFragment> {
        public TongJICallBack1(DinnerFragment requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(TongJiBean tongJiBean, Call<TongJiBean> call) {
            super.onResponseSuccess(tongJiBean, call);
            tongjiOrderAdpter = new TongjiOrderAdpter(tongJiBean.getBody().getGoods(), getActivity());
            list_zonglan.setAdapter(tongjiOrderAdpter);
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
