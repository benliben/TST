package com.example.benben.tst.http;

import org.apache.http.message.BasicNameValuePair;

import java.util.Comparator;

/**
 * Created by benben on 2016/6/12.
 * BasicNameValuePair比较的辅助类
 */
public class PairComparator implements Comparator {
    @Override
    public int compare(Object lhs, Object rhs) {
        if (lhs instanceof BasicNameValuePair && rhs instanceof BasicNameValuePair) {
            return ((BasicNameValuePair) lhs).getName().compareTo(((BasicNameValuePair) rhs).getName());
        }else {

        return 0;
    }}
}
