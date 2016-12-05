package com.example.machenike.mymovie.utils;

import android.content.Context;

/**
 * Created by Machenike on 2016/12/5.
 */
public class GetUrl {

    static final public String  getPath(String path,Context mContext){

        String qian = path.substring(0, path.indexOf("w"));
        String hou = path.substring(path.indexOf("w") + 3);
        int px = DensityUtil.dip2px(mContext,90);
        int px1 = DensityUtil.dip2px(mContext,150);
        String urlUpNew0 = qian + px + "." + px1 + hou;
        return urlUpNew0;
    }

}
