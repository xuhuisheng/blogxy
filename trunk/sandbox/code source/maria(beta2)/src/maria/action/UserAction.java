package maria.action;

import java.io.PrintWriter;
import java.util.List;

import maria.model.User;
import maria.service.UserManager;

import org.core.web.AbstractAction;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 负责管理单个Entity CRUD操作的Struts Action基类.
 * 
 * @author duyu
 */
@SuppressWarnings({ "unchecked", "serial" })
public class UserAction extends AbstractAction<User,UserManager> {	
	/**
	 * dependency injection.
	 */
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
	
	/**
	 * main page and show user table's data.
	 */
	public String grid() throws Exception {
		JSONArray resultArray = new JSONArray();    
        JSONObject jsonObject = new JSONObject();
        
		// 开始数
		String start = request.getParameter("start");
		// 每次读取数
		String size = request.getParameter("limit");
		
		String query = "from User";
		
		// total
		int total = doListEntity().size();
		
		List list = userManager.extPage(query, new Integer(start), new Integer(size));
		
		for (int i = 0; i < list.size(); i++) { 
			 User o = (User) list.get(i);
			 
			 jsonObject = new JSONObject();
			 
			 jsonObject.put("id", o.getId());
			 jsonObject.put("name", o.getName());
			 jsonObject.put("password", o.getPassword());
			 
			 resultArray.put(jsonObject);
		}
		
		String results = "{\"totalCount\":\"" + total + "\",\"rows\":" + resultArray.toString() + "}";
		
		resultToClient(results);
		
		return null;
	}
	
	/**
	 * main page and show user tree's data.
	 */
	public String tree() throws Exception {
		JSONArray resultArray = new JSONArray();    
        JSONObject jsonObject = new JSONObject();
		
		List list = doListEntity();
		
		for (int i = 0; i < list.size(); i++) { 
			 User o = (User) list.get(i);
			 
			 jsonObject = new JSONObject();
			 
             jsonObject.put("text", o.getName()); 
             jsonObject.put("id", o.getId());
             jsonObject.put("allowDrag", true);// 设置拖拽
             jsonObject.put("allowDrop", false);// 设置插入
             jsonObject.put("leaf", true);
             jsonObject.put("cls", "file");
			 
			 resultArray.put(jsonObject);
		}
		
		resultToClient(resultArray.toString());
		
		return null;
	}	
	
    /**   
     * result to response. 
     */   
    public void resultToClient(String result) throws Exception {    
    	response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {    
            out.print(result);    
        } finally {    
            out.close();    
        }
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
