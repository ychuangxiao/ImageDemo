package com.sb.common.fontawesom.typeface;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 文件名称：{@link BaseFontAwesome}
 * <br/>
 * 功能描述：字体
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/5/10 13:40
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/5/10 13:40
 * <br/>
 * 修改备注：
 */
public class BaseFontAwesome implements ITypeface {

    private static final String TTF_FILE = "iconfont.ttf";

    private static Typeface typeface = null;

    private static HashMap<String, Character> mChars;

    @Override
    public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<String, Character>();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }


        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return "icon";
    }

    @Override
    public String getFontName() {
        return "FontAwesome";
    }

    @Override
    public String getVersion() {
        return "3.2.1";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<String>();

        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }


        return icons;
    }

    @Override
    public String getAuthor() {
        return "banditcat";
    }

    @Override
    public String getUrl() {
        return "https://github.com/ychuangxiao";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getLicense() {
        return "SIL OFL 1.1";
    }

    @Override
    public String getLicenseUrl() {
        return "http://scripts.sil.org/OFL";
    }

    @Override
    public Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + TTF_FILE);
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }


    public enum Icon implements IIcon {


        icon_delete('\ue605'),

        icon_setting('\ue601'),

        icon_save('\ue684'),

        icon_phone('\ue65a'),

        icon_ali('\ue67c'),

        icon_nva_home('\ue600'),

        icon_more('\ue61f'),

        icon_repeat('\ue68b'),

        icon_checked('\ue65b'),

        icon_chat_time('\ue682'),

        icon_mobile('\ue64e'),

        icon_brow('\ue6a8'),

        icon_retract('\ue60a'),

        icon_content('\ue65d'),

        icon_weixin('\ue623'),

        icon_nav_more('\ue63b'),

        icon_user('\ue75c'),

        icon_nva_me('\ue6b3'),

        icon_red_packet('\ue61a'),

        icon_voice('\ue789'),

        icon_date('\ue616'),

        icon_key('\ue688'),

        icon_gengduo('\ue6c4'),

        icon_duihao('\ue61c'),

        icon_time('\ue698'),

        icon_camera('\ue666'),

        icon_right('\ue60d'),

        icon_video('\ue64f'),

        icon_transfer('\ue633');


        char character;

        Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return character;
        }

        public String getName() {
            return name();
        }

        // remember the typeface so we can use it later
        private static ITypeface typeface;

        public ITypeface getTypeface() {
            if (typeface == null) {
                typeface = new BaseFontAwesome();
            }
            return typeface;
        }
    }
}
