package com.example.prog1.bean;

public class CominicationBean implements Bean{
    private Integer index;
    private String str;
    public CominicationBean(){}
    public CominicationBean(String str){setStr(str);}
    public CominicationBean(Integer i){setIndex(i);}

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
