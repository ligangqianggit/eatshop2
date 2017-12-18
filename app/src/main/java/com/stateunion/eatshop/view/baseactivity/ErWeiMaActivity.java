package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.util.CreateEiWeiMaImage;

/**
 * Created by 青春 on 2017/12/13.
 */

public class ErWeiMaActivity extends BaseActivity{
    private ImageView iv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erweima);
        Intent intent=getIntent();
        String cord=intent.getStringExtra("cord");
        Bitmap CreateQRImage = CreateEiWeiMaImage.createZXing(cord, 700, 700);
        iv= (ImageView) findViewById(R.id.iv_erweima);
        iv.setImageBitmap(CreateQRImage);

    }
}
