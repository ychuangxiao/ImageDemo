package com.sb.app.utils.convert;

import com.sb.common.utils.StringUtils;
import com.ilogie.android.transformer.parser.AbstractParser;

import java.math.BigDecimal;

/**
 * 文件名称：{@link StringToBigDecimalConvert}
 * <br/>
 * 功能描述：String and   BigDecimal Convert
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：15/12/1 15:46
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：15/12/1 15:46
 * <br/>
 * 修改备注：
 */
public class StringToBigDecimalConvert extends AbstractParser<String, BigDecimal> {


    private static StringToBigDecimalConvert instance;

    private StringToBigDecimalConvert() {
    }

    public static StringToBigDecimalConvert getInstance() {
        if (instance == null) {
            instance = new StringToBigDecimalConvert();
        }
        return instance;
    }

    @Override
    protected BigDecimal onParse(String value) {

        if (StringUtils.isNotEmpty(value)) {
            return new BigDecimal(value);

        }

        return BigDecimal.ZERO;
    }
}

