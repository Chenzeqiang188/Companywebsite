package com.good.web.cms.Controller;

import com.alibaba.fastjson.JSONArray;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.ActivityCard;
import com.good.web.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;

/**
 * 通用活动幻灯片Controller
 */
@Controller
@RequestMapping("/cms/activity")
public class ActivityPPTController {

    @Autowired
    private ActivityServiceImpl service;
    private String imgFileName;
    private String message;
    private JSONArray newsArray = new JSONArray();

    /**
     * 添加/修改新闻
     */
    @RequestMapping(value = "/saveOrUpdateActivity", method = {RequestMethod.POST,RequestMethod.GET})
    public String saveOrUpdateActivity(@Valid ActivityCard activityCard, BindingResult result, MultipartFile img, String img1, Model model){
        try {
            boolean b = org.apache.commons.lang.StringUtils.isBlank(img.getOriginalFilename());
            File file=null;
            if(b==false) {
                file = File.createTempFile("tmp", null);
                imgFileName = img.getOriginalFilename();
                img.transferTo(file);
            }
            message = service.saveOrUpdateActCard(activityCard,file,img1,imgFileName);
            model.addAttribute("message",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/cms/activity/addCard.jsp";
    }

    /**
     * 新闻列表
     */
    @RequestMapping(value = "/cardList")
    public String cardList(ActivityCard news,Model model){
        newsArray = service.queryActCard(news);
        model.addAttribute("cardList",newsArray);
        return "/cms/activity/cardList.jsp";
    }


    /**
     * 查找新闻by id
     */
    @RequestMapping(value = "/findCardById")
    public String findCardById(String id, Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        ActivityCard card = service.findActCardById(iD);
        model.addAttribute("card",card);
        return "/cms/activity/addCard.jsp";
    }
    /**
     * 删除新闻
     * @param id
     */
    @RequestMapping(value = "/deleteCardById")
    public void deleteCardById(String id,HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, service.deleteActCardById(iD), PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
