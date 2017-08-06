package com.sb.app.views.activitys.tencent;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.utils.PictureUtil;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ContactRealm;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class ChangeFaceActivity extends BaseActivity {
    @BindView(R.id.headerImage)
    AppCompatImageView mHeaderImage;
    @BindView(R.id.etFriendName)
    AppCompatEditText etFriendName;
    @BindView(R.id.btnHandleFace)
    AppCompatButton mBtnHandleFace;
    @BindView(R.id.btnHandleName)
    AppCompatButton mBtnHandleName;
    private String userId;
    private static final int PERMISSIONS_EXTERNAL_STORAGE = 801;
    private static final int REQUEST_CODE_PICK_IMAGE = 100;
    String imagePath;
    Realm mRealm;


    ContactRealm mContactRealm;

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

        injectExtras();
        setToolTitle(getString(R.string.title_activity_change_face)).setDisplayHome(true)
                .setHomeOnClickListener();
        mRealm = Realm.getDefaultInstance();


        mContactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                userId).findFirst();

        etFriendName.setText(mContactRealm.getUserNick());

        if (mContactRealm.isSystem()) {
            mHeaderImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                    .getImageIndex()]);
        }
        else if (StringUtils.isNotEmpty(mContactRealm.getImgPath())){
            // 加载本地图片
            File file = new File(mContactRealm.getImgPath());
            Glide.with(this).load(file).into(mHeaderImage);
        }
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_face;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }


    /**
     * 初始化参数
     */
    private void injectExtras() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_ == null) {

            finish();
            return;
        }

        if (!extras_.containsKey(AppConstant.EXTRA_NO)) {
            finish();
            return;
        }


        userId = extras_.getString(AppConstant.EXTRA_NO);


        if (StringUtils.isEmpty(userId)) {
            finish();
            return;
        }


    }

    @OnClick(R.id.btnHandleName)
    void onHandleNameClick() {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mContactRealm.setUserNick(ViewUtils.getDefaultNick()[ViewUtils.getRandomIndex(28)]);

                etFriendName.setText(mContactRealm.getUserNick());


            }
        });
    }


    @OnClick(R.id.btnHandleFace)
    void onHandleFaveClick() {

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mContactRealm.setSystem(true);
                mContactRealm.setImageIndex(ViewUtils.getRandomIndex(28));
                mHeaderImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm.getImageIndex()]);


            }
        });

    }

    @OnClick(R.id.btnHandleSave)
    void onSaveClick() {

        if (StringUtils.isEmpty(etFriendName.getText().toString().trim()))
        {
            alertMsg("好友昵称不能为空！");
            return;
        }
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mContactRealm.setUserNick(etFriendName.getText().toString().trim());


            }
        });

        alertMsg("保存成功！");
    }

    @OnClick(R.id.headerImage)
    void onHeaderImageClick() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.requestPermissions(ChangeFaceActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSIONS_EXTERNAL_STORAGE);
                return;
            }
        }
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                setFilePath(getRealPathFromURI(uri));

            }
        }
    }


    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }


    private Matrix matrix = new Matrix();
    private Bitmap bitmap;


    public void setFilePath(String path) {

        if (this.bitmap != null && !this.bitmap.isRecycled()) {
            this.bitmap.recycle();
        }

        if (path == null) {
            return;
        }

        imagePath = path;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap original = BitmapFactory.decodeFile(path, options);

        try {
            ExifInterface exif = new ExifInterface(path);
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            Matrix matrix = new Matrix();
            int rotationInDegrees = PictureUtil.exifToDegrees(rotation);
            if (rotation != 0f) {
                matrix.preRotate(rotationInDegrees);
            }

            // 图片太大会导致内存泄露，所以在显示前对图片进行裁剪。
            int maxPreviewImageSize = 2560;

            int min = Math.min(options.outWidth, options.outHeight);
            min = Math.min(min, maxPreviewImageSize);

            WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            Point screenSize = new Point();
            windowManager.getDefaultDisplay().getSize(screenSize);
            min = Math.min(min, screenSize.x * 2 / 3);

            options.inSampleSize = PictureUtil.calculateInSampleSize(options, min, min);
            options.inScaled = true;
            options.inDensity = options.outWidth;
            options.inTargetDensity = min * options.inSampleSize;
            options.inPreferredConfig = Bitmap.Config.RGB_565;

            options.inJustDecodeBounds = false;
            this.bitmap = BitmapFactory.decodeFile(path, options);
        } catch (IOException e) {
            e.printStackTrace();
            this.bitmap = original;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        setBitmap(this.bitmap);
    }

    private void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        matrix.reset();
        centerImage(ViewUtils.dip2px(this, 48), ViewUtils.dip2px(this, 48));


        mHeaderImage.setImageBitmap(bitmap);

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mContactRealm.setSystem(false);
                mContactRealm.setImgPath(imagePath);

            }
        });

    }

    private void centerImage(int width, int height) {
        if (width <= 0 || height <= 0 || bitmap == null) {
            return;
        }
        float widthRatio = 1.0f * height / this.bitmap.getHeight();
        float heightRatio = 1.0f * width / this.bitmap.getWidth();

        float ratio = Math.min(widthRatio, heightRatio);

        float dx = (width - this.bitmap.getWidth()) / 2;
        float dy = (height - this.bitmap.getHeight()) / 2;
        matrix.setTranslate(0, 0);
        matrix.setScale(ratio, ratio, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        matrix.postTranslate(dx, dy);
    }

}
