package demo.banditcat.com.imagedemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by banditcat-pc on 2017/7/3.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract void DestroyView();

    Unbinder mUnbinder;
    protected RecyclerView mBaseRecyclerView;


    /**
     * 初始化视图，工具条等信息
     */
    public abstract void initView();

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    protected abstract int getContentViewId();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mRootView =inflater.inflate(getContentViewId(),container,false);
        ButterKnife.bind(this,mRootView);//绑定framgent
        return mRootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    @Override
    public void onDestroy() {

        DestroyView();
        if (mUnbinder != null)
        {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }



    /*
    * 获取订单号
    * */
    protected String randomOrderNo(long currentTimeMillis) {


        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(currentTimeMillis);

        DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
        StringBuilder stringBuilder = new StringBuilder(format2.format(calendar.getTime()));

        stringBuilder.append("2000400");

        for (int i = 0; i < 17; i++) {
            stringBuilder.append(String.valueOf((int) (Math.random() * 10)));
        }


        return stringBuilder.toString();
    }

    /**
     * 导航至新活动窗体
     *
     * @param intent
     */
    public void navigateActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        // overridePendingTransition(R.anim.animation_down_in, R.anim.animation_top_out);
    }

    public void navigateActivity(Intent intent ) {
        startActivity(intent);
        // overridePendingTransition(R.anim.animation_down_in, R.anim.animation_top_out);
    }

    /*
  *计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
  * 根据差值返回多长之间前或多长时间后
  * */
    public boolean getDistanceTime(long time1, long time2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff;
        boolean flag;
        if (time1 <= time2) {
            diff = time2 - time1;
            flag = true;
        } else {
            diff = time1 - time2;
            flag = false;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);


        if (hour >= 2 && flag) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * init LinearLayout RecyclerView
     *
     * @param layoutManager Set the {@link RecyclerView.LayoutManager} that this RecyclerView will
     *                      use.
     * @return widget for RecyclerView
     */
    protected RecyclerView initLinearLayoutRecyclerView(RecyclerView.LayoutManager layoutManager) {


        mBaseRecyclerView.setLayoutManager(layoutManager);
        mBaseRecyclerView.setHasFixedSize(true);
        // 设置item动画
        mBaseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return this.mBaseRecyclerView;
    }
}
