package com.stateunion.eatshop.zxing.common;

/**
 * Created by yzq on 2017/7/20.
 * <p>
 * zxing常量
 */

public class Constant {
    public static final int DECODE = 1;
    public static final int DECODE_FAILED = 2;
    public static final int DECODE_SUCCEEDED = 3;
    public static final int LAUNCH_PRODUCT_QUERY = 4;
    public static final int QUIT = 5;
    public static final int RESTART_PREVIEW = 6;
    public static final int RETURN_SCAN_RESULT = 7;
    public static final int FLASH_OPEN = 8;
    public static final int FLASH_CLOSE = 9;
    public static final int REQUEST_IMAGE = 10;
    public static final String CODED_CONTENT = "codedContent";
    public static final String CODED_BITMAP = "codedBitmap";


    /*传递的zxingconfing*/

    public static final String INTENT_ZXING_CONFIG = "zxingConfig";

    /**
     * @author Richie on 2017.07.31
     *         常量类
     */
         public static final String[] BOOKS = {"星期一", "星期二", "星期三","星期四","星期五","星期六","星期日"};
        public static final String[][] FIGURES = {
                {"早餐", "午餐", "晚餐", "加班"},
                {"早餐", "午餐", "晚餐", "加班"},
                {"早餐", "午餐", "晚餐", "加班"},
                {"早餐", "午餐", "晚餐", "加班"},
                {"早餐", "午餐", "晚餐", "加班"},
                {"早餐", "午餐", "晚餐", "加班"},
                {"早餐", "午餐", "晚餐", "加班"}

        };
    public static final String[][] FIGURESS = {
            {"6:00-8:00", "11:00-12:50", "17:00-18:50", "20:00-21:30"},
            {"6:00-8:00", "11:00-12:50", "17:00-18:50", "20:00-21:30"},
            {"6:00-8:00", "11:00-12:50", "17:00-18:50", "20:00-21:30"},
            {"6:00-8:00", "11:00-12:50", "17:00-18:50", "20:00-21:30"},
            {"6:00-8:00", "11:00-12:50", "17:00-18:50", "20:00-21:30"},
            {"6:00-8:00", "11:00-12:50", "17:00-18:50", "20:00-21:30"},
            {"6:00-8:00", "11:00-12:50", "17:00-18:50", "20:00-21:30"},

    };
        public static final String BOOK_NAME = "book_name";
        public static final String FIGURE_NAME = "figure_name";
}
