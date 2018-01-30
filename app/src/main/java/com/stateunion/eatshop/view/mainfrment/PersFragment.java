package com.stateunion.eatshop.view.mainfrment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.activity.loginactivity.LoginActivity;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.PersonInfoBean;
import com.stateunion.eatshop.retrofit.view.IBaseDialogView;
import com.stateunion.eatshop.util.AppSessionEngine;
import com.stateunion.eatshop.util.CircleTransform;
import com.stateunion.eatshop.view.baseactivity.ChangePwdActivity;
import com.stateunion.eatshop.view.baseactivity.HistoryActivity;
import com.stateunion.eatshop.view.baseactivity.OpinActivity;
import com.stateunion.eatshop.view.baseactivity.ZiLiaoActivity;
import com.stateunion.eatshop.view.basefrment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

/**
 * Created by admini on 2017/11/28.
 */

public class PersFragment extends BaseFragment implements IBaseDialogView {
     SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    private Context context = null;
    private LinearLayout llyt_preson_fankui, llyt_preson_history, llyt_preson_zilaio, llyt_preson_changepwd,llyt_preson_tuichu;
    public static TextView tv_preson_user, tv_preson_iccard, tv_preson_phone, tv_preson_location, tv_preson_yue, tv_preson_jifen;
    private boolean isAlive = false;
    public static ImageView iv_preson_touxiang;
    public static String url, shengri;

    @Override
    public int getLayoutId() {
        return R.layout.frag_preson;
    }

    @Override
    public void createView(View rootView) {
        context = rootView.getContext();
        intview(rootView);
        isAlive = true;
        iv_preson_touxiang = (ImageView) rootView.findViewById(R.id.iv_preson_touxiang);
        llyt_preson_zilaio = (LinearLayout) rootView.findViewById(R.id.llyt_preson_zilaio);
        llyt_preson_zilaio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ZiLiaoActivity.class);
                intent.putExtra("imageurl", url);
                intent.putExtra("shengri", shengri);
                intent.putExtra("phone", tv_preson_phone.getText().toString());
                intent.putExtra("zhuzhi", tv_preson_location.getText().toString());
                startActivity(intent);
            }
        });

        llyt_preson_fankui = (LinearLayout) rootView.findViewById(R.id.llyt_preson_fankui);
        llyt_preson_fankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpinActivity.class);
                startActivity(intent);
            }
        });

        llyt_preson_history = (LinearLayout) rootView.findViewById(R.id.llyt_preson_history);
        llyt_preson_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HistoryActivity.class);
                startActivity(intent);
            }
        });

        llyt_preson_changepwd = (LinearLayout) rootView.findViewById(R.id.llyt_preson_changepwd);
        llyt_preson_changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChangePwdActivity.class);
                startActivity(intent);
            }
        });
        llyt_preson_tuichu = (LinearLayout) rootView.findViewById(R.id.llyt_preson_tuichu);
        llyt_preson_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 LoginOut();
                AppSessionEngine.logout();
                getBaseActivity().startActivity(new Intent(getBaseActivity(), LoginActivity.class));
            }
        });
        init();

    }

    public void intview(View view) {
        tv_preson_user = (TextView) view.findViewById(R.id.tv_preson_user);
        tv_preson_iccard = (TextView) view.findViewById(R.id.tv_preson_iccard);
        tv_preson_phone = (TextView) view.findViewById(R.id.tv_preson_phone);
        tv_preson_location = (TextView) view.findViewById(R.id.tv_preson_location);
        tv_preson_yue = (TextView) view.findViewById(R.id.tv_preson_yue);
        tv_preson_jifen = (TextView) view.findViewById(R.id.tv_preson_jifen);
        swipeRefresh= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

    }
  private void init(){
      swipeRefresh.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,R.color.colorPrimaryDark);
      swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
              getPreson();

          }
      });
  }
  private void getPreson(){
        RequestCommand.getPreson(new PresonCallBck(PersFragment.this), AppSessionEngine.getLoginInfo().getGonghao());
            Log.d("aaaa","是我");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    public static class PresonCallBck extends DialogCallback<PersonInfoBean, PersFragment> {

        public PresonCallBck(PersFragment requestView) {
            super(requestView);
            Log.d("aaaa","是我--------------");

        }
        @Override
        protected void onResponseSuccess(PersonInfoBean personInfoBean, Call<PersonInfoBean> call) {
            super.onResponseSuccess(personInfoBean, call);
            getAttachTarget().tv_preson_user.setText(personInfoBean.getBody().getName());
            getAttachTarget().tv_preson_iccard.setText(personInfoBean.getBody().getGonghao());
            getAttachTarget().tv_preson_phone.setText(personInfoBean.getBody().getPhone());
            getAttachTarget().tv_preson_location.setText(personInfoBean.getBody().getZhuzhi());
            getAttachTarget().tv_preson_yue.setText( personInfoBean.getBody().getYumoney());
            getAttachTarget().tv_preson_jifen.setText( personInfoBean.getBody().getJifen());
            shengri = personInfoBean.getBody().getChusheng();
            getAttachTarget().swipeRefresh.setRefreshing(false);
            //此处加载网络图片
            url = "http://ceshi123.dns178.com/" + personInfoBean.getBody().getTouxiang();
            Picasso.with(getAttachTarget().getContext()).
                    load(url).resize(200, 200).transform
                    (new CircleTransform()).into(iv_preson_touxiang);

        }

        @Override
        public void onFailure(Call<PersonInfoBean> call) {
            super.onFailure(call);
            getAttachTarget().swipeRefresh.setRefreshing(false);

        }
    }

    @Override
    public void refreshData(View rootView) {
        getPreson();
     }

    @Override
    public void onResume() {
        super.onResume();
//        RequestCommand.getPreson(new PresonCallBck(PersFragment.this), AppSessionEngine.getLoginInfo().getGonghao());
    }

    /**
     * 顶掉请求
     */
    private void LoginOut() {
//       RequestCommand.pswLogin(new RequestOutLogCall(PersFragment.this),"111111","123");
    }

    @Override
    public void onStart() {
        super.onStart();
//        RequestCommand.getPreson(new PresonCallBck(PersFragment.this), AppSessionEngine.getLoginInfo().getGonghao());
    }

    @Override
    public Dialog createDialog(@StyleRes int themeResId) {
        Dialog dialog = new Dialog(getActivity(), themeResId);
        return dialog;
    }

    @Override
    public void showError(String message) {
        Log.d("======================",message);

    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void onDestroyView() {
        isAlive = false;

        super.onDestroyView();
        unbinder.unbind();
    }
}
