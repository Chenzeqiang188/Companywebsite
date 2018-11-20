package com.good.web.cms.Controller;
/**
 * @Auther: Administrator
 * @Date: 2018/10/25 10:39
 * @Description:
 */

import com.good.web.base.DataCenter;
import com.good.web.base.common.CommonData;
import com.good.web.base.common.Page;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Show;
import com.good.web.service.IShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName RankController
 * @Description TODO
 * @Author Administrator
 * @Data 10:39
 * @Version 1.0
 **/
@Controller
@RequestMapping("/cms")
public class RankController {
    @Autowired
    private IShowService showService;
    private List<Show> showlist;//展示板块
    private List<Show> rankList;//榜单
    private String message="";
    @Autowired
    private DataCenter dataCenter;

    private Page page=new Page();


    /**
     * 获取板块列表
     */
  //  @RequestMapping("/section/sectionList")
    public String sectionList(Show show){
        showlist = showService.sectionList(show.getType(),page);
//		dataCenter.sectionList.clear();
//		dataCenter.sectionList.addAll(showlist);
        return "/cms/section/sectionList.jsp";
    }
    /**
     * 跳转编辑板块页面
     */
   // @RequestMapping("/section/findSectionPage")
    public String findSectionPage(String id, Model model){
        Show show = showService.getShow(id==null?null:Integer.parseInt(id));
        return "/cms/section/sectionEdit.jsp";
    }
    /**
     * 更新展示板块
     */
   // @RequestMapping("/section/editRankPage")
    public String editSection(Show show,Model model ){
        try {
            showService.editSection(show);
            dataCenter.loadSectionsList();
            message="操作成功";
        } catch (Exception e) {
            e.printStackTrace();
            message="更新失败，请联系工作人员。";
        }
        model.addAttribute("message",message);
        return "/cms/rank/rankEdit.jsp";
    }
    /**
     * 获取榜单列表
     */
    @RequestMapping("/rank/rankList")
    public String rankList(Show show,Model model){
        rankList=showService.showList(show.getType(),show.getTitle(),page);
        model.addAttribute("rankList",rankList);
        return "/cms/rank/rankList.jsp";
    }
    /**
     * 跳转更新界面
     */
    @RequestMapping("/rank/editRankPage")
    public String editRankPage(String id,Model model){
        if(id!=null && !("").equals(id)){
            Show show=showService.getShow(Integer.parseInt(id));
            model.addAttribute("show",show);
        }
        return "/cms/rank/rankEdit.jsp";
    }
    /**
     * 更新/添加 榜单
     */
    @RequestMapping("/rank/addRank")
    public String addRank(Show show,Model model){
        show.setParentId(CommonData.ranking);
        if(show.getId()!=null && !("").equals(show.getId())){
            showService.editShow(show);
            message="更新成功";
        }else{
            showService.addShow(show);
            message="添加成功";
        }
        dataCenter.loadRankList();
        model.addAttribute("message",message);
        return "/cms/rank/rankEdit.jsp";
    }

    @RequestMapping("/rank/delRank")
    public void delRank(String id, HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, showService.delShow(iD), PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
