package com.banditcat.app.utils.convert;

import com.ilogie.android.transformer.parser.AbstractParser;

/**
 * 文件名称：{@link LongToLongConvert}
 * <br/>
 * 功能描述：Long To Long
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：15/12/7 13:48
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：15/12/7 13:48
 * <br/>
 * 修改备注：
 *
 *
 */
public class LongToLongConvert extends AbstractParser<Long, Long> {

    private static LongToLongConvert instance;

    private LongToLongConvert() {
    }

    public static LongToLongConvert getInstance() {
        if (instance == null) {
            instance = new LongToLongConvert();
        }
        return instance;
    }

    @Override
    protected Long onParse(Long value) {


        return (null != value && value>0L) ? value :0L;


    }
}

