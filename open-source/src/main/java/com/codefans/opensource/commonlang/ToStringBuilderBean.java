package com.codefans.opensource.commonlang;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author: caishengzhi
 * @date: 2018-05-29 16:27
 * 这种方式的好处是:
 *    如果domain新增字段了，toString方法不用修改
 * 缺点：
 *    需要继承一个类，这样就不能继承其他类了
 */
public class ToStringBuilderBean implements Serializable {

    //重新toString方法
    @Override
    public String toString() {
        try {
            return ToStringBuilder.reflectionToString(this,
                    ToStringStyle.SHORT_PREFIX_STYLE);
        } catch (Exception e) {
            return "";
        }
    }

}
