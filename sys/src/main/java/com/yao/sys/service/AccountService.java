package com.yao.sys.service;
/**
 * @author 妖妖
 * @date 10:49 2020/12/29
 */

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yao.bean.LoginInfo;
import com.yao.bean.db.AccountPojo;
import com.yao.bean.db.SysPojo;
import com.yao.bean.model.AccountModel;
import com.yao.bean.model.AccountTab;
import com.yao.bean.vo.ResultObj;
import com.yao.common.Consts;
import com.yao.common.util.IdWorker;
import com.yao.common.util.Tool;
import com.yao.sys.dao.AccountDao;
import com.yao.sys.dao.SysDao;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {
    private static Log log = LogFactory.getLog(AccountService.class);

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private HttpSession session;
    @Autowired
    private SysDao sysDao;
    @Autowired
    private IdWorker idWorker;

    public ResultObj obtainAccount(AccountTab tab) {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute(Consts.LOGIN_INFO);
        //page=1&limit=10&sort=sysName&order=asc
        String id = tab.getId();
        String loginId = tab.getLoginId();
        String state = tab.getState();
        String sysId = tab.getSysId();
        AccountPojo sel = new AccountPojo();
        sel.setAgencyId(loginInfo.getAgencyId());
        if (StringUtils.isNotBlank(id))
            sel.setId(id);
        if (StringUtils.isNotBlank(loginId))
            sel.setLoginId(loginId);
        if (StringUtils.isNotBlank(sysId))
            sel.setSysId(sysId);
        if (StringUtils.isNotBlank(state))
            sel.setState(state);
        else
            sel.setInState("1,0");
        if (!"1".equals(loginInfo.getDefaults()))
            sel.setSysId(loginInfo.getId());
        String order = tab.getOrder();
        String sort = tab.getSort();
        if (StringUtils.isNotBlank(sort))
            PageHelper.orderBy(Tool.humpToLine(sort.replaceAll("Name","Id"))+" "+order);
        else
            PageHelper.orderBy(" id desc ");
        ResultObj resultObj = new ResultObj();
        Page<AccountPojo> page = PageHelper.startPage(tab.getPage(),tab.getLimit() ,true);
        accountDao.getRecordListByWhere(sel);
        resultObj.setCount(page.getTotal());
        List<AccountModel> models = new ArrayList<>();

        for (AccountPojo pojo : page.getResult()) {
            AccountModel model = new AccountModel(pojo);
            model.setLoginPass("");
            model.setSysName(sysDao.getRecordByKey(new SysPojo().setId(pojo.getSysId())).getRealName());
            if (StringUtils.isNotBlank(pojo.getCreOperId()))
                model.setCreOperName(sysDao.getRecordByKey(new SysPojo().setId(pojo.getCreOperId())).getRealName());
            if (StringUtils.isNotBlank(pojo.getLastModOperId()))
                model.setLastModOperName(sysDao.getRecordByKey(new SysPojo().setId(pojo.getLastModOperId())).getRealName());
            models.add(model);
        }
        resultObj.setData(models);
        return resultObj;
    }

    public void add(AccountModel model) throws Exception {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute(Consts.LOGIN_INFO);
        String loginId = model.getLoginId();
        String loginPass = model.getLoginPass();
        if (StringUtils.isBlank(loginId))
            throw new Exception("账号不能空");
        if (StringUtils.isBlank(loginPass))
            throw new Exception("密码不能空");
        if (accountDao.getRecordByWhere(new AccountPojo().setLoginId(loginId).setSysId(loginInfo.getId())) != null)
            throw new Exception("账号已存在");
        accountDao.insertRecord(new AccountPojo().setId(idWorker.nextId()).setAgencyId(loginInfo.getAgencyId())
                .setSysId(loginInfo.getId()).setState("1").setLoginId(loginId).setLoginPass(loginPass)
                .setCreOperId(loginInfo.getId()).setCreOperDate(new Date()));

    }

    public void modifyAccount(AccountModel model) throws Exception {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute(Consts.LOGIN_INFO);
        String id = model.getId();
        String loginId = model.getLoginId();
        String loginPass = model.getLoginPass();
        if (StringUtils.isBlank(id))
            throw new Exception("数据异常");
        if (StringUtils.isBlank(loginId))
            throw new Exception("账号不能空");
        if (StringUtils.isBlank(loginPass))
            throw new Exception("密码不能空");
        if (accountDao.getRecordByKey(new AccountPojo().setSysId(loginInfo.getId()).setId(id)) == null)
            throw new Exception("没有权限修改这行数据");
        accountDao.updateRecordByKey(new AccountPojo().setId(id).setLoginId(loginId).setLoginPass(loginPass)
                .setLastModOperDate(new Date()).setLastModOperId(loginInfo.getId()).setLoginState("0"));
    }

    public void upState(String id, String state) throws Exception {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute(Consts.LOGIN_INFO);
        if (StringUtils.isBlank(id))
            throw new Exception("修改状态异常");
        AccountPojo selPojo = accountDao.getRecordByWhere(new AccountPojo().setSysId(loginInfo.getId()).setId(id));
        if (selPojo == null)
            throw new Exception("没有权限修改这行数据");
        AccountPojo upPojo = new AccountPojo();
        upPojo.setId(id).setState(state).setLastModOperId(loginInfo.getId()).setLastModOperDate(new Date()).setLoginState("0");
        if ("2".equals(state))
            upPojo.setLoginId(selPojo.getLoginId()+upPojo.getId());
        accountDao.updateRecordByKey(upPojo);
    }
}
