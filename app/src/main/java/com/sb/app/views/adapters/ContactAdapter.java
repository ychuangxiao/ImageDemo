package com.sb.app.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.AndroidApplication;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.menu.HomeHandleModel;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.activitys.tencent.ContactDetailActivity;
import com.sb.app.views.listeners.ContactClickListener;
import com.sb.app.views.listeners.RecyclerClickListener;
import com.sb.data.entitys.realm.ContactRealm;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by banditcat on 2017/7/23.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static Context mContext;

    private final LayoutInflater inflater;


    @Inject
    public ContactAdapter(AndroidApplication context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);

    }


    List<ContactRealm> mContactRealms;

    public void setContactRealms(List<ContactRealm> contactRealms) {
        mContactRealms = contactRealms;
        this.notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_HEADER.ordinal();
        } else if (position == getItemCount() - 1) {
            return ITEM_TYPE.ITEM_TYPE_FOOT.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_ITEM.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.ITEM_TYPE_HEADER.ordinal()) {
            return new HeaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_contact_header, null));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_ITEM.ordinal()) {
            return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_contact, null));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_FOOT.ordinal()) {
            return new FootViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_contact_footer, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ItemViewHolder) {

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.binder(mContactRealms.get(position));

        } else if (holder instanceof FootViewHolder) {

            FootViewHolder footViewHolder = (FootViewHolder) holder;
            footViewHolder.binder(mContactRealms.size() - 2);

        }

    }

    @Override
    public int getItemCount() {
        return mContactRealms == null ? 0 : mContactRealms.size();
    }


    public static enum ITEM_TYPE {
        ITEM_TYPE_HEADER, ITEM_TYPE_ITEM, ITEM_TYPE_FOOT
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        protected static final String TAG = "HeaderViewHolder";


        @BindView(R.id.tvNewFriend)
        AppCompatTextView tvNewFriend;

        @BindView(R.id.tvGroupChat)
        AppCompatTextView tvGroupChat;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        protected static final String TAG = "HeaderViewHolder";

        @BindView(R.id.tvUserNick)
        AppCompatTextView tvUserNick;

        @BindView(R.id.avatarImage)
        AppCompatImageView avatarImage;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        ContactRealm mContactRealm;

        @OnClick(R.id.avatarConstraintLayout)
        void onContactClick() {


            if (mContactClickListener != null) {
                mContactClickListener.onItemClickListener(mContactRealm);
            }
        }

        public void binder(ContactRealm contactRealm) {

            this.mContactRealm = contactRealm;
            tvUserNick.setText(contactRealm.getUserNick());


            if (mContactRealm.isSystem()) {
                avatarImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                        .getImageIndex()]);
            }
        }
    }


    public class FootViewHolder extends RecyclerView.ViewHolder {

        protected static final String TAG = "FootViewHolder";

        @BindView(R.id.tvContactCount)
        AppCompatTextView tvContactCount;

        public FootViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void binder(int count) {
            tvContactCount.setText(String.format(tvContactCount.getTag().toString(), count));
        }


    }


    ContactClickListener<ContactRealm> mContactClickListener;


    public void setContactClickListener(ContactClickListener<ContactRealm> contactClickListener) {
        mContactClickListener = contactClickListener;
    }
}
