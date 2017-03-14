package com.terry.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.terry.dao.BaseDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.dao.support.Page;


public class BaseDaoImpl<T> implements BaseDao<T>{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;	
		
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
		//return sessionFactory.openSession();
	}
	
	private static final int BATCH_SIZE = 20;	
	
	/**
	 * 通过id获得实体
	 */
	@Override
	public T get(Class<T> entityClazz, Serializable id) {
		// TODO Auto-generated method stub
		return (T)getSession().get(entityClazz,id);
	}
	
	/**
	 * 保存或更新实体
	 */
	@Override
	public T saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * 通过类删除实体
	 */
	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		getSession().delete(entity);
	}

	/**
	 * 通过id删除实体
	 */
	@Override
	public void delete(Class<T> entityClazz, Serializable id) {
		// TODO Auto-generated method stub
		getSession().createQuery("delete "+entityClazz.getSimpleName() +" en where en.id = :id").setParameter("id",id).executeUpdate();
	}

	/**
	 * 找出所有的实体类
	 */
	@Override
	public List<T> findAll(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		return find("from "+ entityClazz.getSimpleName(),null);
	}
	
	/**
	 * 获取全部对象,支持排序.
	 */
	public List<T> findAll(Class<T> entityClass, String orderBy,
			boolean isAsc) {
		Criteria c = getSession().createCriteria(entityClass);
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return c.list();
	}
	
	
	
	/**
	 * 根据指定属性的值获取对象.
	 */
	public T getBy(Class<T> entityClass,String propertyName,
			Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T)getSession().createCriteria(entityClass).add(criterion).uniqueResult();
		
	}		
	
	/**
	 * 统计数量
	 */
	@Override
	public long findCount(Class<T> entityClazz) {
		// TODO Auto-generated method stub	
		List<?> l = find("select count(*) from "+entityClazz.getSimpleName() + " en",null);						
		if(l!=null&&l.size()==1){
			return (long)l.get(0);
		}
		return 0L;
	}		
	
	/**
	 * 根据筛选条件统计数量			
	 * @param clazz
	 * @param enhanceRule
	 * @return
	 */
	public long count(final Class<T> clazz, final EnhancedRule enhanceRule) {

		//logger.debug("Get the number of object whose class name is:"
				//+ clazz.getName() + " by enhanced rule");
		if (enhanceRule == null) {
			return getCount(find(" select count(*) from " + clazz.getName(),null)					
					.get(0));
		}
		return getCount(enhanceRule.getCountCriteria(clazz, getSession()).uniqueResult());
	}
	
		
	/**
	 * 根据占位符参数的HQL语句查询实体
	 * @param hql
	 * @param params 没有参数就传null
	 * @return
	 */
	protected List<T> find(String hql,Map<String,Object> params){
		Query query = getSession().createQuery(hql);	
		if(params!=null){
			for(Entry<String,Object> entry :params.entrySet()){
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}		
		return (List<T>)query.list();
	}
	
	
	/**
	 * 使用HQL语句进行分页查询操作，带占位符，带参数
	 * @param hql
	 * @param offset 从第几条开始   
	 * @param pageSize 获取几条结果 （每页数量）
	 * @param params 没有参数就传null
	 * @return
	 */
	protected List<T> findByPage(String hql,int offset,int pageSize,Map<String,Object> params){
		//创建查询
		Query query = getSession().createQuery(hql);
		if(params!=null){
			for(Entry<String,Object> entry :params.entrySet()){
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}	
		//执行分页，并返回查询结果
		return query.setFirstResult(offset)
				.setMaxResults(pageSize)
				.list();
	}
	
	/**
	 * 根据EnhancedRule返回分页Page对象，EnhancedRule应该要设置firstResult和MaxResult,如果没有参数就params传null
	 * @param hql
	 * @param offset 
	 * @param pageSize
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("rawtypes")
	public Page<T> queryForPage(String hql,int offset, int pageSize,Class<T> entityClazz,String countHQL,Map<String,Object> params) {		
		Query query = getSession().createQuery(hql);
		
		long total = 0;
		if(params!=null){
			for(Entry<String,Object> entry :params.entrySet()){
				query.setParameter(entry.getKey(), entry.getValue());
			}		
			List<?> l = find(countHQL,params);
			if(l!=null&&l.size()==1){
				total = (long)l.get(0);
			}			
		}
		else{
			total = findCount(entityClazz);
		}
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List list = query.list();
		Page page = new Page(offset, pageSize);
		page.setTotal(Integer.parseInt(""+total));
		page.setRows(list);
		return page;
	}
	
	/**
	 * 根据扩展的查询对象，从数据库搜索相应的数据。
	 * 
	 * @param enhanceRule
	 * @return E
	 * 
	 */
	public List<T> query(Class<T> entityClass, EnhancedRule enhanceRule) {
		Criteria criteria = enhanceRule.getCriteria(entityClass, getSession());
		return criteria.list();
	}		
	
	/**
	 * 根据EnhancedRule返回分页Page对象，EnhancedRule应该要设置firstResult和MaxResult
	 * @param entityClass
	 * @param enhanceRule
	 * @return
	 * @throws DAOException
	 */
	public  Page<T> queryForPage(Class<T> entityClass,
			EnhancedRule enhanceRule) {
		Page<T> page = new Page<T>(enhanceRule.getOffset(),
				enhanceRule.getPageSize());
		List<T> list = query(entityClass, enhanceRule);
		int total = (int) count(entityClass, enhanceRule);
		page.setTotal(total);
		page.setRows(list);
		return page;
	}
	
	/**
	 * 按HQL查询多个对象列表.返回的Object类型可以为Map,也可以为List
	 * 
	 * @param values
	 * 数量可变的参数,按顺序绑定.
	 */
	public List<Object> queryWithMoreObject(final String hql,Object...params) {
		Query query = getSession().createQuery(hql);
		for(int i=0;i<params.length;i++){
			query.setParameter(i+"", params[i]);
		}
		return (List<Object>)query.list();
	}

	
	
	/**
	 * 多表查询分页
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 * @throws DAOException
	 */
	public Page<Object> queryForPageWithMoreObject(final String hql,final int offset,final int pageSize) { 
		Page<Object> page = new Page<Object>(offset, pageSize);
		Query query = getSession().createQuery(hql);
		int total = query.list().size();  //这个地方也略有问题				
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List<Object> list = query.list();
		page.setTotal(total);
		page.setRows(list);
		return page;
	}
		
	
	/**
	 * 创建筛选条件
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private Criteria createCriteria(Class<T> entityClass,
			Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		} 
		return criteria;
	}
	
	/**
	 * 通过sql语句进行查询 返回的数据格式为List<Map<String,Object>>
	 * 如果要使用其他类型的sql原生查询，请自行定义
	 * @param sqlQuery
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryListForSql(String sqlQuery, List<Object> params) {
		Query query = getSession().createSQLQuery(sqlQuery);
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query.setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	/**
	 * 通过sql语句分页查询 
	 * @param sqlQuery
	 * @param offset
	 * @param pageSize
	 * @param countHQL
	 * @param entityClazz
	 * @param params
	 * @return
	 */
	public Page<Map<String,Object>> queryPageForSql(String sql,int offset,int pageSize,String countHQL,Map<String,Object> params){
		
		Query query = getSession().createSQLQuery(sql);
		Query countQuery = getSession().createSQLQuery(countHQL);
				
		if(params!=null){
			for(Entry<String,Object> entry :params.entrySet()){
				query.setParameter(entry.getKey(), entry.getValue());
				countQuery.setParameter(entry.getKey(), entry.getValue());
			}				
		}
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String,Object>> dataList = query.list();
		
		Object object  = countQuery.uniqueResult();				
		
		Page<Map<String,Object>> page = new Page<>(offset, pageSize);
		page.setTotal(Integer.parseInt(object.toString()));
		page.setRows(dataList);
		return page;
		
	}
	
	/**
	 * 批量更新实体
	 * @param entitys
	 */
	public void batchSaveOrUpdate(List<T> entitys){
		for (int i = 0; i < entitys.size(); i++) {
			saveOrUpdate(entitys.get(i));
			if (((i + 1) % BATCH_SIZE) == 0) {
				flush();
				clear();
			}
		}
	}
	
	public void flush(){
		getSession().flush();
	}
	
	public void clear(){
		getSession().flush();
	}
	
	/**
	 * @param obj
	 * @return int
	 */
	private int getCount(Object obj) {
		if (obj instanceof Long) {

			Long value = (Long) obj;
			return value.intValue();
		} else if (obj instanceof Integer) {

			Integer value = (Integer) obj;
			return value.intValue();
		}
		return 0;
	}
	
	/**
	 * 执行SQL进行批量修改/删除操作.
	 * 
	 * @return 更新记录数.
	 */
	public int batchSQLExecute(final String sql) {
		return getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 根据指定属性的值获取列表
	 */
	@Override
	public List<T> getListBy(Class<T> entityClass, String propertyName, Object value) {
		// TODO Auto-generated method stub
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (List<T>)getSession().createCriteria(entityClass).add(criterion).list();
	}
	
	@Override
	public List<T> getListBy(Class<T> entityClass, String propertyName, Object value, String orderBy, boolean isAsc) {
		// TODO Auto-generated method stub
		Criterion criterion = Restrictions.eq(propertyName, value);
		Criteria c = getSession().createCriteria(entityClass);
		c.add(criterion);
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return (List<T>)c.list();
	}

}
