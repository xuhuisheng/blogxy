package maria.action;

import java.io.PrintWriter;
import java.util.List;

import maria.model.Role;
import maria.model.User;
import maria.service.RoleManager;

import org.core.web.AbstractAction;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 负责管理单个Entity CRUD操作的Struts Action基类.
 * 
 * @author duyu
 */
@SuppressWarnings({ "unchecked", "serial" })
public class RoleAction extends AbstractAction<Role,RoleManager> {	
	/**
	 * dependency injection.
	 */
	@SuppressWarnings("unused")
	private RoleManager roleManager;

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	
	// name
	protected String name;
	
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
		
		String query = "from Role";
		
		// total
		int total = doListEntity().size();
		
		List list = roleManager.extPage(query, new Integer(start), new Integer(size));
		
		for (int i = 0; i < list.size(); i++) { 
			 Role o = (Role) list.get(i);
			 
			 jsonObject = new JSONObject();
			 
			 jsonObject.put("id", o.getId());
			 jsonObject.put("name", o.getName());
			 
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
        
        // get id
        String id = request.getParameter("node");
        
        if(id.equals("yui")){
    		List list = doListEntity();
    		
    		for (int i = 0; i < list.size(); i++) { 
    			 Role o = (Role) list.get(i);
    			 
    			 jsonObject = new JSONObject();
    			 
                 jsonObject.put("text", o.getName()); 
                 jsonObject.put("id", o.getId());
                 jsonObject.put("allowDrag", false);// 设置拖拽
                 jsonObject.put("cls", "folder");
    			 
    			 resultArray.put(jsonObject);
    		}
    		
    		resultToClient(resultArray.toString());
        }else{
        	String hql = "from Role where id=?";
        	List list = roleManager.find(hql, Integer.parseInt(id));
        	
        	Role o = (Role)list.iterator().next();
        	
        	List user = o.getUsers();
        	
        	for(int i=0; i< user.size(); i++){
        		User o1 = (User)user.get(i);
        		
        		jsonObject = new JSONObject();
        		
                jsonObject.put("text", o1.getName()); 
                jsonObject.put("id", o1.getId());
                jsonObject.put("leaf", true);
                jsonObject.put("cls", "file");
                
                resultArray.put(jsonObject); 
        	}
        	
        	resultToClient(resultArray.toString());
        }
		
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
}
