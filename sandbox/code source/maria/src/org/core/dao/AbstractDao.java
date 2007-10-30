package org.core.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.extremecomponents.table.limit.Filter;
import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.core.utils.GenericsUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Hibernate Dao的泛型基类.
 * @author duyu
 *
 * 防止被sql注入
 * 1个参数的时候使用 ? 占位符 查询用find()
 * find("from User where username=?",用户名)
 * 多个参数的时候用 :name 查询用findByNamedParam()
 * findByNamedParam("from User where username=:name","name",用户名)
 */
@SuppressWarnings("unchecked")
public class AbstractDao<E> extends HibernateDaoSupport{
	protected Class<E> entityClass;// DAO所管理的Entity类型.
	
	/**
	 * 在构造函数中将泛型T.class赋给entityClass,jdk5.0以上版本的特性.
	 */
	public AbstractDao() {
		//根据T,反射获得entityClass
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}
	
	/**
	 * 保存对象.
	 */
	public void save(Object o) {
		getHibernateTemplate().saveOrUpdate(o);
	}

	/**
	 * 删除对象.
	 */
	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}
	
	/**
	 * 根据ID得到对象.
	 * Serializable(String或Integer).
	 */
	public E findById(Serializable id) {
		return (E) getHibernateTemplate().load(entityClass, id);
	}
	
	/**
	 * 根据ID删除对象.
	 * Serializable(String或Integer).
	 */
	public void removeById(Serializable id) {
		remove(findById(id));
	}
	
	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}
	
	/**
	 * 获取全部对象.
	 */
	public List getAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	/**
	 * 获取全部对象,带排序字段与升降序参数.
	 */
	public List getAll(String orderBy, boolean isAsc) {
		if (isAsc) return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
		else return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
	}
	
	/**
	 * 根据hql查询.
	 */
	public List find(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数.
	 */
	public List find(String hql, Object o) {
		return getHibernateTemplate().find(hql, o);
	}
	
	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数.
	 *
	 * @param values 可变参数,见{@link #createQuery(String,Object...)}
	 */
	public List find(String hql, Object... values) {
		return getHibernateTemplate().find(hql, values);
	}	
	
	/**
	 * 创建Criteria对象.
	 *
	 * @param criterions 可变的Restrictions条件列表,见{@link #createQuery(String,Object...)}
	 */
	public Criteria createCriteria(Class<E> entityClass, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	/**
	 * 创建Criteria对象，带排序字段与升降序字段.
	 *
	 * @see #createCriteria(Class,Criterion[])
	 */
	public Criteria createCriteria(Class<E> entityClass, String orderBy, boolean isAsc, Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);

		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));

		return criteria;
	}
	
	/**
	 * 根据属性名和属性值查询对象.
	 *
	 * @return 符合条件的对象列表
	 */
	public List<E> findBy(Class<E> entityClass, String propertyName, Object value) {
		return createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据属性名和属性值查询对象,带排序参数.
	 */
	public List<E> findBy(Class<E> entityClass, String propertyName, Object value, String orderBy, boolean isAsc) {
		return createCriteria(entityClass, orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据属性名和属性值查询唯一对象.
	 *
	 * @return 符合条件的唯一对象 or null if not found.
	 */
	public E findUniqueBy(Class<E> entityClass, String propertyName, Object value) {
		return (E) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
	}
	
	/**
	 * 分页查询函数,使用hql.
	 */
	public List pagedQuery(FilterSet filterset,Sort sort,int pagestart,int pagesize,String sourceQuery,String sortProperty) {
		String modQuery = filterQuery(filterset,sourceQuery);
		String SortQuery = sortQuery(sort,modQuery,sortProperty);
		return findAll(SortQuery,pagestart,pagesize);
	}
	
	/**
	 * 页面过滤.
	 */
	public String filterQuery(FilterSet filterset,String sourceQuery){
		if(!filterset.isFiltered()||filterset.isCleared()){
			return sourceQuery;
		}
		
		Filter filters[]=filterset.getFilters();
		for(int i=0;i<filters.length;i++){
			Filter filter = filters[i];
			String property = filter.getProperty();
			String value = filter.getValue();
			
			sourceQuery = filterQuery(sourceQuery,property,value);
		}
		return sourceQuery;
	}
	
	/**
	 * 页面排序.
	 */
	public String sortQuery(Sort sort,String query,String sortProperty){
		if(!sort.isSorted()){
			String sortQuery = getListSortDefaultQuery(sortProperty);
			
			return query + sortQuery;
		}
		
		//进行排序
		String property = sort.getProperty();
		String sortOder = sort.getSortOrder();
		return sortQuery(query,property,sortOder);
	}
	
	/**
	 * 根据filter属性统计当前总记录数.
	 */
	public int pagedNum(FilterSet filterset,String numQuery){
		String modQuery = filterQuery(filterset,numQuery);
		return getPagedNum(modQuery);
	}
	
	/**
	 * 页面排序.
	 */
	public String getListSortDefaultQuery(String sortProperty){
		return " order by " + sortProperty;
	}
	
	/**
	 * 分页显示.
	 */
	public List findAll(final String sqlQuery,final int pagestart,final int pagesize) {
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session s)throws HibernateException,SQLException{
				Query query = s.createQuery(sqlQuery);
				query.setFirstResult(pagestart);
				query.setMaxResults(pagesize);
				List list = query.list();
				return list;
			}});
	}
	
	/**
	 * 页面排序.
	 */
	public String sortQuery(String query,String property,String sortOrder){
		StringBuffer result = new StringBuffer(query + " order by ");
		result.append(property + " " + sortOrder);
		return result.toString();
	}
	
	/**
	 * 页面过滤append所有的查询串.
	 */
	public String filterQuery(String sourceQuery,String property,String value){
		StringBuffer result = new StringBuffer(sourceQuery);
		
		if (sourceQuery.indexOf("where") == -1){
			result.append(" where 1=1");
		}
			//result.append(" and "+property+" like ? ");
		result.append(" and " + property + " like '%" + value + "%'" );
		
		return result.toString();
	}
	
	/**
	 * 得到总数.
	 */
	public int getPagedNum(String query){
		try{
			List l = getHibernateTemplate().find(query);
			if (!l.isEmpty()){
				return new Integer(l.get(0).toString()).intValue();	
			}else{
				return 0;
			}
		}catch(RuntimeException re){
			return 0;
		}
	}	
}
