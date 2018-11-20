package com.good.web.cms.Controller;

import com.alibaba.fastjson.JSONArray;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Recruit;
import com.good.web.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cms/recruit")
public class RecruitController {
    @Autowired
    private RecruitService service;
    @Autowired
    private WebApplicationContext webApplicationContext;
    //参数
    private Recruit recruit;

    @Value("${contentType}")
    private String contentType;
    //返回
    private String message = "";
    private JSONArray recruitArray = new JSONArray();
    /**
     * 添加职位类型
     */
    @RequestMapping("/addPositionType.do")
    public void addPositionType(String typeName,Integer typeValue,HttpServletResponse response){
        service.addPositionType(typeName,typeValue,response);
    }
    /**
     * 删除职位类型
     */
    @RequestMapping("/delPositionType.do")
    public void delPositionType(String id,HttpServletResponse response) throws IOException {
        Integer iD = (ValidateUtil.str_isEmpty(id)) ? null : Integer.parseInt(id);
        ServletUtil.responseToClient(response, service.delPositionType(iD,response), contentType);
    }
    /**
     * 添加部门
     */
    @RequestMapping("/addDept.do")
    public void addDept(String typeName,HttpServletResponse response){
        service.addDept(typeName,response);
    }
    /**
     * 删除部门
     */
    @RequestMapping("/delDept.do")
    public void delDept(String id,HttpServletResponse response) throws IOException {
        Integer iD = (ValidateUtil.str_isEmpty(id)) ? null : Integer.parseInt(id);
        ServletUtil.responseToClient(response, service.delDept(iD, response), contentType);
    }
    /**
     * 添加/修招聘信息
     */
    @RequestMapping("/saveOrUpdateRecruit.do")
    public String saveOrUpdateRecruit(Recruit recruit,String id, Model model){
        Integer iD = (ValidateUtil.str_isEmpty(id)) ? null : Integer.parseInt(id);
        recruit.setId(iD);
        message = service.addOrUpdateRecruit(recruit);
        model.addAttribute("message",message);
        return "/cms/recruit/addRecruit.jsp";
    }
    /**
     * 查询招聘信息
     */
    @RequestMapping("/recruitList.do")
    public String recruitList(Recruit recruit, Model model){
        recruitArray = service.queryRecruitList(recruit);
        model.addAttribute("recruitArray",recruitArray);
        return "/cms/recruit/recruitList.jsp";
    }
    /**
     * 根据id查询招聘信息
     */
    @RequestMapping(value = "/findRecruitById.do",method = RequestMethod.GET)
    public String findRecruitById(String id,Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        recruit = service.findRecruitById(iD);
        model.addAttribute("recruit",recruit);
        return "/cms/recruit/addRecruit.jsp";
    }
    /**
     * 删除招聘信息
     * @param service
     */
    @RequestMapping("/delRecruitById.do")
    public void delRecruitById(HttpServletResponse response,String id){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, service.deleteRecruitById(iD), PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
