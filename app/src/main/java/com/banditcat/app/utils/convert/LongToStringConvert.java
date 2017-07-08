package com.banditcat.app.utils.convert;


import com.ilogie.android.library.common.util.VerifierUtils;
import com.ilogie.android.transformer.parser.AbstractParser;

/**
 * 文件名称：{@link LongToStringConvert}
 * <br/>
 * 功能描述：Long To String
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：15/11/30 10:20
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：15/11/30 10:20
 * <br/>
 * 修改备注：
 */
public class LongToStringConvert extends AbstractParser<Long, String> {
    private static LongToStringConvert instance;

    private LongToStringConvert() {
    }

    public static LongToStringConvert getInstance() {
        if (instance == null) {
            instance = new LongToStringConvert();
        }
        return instance;
    }


    @Override
    protected String onParse(Long values) {
        return VerifierUtils.getInstance().verifierLong(values) > 0L ? values.toString() : "";
    }
}

