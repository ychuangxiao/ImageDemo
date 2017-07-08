package com.banditcat.app.utils.convert;

import com.ilogie.android.library.common.util.StringUtils;
import com.ilogie.android.transformer.parser.AbstractParser;

import java.math.BigDecimal;

/**
 * 文件名称：{@link StringToScaleBigDecimalConvert}
 * <br/>
 * 功能描述：字符串与 BigDecimal之间的转换类
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
public class StringToScaleBigDecimalConvert extends AbstractParser<String, BigDecimal> {
    @Override
    protected BigDecimal onParse(String value) {

        if (StringUtils.isNotEmpty(value)) {
            return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);

        }

        return BigDecimal.ZERO;
    }
}

