package com.good.web.cms.Controller;

import com.alibaba.fastjson.JSONArray;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.News;
import com.good.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;

@Controller
@RequestMapping("/cms/news")
public class NewController {
    @Autowired
    private NewsService service;


    //回调数据
    private JSONArray newsArray = new JSONArray();
    private String message = "";
    //参数
    private News news;
    private String imgFileName;
    //分页
    private Integer currentPage = 0;

    @Value("${contentType}")
    private String contentType;


   /* @InitBinder
    public void intDate(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        //dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"), "createTime");
    }*/

    /**
     * 添加/修改新闻
     */
        @RequestMapping(value = "/saveOrUpdateNews", method = {RequestMethod.POST,RequestMethod.GET})
        public String saveOrUpdateNews(@Valid News news,BindingResult result,String createTime,MultipartFile img,String img1,Model model){
            try {
                boolean b = org.apache.commons.lang.StringUtils.isBlank(img.getOriginalFilename());
                File file=null;
                if(b==false) {
                    file = File.createTempFile("tmp", null);
                    imgFileName = img.getOriginalFilename();
                    img.transferTo(file);
                }
                message = service.saveOrUpdateNews(news,file,img1,imgFileName);
                model.addAttribute("message",message);
                } catch (Exception e) {
                 e.printStackTrace();
                }
        return "/cms/news/addNews.jsp";
    }

    /**
     * 新闻列表
     */
    @RequestMapping(value = "/newsList")
    public String newsList(News news,Model model){
        newsArray = service.queryNews(news);
        model.addAttribute("newsArray",newsArray);
        return "/cms/news/newsList.jsp";
    }


    /**
     * 查找新闻by id
     */
    @RequestMapping(value = "/findNewsById")
    public String findNewsById(String id, Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        news = service.findNewsById(iD);
        model.addAttribute("news",news);
        return "/cms/news/addNews.jsp";
    }
    /**
     * 删除新闻
     * @param id
     */
    @RequestMapping(value = "/delNewsById")
    public void delNewsById(String id,HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, service.deleteNewsById(iD), contentType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
