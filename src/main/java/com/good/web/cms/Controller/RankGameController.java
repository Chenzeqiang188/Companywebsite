package com.good.web.cms.Controller;
/**
 * @Auther: Administrator
 * @Date: 2018/10/25 14:11
 * @Description:
 */

import com.good.web.base.DataCenter;
import com.good.web.base.common.Page;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.RankGame;
import com.good.web.domain.Show;
import com.good.web.service.IRankGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RankGameController
 * @Description TODO
 * @Author Administrator
 * @Data 14:11
 * @Version 1.0
 **/
@Controller
@RequestMapping("/cms/rankGame")
public class RankGameController {

    @Autowired
    private IRankGameService rankGameService;
    @Autowired
    private DataCenter dataCenter;
    private List<RankGame> rankGamelist;
    private List<Show> ranksList=new ArrayList<Show>();

    private String message;
    private String imgFileName;



    /**
     * 跳转排行榜查询界面
     */
/*    @RequestMapping("/rankGameListPage")
    public String rankGameListPage(Model model){
        ranksList.clear();
        ranksList.addAll(dataCenter.rankList);
        model.addAttribute("ranksList",ranksList);
        return "/cms/rankGame/rankGameList.jsp";
    }*/
    /**
     * 获取排行榜下的游戏数据
     */
    @RequestMapping("/rankGameList")
    public String rankGameList(RankGame rankGame,String showtype,Page page,Model model){
        ranksList.clear();
        ranksList.addAll(dataCenter.rankList);
        ranksList.addAll(dataCenter.phoneRankList);
        rankGamelist=rankGameService.rankGameList(rankGame.getRankId(),rankGame.getGameName(),showtype,page);
        model.addAttribute("ranksList",ranksList);
        model.addAttribute("rankGamelist",rankGamelist);
        return "/cms/rankGame/rankGameList.jsp";
    }
    /**
     * 跳转更新界面
     */
    @RequestMapping("editRankGamePage")
    public String editRankGamePage(String id, Model model){
        ranksList.clear();
        ranksList.addAll( dataCenter.rankList);
        ranksList.addAll(dataCenter.phoneRankList);
        RankGame rankGame=new RankGame();
        if(id!=null && !("").equals(id)){
            rankGame = rankGameService.getRankGame(Integer.parseInt(id));
        }else {
            rankGame = new RankGame();

        }
        model.addAttribute("ranksList",ranksList);
        model.addAttribute("rankGame",rankGame);
        return "/cms/rankGame/rankGameEdit.jsp";
    }
    /**
     * 添加、更新
     */
    @RequestMapping("/addRankGames")
    public String addRankGames(@Valid RankGame rankGame,BindingResult result, MultipartFile image,String image1, String type, String downloadUrl, String downloadUrl2,Model model){
        ranksList.clear();
        ranksList.addAll(dataCenter.rankList);
        ranksList.addAll(dataCenter.phoneRankList);
        if(rankGame.getLinkPosition()!=null){
            rankGame.setLinkPosition(rankGame.getLinkPosition().replace(" ", ""));
        }
        if(type.equals("1")){
            rankGame.setDownloadUrl(downloadUrl2);
        }else{
            rankGame.setDownloadUrl(downloadUrl);
        }
        try {
            boolean b = org.apache.commons.lang.StringUtils.isBlank(image.getOriginalFilename());
            File file=null;
            if(b==false) {
                file = File.createTempFile("tmp", null);
                imgFileName = image.getOriginalFilename();
                image.transferTo(file);
            }
            if(rankGame.getId()!=null && !("").equals(rankGame.getId())){
                rankGameService.editRankGame(file,imgFileName,rankGame,image1);
                message="操作成功";
            }else{
                rankGameService.addRankGame(file,imgFileName,rankGame);
                message="操作成功";
            }
        } catch (IOException e) {
            e.printStackTrace();
            message="操作失败，请联系工作人员。";
        }
        model.addAttribute("message",message);
        dataCenter.loadRankList();
        return "/cms/rankGame/rankGameEdit.jsp";
    }
    /**
     * 删除排行榜的游戏
     */
    @RequestMapping("/delRankGame")
    public String delRankGame(String id){
        Integer iD = ValidateUtil.str_isEmpty(id) ? null : Integer.parseInt(id);
        rankGameService.delRankGame(iD);
        ranksList.clear();
        ranksList.addAll(dataCenter.rankList);
        ranksList.addAll(dataCenter.phoneRankList);
       // rankGamelist=rankGameService.rankGameList(rankGame.getRankId(),rankGame.getGameName(),showtype,page);
        dataCenter.loadRankList();
        return "/cms/rankGame/rankGameList.jsp";
    }


}
