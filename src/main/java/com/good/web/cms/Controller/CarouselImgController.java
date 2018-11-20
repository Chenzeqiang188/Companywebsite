package com.good.web.cms.Controller;
/**
 * @Auther: Administrator
 * @Date: 2018/10/25 16:17
 * @Description:
 */

import com.good.web.base.common.CommonData;
import com.good.web.base.common.Page;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.CarouselImg;
import com.good.web.service.ICarouselImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName CarouselImgController
 * @Description TODO
 * @Author Administrator
 * @Data 16:17
 * @Version 1.0
 **/
@Controller
@RequestMapping("/cms/carousel")
public class CarouselImgController {

    @Autowired
    private ICarouselImgService carouselImgService;

    private List<CarouselImg> carouselImglist;
    private String message;
    private String imgFileName;


    @RequestMapping("/carouselImgList")
    public String carouselImgList(@Valid CarouselImg carouselImg,BindingResult result, Page page, Model model){
        carouselImglist=carouselImgService.carouselImgList(carouselImg.getStartTime(),carouselImg.getEndTime(),page);
        model.addAttribute("carouselImglist",carouselImglist);
        return "/cms/carousel/carouselImgList.jsp";
    }
    /**
     * 跳转编辑图片界面
     */
    @RequestMapping("/editCarouselImgPage")
    public String editCarouselImgPage(String id,Model model){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            CarouselImg carouselImg=carouselImgService.getCarouselImg(iD);
            model.addAttribute("carouselImg",carouselImg);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "/cms/carousel/carouselImgEdit.jsp";
    }
    /**
     * 新增、更新 图片
     */
    @RequestMapping("/addCarouselImg")
    public String addCarouselImg(@Valid CarouselImg carouselImg, BindingResult result, MultipartFile image,String image1,Model model){
        carouselImg.setSectionId(CommonData.ranking);
        try {
            boolean b = org.apache.commons.lang.StringUtils.isBlank(image.getOriginalFilename());
            File file=null;
            if(b==false) {
                file = File.createTempFile("tmp", null);
                imgFileName = image.getOriginalFilename();
                image.transferTo(file);
            }
            if(carouselImg.getId()!=null && !("").equals(carouselImg.getId())){
                carouselImgService.editCarouselImg(file,imgFileName,carouselImg,image1);
                message="操作成功";
            }else{
                carouselImgService.addCarouselImg(file,imgFileName,carouselImg);
                message="操作成功";
            }
        } catch (IOException e) {
            e.printStackTrace();
            message="操作失败，请联系工作人员。";
        }
        model.addAttribute("message",message);
        return "/cms/carousel/carouselImgEdit.jsp";
    }

    @RequestMapping("/delCarouseImg")
    public void delCarouseImg(String id, HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, carouselImgService.delCarouselImg(iD), PropertiesUtil.getValue("contentType"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
