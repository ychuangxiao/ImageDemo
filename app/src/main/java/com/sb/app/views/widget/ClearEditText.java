package com.sb.app.views.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sb.common.fontawesom.IconicsDrawable;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;


/**
 * 文件名称：{@link ClearEditText}
 * <br/>
 * 功能描述：清除文本编辑框
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/6/9 10:26
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/6/9 10:26
 * <br/>
 * 修改备注：
 */
public class ClearEditText extends AppCompatEditText implements View.OnTouchListener,View.OnFocusChangeListener,TextWatcher{

    private Drawable mClearDrawable;//清除图片
    private OnFocusChangeListener mOnFocusChangeListener;//焦点更改监听对象
    private OnTouchListener mOnTouchListener;//触摸监听对象

    public ClearEditText(Context context) {
        super(context);

        init(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context ctx){

        Drawable drawable = new IconicsDrawable(ctx, BaseFontAwesome.Icon.icon_delete).actionBar(4F)
                .paddingDp(0);

        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);


        DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
        mClearDrawable = wrappedDrawable;
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicHeight(), mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isFocused()) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int x = (int) event.getX();
        if (mClearDrawable.isVisible() && x > getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                setText("");
            }
            return true;
        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(view, event);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        mOnFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    private void setClearIconVisible(final boolean visible) {
        mClearDrawable.setVisible(visible, false);
        final Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                visible ? mClearDrawable : null,
                compoundDrawables[3]);
    }
}
