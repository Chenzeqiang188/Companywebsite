package com.good.web.cms.Controller;

import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Card;
import com.good.web.domain.Image;
import com.good.web.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cms")
public class ImageController {
    @Autowired
    private ImageService service;

    private String message;
    private List<Image> imageList;
    private List<Card> cardList;

    /**
     * 添加/修改图片
     */
    @RequestMapping(value ="/image/saveOrUpdateImage",method =RequestMethod.POST)
    public String saveOrUpdateImage(@Valid Image image, BindingResult result, @RequestParam(value = "img")MultipartFile img, String img1, String id, Model model) throws IOException {
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        boolean b = org.apache.commons.lang.StringUtils.isBlank(img.getOriginalFilename());
        File file = null;
        String imgFileName="";
        if(b==false) {
            file = File.createTempFile("tmp", null);
            imgFileName = img.getOriginalFilename();
            img.transferTo(file);
        }
        image.setId(iD);
        message = service.saveOrUpdateImage(image,file,img1,imgFileName);
        model.addAttribute("message",message);
        if(!"bar".equals(image.getImgType()))
            return "/cms/image/addImage.jsp";
        else
            return "/cms/image/addBarImage.jsp";
    }
    /**
     * 查询图片
     */
    @RequestMapping(value = "/image/imageList",method =RequestMethod.POST)
    public String imageList(Image image, Model model){
        imageList = service.queryImageList(image);
        /*JSONArray arr = new JSONArray();
        arr.addAll(imageList);*/
        model.addAttribute("imageList",imageList);
        return "/cms/image/imageList.jsp";
    }
    /**
     * 根据id查找图片
     */
    @RequestMapping(value = "/image/findImageById")
    public String findImageById(String id,Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        Image image = service.findImageById(iD);
        model.addAttribute("image",image);
        if(!"bar".equals(image.getImgType()))
            return "/cms/image/addImage.jsp";
        else
            return "/cms/image/addBarImage.jsp";
    }
    /**
     * 根据id删除图片
     */
    @RequestMapping(value = "/image/deleteImageById")
    public void deleteImageById(String id, HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, service.deleteImageById(iD), PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 添加/修改幻灯片
     */
    @RequestMapping(value = "/card/saveOrUpdateCard",method = RequestMethod.POST)
    public String saveOrUpdateCard(@Valid Card card,BindingResult result,MultipartFile img,String img1,String id,Model model) throws IOException {
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        boolean b = org.apache.commons.lang.StringUtils.isBlank(img.getOriginalFilename());
        File file = null;
        String imgFileName="";
        if(b==false) {
            file = File.createTempFile("tmp", null);
            imgFileName = img.getOriginalFilename();
            img.transferTo(file);
        }
        card.setId(iD);
        message = service.saveOrUpdateCard(card,file,img1,imgFileName);
        model.addAttribute("message",message);
        return "/cms/card/addCard.jsp";
    }
    /**
     * 查询幻灯片
     */
    @RequestMapping(value = "/card/cardList",method = RequestMethod.POST)
    public String cardList(Card card,Model model){
        cardList = service.queryCardList(card);
        model.addAttribute("cardList",cardList);
        return "/cms/card/cardList.jsp";
    }
    /**
     * 根据id查找幻灯片
     */
    @RequestMapping(value = "/card/findCardById")
    public String findCardById(String id,Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        Card card = service.findCardById(iD);
        model.addAttribute("card",card);
        return "/cms/card/addCard.jsp";
    }
    /**
     * 根据id删除幻灯片
     */
    @RequestMapping(value = "/card/deleteCardById")
    public void deleteCardById(String id,HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, service.deleteCardById(iD), PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
