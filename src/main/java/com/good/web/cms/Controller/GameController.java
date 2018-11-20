package com.good.web.cms.Controller;

import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Game;
import com.good.web.service.GameService;
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
@RequestMapping("/cms/game")
public class GameController {

    @Autowired
    private GameService service;

    private String message;
    private List<Game> gameList;

    private String imgFileName;
    private String qrCodeImgFileName;

    /**
     * 添加/修改游戏
     */
    @RequestMapping(value = "/saveOrUpdateGame",method = RequestMethod.POST)
    public String saveOrUpdateGame(@Valid Game game, BindingResult result, MultipartFile img,
                                   String img1, @RequestParam(required = false) MultipartFile qrCodeImg,
                                   @RequestParam(required = false) String qrCodeImg1 , Model model) throws IOException {
        int i=game.getHot();
        boolean b = org.apache.commons.lang.StringUtils.isBlank(img.getOriginalFilename());
        boolean b2 = org.apache.commons.lang.StringUtils.isBlank(img.getOriginalFilename());
        File file=null;
        File file2=null;
        if(i==1)
        {
            if(b==false) {
                file = File.createTempFile("tmp", null);
                imgFileName = img.getOriginalFilename();
                img.transferTo(file);
            }
            message=service.saveOrUpdateHotGame(game,file,img1,imgFileName);
        }else{
            if(b==false) {
                file = File.createTempFile("tmp", null);
                imgFileName = img.getOriginalFilename();
                img.transferTo(file);
            }
            if(b2==false) {
                file2 = File.createTempFile("tmp", null);
                qrCodeImgFileName = qrCodeImg.getOriginalFilename();
                qrCodeImg.transferTo(file2);
            }
            message = service.saveOrUpdateGame(game, file, imgFileName, file2,qrCodeImgFileName,img1,qrCodeImg1);
        }
        model.addAttribute("message",message);
        if(game.getHot()==0)
            return "/cms/game/addGame.jsp";
        else
            return "/cms/game/addHotGame.jsp";
    }
    /**
     * 查询游戏
     */
    @RequestMapping("/gameList")
    public String gameList(Game game,Model model){
        try {
            gameList = service.queryGameList(game);
            model.addAttribute("gameList",gameList);
            return "/cms/game/gameList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 根据id查找游戏
     */
    @RequestMapping("/findGameById")
        public String findGameById(String id,Model model){
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            Game game = service.findGameById(iD);
            model.addAttribute("game",game);
            if(game.getHot()==1)
                return "/cms/game/addHotGame.jsp";
            else
                return "/cms/game/addGame.jsp";
    }
    /**
     * 根据id删除游戏
     */
    @RequestMapping("/deleteGameById")
    public void deleteGameById(String id, HttpServletResponse response){
        try {
            Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
            ServletUtil.responseToClient(response, service.deleteGameById(iD), PropertiesUtil.getValue("contentType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
