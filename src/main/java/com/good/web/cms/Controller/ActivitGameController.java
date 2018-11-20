package com.good.web.cms.Controller;
/**
 * @Auther: Administrator
 * @Date: 2018/10/25 09:24
 * @Description:
 */

import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.ActivityGame;
import com.good.web.domain.Game;
import com.good.web.service.ActivityGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName ActivitGameController
 * @Description TODO
 * @Author Administrator
 * @Data 2018/10/25 9:24
 * @Version 1.0
 **/
@RequestMapping("/cms/game")
public class ActivitGameController {
    @Autowired
    private ActivityGameService service;

    //返回数据
    private String message="";
    private List<ActivityGame> activityGameList;

    /**
     * 添加/修改游戏
     */
    @RequestMapping("/saveOrUpdateActivityGame")
    public String saveOrUpdateActivityGame(ActivityGame activityGame, Model model){
     //   message = service.saveOrUpdateActivityGame(game,icon,iconFileName,imgs,imgsFileName);
        return "/cms/game/addActivityGame.jsp";
    }
    /**
     * 查询游戏
     */
    @RequestMapping("/activityGameList")
    public String activityGameList(Model model){
        activityGameList = service.queryActivityGameList();
        model.addAttribute("activityGameList",activityGameList);
        return "/cms/game/activityGameList.jsp";
    }
    /**
     * 根据id查找游戏
     */
    @RequestMapping("/findActivityGameById")
    public String findActivityGameById(String id,Model model){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        ActivityGame game = service.findActivityGameById(iD);
        activityGameList = new ArrayList<ActivityGame>();
        activityGameList.add(game);
        model.addAttribute("activityGameList",activityGameList);
        return "";
    }

}
