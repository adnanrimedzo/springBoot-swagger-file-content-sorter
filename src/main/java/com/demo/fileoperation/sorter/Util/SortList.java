package com.demo.fileoperation.sorter.Util;

import java.util.ArrayList;
import java.util.List;

public class SortList {

    private static List<Integer> merge(List<Integer> left, List<Integer> right, List<Integer> result) {
        if (right.isEmpty()) {
            result.addAll(left);
        } else if (left.isEmpty()) {
            result.addAll(right);
        } else {
            if (left.get(0) <= right.get(0)) {
                result.add(left.get(0));
                return merge(left.subList(1, left.size()), right, result);
            } else {
                result.add(right.get(0));
                return merge(left, right.subList(1, right.size()), result);
            }
        }
        return result;
    }

    public static List<Integer> mergeSort(List<Integer> list) {
        int middle = list.size() / 2;
        if (middle == 0)
            return list;
        return merge(mergeSort(list.subList(0, middle)), mergeSort(list.subList(middle, list.size())),
                new ArrayList<>());
    }

}
