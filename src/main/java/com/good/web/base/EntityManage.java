package com.good.web.base;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.AbstractStandardBasicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


/**
 * 
 * @author 黄骏
 * Class name:EntityManage
 * Description:用于进行数据库的持久化操作，即把实体对象保存到数据库中
 * Create time:May 3, 2009
 */

@Component
public class EntityManage extends HibernateDaoSupport {



	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
		this.getHibernateTemplate().setCheckWriteOperations(false);
	}

	public Session getSession(){
		return this.getSessionFactory().openSession();
	}


	private static final Log log = LogFactory.getLog(EntityManage.class);

	/**
	 *
	 * Function name:save
	 * Description: 保存对象到数据库
	 * @param transientInstance：需要保存的对象
	 */
	public void save(Object transientInstance) {
		log.debug("saving tbActor instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;

		}
	}

	/**
	 *
	 * Function name:delete
	 * Description: 删除数据库中的记录
	 * @param persistentInstance：所要删除的对象实例
	 */
	public void delete(Object persistentInstance) {
		log.debug("deleting tbActor instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 *
	 * Function name:update
	 * Description: 更新数据库的对象
	 * @param instance：所要更新的对象实例
	 */
	public void update(Object instance) {
		log.debug("attaching dirty tbActor instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 *
	 * Function name:findById
	 * Description: 通过编号获取实体对象实例
	 * @param entity：实体对象的class
	 * @param id：对象编号
	 * @return：实体对象实例
	 */
	public Object findById(Class entity,long id) {
		log.debug("finding instance with id: " + id);
		try {
			Object rs = getHibernateTemplate().get(entity, id);
			return rs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 *
	 * Function name:findById
	 * Description: 通过编号获取实体对象实例
	 * @param entity：实体对象的class
	 * @param id：对象编号
	 * @return：实体对象实例
	 */
	public Object findById(Class entity,String id) {
		log.debug("finding instance with id: " + id);
		try {
			Object rs = getHibernateTemplate().get(entity, id);
			return rs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 *
	 * Function name:findById
	 * Description: 通过编号获取实体对象实例
	 * @param entity：实体对象的class
	 * @param id：对象编号
	 * @return：实体对象实例
	 */
	public Object findById(Class entity,int id) {
		log.debug("finding instance with id: " + id);
		try {
			Object rs = getHibernateTemplate().get(entity, id);
			return rs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw re;
		}
	}

	/**
	 *
	 * Function name:findAll
	 * Description: 查找指定表的所有数据
	 * @param tablename：所要查找的表的名字，表名在tbEntityDAO中有定义
	 * @return：表中所有数据的集合
	 */
	public List findAll(String tablename) {
		log.debug("finding all from "+tablename);
		try {
			String queryString = "from "+tablename;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 *
	 * Function name:findByProperty
	 * Description: 通过属性查找符合条件的数据
	 * @param tableName：数据表名，在tbEntityDAO中定义
	 * @param propertyName：属性名
	 * @param value：属性值
	 * @return:返回符合条件的数据列表
	 */
	public List findByProperty(String tableName,String propertyName, Object value) {
		log.debug("finding instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from "+tableName+" as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 *
	 * Function name:findByProperties
	 * Description: 通过多个属性查询符合条件的数据
	 * @param tableName：数据表名，在tbEntityDAO中定义
	 * @param propertyName：属性名数组
	 * @param value：属性值数组
	 * @return：返回符合条件的数据列表
	 */
	public List findByProperties(String tableName,String[] propertyName, Object[] value) {
		log.debug("finding instance with properties: "
				+ propertyName.toString() + ", value: " + value.toString());
		try {
			String queryString = "from "+tableName+" as model ";
			for(int i=0;i<propertyName.length;i++){
				if(i==0){
					queryString += " where model."+propertyName[i]+"=? ";
				}else{
					queryString += " and model."+propertyName[i]+"=? ";
				}
			}
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 *
	 * Function name:findByHql
	 * Description: 根据hql查询数据库，获取相应数据
	 * @param hql：hql查询语句
	 * @return：符合查询条件的数据集合
	 */
	public List findByHql(String hql){
		log.debug("finding instance with hql: "+hql);
		try {
			return getHibernateTemplate().find(hql);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 *
	 * Function name:findByHql
	 * Description:通过hql查找数据，带分页功能
	 * @param hql:hql语句
	 * @param pageNum：页码（1开始）
	 * @param pageSize：页个数
	 * @return：返回数据列表
	 */
	public <T> List<T> findByHql(final String hql,final int pageNum,final short pageSize){
		return (List<T>) this.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					final int items = (pageNum - 1) * pageSize;
					public List doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setFirstResult(items);// 定义从第几条开始查询
						query.setMaxResults(pageSize);// 定义返回的记录数

						List list = query.list();
						return list;
					}
				}
		);
	}

	public <T> List<T> findByHqlMap(final String hql,final Map<String, Object> map,final int pageNum,final short pageSize){

			// pageIndex 当前页，pageSize页显示大小

			return (List<T>) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					final int items = (pageNum - 1) * pageSize;
					public Object doInHibernate(Session session)
							throws HibernateException{
						Query query = session.createQuery(hql);
						query = fillmapParams(query, map);
						query.setFirstResult(items);// 定义从第几条开始查询
						query.setMaxResults(pageSize);// 定义返回的记录数

						List list = query.list();
						return list;
					}
				}
			);
	}


	public <T> List<T> findByHql(final String hql, final Object[] params,int pageNum,final int pageSize){
			// pageIndex 当前页，pageSize页显示大小
			final int items = (pageNum - 1) * pageSize;
			return (List)getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query = fillParams(query, params);
						query.setFirstResult(items);// 定义从第几条开始查询
						query.setMaxResults(pageSize);// 定义返回的记录数

						List list = query.list();
						return list;
					}
				}
			);
	}
	public  Query fillParams(Query q , Object[] params){
		for(int i = 0;i < params.length;++i){
			q.setParameter(i, params[i]);
		}
		return q;
	}
	/**
	 *
	 * Function name:getCountByHql
	 * Description:通过一个hql语句获得数据总量
	 * @param hql：hql语句
	 * @return：返回数据总量
	 */
	public int getCountByHql(String hql){
		try{
			String countHql = "select count(*) "+hql;
			int count = ((Long)getHibernateTemplate().find(countHql).get(0)).intValue();
//			int count = ((Long)getHibernateTemplate().iterate(countHql).next()).intValue();
			return count;
		}catch(RuntimeException ex){
			log.error("find by getCountByHql", ex);
			throw ex;
		}
	}
	/**
	 *
	 * Function name:getCountByHql
	 * Description:通过一个hql语句获得数据总量
	 * @param hql：hql语句
	 * @return：返回数据总量
	 */
	public int getCountByHql(final String hql,final Map<String, Object> map){
		log.debug("finding instance with hql: "+hql);
		int i=0;
		Session session = this.getSession();
		try {
			String countHql = "select count(*) "+hql;
			Query query = session.createQuery(countHql);
			query = fillmapParams(query, map);
			List<Object> rs = query.list();
			if(rs!=null&&rs.size()>0){
				Object objs=rs.get(0);
				if(objs == null){
				}else if(objs instanceof Integer){
					i= (Integer)objs;
				}else if(objs instanceof BigInteger){
					i= ((BigInteger)objs).intValue();
				}else if(objs instanceof Long){
					i= ((Long)objs).intValue();
				}
			}
			return i;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}finally{
			session.close();
			//this.releaseSession(session);
		}
	}

	/**
	 *
	 * Function name:findByHqlWithCount
	 * Description: 通过hql查找数据，带分页功能，并且返回总条目数
	 * @param hql：查询语句
	 * @param pageNum：页码（1开始）
	 * @param pageSize：页个数
	 * @return：返回数据列表
	 */
	public <T> List<T> findByHqlWithCount(final String hql,final int pageNum,final short pageSize){
			// pageIndex 当前页，pageSize页显示大小
			return (List<T>) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						final int items = (pageNum - 1) * pageSize;
						String countHql = "select count(*) "+hql;
						int count = ((Long)getHibernateTemplate().find(countHql).get(0)).intValue();
						Query query = session.createQuery(hql);

						query.setFirstResult(items);// 定义从第几条开始查询
						query.setMaxResults(pageSize);// 定义返回的记录数

						List list = query.list();
						return list;
					}
				}
			);

	}

	/**
	 *
	 * Function name:findBySql
	 * Description: 通过sql查找数据
	 * @param sql：sql字串
	 * @return:数据列表
	 */
	public List findBySql(String sql){
		log.debug("finding instance with hql: "+sql);
		Session session = this.getSession();
		try {

			List rs = session.createSQLQuery(sql).list();
			return rs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}finally{
			session.close();
			//this.releaseSession(session);
		}
	}

	/**
	 * Function name:findStringDataBySql
	 * Description:通过sql查询数据，并且转化成为String[]的列表输出
	 * @param sql：sql语句
	 * @return：返回结果
	 */
	public List<String[]> findStringDataBySql(String sql){
		log.debug("finding instance with hql: "+sql);
		Session session = this.getSession();
		try {
			List<String[]> result = new ArrayList<String[]>();
//			List<Object[]> rs = (List<Object[]>)findBySql(sql);
			List rs = findBySql(sql);
			if(rs!=null){
				if(rs.size()>0){
					if(rs.get(0) instanceof Object[]){
						for(Object[] objs:(List<Object[]>)rs){
							String[] data = new String[objs.length];
							for(int i=0;i<data.length;i++){
								Object obj = objs[i];
								if(obj!=null){
									data[i]=obj.toString();
								}else{
									data[i]=null;
								}
							}
							result.add(data);
						}
					}else if(rs.get(0) instanceof BigInteger){
						for(BigInteger obj:(List<BigInteger>)rs){
							String[] data = new String[1];
							if(obj!=null){
								data[0]=obj.toString();
							}else{
								data[0]=null;
							}
							result.add(data);
						}
					}else{
						for(Object obj:(List<Object>)rs){
							String[] data = new String[1];
							if(obj!=null){
								data[0]=obj.toString();
							}else{
								data[0]=null;
							}
							result.add(data);
						}
					}
				}
				return result;
			}else{
				return null;
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}finally{
			session.close();
			//this.releaseSession(session);
		}
	}

	/**
	 * Function name:findBySql
	 * Description: 通过sql查找数据，并且可以指定输出字段类型
	 * @param sql：sql语句
	 * @param fieldnames：字段名列表
	 * @param fieldtypes：字段指向的Hibernate类型，用Hibernate.获取，例如Hibernate.STRING
	 * @return
	 */
	public List findBySql(String sql,String[] fieldnames,AbstractStandardBasicType[] fieldtypes){
		log.debug("finding instance with hql: "+sql);
		Session session = this.getSession();
		try {
			SQLQuery query = session.createSQLQuery(sql);
			for(int i=0;i<fieldnames.length;i++){
				if(fieldtypes[i] == null){
					query.addScalar(fieldnames[i]);
				}else{
					query.addScalar(fieldnames[i], fieldtypes[i]);
				}
			}
			List rs = query.list();
			return rs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}finally{
			session.close();
			//this.releaseSession(session);
		}
	}

	/**
	 *
	 * Function name:findBySql
	 * Description: 通过sql查找数据
	 * @param sql：sql字串
	 * @param clasz:要查询的类
	 * @return:数据列表
	 */
	public List findBySql(String sql,Class clasz){
 		log.debug("finding instance with sql: "+sql);
		Session session = this.getSession();
		try {
			List rs = session.createSQLQuery(sql).addEntity(clasz).list();
			return rs;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}finally{
			session.close();
			//this.releaseSession(session);
		}
	}

	/**
	 *
	 * Function name:updateBySql
	 * Description: 根据sql进行更新
	 * @param sql：sql语句
	 * @return：返回结果
	 */
	public boolean updateBySql(String sql){
		log.debug("update by sql: "+sql);
		Session session = this.getSession();
		try {
			int rs = session.createSQLQuery(sql).executeUpdate();
			return true;
		} catch (Exception re) {
			log.error("update by sql error ", re);
			return false;
		}finally{
			session.close();
			//this.releaseSession(session);
		}
	}
	/**
	 * sql语句更新数据
	 * @Title: updateBySql
	 * @Description:
	 * @author：     周文广
	 * @date 2017-3-6 下午05:51:36
	 * @param sql
	 * @return
	 */
	public Integer updateBySql(String sql,Map<String,Object> map){
		log.debug("update by sql: "+sql);
		Integer rows = null;
		Session session = this.getSession();
		try {
			Query q = session.createSQLQuery(sql);
			fillmapParams(q, map);
			rows = q.executeUpdate();
			return rows;
		} catch (Exception re) {
			log.error("update by sql error ", re);
			return 0;
		}finally{
			session.close();
			//this.releaseSession(session);
		}
	}

	/**
	 * map 类参数设置
	 * @Title: mapParams
	 * @Description:
	 * @author：周文广
	 * @date 2016-12-19 上午11:38:30
	 * @param q
	 * @return
	 */
	public  Query fillmapParams(Query q , Map<String,Object> map){
		for (Map.Entry<String,Object> m : map.entrySet()) {
			q.setParameter(m.getKey(), m.getValue());
		}
		return q;
	}

	/**
	 *
	 * Function name:updateBySql
	 * Description: 根据sql进行更新
	 * @param sql：sql语句
	 * @return：返回更新影响个数(-1表示失败)
	 */
	public int updateBySqlWithCount(String sql){
		log.debug("update by sql with count: "+sql);
		Session session = this.getSession();
		try {
			int rs = session.createSQLQuery(sql).executeUpdate();
			return rs;
		} catch (Exception re) {
			log.error("update by sql error ", re);
			return -1;
		}finally{
			session.close();
			//this.releaseSession(session);
		}
	}

	/**
	 *
	 * Function name:findByHql
	 * Description: 根据hql查询数据库，获取相应数据
	 * @param hql: hql查询语句
	 * @param values: hql中的？号填充值
	 * @return：符合查询条件的数据集合
	 */
	public List findByHql(String hql,Object[] values){
		log.debug("finding instance with hql: "+hql);
		try {
			return getHibernateTemplate().find(hql,values);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 *
	 * Function name:insertRecords
	 * Description: 批量插入数据
	 * @param records：数据集合
	 * @return: true成功 false失败
	 */
	public boolean insertRecords(List records){
		//Session session = getSessionFactory().openSession();
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < records.size() && i < 10000; i++) {
				Object record = records.get(i);
				session.save(record);
				if (i % 20 == 0) { //20, same as the JDBC batch size
					//flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("批量插入数据出错了:"+e.getMessage());
			tx.rollback();
			return false;
		} finally{
			session.close();
			//this.releaseSession(session);
		}
	}

	/**
	 *
	 * Function name:insertRecords
	 * Description: 批量更新数据
	 * @param records：数据集合
	 * @return: true成功 false失败
	 */
	public boolean updateRecords(List records){
		//Session session = getSessionFactory().openSession();
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < records.size() && i < 10000; i++) {
				Object record = records.get(i);
				session.update(record);
				if (i % 20 == 0) { //20, same as the JDBC batch size
					//flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("批量更新数据出错了:"+e.getMessage());
			tx.rollback();
			return false;
		} finally{
			session.close();
			//this.releaseSession(session);
		}
	}




	/**
	 *
	 * Function name:insertRecords
	 * Description: 批量删除数据
	 * @param records：数据集合
	 * @return: true成功 false失败
	 */
	public boolean deleteRecords(List records){
		//Session session = getSessionFactory().openSession();
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < records.size() && i < 10000; i++) {
				Object record = records.get(i);
				session.delete(record);
				if (i % 20 == 0) { //20, same as the JDBC batch size
					//flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("批量删除数据出错了:"+e.getMessage());
			tx.rollback();
			return false;
		} finally{
			session.close();
			//this.releaseSession(session);
		}
	}


	/**
	 * Function name:insertOperLogMultiTable
	 * Description: 批量插入玩家操作日志(分表)
	 * @param records:日志记录列表
	 */
	public void insertOperLogMultiTable(List records){
		Session session = getSessionFactory().openSession();
		try {
			Transaction tx = session.beginTransaction();
			for (int i = 0; i < records.size() && i < 10000; i++) {
				Object record = records.get(i);
				session.save(record);
				if (i % 100 == 0) { //20, same as the JDBC batch size
					//flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (Exception e) {
			log.error("批量插入玩家操作日志出错了:"+e.getMessage());
		}finally{
			session.close();
		}
	}

	public void saveOrUpdate(Object entity){
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public <T> List<T> findByHql(String hql,Map<String,Object> attrs){
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		Set<String> keys = attrs.keySet();
		if(keys.size()>0){
			for(String key:keys)
				query.setParameter(key, attrs.get(key));
		}
		return query.list();
	}

	/**
	 *
	 * Function name:main
	 * Description:用于测试EntityManage的主方法
	 * @param argv：main方法的入参
	 */
	public final static void main(String[] argv){

	}
}
