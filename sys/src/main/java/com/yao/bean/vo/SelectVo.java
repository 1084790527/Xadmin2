package com.yao.bean.vo;
/**
 * @author 妖妖
 * @date 11:20 2020/12/29
 */


public class SelectVo {
    private String value;
    private String id;
    private boolean state;

    public boolean isState() {
        return state;
    }

    public SelectVo setState(boolean state) {
        this.state = state;
        return this;
    }

    public String getValue() {
        return value;
    }

    public SelectVo setValue(String value) {
        this.value = value;
        return this;
    }

    public String getId() {
        return id;
    }

    public SelectVo setId(String id) {
        this.id = id;
        return this;
    }
}
