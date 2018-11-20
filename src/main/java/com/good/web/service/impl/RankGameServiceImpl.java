package com.good.web.service.impl;

import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.common.Page;
import com.good.web.base.utils.FileUtils;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.domain.RankGame;
import com.good.web.domain.Show;
import com.good.web.service.IRankGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class RankGameServiceImpl implements IRankGameService {

	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	@Autowired
    WebApplicationContext webApplicationContext;


	@Override
	public List<RankGame> rankGameList(Integer showid, String name,String type, Page page){
		String hql="select new RankGame(rg.id,rg.gameRanking,rg.gameName,rg.gameType,rg.image, rg.reffer," +
				"rg.linkPosition,rg.andoridUrl,rg.iosUrl,rg.downloadUrl,rg.info, rg.rankId,s.type,rg.status) " +
				"from RankGame rg,Show s where rg.rankId=s.id ";
		String hqlcount=" from RankGame rg,Show s where rg.rankId=s.id  ";
		Map<String, Object> map = new HashMap<String, Object>();
		if(showid!=null && !("").equals(showid)){
			hql+=" and rg.rankId=:showid";
			hqlcount+=" and rg.rankId=:showid";
			map.put("showid", showid);
		}
		if(name!=null && !("").equals(name)){
			hql+=" and rg.gameName like :name";
			hqlcount+=" and rg.gameName like :name";
			map.put("name", "%"+name+"%");
		}
		if(type!=null && !("").equals(type)){
			hql+=" and s.type =:type";
			hqlcount+=" and s.type=:type";
			map.put("type", Integer.parseInt(type));
		}
		hql+=" order by if(isnull(rg.gameRanking),1,0),rg.gameRanking asc";
		page.setTotalSize(entityManage.getCountByHql(hqlcount,map));
		return entityManage.findByHqlMap(hql,map,page.getPageNum(),(short)page.getPageSize());
	}
	@Override
	public void editRankGame(File img,String imgFileName,RankGame rankGame,String img1){
		String upload = PropertiesUtil.getValue("uploadPath");
		String savePath = webApplicationContext.getServletContext().getRealPath(upload);
		if(img!=null){
			String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
			if(fileName!=null){
				rankGame.setImage(upload + fileName);
			}
		}else{
		    rankGame.setImage(img1);
        }
		entityManage.update(rankGame);
		dataCenter.loadRankList();
		/*String sql="update tb_rank_game set game_ranking=game_ranking+1 where" +
				" game_ranking>=:gameRanking and show_id=:showid and id!=:id and game_ranking is not null";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameRanking", rankGame.getGameRanking());
		map.put("id", rankGame.getId());
		map.put("showid", rankGame.getRankId());
		entityManage.updateBySql(sql, map);*/
	}
	@Override
	public void addRankGame(File img,String imgFileName,RankGame rankGame){
		String upload = PropertiesUtil.getValue("uploadPath");
		String savePath =webApplicationContext.getServletContext().getRealPath(upload);
		if(img!=null){
			String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
                if(fileName!=null){
                    rankGame.setImage(upload + fileName);
			}
		}
		entityManage.save(rankGame);
		dataCenter.loadRankList();
	}
	@Override
	public RankGame getRankGame(Integer id){
		return (RankGame) entityManage.findById(RankGame.class, id);
	}
	@Override
	public List<Show> rankList(Integer type){
		String hql="from Show where parentId=2 and type=?";
//		List<Show> ranks =entityManage.findByHql(hql,new Object[]{type});
		return entityManage.findByHql(hql,new Object[]{type});
	}
	
	@Override
	public void delRankGame(Integer id) {
		try {
            RankGame rankGame = (RankGame)entityManage.findById(RankGame.class, id);
            entityManage.delete(rankGame);
            entityManage.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataCenter.loadRankList();
		
	}
	
	public List<RankGame> rankGameList(Integer rankId){
		String hql=" from RankGame rg where rankId=:rankId and gameRanking is not null order by gameRanking ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rankId", rankId);
		return entityManage.findByHqlMap(hql,map, 1, (short)10);
	}
	
}
