package com.good.web.service;

import com.good.web.domain.ActivityGame;

import java.io.File;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/10/25 09:32
 * @Description:通用活动游戏模块接口
 */
public interface ActivityGameService {
    List<ActivityGame> queryActivityGameList();

    ActivityGame findActivityGameById(Integer id);

    int deleteActivityGameById(Integer id);

    List<ActivityGame> findActivityGameByName(String name);

    String saveOrUpdateActivityGame(ActivityGame game,File icon,
                                           String iconFileName,File[] imgs, List<String> imgsFileName);

}
