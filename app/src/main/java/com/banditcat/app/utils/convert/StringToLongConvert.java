package com.banditcat.app.utils.convert;

import com.ilogie.android.library.common.util.StringUtils;
import com.ilogie.android.transformer.parser.AbstractParser;

/**
 * 文件名称：{@link StringToLongConvert}
 * <br/>
 * 功能描述：String and Long Convert
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：15/12/1 15:45
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：15/12/1 15:45
 * <br/>
 * 修改备注：
 *
 *
 */ 
public class StringToLongConvert extends AbstractParser<String, Long> {

    private static StringToLongConvert instance;

    private StringToLongConvert() {
    }

    public static StringToLongConvert getInstance() {
        if (instance == null) {
            instance = new StringToLongConvert();
        }
        return instance;
    }
    @Override
    protected Long onParse(String value) {

        if(StringUtils.isNotEmpty(value))
        {
            return Long.parseLong(value);
        }

        return 0L;
    }
}

