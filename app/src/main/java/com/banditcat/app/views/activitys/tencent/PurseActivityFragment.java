package com.banditcat.app.views.activitys.tencent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseFragment;

import butterknife.BindView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PurseActivityFragment extends BaseFragment {


    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;


    @BindView(R.id.relativeLayout4)
    RelativeLayout relativeLayout4;


    @BindView(R.id.relativeLayout7)
    RelativeLayout relativeLayout7;


    @BindView(R.id.relativeLayout10)
    RelativeLayout relativeLayout10;

    @BindView(R.id.relativeLayout13)
    RelativeLayout relativeLayout13;

    @BindView(R.id.relativeLayout16)
    RelativeLayout relativeLayout16;

    public PurseActivityFragment() {
    }

    @Override
    protected void DestroyView() {

    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {


        DisplayMetrics dm = new DisplayMetrics();
        //获取屏幕信息

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int screenWidth = (dm.widthPixels/3);

        int screenHeight =  (int)(dm.widthPixels/3*0.85);



        ViewGroup.LayoutParams layoutParams = relativeLayout1.getLayoutParams();

        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;

        relativeLayout1.setLayoutParams(layoutParams);




        layoutParams = relativeLayout4.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout4.setLayoutParams(layoutParams);


        layoutParams = relativeLayout7.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout7.setLayoutParams(layoutParams);


        layoutParams = relativeLayout10.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout10.setLayoutParams(layoutParams);



        layoutParams = relativeLayout13.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout13.setLayoutParams(layoutParams);


        layoutParams = relativeLayout16.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout16.setLayoutParams(layoutParams);

    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_purse;
    }


}
