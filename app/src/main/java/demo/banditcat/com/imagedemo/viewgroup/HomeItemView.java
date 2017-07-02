package demo.banditcat.com.imagedemo.viewgroup;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;


import butterknife.BindView;
import butterknife.ButterKnife;
import demo.banditcat.com.imagedemo.R;
import demo.banditcat.com.imagedemo.model.BankModel;
import demo.banditcat.com.imagedemo.utils.ViewUtils;

/**
 * 文件名称：{@link HomeItemView}
 * <br/>
 * 功能描述：首页视图
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
public class HomeItemView extends RelativeLayout {


    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;

    @BindView(R.id.leftBankImage)
    AppCompatImageView leftBankImage;

    private boolean alreadyInflated = false;

    public AppCompatTextView getHandleTitle() {
        return tvTitle;
    }

    Context mContext;//上下文

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static HomeItemView build(Context context) {
        HomeItemView instance = new HomeItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public HomeItemView(Context context) {
        super(context);
        mContext = context;
    }

    Drawable mDrawable;

    /**
     * 绑定按钮标题
     *
     * @param homeHandleModel 操作视图模型
     */
    public void binder(BankModel homeHandleModel, Integer defaultBank) {

        tvTitle.setText(homeHandleModel.getBankName());


        if (defaultBank == homeHandleModel.getType()) {

            mDrawable = mContext.getResources().getDrawable(R.mipmap.ic_ok);
            mDrawable.setBounds(0, 0, 36,36);
            tvTitle.setCompoundDrawables(null, null, mDrawable, null); //设置右边图标
        } else {
            tvTitle.setCompoundDrawables(null, null, null, null); //设置右边图标
        }

        ViewUtils.initBankInfo(leftBankImage, homeHandleModel.getType());


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
            inflate(getContext(), R.layout.item_bank, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
