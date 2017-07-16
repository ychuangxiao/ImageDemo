package com.banditcat.app.views.viewgroup;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.RelativeLayout;

import com.banditcat.app.R;
import com.banditcat.app.model.BankModel;
import com.banditcat.app.model.EditModel;
import com.banditcat.app.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名称：{@link EditTextItemView}
 * <br/>
 * 功能描述：编辑视图
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/1 17:08
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/1 17:08
 * <br/>
 * 修改备注：
 */
public class EditTextItemView extends RelativeLayout {


    private boolean alreadyInflated = false;


    @BindView(R.id.etChangeValue)
    AppCompatEditText etChangeValue;

    @BindView(R.id.btnHandle)
    AppCompatButton btnHandle;

    @BindView(R.id.tvChangeValue)
    AppCompatTextView tvChangeValue;


    @BindView(R.id.btnCancel)
    AppCompatButton btnCancel;

    Context mContext;//上下文


    public AppCompatEditText getEtChangeValue() {
        return etChangeValue;
    }

    public AppCompatButton getBtnHandle() {
        return btnHandle;
    }

    public AppCompatButton getBtnCancel() {
        return btnCancel;
    }

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static EditTextItemView build(Context context) {
        EditTextItemView instance = new EditTextItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public EditTextItemView(Context context) {
        super(context);
        mContext = context;
    }


    /**
     * 绑定按钮标题
     */
    public void binder(EditModel model) {

        tvChangeValue.setText(model.getHintText());
        etChangeValue.setText(model.getText());
        etChangeValue.setSelection(model.getText().length());

        etChangeValue.setInputType(model.getInputType());

        InputFilter[] filters = {new InputFilter.LengthFilter(model.getMaxLength())};
        etChangeValue.setFilters(filters);
    }


    /**
     * The malreadyInflated hack is needed because of an Android bug
     * which leads to infinite calls of onFinishInflate()
     * when inflating a layout with a parent and using
     * the <merge /> tag.
     */
    @Override
    public void onFinishInflate() {
        if (!alreadyInflated) {
            alreadyInflated = true;
            inflate(getContext(), R.layout.view_edittext, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
