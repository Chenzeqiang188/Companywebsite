package com.good.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.common.Page;
import com.good.web.domain.Show;
import com.good.web.service.IShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ShowServiceImpl implements IShowService{

	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;

	@Override
	public List<Show> sectionList(Integer type,Page page){
		String hql="from Show where parentId=0 ";
		if(type!=null && !("").equals(type)){
			hql+=" and type="+type;
		}
		page.setTotalSize(entityManage.getCountByHql(hql));
		return entityManage.findByHql(hql,page.getPageNum(),(short)page.getPageSize());
	}
	@Override
	public String editSection(Show show){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sql=new StringBuffer("update tb_show set ");
		if(show.getTitle()!=null && !("").equals(show.getTitle())){
			sql.append("title=:title,");
			map.put("title", show.getTitle());
		}
		sql.append("status=:status where id=:id");
		map.put("status", show.getStatus());
		map.put("id", show.getId());
		entityManage.updateBySql(sql.toString(),map);
		dataCenter.loadSectionsList();
		return "操作成功";
	}
	@Override
	public List<Show> showList(Integer type,String title,Page page){
		String hql="from Show where parentId!=0 ";
		if(type!=null && !("").equals(type)){
			hql+=" and type="+type;
		}
		if(title!=null && !("").equals(title)){
			hql+=" and title like '%"+title+"%'";
		}
		page.setTotalSize(entityManage.getCountByHql(hql));
		return entityManage.findByHql(hql,page.getPageNum(),(short)page.getPageSize());
	}
	@Override
	public void editShow(Show show){
		entityManage.update(show);
		dataCenter.loadSectionsList();
	}
	@Override
	public void addShow(Show show){
		entityManage.save(show);
		dataCenter.loadSectionsList();
	}
	@Override
	public Show getShow(Integer id){
		return (Show) entityManage.findById(Show.class, id);
	}

	@Override
	public int delShow(Integer id) {
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String sql = "delete from tb_rank_game where show_id=:id";
			map.put("id", id);
			entityManage.updateBySql(sql, map);
			Show show =(Show)entityManage.findById(Show.class,id);
			entityManage.delete(show);
			entityManage.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		dataCenter.loadSectionsList();
		//dataCenter.loadRankList();
		return 1;
	}
}
