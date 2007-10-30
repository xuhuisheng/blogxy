package maria.action;

import maria.model.User;
import maria.service.UserManager;

import org.core.web.AbstractAction;

/**
 * 负责管理单个Entity CRUD操作的Struts Action基类.
 * 
 * @author duyu
 */
@SuppressWarnings("serial")
public class UserAction extends AbstractAction<User,UserManager> {
	// 依赖注入
	@SuppressWarnings("unused")
	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	//name
	protected String name;
	
	//password
	protected String password;
	
	/**
	 * 列出所有对象的Action函数.
	 */
	public String list() throws Exception {
		//name
		name = "duyu";
		
		//无条件得到Collection
		collection = doListEntity();
		
		return SUCCESS;
	}		

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
