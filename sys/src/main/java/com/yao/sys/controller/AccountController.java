package com.yao.sys.controller;
/**
 * @author 妖妖
 * @date 10:48 2020/12/29
 */

import com.yao.bean.model.AccountModel;
import com.yao.bean.model.AccountTab;
import com.yao.bean.vo.ResultObj;
import com.yao.sys.service.AccountService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "tbAccount")
public class AccountController {
    private static Log log = LogFactory.getLog(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "index")
    public ResultObj index(AccountTab tab){
        return accountService.obtainAccount(tab);
    }

    @PostMapping(value = "add")
    public ResultObj addAccount(AccountModel model){
        ResultObj resultObj = new ResultObj();
        try {
            accountService.add(model);
            resultObj.setState(true);
            return resultObj;
        }catch (Exception e){
            e.printStackTrace();
            resultObj.setMsg(e.getMessage());
            resultObj.setState(false);
            return resultObj;
        }
    }

    @PostMapping(value = "modify")
    public ResultObj modifyAccount(AccountModel model){
        ResultObj resultObj = new ResultObj();
        try {
            accountService.modifyAccount(model);
            resultObj.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultObj.setState(false);
            resultObj.setMsg(e.getMessage());
        }
        return resultObj;
    }

    @PostMapping(value = "disable")
    public ResultObj disableAccount(AccountModel model){
        ResultObj resultObj = new ResultObj();
        try {
            accountService.upState(model.getId(),"0");
            resultObj.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultObj.setState(false);
            resultObj.setMsg(e.getMessage());
        }
        return resultObj;
    }

    @PostMapping(value = "enable")
    public ResultObj enableAccount(AccountModel model){
        ResultObj resultObj = new ResultObj();
        try {
            accountService.upState(model.getId(),"1");
            resultObj.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultObj.setState(false);
            resultObj.setMsg(e.getMessage());
        }
        return resultObj;
    }

    @PostMapping(value = "delete")
    public ResultObj deleteAccount(AccountModel model){
        ResultObj resultObj = new ResultObj();
        try {
            accountService.upState(model.getId(),"2");
            resultObj.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultObj.setState(false);
            resultObj.setMsg(e.getMessage());
        }
        return resultObj;
    }
}
