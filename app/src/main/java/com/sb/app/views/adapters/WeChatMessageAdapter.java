package com.sb.app.views.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.AndroidApplication;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.views.listeners.ContactClickListener;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017/7/23.
 */

public class WeChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static Context mContext;

    private final LayoutInflater inflater;


    @Inject
    public WeChatMessageAdapter(AndroidApplication context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);

    }


    List<WebChatMessageRealm> mMessageRealms;

    public void setMessageRealms(List<WebChatMessageRealm> contactRealms) {
        mMessageRealms = contactRealms;
        this.notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {


        WebChatMessageRealm webChatMessageRealm = mMessageRealms.get(position);


        //红包
        if (webChatMessageRealm.getMessageType() == AppConstant.MESSAGE_TYPE_RED_PACKED) {
            return (webChatMessageRealm.getUserRealm().isMe()) ? AppConstant.MESSAGE_TYPE_ME_SEND_RED_PACKED :
                    AppConstant.MESSAGE_TYPE_OTHER_SEND_RED_PACKED;
        } else  {
            return (webChatMessageRealm.getUserRealm().isMe()) ? AppConstant.MESSAGE_TYPE_ME_SEND_TRANSFER :
                    AppConstant.MESSAGE_TYPE_OTHER_SEND_TRANSFER;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == AppConstant.MESSAGE_TYPE_OTHER_SEND_RED_PACKED) {
            return new RedPacketViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_redpacket_we_chat, null));
        } else if (viewType == AppConstant.MESSAGE_TYPE_ME_SEND_RED_PACKED) {
            return new RedPacketViewHolder(LayoutInflater.from(mContext).inflate(R.layout
                    .row_redpacket_right_we_chat, null));
        } else if (viewType == AppConstant.MESSAGE_TYPE_ME_SEND_TRANSFER) {
            return new TransferViewHolder(LayoutInflater.from(mContext).inflate(R.layout
                    .row_transfer_right_we_chat, null));
        } else if (viewType == AppConstant.MESSAGE_TYPE_ME_SEND_TRANSFER) {
            return new TransferViewHolder(LayoutInflater.from(mContext).inflate(R.layout
                    .row_transfer_we_chat, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RedPacketViewHolder) {

            RedPacketViewHolder redPacketViewHolder = (RedPacketViewHolder) holder;
            redPacketViewHolder.binder(mMessageRealms.get(position));

        } else if (holder instanceof TransferViewHolder) {

            TransferViewHolder transferViewHolder = (TransferViewHolder) holder;
            transferViewHolder.binder(mMessageRealms.get(position));

        }

    }

    @Override
    public int getItemCount() {
        return mMessageRealms == null ? 0 : mMessageRealms.size();
    }


    public class RedPacketViewHolder extends RecyclerView.ViewHolder {

        protected static final String TAG = "HeaderViewHolder";


        public RedPacketViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        WebChatMessageRealm mContactRealm;


        public void binder(WebChatMessageRealm webChatMessageRealm) {

            this.mContactRealm = webChatMessageRealm;

        }
    }


    public class TransferViewHolder extends RecyclerView.ViewHolder {


        public TransferViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        WebChatMessageRealm mContactRealm;


        public void binder(WebChatMessageRealm webChatMessageRealm) {

            this.mContactRealm = webChatMessageRealm;

        }
    }


    ContactClickListener<WebChatMessageRealm> mContactClickListener;


    public void setContactClickListener(ContactClickListener<WebChatMessageRealm> contactClickListener) {
        mContactClickListener = contactClickListener;
    }
}
