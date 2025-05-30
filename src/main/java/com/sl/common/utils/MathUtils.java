package com.sl.common.utils;

import java.util.Arrays;
import java.util.List;

public class MathUtils {

    /**
     * 执行插值计算
     * @param xList x轴数据列表（必须严格递增）
     * @param yList y轴数据列表（必须与xList长度一致）
     * @param target 需要计算的值
     * @return 计算结果，若无效则返回null
     */
    public static Double calculate(List<Double> xList, List<Double> yList, double target) {
        validateData(xList, yList);
        if (target <= xList.get(0)) return yList.get(0);
        if (target >= xList.get(xList.size() - 1)) return yList.get(xList.size() - 1);

        int index = binarySearch(xList, target);
        List<Double> lowerPoint = Arrays.asList(xList.get(index - 1), yList.get(index - 1));
        List<Double> upperPoint = Arrays.asList(xList.get(index), yList.get(index));

        double ratio = (target - lowerPoint.get(0)) / (upperPoint.get(0) - lowerPoint.get(0));
        return lowerPoint.get(1) + ratio * (upperPoint.get(1) - lowerPoint.get(1));

    }

    public static Double calculateReverse(List<Double> xList, List<Double> yList, double target) {
        return calculate( yList ,xList, target);
    }

    // 数据验证模块（引用自[1](@ref)）
    private static void validateData(List<Double> xList, List<Double> yList) {
        if (xList == null || yList == null || xList.isEmpty() || yList.isEmpty()) {
            throw new IllegalArgumentException("数据列表不能为空");
        }
        if (xList.size() != yList.size()) {
            throw new IllegalArgumentException("x/y数据长度不一致");
        }
        // 验证x轴严格递增（引用自[1](@ref)）
        for (int i = 1; i < xList.size(); i++) {
            if (xList.get(i) <= xList.get(i - 1)) {
                throw new IllegalArgumentException("x轴数据必须严格递增");
            }
        }
    }

    // 二分查找定位区间（引用自[1](@ref)）
    private static int binarySearch(List<Double> list, double target) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            double midValue = list.get(mid);
            if (midValue < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
