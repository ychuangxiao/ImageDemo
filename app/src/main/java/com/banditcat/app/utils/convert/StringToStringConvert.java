package com.banditcat.app.utils.convert;

import com.ilogie.android.library.common.util.VerifierUtils;
import com.ilogie.android.transformer.parser.AbstractParser;

/**
 * 文件名称：{@link StringToStringConvert}
 * <br/>
 * 功能描述：字符串转换类
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：15/12/1 15:44
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：15/12/1 15:44
 * <br/>
 * 修改备注：
 *
 *
 */ 
public class StringToStringConvert extends AbstractParser<String, String> {


    private static StringToStringConvert instance;

    private StringToStringConvert() {
    }

    public static StringToStringConvert getInstance() {
        if (instance == null) {
            instance = new StringToStringConvert();
        }
        return instance;
    }
    
    @Override
    protected String onParse(String value) {

        return VerifierUtils.getInstance().verifierString(value);
    }
}

