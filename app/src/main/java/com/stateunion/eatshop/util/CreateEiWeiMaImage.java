package com.stateunion.eatshop.util;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

/**
 * Created by 青春 on 2017/12/13.
 */

public class CreateEiWeiMaImage {
    /**
     * @param order
     * @param width
     * @param height
     * @return 生成二维码
     * <p>
     * 调用         Bitmap CreateQRImage = createZXing(apkDownLoadUrl, 700, 700);// 生成的二维码
     * <p>
     * imageview.setImageBitmap
     */

    public static Bitmap createZXing(String order, final int width, final int height) {


        try {

            // 判断URL合法性

            if (order == null || "".equals(order) || order.length() < 1) {

                return null;

            }

            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();

            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

            // 图像数据转换，使用了矩阵转换

            BitMatrix bitMatrix = new QRCodeWriter().encode(order,

                    BarcodeFormat.QR_CODE, width, height, hints);

            int[] pixels = new int[width * height];

            // 下面这里按照二维码的算法，逐个生成二维码的图片，

            // 两个for循环是图片横列扫描的结果

            for (int y = 0; y < height; y++) {

                for (int x = 0; x < width; x++) {

                    if (bitMatrix.get(x, y)) {

                        pixels[y * width + x] = 0xff000000;

                    } else {

                        pixels[y * width + x] = 0xffffffff;

                    }

                }

            }

            // 生成二维码图片的格式，使用ARGB_8888

            Bitmap bitmap = Bitmap.createBitmap(width, height,

                    Bitmap.Config.ARGB_8888);

            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

            return bitmap;

        } catch (WriterException e) {

            e.printStackTrace();

        }

        return null;

    }
}
