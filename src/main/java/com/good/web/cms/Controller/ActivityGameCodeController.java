package com.good.web.cms.Controller;/**
 * @Auther: Ethan
 * @Date: 2018/10/24 17:47
 * @Description:
 */

import com.good.web.domain.ActivityGameCode;
import com.good.web.service.impl.ActivityGameCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName ActivityGameCodeController
 * @Description TODO
 * @Author Administrator
 * @Data 17:47
 * @Version 1.0
 **/
@Controller
@RequestMapping("/cms/libao")
public class ActivityGameCodeController {
    @Autowired
    private ActivityGameCodeServiceImpl service;

    //返回数据
    private String message;
    private List<ActivityGameCode> gameCodeList;

    @RequestMapping("/saveOrUpdateActivityGameCode")
    public String saveOrUpdateActivityGameCode(ActivityGameCode gameCode, Model model){
        message = service.saveOrUpdateActivityGameCode(gameCode);
        model.addAttribute("message",message);
        return "/cms/libao/addActivityGameCode.jsp";
    }
    @RequestMapping("/queryActivityGameCode")
    public String queryActivityGameCode(ActivityGameCode gameCode,Model model){
        try {
            gameCodeList = service.queryActivityGameCode(gameCode.getStatus());
            model.addAttribute("gameCodeList",gameCodeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/cms/libao/activityGameCodeList.jsp";
    }


}
