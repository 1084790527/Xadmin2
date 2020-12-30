package com.yao.bean.db;

import java.io.Serializable;
import java.util.Date;

public class AccountPojo implements Serializable {
    private String id;
    private String agencyId;
    private String sysId;
    private String state;
    private String inState;
    private String loginId;
    private String loginPass;
    private String creOperId;
    private Date creOperDate;
    private String lastModOperId;
    private Date lastModOperDate;
    private String loginState;

    public String getLoginState() {
        return loginState;
    }

    public AccountPojo setLoginState(String loginState) {
        this.loginState = loginState;
        return this;
    }

    public String getInState() {
        return inState;
    }

    public AccountPojo setInState(String inState) {
        this.inState = inState;
        return this;
    }

    public String getId() {
        return id;
    }
    public AccountPojo setId(String id) {
        this.id = id;
        return this;
    }

    public String getAgencyId() {
        return agencyId;
    }
    public AccountPojo setAgencyId(String agencyId) {
        this.agencyId = agencyId;
        return this;
    }

    public String getSysId() {
        return sysId;
    }
    public AccountPojo setSysId(String sysId) {
        this.sysId = sysId;
        return this;
    }

    public String getState() {
        return state;
    }
    public AccountPojo setState(String state) {
        this.state = state;
        return this;
    }

    public String getLoginId() {
        return loginId;
    }
    public AccountPojo setLoginId(String loginId) {
        this.loginId = loginId;
        return this;
    }

    public String getLoginPass() {
        return loginPass;
    }
    public AccountPojo setLoginPass(String loginPass) {
        this.loginPass = loginPass;
        return this;
    }

    public String getCreOperId() {
        return creOperId;
    }
    public AccountPojo setCreOperId(String creOperId) {
        this.creOperId = creOperId;
        return this;
    }

    public Date getCreOperDate() {
        return creOperDate;
    }
    public AccountPojo setCreOperDate(Date creOperDate) {
        this.creOperDate = creOperDate;
        return this;
    }

    public String getLastModOperId() {
        return lastModOperId;
    }
    public AccountPojo setLastModOperId(String lastModOperId) {
        this.lastModOperId = lastModOperId;
        return this;
    }

    public Date getLastModOperDate() {
        return lastModOperDate;
    }
    public AccountPojo setLastModOperDate(Date lastModOperDate) {
        this.lastModOperDate = lastModOperDate;
        return this;
    }

}
