package com.good.web.cms.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.utils.FileUtils;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Advice;
import com.good.web.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cms/advice")
public class AdviceController {

    @Autowired
    private AdviceService service;
    private String message = "";
    private String imgFileName;
    private String minImgFileName;

 /*   @InitBinder
    public void intDate(WebDataBinder dataBinder){
        dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"), "createTime");
    }
*/
    @RequestMapping("/saveOrUpdateAdvice")
    public String saveOrUpdateAdvice(@Valid Advice advice, BindingResult result, MultipartFile minImage, String minImage1, MultipartFile image, String image1, Model model) {
        try {
            boolean b = org.apache.commons.lang.StringUtils.isBlank(image.getOriginalFilename());
            boolean b2 = org.apache.commons.lang.StringUtils.isBlank(minImage.getOriginalFilename());
            File file=null;
            if(b2==true && "".equals(minImage1))
            {
                if(b==false) {
                    file = File.createTempFile("tmp", null);
                    imgFileName = image.getOriginalFilename();
                    image.transferTo(file);
                }
                message = service.saveOrUpdateAdvice(advice,file,image1,imgFileName);
            }else{
                File file2=null;
                if(b==false) {
                    file = File.createTempFile("tmp", null);
                    imgFileName = image.getOriginalFilename();
                    image.transferTo(file);
                }
                if(b2==false) {
                    file2 = File.createTempFile("tmp", null);
                    minImgFileName = minImage.getOriginalFilename();
                    minImage.transferTo(file2);
                }
                message = service.saveOrUpdateAdvice2(advice, file, imgFileName, file2,minImgFileName,minImage1,image1);
            }
            model.addAttribute("message",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/cms/advice/addAdvice.jsp";
        }


    /**
     * 查询广告列表
     */
    @RequestMapping("/adviceList")
    public String adviceList(Advice advice, Model model){
        try {
            JSONArray adviceArray = service.queryAdviceList(advice);
            model.addAttribute("advices",adviceArray);
            return "/cms/advice/adviceList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 根据id查找广告
     */
    @RequestMapping("findAdviceById")
    public String findAdviceById(String id,Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        Advice advice = service.findAdviceById(iD);
        model.addAttribute("advice",advice);
        return "/cms/advice/addAdvice.jsp";
    }
    /**
     * 根据id删除广告
     * @throws IOException
     */
    @RequestMapping("/deleteAdviceById")
    public void deleteAdviceById(String id, HttpServletResponse response) {
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            int i = service.deleteAdviceById(iD);
            JSONObject obj=new JSONObject();
            ServletUtil.responseToClient(response, i, PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
