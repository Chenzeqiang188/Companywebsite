package com.good.web.cms.Controller;

import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Honor;
import com.good.web.service.HonorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/cms/honor")
public class HonorController {

    @Autowired
    private HonorService service;

    //返回数据
    private String message;

    /**
     * 添加/修改荣誉
     */
    @RequestMapping("/saveOrUpdateHonor")
    public String saveOrUpdateHonor(@Valid Honor honor, BindingResult bindingResult, String id ,  Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        if(iD==null){
            honor.setAwardDate(new Date(System.currentTimeMillis()));
        }
        honor.setId(iD);
        message = service.saveOrUpdateHonor(honor);
        model.addAttribute("message",message);
        //List<ObjectError> allErrors = bindingResult.getAllErrors();
        return "/cms/honor/addHonor.jsp";
    }
    /**
     * 查询荣誉
     */
    @RequestMapping("/honorList")
    public String honorList(Honor honor,Model model){
        List<Honor> honorList = service.queryHonorList(honor);
        model.addAttribute("honorList",honorList);
        return "/cms/honor/honorList.jsp";
    }
    /**
     * 根据id查找荣誉
     */
    @RequestMapping("/findHonorById")
    public String findHonorById(String id,Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        Honor honor = service.findHonorById(iD);
        model.addAttribute("honor",honor);
        return "/cms/honor/addHonor.jsp";
    }
    /**
     * 根据id删除荣誉
     */
    @RequestMapping("/deleteHonorById")
    public void deleteHonorById(String id, HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, service.deleteHonorById(iD), PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
