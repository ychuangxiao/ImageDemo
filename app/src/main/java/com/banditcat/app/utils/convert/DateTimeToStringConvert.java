package com.banditcat.app.utils.convert;

import com.ilogie.android.library.common.util.TimeUtils;
import com.ilogie.android.library.common.util.VerifierUtils;
import com.ilogie.android.transformer.parser.AbstractParser;


/**
 * 文件名称：{@link DateTimeToStringConvert}
 * <br/>
 * 功能描述：Date To String
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：15/12/1 15:46
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：15/12/1 15:46
 * <br/>
 * 修改备注：
 *
 *
 */
public class DateTimeToStringConvert extends AbstractParser<Long, String> {


    private static DateTimeToStringConvert instance;

    private DateTimeToStringConvert() {
    }

    public static DateTimeToStringConvert getInstance() {
        if (instance == null) {
            instance = new DateTimeToStringConvert();
        }
        return instance;
    }
    @Override
    protected String onParse(Long date) {


        if(VerifierUtils.getInstance().verifierLong(date)>=1L)
        {
            return TimeUtils.getTime(date, TimeUtils.DEFAULT_DATE_FORMAT_NO_SECOND);
        }

        return "";
    }
}

