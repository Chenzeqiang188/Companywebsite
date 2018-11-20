package com.good.web.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.swing.text.html.parser.Entity;

//import org.apache.struts2.ServletActionContext;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.good.web.base.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/*import com.good.web.domain.ActivityCard;
import com.good.web.domain.ActivityGame;
import com.good.web.domain.ActivityGameNews;
import com.good.web.domain.Advice;
import com.good.web.domain.Card;
import com.good.web.domain.CarouselImg;
import com.good.web.domain.Dept;
import com.good.web.domain.Game;
import com.good.web.domain.History;
import com.good.web.domain.Honor;
import com.good.web.domain.Image;
import com.good.web.domain.News;
import com.good.web.domain.NewsCategory;
import com.good.web.domain.Permission;
import com.good.web.domain.PositionType;
import com.good.web.domain.RankGame;
import com.good.web.domain.Recruit;
import com.good.web.domain.Show;*/

@SuppressWarnings("unchecked")
@Component
@PropertySource(value = {"classpath:config.properties"})
public class DataCenter implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DataCenter.class);

	@Autowired
	public EntityManage entityManage;

	@Autowired
	private WebApplicationContext webApplicationConnect;

	@Value("${imagepath}")
	private String imagepath;


	//数据
	//幻灯片列表
	public static List<Card> cardList = new ArrayList<Card>();

	//游戏列表
	public List<Game> gameList = new ArrayList<Game>();
	//发展历程列表
	public List<History> historyList = new ArrayList<History>();
	//荣誉列表
	public List<Honor> honorList = new ArrayList<Honor>();
	//图片列表
	public List<Image> imageList = new ArrayList<Image>();
	
	//新闻列表
	public List<News> newsList = new ArrayList<News>();
	public HashMap<Integer,News> NewsMap=new HashMap<Integer, News>();
	//新闻类型列表
	public List<NewsCategory> newsCatList = new ArrayList<NewsCategory>();
	
	//职位或搜索标签列表
	public List<PositionType> positionTypeList = new ArrayList<PositionType>();
	//招聘列表
	public List<Recruit> recruitList = new ArrayList<Recruit>();
	public HashMap<Integer,Recruit>recruitMap=new HashMap<Integer, Recruit>();
	//部门表
	public List<Dept> deptList = new ArrayList<Dept>();



	//通用活动幻灯片
	public List<ActivityCard> activitycardList = new ArrayList<ActivityCard>();
	
	//通用活动游戏列表
	public List<ActivityGame> activitygameList = new ArrayList<ActivityGame>();
	public HashMap<Integer,ActivityGame> activityGameMap=new HashMap<Integer, ActivityGame>();
	
	//通用活动新闻列表
	public  List<ActivityGameNews> gamenewslist=new ArrayList<ActivityGameNews>();
	//游戏id列表
	public List<Integer> gameIdList = new ArrayList<Integer>();
	//系统权限(用于鉴权)
	public List<Permission> permissions = new ArrayList<Permission>();
	//底部通栏
  public  List<Image> limage=new ArrayList<Image>();
//	----------------------------------------------------------
	//广告
	public List<Advice> adviceList = new ArrayList<Advice>();
	//展示板块
	public List<Show> sectionList = new ArrayList<Show>();
	public Map<Integer, Show> sectionMap  = new HashMap<Integer, Show>();
  //pc榜单名称
	public List<Show> rankList = new ArrayList<Show>();
	public Map<Integer, Show> rankMap  = new HashMap<Integer, Show>();
//手机榜单名称
	public List<Show> phoneRankList = new ArrayList<Show>();
	public Map<Integer, Object> phoneRankMap  = new HashMap<Integer, Object>();
	//手机轮播图
	public List<CarouselImg> carouselImgList = new ArrayList<CarouselImg>();
	
