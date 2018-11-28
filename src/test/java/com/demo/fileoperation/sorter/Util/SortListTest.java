package com.demo.fileoperation.sorter.Util;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class SortListTest {

    @Test
    public void merge() {
        List<Integer> numberList = Arrays.asList(9, 2, -2, 7, 5);
        List<Integer> sortedList = SortList.mergeSort(numberList);
        assertThat(sortedList, CoreMatchers.hasItems(-2, 2, 5, 7, 9));
    }
}