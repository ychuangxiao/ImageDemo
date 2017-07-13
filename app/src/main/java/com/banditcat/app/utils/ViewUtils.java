package com.banditcat.app.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.banditcat.common.fontawesom.IconicsDrawable;
import com.banditcat.common.fontawesom.typeface.IIcon;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.banditcat.app.R;


/**
 * 文件名称：{@link ViewUtils}
 * <br/>
 * 功能描述： 视图工具类
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 12:40
 * <br/>
 * 修改作者：banditcat
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
    public static void setCompoundRightDrawables(Context context, TextView textView, IIcon icon, int color, Float size) {
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

    public static void setCompoundDrawables(Context context, TextView textView, IIcon left, IIcon top, IIcon right, IIcon bottom, int color, Float
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
    public static void setDrawable(Context context, FloatingActionButton floatingActionButton, IIcon icon, Float size, int color) {
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
}
