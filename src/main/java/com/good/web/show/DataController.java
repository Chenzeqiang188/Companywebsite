package com.good.web.show;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.*;
import com.good.web.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DataController
 * @Description TODO
 * @Author Administrator
 * @Data 2018/10/24 18:22
 * @Version 1.0
 **/
@Controller
@RequestMapping("/")
public class DataController {

    private JSONArray historyArray = new JSONArray();
    private JSONArray honorArray = new JSONArray();
    private List<Show> rankList;
    private String jsonpCallback;


    @Autowired
    private DataService service;

    @RequestMapping("/")
    public String Default(){
        return "/index";
    }

    @RequestMapping("/index")
        public String index(ModelMap model){
        try {
            List<Card> cardArray = new ArrayList<Card>();
            List<News> newsArray = new ArrayList<News>();
            List<Game> gameArray = new ArrayList<Game>();
            //新闻列表
            newsArray=service.getNews();
            //幻灯片(大、小)
            cardArray=service.getCards();
            //热门游戏
            gameArray=service.getGames();
            model.addAttribute("newsArray",newsArray);
            model.addAttribute("cardArray",cardArray);
            model.addAttribute("gameArray",gameArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/index";
    }


    /**
     * @Title: queryNews
     *@Description: 新闻列表页
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:52:08
     */
    @RequestMapping("/pc_news")
    public String queryNews(Model model){
        try {
            List<News> newsArray = new ArrayList<News>();
            newsArray = service.getNews();
            model.addAttribute("newsArray",newsArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/newslist";
    }

    /**
     * @Title: queryNewsById
     *@Description: 获取新闻详情
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:59:16
     */
    @RequestMapping("/pc_newsDetail")
    public String queryNewsById(String id,Model model){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            News news = service.queryNewsById(iD);
            model.addAttribute("news",news);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "desktop/template/news";
    }


    /**
     * @Title: queryHistories
     *@Description: 公司发展历程
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:54:58
     */
    @RequestMapping("/pc_history")
    public String queryHistories(Model model){
        try {
            JSONArray historyArray = new JSONArray();
            historyArray = service.getHistories();
            model.addAttribute("historyArray",historyArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/enterprise_4";
    }
    /**
     * @Title: queryHonors
     *@Description: 所获荣誉
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:55:13
     */
    @RequestMapping("/pc_honor")
    public String queryHonors(Model model){
        try {
            JSONArray honorArray = new JSONArray();
            honorArray = service.getHonors();
            model.addAttribute("honorArray",honorArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/enterprise_5";
    }
    /**
     * @Title: queryGames
     *@Description: 游戏列表
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:55:47
     */
    @RequestMapping("/pc_game")
    public String queryGames(Model model){
        try {
            List<Game> gameArray = new ArrayList<Game>();
            gameArray = service.getGames();
            model.addAttribute("gameArray",gameArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/gamelist";
    }
    /**
     * 招聘信息
     */
    @RequestMapping("/pc_recruit")
    public String queryRecruits(Model model,@RequestParam(required = false) String positionType){
        try {
            List<Recruit> recruitList=new ArrayList<Recruit>();
            recruitList=service.queryRecruit(null);
            model.addAttribute("recruitList",recruitList);
            if(positionType!=null && !positionType.equals(""))
            {
                model.addAttribute("positionType",positionType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/recruitment_1";
    }

    /**
     * 招聘信息详情
     */
    @RequestMapping("/pc_recruitDetail")
    public String queryRecruitById(String id,Model model){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            //recruit = service.queryRecruitById(id);
            Recruit recruit=service.findRecruitById(iD);
            model.addAttribute("recruit",recruit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/recruit";
    }


    /**
     * @Title: queryImages
     *@Description: 培训发展
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:57:42
     */
    @RequestMapping("/pc_td")
    public String queryImagesTD(Model model){
        try {
            List<Image> imageList = new ArrayList<Image>();
            imageList = service.getImages("TD");
            model.addAttribute("imageList",imageList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "desktop/template/recruitment_4";
    }
    /**
     * @Title: queryImages
     *@Description: 薪资福利
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:57:42
     */
    @RequestMapping("/pc_cb")
    public String queryImagesCB(Model model){
        try {
            List<Image> imageList = new ArrayList<Image>();
            imageList = service.getImages("CB");
            model.addAttribute("imageList",imageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/recruitment_3";
    }

    /**
     * @Title: queryImages
     *@Description: 公司简介
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:57:42
     */
    @RequestMapping("/pc_ci")
    public String queryImagesCI(Model model){
        try {
            List<Image> imageList = new ArrayList<Image>();
            imageList = service.getImages("CI");
            model.addAttribute("imageList",imageList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "desktop/template/enterprise_1";
    }

    /**
     * @Title: queryImages
     *@Description: 企业文化
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:57:42
     */
    @RequestMapping("/pc_ev")
    public String queryImagesEV(Model model){
        try {
            List<Image> imageList = new ArrayList<Image>();
            imageList = service.getImages("EV");
            model.addAttribute("imageList",imageList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "desktop/template/enterprise_3";
    }

    /**
     * @Title: queryImages
     *@Description: 谷得生活
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:57:42
     */
    @RequestMapping("/pc_el")
    public String queryImagesEL(Model model){
        try {
            List<Image> imageList = new ArrayList<Image>();
            imageList = service.getImages("EL");
            model.addAttribute("imageList",imageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/recruitment_5";
    }

    /**
     * @Title: queryImages
     *@Description: 工作环境
     * @param @return
     *@return String
     * @author 李松茂
     * 2016-6-15 下午04:57:42
     */
    @RequestMapping("/pc_wc")
    public String queryImagesWC(Model model){
        try {
            List<Image> imageList = new ArrayList<Image>();
            imageList = service.getImages("WC");
            model.addAttribute("imageList",imageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "desktop/template/recruitment_2";
    }

    /**
     * @Title: queryPcAdvImage
     *@Description: 查询PC首页广告图
     * @param @return
     *@return String
     * @author 黄李松
     * 2017-3-6 10:42:53
     * @throws IOException
     */
    @RequestMapping("/queryPcAdvImage")
    public void queryPcAdvImage(HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        try {
            result.put("result", 0);
            result.put("data", service.getSingleBannerAdvice(0));
            ServletUtil.responseToClient(response, jsonpCallback + "(" + result.toJSONString() + ");", PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", -1);
            ServletUtil.responseToClient(response, jsonpCallback + "(" + result.toJSONString() + ");", PropertiesUtil.getValue("contentType"));
        }
    }

    /**
     * @Title: queryMobileOpenImage
     *@Description: 查询移动端开屏大图
     * @param @return
     *@return String
     * @author 黄李松
     * 2017-3-6 10:42:53
     * @throws IOException
     */
    @RequestMapping("/queryMobileOpenImage")
    public void queryMobileOpenImage(HttpServletResponse response) throws IOException{
        JSONObject result = new JSONObject();
        try {
            result.put("result", 0);
            result.put("data", service.getSingleBannerAdvice(1));
            ServletUtil.responseToClient(response, jsonpCallback + "(" + result.toJSONString() + ");", PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", -1);
            ServletUtil.responseToClient(response, jsonpCallback + "(" + result.toJSONString() + ");", PropertiesUtil.getValue("contentType"));
        }
    }

    /**
     * @Title: queryMobileAdvImage
     *@Description: 查询移动端底部小广告图
     * @param @return
     *@return String
     * @author 黄李松
     * 2017-3-6 10:42:53
     * @throws IOException
     */
    @RequestMapping("/queryMobileAdvImage")
    public void queryMobileAdvImage(HttpServletResponse response) throws IOException{
        JSONObject result = new JSONObject();
        try {
            result.put("result", 0);
            result.put("data", service.getSingleBannerAdvice(2));
            ServletUtil.responseToClient(response, jsonpCallback + "(" + result.toJSONString() + ");", PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", -1);
            ServletUtil.responseToClient(response, jsonpCallback + "(" + result.toJSONString() + ");", PropertiesUtil.getValue("contentType"));
        }
    }

    @RequestMapping("/pc_cooperation")
    public String pc_cooperation(){
        return "desktop/template/cooperation";
    }
    @RequestMapping("/parent1")
    public String parent1(){
        return "desktop/template/parent1";
    }
    @RequestMapping("/parent2")
    public String parent2(){
        return "desktop/template/parent2";
    }
    @RequestMapping("/parent3")
    public String parent3(){
        return "desktop/template/parent3";
    }
    @RequestMapping("/parent4")
    public String parent4(){
        return "desktop/template/parent4";
    }
    @RequestMapping("/privacy")
    public String privacy(){
        return "desktop/template/privacy";
    }





}
