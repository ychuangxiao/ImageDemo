package com.sb.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sb.app.R;
import com.sb.app.model.ScreenModel;
import com.sb.common.fontawesom.IconicsDrawable;
import com.sb.common.fontawesom.typeface.IIcon;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;


/**
 * 文件名称：{@link ViewUtils}
 * <br/>
 * 功能描述： 视图工具类
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 12:40
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/5 12:40
 * <br/>
 * 修改备注：
 */
public class ViewUtils {


    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }




    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 设置顶部图标
     *
     * @param context  上下文
     * @param textView 文本控件
     * @param icon     icon接口
     * @param color    颜色
     * @param size     大小
     */
    public static void setCompoundTopDrawables(Context context, TextView textView, IIcon icon, int color, Float size) {
        Drawable drawable = new IconicsDrawable(context, icon).actionBar(size).color(color).paddingDp(2);

        textView.setCompoundDrawables(null, drawable, null, null); //设置顶部图标
    }

    /**
     * 设置左侧图标
     *
     * @param context  上下文
     * @param editText 编辑控件
     * @param icon     icon接口
     * @param color    颜色
     * @param size     大小
     */
    public static void setCompoundDrawables(Context context, EditText editText, IIcon icon, int color, Float size) {
        Drawable drawable = new IconicsDrawable(context, icon).actionBar(size).color(color).paddingDp(2);

        editText.setCompoundDrawables(drawable, null, null, null); //设置左图标
    }

    /**
     * 设置右侧图标
     *
     * @param context  上下文
     * @param textView 文本控件
     * @param icon     icon接口
     * @param color    颜色
     * @param size     大小
     */
    public static void setCompoundRightDrawables(Context context, TextView textView, IIcon icon, int color, Float
            size) {
        Drawable drawable = new IconicsDrawable(context, icon).actionBar(size).color(color).paddingDp(2);

        textView.setCompoundDrawables(null, null, drawable, null); //设置右边图标
    }

    /**
     * 设置左侧图标
     *
     * @param context  上下文
     * @param textView 文本控件
     * @param icon     icon接口
     * @param color    颜色
     * @param size     大小
     */
    public static void setCompoundLeftDrawables(Context context, TextView textView, IIcon icon, int color, Float size) {
        Drawable drawable = new IconicsDrawable(context, icon).actionBar(size).color(color).paddingDp(2);

        textView.setCompoundDrawables(drawable, null, null, null); //设置左边图标
    }

    public static void setCompoundDrawables(Context context, TextView textView, IIcon left, IIcon top, IIcon right,
                                            IIcon bottom, int color, Float
                                                    size) {

        Drawable drawableLeft = null;
        Drawable drawableTop = null;
        Drawable drawableRight = null;
        Drawable drawableBottom = null;

        if (left != null) {
            drawableLeft = new IconicsDrawable(context, left).actionBar(size).color(color).paddingDp(2);
        }
        if (top != null) {
            drawableTop = new IconicsDrawable(context, top).actionBar(size).color(color).paddingDp(2);
        }
        if (right != null) {
            drawableRight = new IconicsDrawable(context, right).actionBar(size - 2).color(color).paddingDp(2);
        }
        if (bottom != null) {
            drawableBottom = new IconicsDrawable(context, bottom).actionBar(size).color(color).paddingDp(2);

        }


        textView.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom); //设置左边图标
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }


        int totalHeight = 0;
        View listItem = listAdapter.getView(0, null, listView);
        listItem.measure(0, 0); // 计算子项View 的宽高
        totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = (totalHeight * (listAdapter.getCount()));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);

    }

    public static void setGridViewHeightBasedOnChildren(GridView gridView) {

        setGridViewHeightBasedOnChildren(gridView, gridView.getNumColumns());
    }

    public static void setGridViewHeightBasedOnChildren(GridView gridView, int numColumns) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        return;


    }

    /**
     * 组合高度（GridView）
     *
     * @param spanCount   分隔数
     * @param totalSize   总记录数
     * @param widthPixels 屏幕宽度
     */
    public static int mergeHieght(int totalSize, int spanCount, int widthPixels) {


        int zero = totalSize % spanCount;

        double cc = spanCount;

        int itemHeight = (int) (widthPixels / cc);

        int totalHeight = totalSize / spanCount;

        if (totalHeight == 1 && zero == 0) {
            totalHeight = itemHeight;
        } else if (zero != 0) {
            totalHeight = itemHeight * (totalHeight + 1);
        } else {
            totalHeight = itemHeight * (totalHeight);
        }

        return totalHeight;
    }


    /**
     * 设置ImageView背景图
     *
     * @param context   {@link Context}
     * @param imageView ImageView
     * @param icon      {@link IIcon}
     * @param size      图标大小
     * @param color     图标颜色
     */
    public static void setDrawable(Context context, ImageView imageView, IIcon icon, Float size, int color) {

        Drawable drawable = new IconicsDrawable(context, icon).actionBar(size).color(color).paddingDp(2);

        imageView.setBackground(drawable);
    }


    /**
     * @param context              {@link Context}
     * @param floatingActionButton {@link FloatingActionButton}
     * @param icon                 {@link IIcon}
     * @param size                 图标大小
     * @param color                图标颜色
     */
    public static void setDrawable(Context context, FloatingActionButton floatingActionButton, IIcon icon, Float
            size, int color) {
        Drawable drawable = new IconicsDrawable(context, icon).actionBar(size).color(color).paddingDp(2);

        floatingActionButton.setImageDrawable(drawable);
    }

    /*
    * 设置金额
    * */
    public static String mergeMoney(BigDecimal money) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(money);

    }

    /*
    * 设置银行信息
    * */
    public static void initBankInfo(AppCompatImageView leftBankImage, Integer type) {
        switch (type) {
            case 10:
                leftBankImage.setImageResource(R.mipmap.bank_zhongguo); //设置左图标
                break;
            case 20:
                leftBankImage.setImageResource(R.mipmap.bank_jianshe); //设置左图标
                break;
            case 30:
                leftBankImage.setImageResource(R.mipmap.bank_nongye); //设置左图标
                break;
            case 40:
                leftBankImage.setImageResource(R.mipmap.bank_gongshang); //设置左图标
                break;
            case 50:
                leftBankImage.setImageResource(R.mipmap.bank_youzhen); //设置左图标
                break;
            case 60:
                leftBankImage.setImageResource(R.mipmap.bank_minsheng); //设置左图标
                break;
            case 70:
                leftBankImage.setImageResource(R.mipmap.bank_guangda); //设置左图标
                break;
            case 80:
                leftBankImage.setImageResource(R.mipmap.bank_zhongxin); //设置左图标
                break;
            case 90:
                leftBankImage.setImageResource(R.mipmap.bank_zhaoshang); //设置左图标
                break;
            case 100:
                leftBankImage.setImageResource(R.mipmap.bank_xingye); //设置左图标
                break;
            case 110:
                leftBankImage.setImageResource(R.mipmap.bank_wangshang); //设置左图标
                break;
            case 120:
                leftBankImage.setImageResource(R.mipmap.bank_pufa); //设置左图标
                break;
            case 130:
                leftBankImage.setImageResource(R.mipmap.bank_pingan); //设置左图标
                break;
            case 140:
                leftBankImage.setImageResource(R.mipmap.bank_jiaotong); //设置左图标
                break;
            case 150:
                leftBankImage.setImageResource(R.mipmap.bank_huaxia); //设置左图标
                break;
            case 160:
                leftBankImage.setImageResource(R.mipmap.bank_guangfa); //设置左图标
                break;
            default:
                leftBankImage.setImageResource(R.mipmap.bank_zhongguo); //设置左图标
                break;
        }
    }


    /**
     * 判断网络
     */
    public static boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {

            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
                NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

                if (networkInfo != null && networkInfo.length > 0) {
                    for (int i = 0; i < networkInfo.length; i++) {
                        System.out.println(i + "===状态===" + networkInfo[i].getState());
                        System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                        // 判断当前网络状态是否为连接状态
                        if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            } else {
                //获取所有网络连接的信息
                Network[] networks = connectivityManager.getAllNetworks();

                NetworkInfo networkInfo;
                //通过循环将网络信息逐个取出来
                for (int i = 0; i < networks.length; i++) {
                    //获取ConnectivityManager对象对应的NetworkInfo对象
                    networkInfo = connectivityManager.getNetworkInfo(networks[i]);
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }

        }
        return false;
    }


    public static ScreenModel getScreen(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        //获取屏幕信息

        context.getWindowManager().getDefaultDisplay().getMetrics(dm);

        ScreenModel screenModel = new ScreenModel();

        screenModel.setScreenWidth(dm.widthPixels);

        screenModel.setScreenHeight(dm.heightPixels);


        return screenModel;
    }


    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {


        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static int[] getDefaultFace() {
        int[] drawables = new int[28];


        drawables[0] = R.mipmap.ic_face_1;
        drawables[1] = R.mipmap.ic_face_2;
        drawables[2] = R.mipmap.ic_face_3;
        drawables[3] = R.mipmap.ic_face_4;
        drawables[4] = R.mipmap.ic_face_5;
        drawables[5] = R.mipmap.ic_face_6;
        drawables[6] = R.mipmap.ic_face_7;
        drawables[7] = R.mipmap.ic_face_8;
        drawables[8] = R.mipmap.ic_face_9;
        drawables[9] = R.mipmap.ic_face_10;
        drawables[10] = R.mipmap.ic_face_11;
        drawables[11] = R.mipmap.ic_face_12;
        drawables[12] = R.mipmap.ic_face_13;
        drawables[13] = R.mipmap.ic_face_14;
        drawables[14] = R.mipmap.ic_face_15;
        drawables[15] = R.mipmap.ic_face_16;
        drawables[16] = R.mipmap.ic_face_17;
        drawables[17] = R.mipmap.ic_face_18;
        drawables[18] = R.mipmap.ic_face_19;
        drawables[19] = R.mipmap.ic_face_20;
        drawables[20] = R.mipmap.ic_face_21;
        drawables[21] = R.mipmap.ic_face_22;
        drawables[22] = R.mipmap.ic_face_23;
        drawables[23] = R.mipmap.ic_face_24;
        drawables[24] = R.mipmap.ic_face_25;
        drawables[25] = R.mipmap.ic_face_26;
        drawables[26] = R.mipmap.ic_face_27;
        drawables[27] = R.mipmap.ic_face_28;


        return drawables;
    }


    public static String[] getDefaultNick() {
        String[] drawables = new String[28];


        drawables[0] = "F.Q";
        drawables[1] = "飞鸟";
        drawables[2] = "飞鹰";
        drawables[3] = "Frank";
        drawables[4] = "fu18";
        drawables[5] = "Ken";
        drawables[6] = "雷子健";
        drawables[7] = "Luck.lin";
        drawables[8] = "梦~磁带存储";
        drawables[9] = "*^o^*pp";
        drawables[10] = "OGA";
        drawables[11] = "胖子";
        drawables[12] = "抛物线~凯凯";
        drawables[13] = "seven";
        drawables[14] = "ValuesFeng";
        drawables[15] = "Water";
        drawables[16] = "Evil.Angel";
        drawables[17] = "大头鬼";
        drawables[18] = "COCO";
        drawables[19] = "笨笨木木~";
        drawables[20] = "albert";
        drawables[21] = "彩霞仙子";
        drawables[22] = "爱的代价";
        drawables[23] = "阿豆";
        drawables[24] = "~Amor、野狼";
        drawables[25] = "马大帅";
        drawables[26] = "风流一夜";
        drawables[27] = "SuperMan";


        return drawables;
    }

    public static int getRandomIndex(int index) {
        Random rand = new Random();


        return rand.nextInt(index);
    }
}
