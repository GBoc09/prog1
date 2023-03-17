package com.example.prog1.bean;

public class CominicationBean implements Bean{
    private String str;

    public CominicationBean(){}
    public CominicationBean(String str){setStr(str);}

    public String getStr() {
        return str;
    }
    public void setStr(String str) {
        this.str = str;
    }
}
