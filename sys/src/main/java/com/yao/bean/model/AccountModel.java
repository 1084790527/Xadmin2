package com.yao.bean.model;

import com.yao.bean.db.AccountPojo;

/**
 * @author 妖妖
 * @date 10:36 2020/12/29
 */

public class AccountModel extends AccountPojo{
    private String sysName;
    private String creOperName;
    private String lastModOperName;

    public AccountModel() {

    }
    public AccountModel(AccountPojo account){
        setId(account.getId());
        setAgencyId(account.getAgencyId());
        setSysId(account.getSysId());
        setState(account.getState());
        setLoginId(account.getLoginId());
        setLoginPass(account.getLoginPass());
        setCreOperId(account.getCreOperId());
        setCreOperDate(account.getCreOperDate());
        setLastModOperId(account.getLastModOperId());
        setLastModOperDate(account.getLastModOperDate());
        setLoginState(account.getLoginState());
    }

    public String getSysName() {
        return sysName;
    }

    public AccountModel setSysName(String sysName) {
        this.sysName = sysName;
        return this;
    }

    public String getCreOperName() {
        return creOperName;
    }

    public AccountModel setCreOperName(String creOperName) {
        this.creOperName = creOperName;
        return this;
    }

    public String getLastModOperName() {
        return lastModOperName;
    }

    public AccountModel setLastModOperName(String lastModOperName) {
        this.lastModOperName = lastModOperName;
        return this;
    }


}
