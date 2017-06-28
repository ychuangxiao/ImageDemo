 package com.banditcat.common.fontawesom;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.banditcat.common.fontawesom.typeface.IIcon;
import com.banditcat.common.fontawesom.typeface.ITypeface;
import com.banditcat.common.fontawesom.utils.Utils;


 /**
 * A custom {@link Drawable} which can display icons from icon fonts.
 */
public class IconicsDrawable extends Drawable {
    public static final int ANDROID_ACTIONBAR_ICON_SIZE_DP = 24;
    public static final int ANDROID_ACTIONBAR_ICON_SIZE_PADDING_DP = 6;

    private Context mContext;

    private int mSize = -1;

    private Paint mIconPaint;
    private Paint mContourPaint;

    private int mBackgroundColor = -1;

    private Rect mPaddingBounds;
    private RectF mPathBounds;

    private Path mPath;

    private int mIconPadding;
    private int mContourWidth;

    private int mIconOffsetX = 0;
    private int mIconOffsetY = 0;

    private int mAlpha = 255;

    private boolean mDrawContour;

    private IIcon mIcon;

    public IconicsDrawable(Context context, String icon) {
        mContext = context.getApplicationContext();
        prepare();

        ITypeface font = Iconics.findFont(icon.substring(0, 3));
        icon = icon.replace("-", "_");
        icon(font.getIcon(icon));
    }

    public IconicsDrawable(Context context, final IIcon icon) {
        mContext = context.getApplicationContext();
        prepare();
        icon(icon);
    }

    public IconicsDrawable(Context context, final ITypeface typeface, final IIcon icon) {
        mContext = context.getApplicationContext();
        prepare();
        icon(typeface, icon);
    }

    private void prepare() {
        mIconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mContourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mContourPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();

        mPathBounds = new RectF();
        mPaddingBounds = new Rect();
    }

    /**
     * Loads and draws given.
     *
     * @param icon
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable icon(IIcon icon) {
        mIcon = icon;

        ITypeface typeface = icon.getTypeface();
        mIconPaint.setTypeface(typeface.getTypeface(mContext));
        invalidateSelf();
        return this;
    }

    /**
     * Loads and draws given.
     *
     * @param typeface
     * @param icon
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable icon(ITypeface typeface, IIcon icon) {
        mIcon = icon;
        mIconPaint.setTypeface(typeface.getTypeface(mContext));
        invalidateSelf();
        return this;
    }

    /**
     * Set the color of the drawable.
     *
     * @param color The color, usually from android.graphics.Color or 0xFF012345.
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable color(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        mIconPaint.setColor(Color.rgb(red, green, blue));
        setAlpha(Color.alpha(color));
        invalidateSelf();
        return this;
    }

    /*
    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color));
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
    */

    /**
     * Set the color of the drawable.
     *
     * @param colorRes The color resource, from your R file.
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable colorRes(int colorRes) {
        return color(mContext.getResources().getColor(colorRes));
    }


    /**
     * set the icon offset for X from resource
     *
     * @param iconOffsetXRes
     * @return
     */
    public IconicsDrawable iconOffsetXRes(int iconOffsetXRes) {
        return iconOffsetXPx(mContext.getResources().getDimensionPixelSize(iconOffsetXRes));
    }

    /**
     * set the icon offset for X as dp
     *
     * @param iconOffsetXDp
     * @return
     */
    public IconicsDrawable iconOffsetXDp(int iconOffsetXDp) {
        return iconOffsetXPx(Utils.convertDpToPx(mContext, iconOffsetXDp));
    }

    /**
     * set the icon offset for X
     *
     * @param iconOffsetX
     * @return
     */
    public IconicsDrawable iconOffsetXPx(int iconOffsetX) {
        this.mIconOffsetX = iconOffsetX;
        return this;
    }

    /**
     * set the icon offset for Y from resource
     *
     * @param iconOffsetYRes
     * @return
     */
    public IconicsDrawable iconOffsetYRes(int iconOffsetYRes) {
        return iconOffsetYPx(mContext.getResources().getDimensionPixelSize(iconOffsetYRes));
    }

    /**
     * set the icon offset for Y as dp
     *
     * @param iconOffsetYDp
     * @return
     */
    public IconicsDrawable iconOffsetYDp(int iconOffsetYDp) {
        return iconOffsetYPx(Utils.convertDpToPx(mContext, iconOffsetYDp));
    }

