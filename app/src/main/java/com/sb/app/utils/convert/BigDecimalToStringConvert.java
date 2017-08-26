package com.sb.app.utils.convert;

import com.sb.app.utils.MathUtils;
import com.sb.common.utils.BigDecimalUtils;
import com.ilogie.android.transformer.parser.AbstractParser;

import java.math.BigDecimal;

/**
 * 文件名称：BigDecimalToStringConvert
 * <br/>
 * 功能描述：BigDecimal To String
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：15/11/30 10:20
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：15/11/30 10:20
 * <br/>
 * 修改备注：
 */
public class BigDecimalToStringConvert extends AbstractParser<BigDecimal, String> {

    private static BigDecimalToStringConvert instance;

    private BigDecimalToStringConvert() {
    }

    public static BigDecimalToStringConvert getInstance() {
        if (instance == null) {
            instance = new BigDecimalToStringConvert();
        }
        return instance;
    }

    @Override
    protected String onParse(BigDecimal bigDecimal) {



        if (BigDecimalUtils.isNotNull(bigDecimal)) {
            return MathUtils.toString(bigDecimal);
        }
        return "0";
    }
}
