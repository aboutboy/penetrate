package com.weasel.penetrate.manager.interfaces.controller;

import com.weasel.penetrate.manager.infrastructure.Frp;
import com.weasel.penetrate.manager.interfaces.vo.ResponseMessage;
import com.weasel.penetrate.manager.service.FrpConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController{

    private final static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private FrpConfigService frpConfigService;

    @ResponseBody
    @RequestMapping(value = "/reload_config",method = GET)
    public ResponseMessage reload(){

        try {
            frpConfigService.reloadConfig(Frp.getHome());
            return ResponseMessage.success();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseMessage.error().setMessage("加载frp配置文件失败");
    }
}
