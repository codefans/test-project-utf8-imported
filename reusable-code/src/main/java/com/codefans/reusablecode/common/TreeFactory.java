package com.codefans.reusablecode.common;

/**
 * Created by caishengzhi on 2017/9/28.
 */
public interface TreeFactory<T> {

    public T create(String treeType);

}
