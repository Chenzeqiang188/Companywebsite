package com.good.web.cms.Controller;

import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.ActivityGameNews;
import com.good.web.service.impl.ActivityGameNewsServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cms/news")
public class ActivityNewsController {
    @Autowired
    private ActivityGameNewsServiceImpl service;
    private String message;

    @RequestMapping("/saveOrUpdateActivityGameNews")
    public String saveOrUpdateActivityGameNews(ActivityGameNews gameNews,Model model){
        message = service.saveOrUpdateActivityGameNews(gameNews);
        model.addAttribute("message",message);
        return "/cms/news/addActivityNews.jsp";
    }

    /**
     * 方法功能说明：模糊查询，根据新闻类别和标题
     * @return
     * String
     * author:邓超
     * 2016-8-4 上午9:41:32
     */
    @RequestMapping("/queryActivityGameNews")
    public String queryActivityGameNews(ActivityGameNews gameNews, Model model){
        Integer i = gameNews.getCategoryId();
        String title = gameNews.getTitle();
        List<ActivityGameNews> gameNewsList = service.queryActivityGameNews(i, title);
        model.addAttribute("gameNewsList",gameNewsList);
        return "/cms/news/activityNewsList.jsp";
    }
    @RequestMapping("/queryActivityGameNewsById")
    public String queryActivityGameNewsById(String id , Model model){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ActivityGameNews gameNews = service.findGameNewsById(iD);
            model.addAttribute("gameNews",gameNews);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/cms/news/addActivityNews.jsp";
    }
    /**
     * 方法功能说明：根据id值删除新闻
     */
    @RequestMapping("/delGameNewsById")
    public void delGameNewsById(String id, HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, service.delGameNewsById(iD), PropertiesUtil.getValue("contentType"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
