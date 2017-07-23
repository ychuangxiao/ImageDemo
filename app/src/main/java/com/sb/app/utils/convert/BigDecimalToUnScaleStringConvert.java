package com.sb.app.utils.convert;

import com.ilogie.android.library.common.util.BigDecimalUtils;
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
public class BigDecimalToUnScaleStringConvert extends AbstractParser<BigDecimal, String> {

    private static BigDecimalToUnScaleStringConvert instance;

    private BigDecimalToUnScaleStringConvert() {
    }

    public static BigDecimalToUnScaleStringConvert getInstance() {
        if (instance == null) {
            instance = new BigDecimalToUnScaleStringConvert();
        }
        return instance;
    }

    @Override
    protected String onParse(BigDecimal bigDecimal) {


        if (BigDecimalUtils.isNotNull(bigDecimal)) {
            return BigDecimalUtils.convertBigDecimalToString(bigDecimal);
        }
        return "";
    }
}
