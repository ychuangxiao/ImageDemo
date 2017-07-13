package com.banditcat.app.views.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.banditcat.app.R;


/**
 * 文件名称：{@link BatteryView}
 * <br/>
 * 功能描述：电池
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/7/12 20:27
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/7/12 20:27
 * <br/>
 * 修改备注：
 */
public class BatteryView extends View {
    private int mPower = 100;
    private int orientation;
    private int width;
    private int height;
    private int mColor;

    public BatteryView(Context context) {
        super(context);
    }

    public BatteryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Battery);
        mColor = typedArray.getColor(R.styleable.Battery_batteryColor, 0xFFFFFFFF);
        orientation = typedArray.getInt(R.styleable.Battery_batteryOrientation, 0);
        mPower = typedArray.getInt(R.styleable.Battery_batteryPower, 100);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        /**
         * recycle() :官方的解释是：回收TypedArray，以便后面重用。在调用这个函数后，你就不能再使用这个TypedArray。
         * 在TypedArray后调用recycle主要是为了缓存。当recycle被调用后，这就说明这个对象从现在可以被重用了。
         *TypedArray 内部持有部分数组，它们缓存在Resources类中的静态字段中，这样就不用每次使用前都需要分配内存。
         */
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //对View上的內容进行测量后得到的View內容占据的宽度
        width = getMeasuredWidth();
        //对View上的內容进行测量后得到的View內容占据的高度
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //判断电池方向    horizontal: 0   vertical: 1
        if (orientation == 0) {
            drawHorizontalBattery(canvas);
        } else {
            drawVerticalBattery(canvas);
        }
    }

    /**
     * 绘制水平电池
     *
     * @param canvas
     */
    private void drawHorizontalBattery(Canvas canvas) {
       /* Paint paint;

        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);// 设置画笔的锯齿效果

        RectF rect = new RectF(50, 50, 200, 200);

        canvas.drawRect(rect, paint);

        paint.setColor(Color.RED);




        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                270,  //开始角度
                180, //扫过的角度
                true, //是否使用中心
                paint);*/


       Float rightHeader =10F;


        Paint paint = new Paint();
        paint.setColor(mColor);
        paint.setStyle(Paint.Style.STROKE);


        float strokeWidth = width / 20.f;
        float strokeWidth_2 = strokeWidth / 2;
        paint.setStrokeWidth(1.0F);
        RectF r1 = new RectF(strokeWidth_2, strokeWidth_2, width - strokeWidth - strokeWidth_2-rightHeader, height -
                strokeWidth_2);


        //设置外边框颜色为黑色
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);// 设置画笔的锯齿效果
        canvas.drawRoundRect(r1, 2.6F, 2.6F, paint);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);//充满


        //画电池内矩形电量
        float offset = (width - strokeWidth * 2) * mPower / 100.f;
        RectF r2 = new RectF(strokeWidth + 1, strokeWidth + 1, offset - 1-rightHeader, height - strokeWidth - 1);
        //根据电池电量决定电池内矩形电量颜色
        if (mPower < 30) {
            paint.setColor(Color.RED);
        }
        if (mPower >= 30 && mPower < 50) {
            paint.setColor(Color.YELLOW);
        }
        if (mPower >= 50) {
            paint.setColor(Color.GREEN);
        }

        canvas.drawRoundRect(r2, 2, 2, paint);

        float lastX = width - strokeWidth-rightHeader;
        //画电池头
        RectF r3 = new RectF(lastX  , height * 0.25f, width, height * 0.75f);
        //设置电池头颜色为黑色
        paint.setColor(Color.BLACK);


        //canvas.drawRect(r3,  paint);



        canvas.translate(-(width-lastX)+rightHeader*0.75F, 0);
        canvas.drawArc(r3, //弧线所使用的矩形区域大小
                270,  //开始角度
                180, //扫过的角度
                true, //是否使用中心
                paint);




    }

    /**
     * 绘制垂直电池
     *
     * @param canvas
     */
    private void drawVerticalBattery(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mColor);
        paint.setStyle(Paint.Style.STROKE);
        float strokeWidth = height / 20.0f;
        float strokeWidth2 = strokeWidth / 2;
        paint.setStrokeWidth(1.5F);
        int headHeight = (int) (strokeWidth + 0.5f+15F);
        RectF rect = new RectF(strokeWidth2, headHeight + strokeWidth2, width - strokeWidth2, height - strokeWidth2);

        canvas.drawRoundRect(rect, 2, 2, paint);
        paint.setStrokeWidth(0);
        float topOffset = (height - headHeight - strokeWidth) * (100 - mPower) / 100.0f;
        RectF rect2 = new RectF(strokeWidth, headHeight + strokeWidth + topOffset, width - strokeWidth, height -
                strokeWidth);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        canvas.drawRect(rect2, paint);
        //画电池头
        paint.setColor(Color.BLACK);
        //RectF headRect = new RectF(width / 4.0f, 2, width * 0.75f, headHeight);

        RectF headRect = new RectF(width / 4.0f, 0, width * 0.75f, headHeight);

        canvas.drawArc(headRect, //弧线所使用的矩形区域大小
                180,  //开始角度
                180, //扫过的角度
                true, //是否使用中心
                paint);

    }

    /**
     * 设置电池电量
     *
     * @param power
     */
    public void setPower(int power) {
        this.mPower = power;
        if (mPower < 0) {
            mPower = 100;
        }
        invalidate();//刷新VIEW
    }

    /**
     * 设置电池颜色
     *
     * @param color
     */
    public void setColor(int color) {
        this.mColor = color;
        invalidate();
    }

    /**
     * 获取电池电量
     *
     * @return
     */
    public int getPower() {
        return mPower;
    }
}
