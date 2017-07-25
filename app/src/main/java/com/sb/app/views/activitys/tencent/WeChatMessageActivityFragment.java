package com.sb.app.views.activitys.tencent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.R;
import com.sb.app.views.adapters.BankAdapter;
import com.sb.app.views.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeChatMessageActivityFragment extends BaseFragment {


    @BindView(R.id.emojiRecycler)
    RecyclerView emojiRecycler;

    @BindView(R.id.btnEmoji)
    AppCompatImageView btnEmoji;



    BankAdapter mBankAdapter;

    public WeChatMessageActivityFragment() {
    }

    @Override
    protected void DestroyView() {

    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        mBaseRecyclerView = emojiRecycler;

        mBankAdapter = new BankAdapter(getActivity(), 0);

        initLinearLayoutRecyclerView(new GridLayoutManager(getActivity(),10)).setAdapter(mBankAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);

        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.bg_decoration_bottom));
        mBaseRecyclerView.addItemDecoration(dividerItemDecoration);


        mBankAdapter.setItems(initBankInfo());
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_we_chat_message;
    }



    @OnClick(R.id.btnEmoji)
    void onEmojiClick() {
        //emojiRecycler.setVisibility(View.VISIBLE);
    }
}
