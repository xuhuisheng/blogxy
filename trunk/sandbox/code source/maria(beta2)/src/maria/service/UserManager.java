package maria.service;

import java.util.List;

import maria.model.User;

import org.core.dao.AbstractDao;

/**
 * 用户管理业务类.
 * <p/>
 * 继承于HibernateEntityDao,不需任何代码即拥有默认的对User对象的CRUD函数. 如果想了解不继承于EntityDao,自行编写CRUD的写法, 参考{@link UserManagerNativeVersion}.
 *
 * @author calvin,duyu
 * @see HibernateEntityDao
 * @see org.springside.core.dao.HibernateGenericDao
 * @see UserManagerNativeVersion
 */
@SuppressWarnings("unchecked")
public class UserManager extends AbstractDao<User> {
	// ....CRUD以外的其它商业方法
	
	/**
	 * ext2.0 data paging.
	 */
	public List extPage(String query, int pagestart, int pagesize) {
		return findAll(query, pagestart, pagesize);
	}	
}
