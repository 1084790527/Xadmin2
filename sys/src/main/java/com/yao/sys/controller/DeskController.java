package com.yao.sys.controller;
/**
 * @author 妖妖
 * @date 15:32 2020/12/29
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yao.bean.vo.ResultObj;
import com.yao.sys.service.DeskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;

@RequestMapping(value = "desk")
@RestController
public class DeskController {
    private static Log log = LogFactory.getLog(DeskController.class);

    @Autowired
    private DeskService deskService;

    @GetMapping
    public ResultObj get(@RequestParam(name = "accountNumber")String accountNumber,@RequestParam(value = "data")String data){
        ResultObj resultObj = new ResultObj();
        try {
            String retJson = deskService.get(accountNumber, data);
            resultObj.setData(retJson);
            resultObj.setState(true);
        } catch (Exception e) {
            resultObj.setState(false);
            resultObj.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return resultObj;
    }

    @PostMapping
    public ResultObj post(@RequestParam(name = "accountNumber")String accountNumber, @RequestBody JSONObject jsonObject){
        ResultObj resultObj = new ResultObj();
        try {
            String data = deskService.post(accountNumber,jsonObject);
            resultObj.setData(URLEncoder.encode(data,"utf-8"));
            resultObj.setState(true);
        } catch (Exception e) {
            resultObj.setState(false);
            resultObj.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return resultObj;
    }
}
