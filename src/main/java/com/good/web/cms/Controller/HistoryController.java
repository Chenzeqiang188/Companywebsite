package com.good.web.cms.Controller;

import com.alibaba.fastjson.JSONArray;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.History;
import com.good.web.service.HistoryService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cms/history")
public class HistoryController {

    @Autowired
    private HistoryService service;
    private String message;

    /**
     * 添加/修改历程
     */
    @RequestMapping("/saveOrUpdateHistory")
    public String saveOrUpdateHistory(History history/*,String id*/, Model model){
        //Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        //Date date = ValidateUtil.str_isEmpty(historyDate) ? null : Date.valueOf(historyDate);
        //history.setId(iD);
        message = service.saveOrUpdateHistory(history);
        model.addAttribute("message",message);
        return "/cms/history/addHistory.jsp";
    }
    /**
     * 查询历程
     */
    @RequestMapping("/historyList")
    public String historyList(History history,Model model){
        JSONArray historyArray = service.queryHistoryList(history);
        model.addAttribute("historyArray",historyArray);
        return "/cms/history/historyList.jsp";
    }
    /**
     * 根据id查找历程
     */

    @RequestMapping("/findHistoryById")
    public String findHistoryById(String id,Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        History history = service.findHistoryById(iD);
        model.addAttribute("history",history);
        return "/cms/history/addHistory.jsp";
    }
    /**
     * 根据id删除历程
     */
    @RequestMapping("/deleteHistoryById")
    public void deleteHistoryById(String id, HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, service.deleteHistoryById(iD), PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
