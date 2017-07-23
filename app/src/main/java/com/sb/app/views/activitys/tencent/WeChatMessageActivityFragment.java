package com.sb.app.views.activitys.tencent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeChatMessageActivityFragment extends Fragment {

    public WeChatMessageActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_we_chat_message, container, false);
    }
}