//	---------------------------------------------------------
	/**
	 * 管理平台启动的时候加载数据
	 */
	public void loadDataOnStart() {
		loadCard();
		loadGame();
		loadHistory();
		loadHonor();
		loadImage();
		loadNews();
		loadNewsCategory();
		loadPositionType();
		loadRecruit();
		loadDept();
		loadActivityCard();
		loadActivityGame();
		loadActivityGameNews();
		loadGameIdList();
		loadSectionsList();
		loadCarouselImgList();
		//getPermissionData();
	}
	/**
	 * 手机榜单轮播图
	 */
	public void loadCarouselImgList(){
		carouselImgList.clear();
		String hql="from CarouselImg where  NOW()<endTime and status=0 order by createTime desc";
		carouselImgList = entityManage.findByHql(hql);
	}
	
		
	/**
	 * 读取榜单 排行榜
	 */
	public void loadRankList() {
		rankList.clear();
		rankMap.clear();
		phoneRankList.clear();
		phoneRankMap.clear();
		List<Show> list = entityManage.findAll("Show where parentId=2 and status=0");
		if(list!=null && list.size()>0){
			for(Show rank : list){
				/*for (int i = 0; i < rank.getGameList().size(); i++) {
					if(rank.getGameList().get(i).getStatus()!=0){
						rank.getGameList().remove(i);
					}
				}*/
				if(rank.getType()==0){
					rankList.add(rank);
					rankMap.put(rank.getId(), rank);
				}else if(rank.getType()==1){
					phoneRankList.add(rank);
					phoneRankMap.put(rank.getId(), rank.getGameList());
				}
			  
			}
		}
	}
	/**
	 * 读取板块
	 */
	
	public void loadSectionsList() {
		sectionList.clear();
		sectionMap.clear();
		List<Show> list = entityManage.findAll("Show where parentId=0");
		if(list!=null && list.size()>0){
			sectionList.addAll(list);
			for(Show section : list){
				sectionMap.put(section.getId(), section);
			}
		}
		loadAdvicesList();
		loadRankList();
	}


	/**
	 * 读取广告
	 */
	
	public void loadAdvicesList() {
		adviceList.clear();
		List<Advice> advices = entityManage.findAll("Advice  where endTime>NOW()  and status=0");
		adviceList.addAll(advices);
	}


	/**
	 * 读取幻灯片
	 */
	
	public void loadCard(){
		cardList.clear();
		List<Card> list = entityManage.findAll("Card where state=1  order  by sorting asc,createTime desc");
		if(list!=null && list.size()>0){
			cardList.addAll(list);
		}
		System.out.println(list);
	}
	/**
	 * 读取游戏资料
	 */
	public void loadGame(){
		gameList.clear();
		List<Game> list = entityManage.findAll("Game where state=1 order by sort asc");
		if(list!=null && list.size()>0){
			gameList.addAll(list);
		}
	}
	/**
	 * 读取公司历程
	 */
	public void loadHistory(){
		historyList.clear();
		List<History> list = entityManage.findAll("History where state=1  order by historyDate desc");
		if(list!=null && list.size()>0){
			historyList.addAll(list);
		}
	}
	/**
	 * 读取荣誉
	 */
	public void loadHonor(){
		honorList.clear();
		List<Honor> list = entityManage.findAll("Honor  where state=1 order by year desc,awardDate desc");
		if(list!=null && list.size()>0){
			honorList.addAll(list);
		}
	}
	/**
	 * 读取各种图片
	 */
	public void loadImage(){
		imageList.clear();
		limage.clear();
		List<Image> list = entityManage.findAll("Image where state=1 order by sorting asc");
		if(list!=null && list.size()>0){
			imageList.addAll(list);
			for(Image img:imageList){
      	if(!ValidateUtil.str_isEmpty(img.getImgType())&&img.getImgType().equals("bar") ){
      		limage.add(img);
      	}
      }
		}
	}
	/**
	 * 读取新闻
	 */
	public void loadNews(){
		newsList.clear();
		NewsMap.clear();
//		String hql="select new  News(id, title,categoryId,author,createTime,img,description,url,state) " +
//				" from News where state=1 order by createTime desc LIMIT 200";
		String hql=" from News where state=1 order by createTime " /* desc LIMIT 200*/;
		List<News> list =entityManage.findByHql(hql);
		if(list!=null && list.size()>0){
			newsList.addAll(list);
			for(News news:list){
				NewsMap.put(news.getId(), news);
			}
			
		}
	}
	
	/**
	 * 读取新闻类型
	 */
	public void loadNewsCategory(){
		newsCatList.clear();
		List<NewsCategory> list = entityManage.findAll("NewsCategory order by sorting asc");
		if(list!=null && list.size()>0){
			newsCatList.addAll(list);
		}
	}
	/**
	 * 读取职位分类
	 */
	public void loadPositionType(){
		positionTypeList.clear();
		List<PositionType> list = entityManage.findAll("PositionType");
		if(list!=null && list.size()>0){
			positionTypeList.addAll(list);
		}
	}
	/**
	 * 读取招聘信息
	 */
	public void loadRecruit(){
		recruitList.clear();
		recruitMap.clear();
		List<Recruit> list = entityManage.findAll("Recruit where state=1 order by createTime desc");
		if(list!=null && list.size()>0){
			recruitList.addAll(list);
			for(Recruit r:list){
				recruitMap.put(r.getId(), r);
			}
		}
	}



	/**
	 * 读取部门
	 */
	public void loadDept() {
		deptList.clear();
		List<Dept> list = entityManage.findAll("Dept");
		if(list!=null && list.size()>0){
			deptList.addAll(list);
		}
	}
	
	/**
	 * 获取权限列表
	 */
	private void getPermissionData(){
		List<Permission> perms = entityManage.findAll("Permission");
		if(perms!=null && perms.size()>0){
			permissions = perms;
		}
	}
	
	
	/**
	 * @Title: loadActivityCard
	 *@Description: 通用活动幻灯片
	 * @param 
	 *@return void
	 * @author 李松茂   
	 * 2016-7-12 上午11:21:32
	 */
	public void loadActivityCard(){
		activitycardList.clear();
		String hql=" from ActivityCard  order by sort asc";
		List<ActivityCard> list =entityManage.findByHql(hql);
		if(list!=null && list.size()>0){
			activitycardList.addAll(list);
		}
	}
	
	
	public void loadActivityGame(){
		activitygameList.clear();
		activityGameMap.clear();
		List<ActivityGame> list = entityManage.findAll("ActivityGame order by sort asc");
		if(list!=null && list.size()>0){
			activitygameList.addAll(list);
			for(ActivityGame r:list){
				activityGameMap.put(r.getId(), r);
			}
		}
	}

	public void loadActivityGameNews(){
		gamenewslist.clear();
		List<ActivityGameNews> list = entityManage.findAll("ActivityGameNews  order by id desc ");
		gamenewslist.addAll(list);
	}
	
	public void loadGameIdList(){
		gameIdList.clear();
		List<ActivityGame> list = entityManage.findAll("ActivityGame");
		for(ActivityGame game:list){
			gameIdList.add(game.getId());
		}
	}



	@Override
	public void run(String... strings) throws Exception {
		ServletContext se = webApplicationConnect.getServletContext();
		System.out.println("============================InitListener============================");
		loadDataOnStart();
		se.setAttribute("newsCategory",newsCatList);
		se.setAttribute("footimagelist", limage);
		se.setAttribute("positionTypeList",positionTypeList);
		se.setAttribute("deptList", deptList);
		se.setAttribute("gameIdList", gameIdList);
		se.setAttribute("sectionList", sectionList);
		se.setAttribute("imgpath",imagepath);
		logger.info("初始化完成");
	}
}
