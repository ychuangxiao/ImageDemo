package com.sb.app.views.activitys.tencent;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;

import com.sb.ui.camera.CropImageIntentBuilder;
import com.bumptech.glide.Glide;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.utils.PictureUtil;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseDaggerActivity;
import com.sb.app.views.widget.ClearEditText;
import com.sb.data.entitys.realm.ContactRealm;

import java.io.File;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class EditWeChatActivity extends BaseDaggerActivity {
    private static final int PERMISSIONS_EXTERNAL_STORAGE = 801;
    private static final int REQUEST_CODE_PICK_IMAGE = 100;

    private static int REQUEST_PICTURE = 1;
    private static int REQUEST_CROP_PICTURE = 2;


    Realm mRealm;
    ContactRealm mContactRealm;
    @BindView(R.id.headerImage)
    AppCompatImageView mHeaderImage;
    @BindView(R.id.etFriendName)
    ClearEditText mEtFriendName;
    @BindView(R.id.etWeChatNo)
    ClearEditText mEtWeChatNo;
    @BindView(R.id.btnHandleSave)
    AppCompatButton mBtnHandleSave;

    String imagePath;


    File saveImageFile;


    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        setToolTitle(getString(R.string.title_activity_edit_we_chat)).setDisplayHome(true)
                .setHomeOnClickListener();


        PictureUtil.setAlbumName("sharkman");
        saveImageFile = new File(PictureUtil.getAlbumDir(), UUID.randomUUID().toString() + ".jpg");

        mRealm = Realm.getDefaultInstance();


        mContactRealm = mRealm.where(ContactRealm.class).equalTo("isMe",
                true).findFirst();

        mEtFriendName.setText(mContactRealm.getUserNick());
        mEtFriendName.setSelection(mEtFriendName.length());

        mEtWeChatNo.setText(mContactRealm.getWeChatNo());

        mEtWeChatNo.setSelection(mEtWeChatNo.length());

        if (mContactRealm.isSystem()) {
            mHeaderImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                    .getImageIndex()]);
        } else if (StringUtils.isNotEmpty(mContactRealm.getImgPath())) {
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
        return R.layout.activity_edit_we_chat;
    }

    /**
     * 初始化注解
     */
    @Override
    public void initInjector() {

    }

    /**
     * 初始化中间者
     */
    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnHandleSave)
    void onSaveClick() {

        if (StringUtils.isEmpty(mEtFriendName.getText().toString().trim())) {
            alertMsg("昵称不能为空！");
            return;
        }

        if (StringUtils.isEmpty(mEtWeChatNo.getText().toString().trim())) {
            alertMsg("微信号不能为空！");
            return;
        }


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mContactRealm.setUserNick(mEtFriendName.getText().toString().trim());
                mContactRealm.setWeChatNo(mEtWeChatNo.getText().toString().trim());

            }
        });

        alertMsg("保存成功！");
    }


    @OnClick(R.id.headerImage)
    void onHeaderImageClick() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.requestPermissions(EditWeChatActivity.this,
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
        } else if ((requestCode == REQUEST_CROP_PICTURE) && (resultCode == RESULT_OK)) {


            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {


                    mContactRealm.setSystem(false);
                    mContactRealm.setImgPath(saveImageFile.getPath());

                }
            });

            Glide.with(this).load(saveImageFile).into(mHeaderImage);

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




    public void setFilePath(String path) {

        File croppedImageFile = new File(path);
        imagePath = path;

        Uri saveImage = Uri.fromFile(saveImageFile);
        Uri croppedImage = Uri.fromFile(croppedImageFile);

        CropImageIntentBuilder cropImage = new CropImageIntentBuilder(200, 200, saveImage);
        cropImage.setOutlineColor(getResources().getColor(R.color.silver));
        cropImage.setSourceImage(croppedImage);

        startActivityForResult(cropImage.getIntent(this), REQUEST_CROP_PICTURE);



    }


}
