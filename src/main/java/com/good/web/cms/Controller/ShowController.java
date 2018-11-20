package com.good.web.cms.Controller;

import com.good.web.base.common.Page;
import com.good.web.domain.Show;
import com.good.web.service.IShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cms/section")
public class ShowController {

    @Autowired
    private IShowService showService;


    @RequestMapping("/sectionList")
    public String sectionList(Show show, Page page, Model model){
        List<Show> showlist = showService.sectionList(show.getType(),page);
        model.addAttribute("showlist",showlist);
        return "/cms/section/sectionList.jsp";
    }
    /**
     * 跳转编辑板块页面
     * @return
     */
    @RequestMapping("/findSectionPage")
    public String findSectionPage(String id,Model model){
        Show show = showService.getShow(id==null?null:Integer.parseInt(id));
        model.addAttribute("show",show);
        return "/cms/section/sectionEdit.jsp";
    }
    /**
     * 更新展示板块
     * @return
     */
    @RequestMapping("/editSection")
    public String editSection(Show show,Model model){
        String message = showService.editSection(show);
        model.addAttribute("message",message);
        return "/cms/section/sectionEdit.jsp";
    }

    /**
     * 获取榜单列表
     * @return
     *//*
    public String rankList(){
        List<Show> rankList=showService.showList(show.getType(),show.getTitle(),page);
        return "rankList";
    }
    *//**
     * 跳转更新界面
     * @return
     *//*
    public String editRankPage(){
        if(id!=null && !("").equals(id)){
            show=showService.getShow(Integer.parseInt(id));
        }
        return "editRankPage";
    }
    *//**
     * 更新/添加 榜单
     * @return
     *//*
    public String addRank(){
        show.setParentId(CommonData.ranking);
        if(show.getId()!=null && !("").equals(show.getId())){
            showService.editShow(show);
            message="操作成功";
        }else{
            showService.addShow(show);
            message="操作成功";
        }
        dataCenter.loadRankList();
        return "addRank";
    }

    public String delRank(){
        if(id!=null && !("").equals(id)){
            showService.delShow(Integer.parseInt(id));
        }
        dataCenter.loadRankList();
        rankList();
        return "delRank";
    }
*/

}
