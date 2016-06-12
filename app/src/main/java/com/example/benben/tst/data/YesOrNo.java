package com.example.benben.tst.data;

/**
 * Created by benben on 2016/6/7.
 * 其他数字常理
 */
public enum  YesOrNo {
    NO(0),//否
    YES(1);//是



    public int code;

    YesOrNo(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static YesOrNo valueOf(int code) {
        YesOrNo[] values = YesOrNo.values();
        for (int i = 0; i < values.length; i++) {
            YesOrNo retInfoa = values[i];
            if (retInfoa.code == code) {
                return retInfoa;
            }
        }
        return null;
    }


    public static void mian(String[] args) {
        YesOrNo retInfo = YesOrNo.valueOf(1);
    }

}
