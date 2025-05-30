package com.sl.common.core;


/**
 * <h2>数组枚举接口</h2>
 *
 * <p>该接口用于定义与数组相关的操作和枚举方法，主要用于将数据库中常用的数值存储与其实际意义对应。</p>
 *
 * <p>实现该接口的枚举可以提供一个整型数组，以便于处理状态或标识符的集合。</p>
 *
 * <p>例如，可用于定义数据保存状态、权限等。</p>
 *
 * <p><strong>作者:</strong> zb</p>
 * <p><strong>创建日期:</strong> 2024/9/14</p>
 * <p><strong>版本:</strong> 1.0</p>
 */
public interface IntArrayValuable {

    /**
     * 获取整型数组。
     *
     * @return 包含整型值的数组
     */
    int[] array();
}

