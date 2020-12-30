package com.yao.sys.dao;

import com.yao.bean.db.AccountPojo;

import java.util.List;

public interface AccountDao {
    public int insertRecord(AccountPojo record);

    public AccountPojo getRecordByKey(AccountPojo record);

    public AccountPojo getRecordByWhere(AccountPojo record);

    public List<AccountPojo> getRecordListByWhere(AccountPojo record);

    public int updateRecordByKey(AccountPojo record);

    public int deleteRecordByKey(AccountPojo record);

}
