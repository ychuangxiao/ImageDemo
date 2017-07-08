package com.banditcat.app.utils.convert;

import com.ilogie.android.library.common.util.TimeUtils;
import com.ilogie.android.library.common.util.VerifierUtils;
import com.ilogie.android.transformer.parser.AbstractParser;


/**
 * 文件名称：{@link DateToStringConvert}
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
public class DateToStringConvert extends AbstractParser<Long, String> {


    private static DateToStringConvert instance;

    private DateToStringConvert() {
    }

    public static DateToStringConvert getInstance() {
        if (instance == null) {
            instance = new DateToStringConvert();
        }
        return instance;
    }
    @Override
    protected String onParse(Long date) {


        if(VerifierUtils.getInstance().verifierLong(date)>=1L)
        {
            return TimeUtils.getTime(date, TimeUtils.DATE_FORMAT_DATE);
        }

        return "";
    }
}