    /**
     * set the icon offset for Y
     *
     * @param iconOffsetY
     * @return
     */
    public IconicsDrawable iconOffsetYPx(int iconOffsetY) {
        this.mIconOffsetY = iconOffsetY;
        return this;
    }

    /**
     * Set the padding of the drawable from res
     *
     * @param dimenRes
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable paddingRes(int dimenRes) {
        return paddingPx(mContext.getResources().getDimensionPixelSize(dimenRes));
    }


    /**
     * Set the padding in dp for the drawable
     *
     * @param iconPadding
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable paddingDp(int iconPadding) {
        return paddingPx(Utils.convertDpToPx(mContext, iconPadding));
    }

    /**
     * Set a padding for the.
     *
     * @param iconPadding
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable paddingPx(int iconPadding) {
        if (mIconPadding != iconPadding) {
            mIconPadding = iconPadding;
            if (mDrawContour) {
                mIconPadding += mContourWidth;
            }

            invalidateSelf();
        }
        return this;
    }

    /**
     * Set the size of this icon to the standard Android ActionBar.
     *
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable actionBarSize() {
        return sizeDp(ANDROID_ACTIONBAR_ICON_SIZE_DP);
    }

    /**
     * Sets the size and the Padding to the correct values to be used for the actionBar / toolBar
     *
     * @return
     */
    public IconicsDrawable actionBar() {
        sizeDp(ANDROID_ACTIONBAR_ICON_SIZE_DP + (2 * ANDROID_ACTIONBAR_ICON_SIZE_PADDING_DP));
        paddingDp(ANDROID_ACTIONBAR_ICON_SIZE_PADDING_DP);
        return this;
    }

    public IconicsDrawable actionBar(Float size) {
        sizeDp((int) (size + (2 * size)));
        paddingDp(size.intValue());
        return this;
    }

