package com.pyc.java.board.base;



import com.pyc.java.board.util.Util;

import java.util.Map;

public class Rq {
    public String url;

    public Map<String, String> params;
    public String urlPath;

    public Rq(String url){
        this.url = url;
        params = Util.getParamsFromUrl(this.url);
        urlPath = Util.getPathFromUrl(this.url);
    }

    public Map<String,String> getParams(){
        return params;
    }

    public String getUrlPath(){
        return urlPath;
    }
}
