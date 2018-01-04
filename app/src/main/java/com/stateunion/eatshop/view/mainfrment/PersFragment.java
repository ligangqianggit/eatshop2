package com.stateunion.eatshop.view.mainfrment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.activity.loginactivity.LoginActivity;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.PersonInfoBean;
import com.stateunion.eatshop.retrofit.entiity.PersonInfoEntity;
import com.stateunion.eatshop.retrofit.view.IBaseDialogView;
import com.stateunion.eatshop.util.AppSessionEngine;
import com.stateunion.eatshop.view.baseactivity.HistoryActivity;
import com.stateunion.eatshop.view.baseactivity.OpinActivity;
import com.stateunion.eatshop.view.baseactivity.ZiLiaoActivity;
import com.stateunion.eatshop.view.basefrment.BaseFragment;
import retrofit2.Call;
/**
 * Created by admini on 2017/11/28.
 */

public class PersFragment extends BaseFragment implements IBaseDialogView{
    private Context context=null;
    private LinearLayout llyt_preson_fankui,llyt_preson_history,llyt_preson_zilaio;
    private TextView tv_preson_user,tv_preson_iccard,tv_preson_phone,tv_preson_location;
    private Button btLogOut;
    private boolean isAlive =false;
    @Override
    public int getLayoutId() {
        return R.layout.frag_preson;
    }

    @Override
    public void createView(View rootView) {
        context=rootView.getContext();
        intview(rootView);
isAlive=true;
        llyt_preson_zilaio=(LinearLayout) rootView.findViewById(R.id.llyt_preson_zilaio);
        llyt_preson_zilaio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ZiLiaoActivity.class);
                startActivity(intent);
            }
        });

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
        btLogOut= (Button) rootView.findViewById(R.id.bt_logout);
        btLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 LoginOut();
                AppSessionEngine.logout();
                getBaseActivity().startActivity(new Intent(getBaseActivity(),LoginActivity.class));
               }
        });

    }
    public void intview(View view){

        AppSessionEngine.getLoginInfo().getGonghao();
                tv_preson_user= (TextView) view.findViewById(R.id.tv_preson_user);
                tv_preson_iccard=(TextView) view.findViewById(R.id.tv_preson_iccard);
                tv_preson_phone=(TextView) view.findViewById(R.id.tv_preson_phone);
                tv_preson_location=(TextView) view.findViewById(R.id.tv_preson_location);

    }
    public static class PresonCallBck extends DialogCallback<PersonInfoBean,PersFragment>{

        public PresonCallBck(PersFragment requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(PersonInfoBean personInfoBean, Call<PersonInfoBean> call) {
            super.onResponseSuccess(personInfoBean, call);
               Log.v("eatshop","返回数据"+personInfoBean.getBody());
        }
    }
    @Override
    public void refreshData(View rootView) {
        RequestCommand.getPreson(new PresonCallBck(PersFragment.this),AppSessionEngine.getLoginInfo().getGonghao());

    }


    /**
     * 顶掉请求
     */
   private void LoginOut(){
//       RequestCommand.pswLogin(new RequestOutLogCall(PersFragment.this),"111111","123");
   }

    @Override
    public Dialog createDialog(@StyleRes int themeResId) {
        Dialog dialog=new Dialog(getContext(),themeResId);
        return dialog;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isAlive=false;
    }
}
