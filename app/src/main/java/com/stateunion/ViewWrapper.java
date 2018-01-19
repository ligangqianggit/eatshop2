package com.stateunion;

import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.stateunion.eatshop.R;

/**
 * Created by admini on 2018/1/19.
 */

public class ViewWrapper {
    View base;
    RatingBar rate = null;
    EditText label = null;
    public ViewWrapper(View base){
        this.base = base;
    }

    public RatingBar getRatingBar(){
        if(rate == null)
            rate =(RatingBar) base.findViewById(R.id.ratingBar);
        return rate;
    }
    public TextView getLabel(){
        if(label == null)
            label = (EditText)base.findViewById(R.id.et_ygpingjiaitem_message);
        return label;
    }
}
