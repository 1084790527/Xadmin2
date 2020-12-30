package com.yao.sys.service;
/**
 * @author 妖妖
 * @date 15:34 2020/12/29
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yao.bean.db.AccountPojo;
import com.yao.bean.db.SysPojo;
import com.yao.common.util.AESUtil;
import com.yao.sys.dao.AccountDao;
import com.yao.sys.dao.SysDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeskService {
    private static Log log = LogFactory.getLog(DeskService.class);

    @Autowired
    private SysDao sysDao;
    @Autowired
    private AccountDao accountDao;


    public String get(String mobileNo, String data) throws Exception {
        SysPojo sys = sysDao.getRecordByWhere(new SysPojo().setMobileNo(mobileNo).setState("1"));
        if (sys == null)
            throw new Exception("账号异常");
        String resStr = AESUtil.decrypt(data,sys.getPassword());
        JSONObject resJson = JSON.parseObject(resStr);
        String state = resJson.getString("state");
        switch (state){
            case "connect": return "";
            default: return "";
        }
    }

    public String post(String mobileNo, JSONObject jsonObject) throws Exception {
        SysPojo sys = sysDao.getRecordByWhere(new SysPojo().setMobileNo(mobileNo).setState("1"));
        if (sys == null)
            throw new Exception("账号异常");
        JSONObject resJson = JSON.parseObject(AESUtil.decrypt(jsonObject.getString("data"),sys.getPassword()));
        String type = resJson.getString("type");
        switch (type){
            case "GetAccount": return getAccount(sys.getId());
            case "hbOneDesc": return hbOneDesc(sys.getId(),resJson);
            case "SaveCookie": return SaveCookie(sys.getId(),resJson);
            case "retHbOne": return retHbOne(sys.getId(),resJson);
            default: return "";
        }
    }

    private String retHbOne(String sysId, JSONObject resJson) {
        String state = resJson.getString("state");
        String loginState = resJson.getString("loginState");
        String id = resJson.getString("id");
        if (accountDao.getRecordByWhere(new AccountPojo().setSysId(sysId).setId(id)) != null)
            accountDao.updateRecordByKey(new AccountPojo().setId(id).setState(state).setLoginState(loginState));
        return "";
    }

    private String SaveCookie(String sysId, JSONObject resJson) {
        String id = resJson.getString("id");
        if (accountDao.getRecordByWhere(new AccountPojo().setSysId(sysId).setId(id)) != null)
            accountDao.updateRecordByKey(new AccountPojo().setId(id).setLoginState("1").setState("1"));
        return "";
    }

    private String hbOneDesc(String sysId, JSONObject resJson) {
        String accountId = resJson.getString("accountId");
        AccountPojo pojo = accountDao.getRecordByWhere(new AccountPojo().setId(accountId).setSysId(sysId));
        return JSON.toJSONString(pojo);
    }

    private String getAccount(String sysId){
        List<AccountPojo> pojoList = accountDao.getRecordListByWhere(new AccountPojo().setInState("1,0").setSysId(sysId));
        return JSON.toJSONString(pojoList);
    }
}