    /**
     * Set the size of the drawable.
     *
     * @param dimenRes The dimension resource.
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable sizeRes(int dimenRes) {
        return sizePx(mContext.getResources().getDimensionPixelSize(dimenRes));
    }


    /**
     * Set the size of the drawable.
     *
     * @param size The size in density-independent pixels (dp).
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable sizeDp(int size) {
        return sizePx(Utils.convertDpToPx(mContext, size));
    }

    /**
     * Set the size of the drawable.
     *
     * @param size The size in pixels (px).
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable sizePx(int size) {
        this.mSize = size;
        setBounds(0, 0, size, size);
        invalidateSelf();
        return this;
    }


    /**
     * Set contour color for the.
     *
     * @param contourColor
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable contourColor(int contourColor) {
        mContourPaint.setColor(contourColor);
        drawContour(true);
        invalidateSelf();
        return this;
    }

    /**
     * Set contour color from color res.
     *
     * @param contourColorRes
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable contourColorRes(int contourColorRes) {
        mContourPaint.setColor(mContext.getResources().getColor(contourColorRes));
        drawContour(true);
        invalidateSelf();
        return this;
    }

    /**
     * set background color
     *
     * @param backgroundColor
     * @return
     */
    public IconicsDrawable backgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
        return this;
    }

    /**
     * set background color from res
     *
     * @param backgroundColorRes
     * @return
     */
    public IconicsDrawable backgroundColorRes(int backgroundColorRes) {
        this.mBackgroundColor = mContext.getResources().getColor(backgroundColorRes);
        return this;
    }


    /**
     * Set contour width from an dimen res for the icon
     *
     * @param contourWidthRes
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable contourWidthRes(int contourWidthRes) {
        return contourWidthPx(mContext.getResources().getDimensionPixelSize(contourWidthRes));
    }

    /**
     * Set contour width from dp for the icon
     *
     * @param contourWidthDp
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable contourWidthDp(int contourWidthDp) {
        return contourWidthPx(Utils.convertDpToPx(mContext, contourWidthDp));
    }

    /**
     * Set contour width for the icon.
     *
     * @param contourWidth
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable contourWidthPx(int contourWidth) {
        mContourWidth = contourWidth;
        mContourPaint.setStrokeWidth(mContourWidth);
        drawContour(true);
        invalidateSelf();
        return this;
    }

    /**
     * Enable/disable contour drawing.
     *
     * @param drawContour
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable drawContour(boolean drawContour) {
        if (mDrawContour != drawContour) {
            mDrawContour = drawContour;

            if (mDrawContour) {
                mIconPadding += mContourWidth;
            } else {
                mIconPadding -= mContourWidth;
            }

            invalidateSelf();
        }
        return this;
    }

    /**
     * Set the colorFilter
     *
     * @param cf
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable colorFilter(ColorFilter cf) {
        setColorFilter(cf);
        return this;
    }

    /**
     * Sets the opacity
     *
     * @param alpha
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable alpha(int alpha) {
        setAlpha(alpha);
        return this;
    }

    /**
     * Sets the style
     *
     * @param style
     * @return The current IconExtDrawable for chaining.
     */
    public IconicsDrawable style(Paint.Style style) {
        mIconPaint.setStyle(style);
        return this;
    }

    @Override
    public void draw(Canvas canvas) {
        if (mIcon != null) {
            final Rect viewBounds = getBounds();

            updatePaddingBounds(viewBounds);
            updateTextSize(viewBounds);
            offsetIcon(viewBounds);

            if (mBackgroundColor != -1) {
                canvas.drawColor(mBackgroundColor);
            }

            mPath.close();

            if (mDrawContour) {
                canvas.drawPath(mPath, mContourPaint);
            }

            mIconPaint.setAlpha(mAlpha);

            canvas.drawPath(mPath, mIconPaint);
        }
    }

    @Override
    public boolean isStateful() {
        return true;
    }

    @Override
    public boolean setState(int[] stateSet) {
        setAlpha(mAlpha);
        return true;
    }

    @Override
    public int getIntrinsicWidth() {
        return mSize;
    }

    @Override
    public int getIntrinsicHeight() {
        return mSize;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }


    @Override
    public void setAlpha(int alpha) {
        //mIconPaint.setAlpha(alpha);
        mAlpha = alpha;
    }

    @Override
    public int getAlpha() {
        return mAlpha;
    }

    /**
     * just a helper method to get the alpha value
     *
     * @return
     */
    public int getCompatAlpha() {
        return mAlpha;
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mIconPaint.setColorFilter(cf);
    }

    /**
     * Creates a BitMap to use in Widgets or anywhere else
     *
     * @return bitmap to set
     */
    public Bitmap toBitmap() {
        if (mSize == -1) {
            this.actionBarSize();
        }

        final Bitmap bitmap = Bitmap.createBitmap(this.getIntrinsicWidth(), this.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        this.style(Paint.Style.FILL);

        final Canvas canvas = new Canvas(bitmap);
        this.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        this.draw(canvas);

        return bitmap;
    }

    //------------------------------------------
    // PRIVATE HELPER METHODS
    //------------------------------------------

    /**
     * Update the Padding Bounds
     *
     * @param viewBounds
     */
    private void updatePaddingBounds(Rect viewBounds) {
        if (mIconPadding >= 0
                && !(mIconPadding * 2 > viewBounds.width())
                && !(mIconPadding * 2 > viewBounds.height())) {
            mPaddingBounds.set(
                    viewBounds.left + mIconPadding,
                    viewBounds.top + mIconPadding,
                    viewBounds.right - mIconPadding,
                    viewBounds.bottom - mIconPadding);
        }
    }

    /**
     * Update the TextSize
     *
     * @param viewBounds
     */
    private void updateTextSize(Rect viewBounds) {
        float textSize = (float) viewBounds.height() * 2;
        mIconPaint.setTextSize(textSize);

        String textValue = String.valueOf(mIcon.getCharacter());
        mIconPaint.getTextPath(textValue, 0, 1,
                0, viewBounds.height(), mPath);
        mPath.computeBounds(mPathBounds, true);

        float deltaWidth = ((float) mPaddingBounds.width() / mPathBounds.width());
        float deltaHeight = ((float) mPaddingBounds.height() / mPathBounds.height());
        float delta = (deltaWidth < deltaHeight) ? deltaWidth : deltaHeight;
        textSize *= delta;

        mIconPaint.setTextSize(textSize);

        mIconPaint.getTextPath(textValue, 0, 1,
                0, viewBounds.height(), mPath);
        mPath.computeBounds(mPathBounds, true);
    }

    /**
     * Set the icon offset
     *
     * @param viewBounds
     */
    private void offsetIcon(Rect viewBounds) {
        float startX = viewBounds.centerX() - (mPathBounds.width() / 2);
        float offsetX = startX - mPathBounds.left;

        float startY = viewBounds.centerY() - (mPathBounds.height() / 2);
        float offsetY = startY - (mPathBounds.top);

        mPath.offset(offsetX + mIconOffsetX, offsetY + mIconOffsetY);
    }
}