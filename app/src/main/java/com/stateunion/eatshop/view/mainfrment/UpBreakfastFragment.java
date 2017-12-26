package com.stateunion.eatshop.view.mainfrment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stateunion.eatshop.R;

/**
 * Created by 青春 on 2017/12/11.
 */

public class UpBreakfastFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_upbreakfast,container,false);
        return view;

    }
}
