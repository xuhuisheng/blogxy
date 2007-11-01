package org.core.web;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.core.dao.AbstractDao;
import org.core.utils.BeanUtils;
import org.core.utils.GenericsUtils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 简单封装Struts2基类.
 * 
 * @author duyu
 */
@SuppressWarnings({ "unchecked", "serial" })
public class AbstractAction<E,M extends AbstractDao<E>> extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	/**
	 * 利用ServletRequestAware接口,dependency injection.
	 */
	@SuppressWarnings("unused")
	protected HttpServletRequest request;
	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	
	/**
	 * 利用ServletResponseAware接口,dependency injection.
	 */
	@SuppressWarnings("unused")
	protected HttpServletResponse response;
	
	public void setServletResponse(HttpServletResponse response){
		this.response = response;
	}
	
	//object
	private E object;
	
	//id
	protected Integer id;
	
	//Collection
	protected Collection collection;
	
	/**
	 * 通过泛型类型暴力取得entityClass的函数.
	 */
	protected Class<E> getEntityClass() {
		// 根据T,反射获得entityClass
		return GenericsUtils.getSuperClassGenricType(getClass());
	}
	
	/**
	 * 通过泛型类型暴力获得EntityManager类进行CRUD操作.
	 */
	protected M getEntityManager() throws NoSuchFieldException {
		// 根据M,反射获得符合M类型的manager
		List<Field> fields = BeanUtils.getFieldsByType(this, GenericsUtils.getSuperClassGenricType(getClass(), 1));
		return (M) BeanUtils.forceGetProperty(this, fields.get(0).getName());
	}
	
	/**
	 * 列出所有对象的Action函数.
	 */
	public String list() throws Exception {
		//无条件得到Collection
		collection = doListEntity();
		
		return SUCCESS;
	}
	
	/**
	 * 显示新建对象Form的Action函数.
	 */
	public String create() throws Exception {
		return edit();
	}
	
	/**
	 * 显示修改对象Form的Action函数.
	 */
	public String edit() throws Exception {
		object = null;

		// 如果是修改操作，id!=null
		if (id != null) {
			object = doGetEntity();
			if (object == null) {
				return SUCCESS;
			}
		} else {
			object = doNewEntity();
		}
		
		return "edit";
	}
	
	/**
	 * 保存对象的Action函数.
	 */
	public String save() throws Exception {
		// 如果是修改操作，id!=null
		if (id != null) {
			object = doGetEntity();
			if (object == null) {
				return SUCCESS;
			}
		} else { // 否则为新增操作
			object = doNewEntity();
		}
		
		doSaveEntity(object);
		
		return SUCCESS;
	}

	/**
	 * 删除单个对象的Action函数.
	 */
	public String delete() throws Exception {
		doDeleteEntity();

		return SUCCESS;
	}	
	
	/**
	 * 获取业务对象列表的函数.
	 */
	protected List doListEntity() throws NoSuchFieldException {
		return getEntityManager().getAll();
	}
	
	/**
	 * 新建业务对象的函数.
	 */
	protected E doNewEntity() throws InstantiationException, IllegalAccessException {
		return (E) getEntityClass().newInstance();
	}
	
	/**
	 * 从数据库获取业务对象的函数.
	 */
	protected E doGetEntity() throws NoSuchFieldException {
		return (E) getEntityManager().findById(id);
	}
	
	/**
	 * 保存业务对象的函数.
	 */
	protected void doSaveEntity(E object) throws NoSuchFieldException {
		getEntityManager().save(object);
	}

	/**
	 * 删除业务对象的函数.
	 */
	protected void doDeleteEntity() throws NoSuchFieldException {
		getEntityManager().removeById(id);
	}	

	public E getObject() {
		return object;
	}

	public void setObject(E object) {
		this.object = object;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}
}
