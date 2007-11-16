package com.google.code.blogxy.demo.struts2.crud;

import java.io.PrintWriter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


public class TestAction extends ActionSupport
    implements ServletRequestAware, ServletResponseAware, Preparable,
        ModelDriven<Test>, ParameterNameAware {
    private static Log logger = LogFactory.getLog(TestAction.class);

    /**
    * 利用ServletRequestAware接口,dependency injection.
    */
    @SuppressWarnings("unused")
    private HttpServletRequest request;

    /**
     * 利用ServletResponseAware接口,dependency injection.
     */
    @SuppressWarnings("unused")
    private HttpServletResponse response;
    private TestManager entityManager;
    private Test entity = new Test();
    private Long id;

    public void prepare() throws Exception {
        logger.info("do prepare, and the id is : " + id);

        if (id != null) {
            entity = entityManager.get(id);
        }
    }

    public void setTestManager(TestManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setId(Long id) {
        logger.info("------------------------------------------------");
        logger.info(id);
        this.id = id;
    }

    public void setId2(Long id) {
        logger.info("=======================================");
        logger.info(id);
    }

    public Test getModel() {
        return entity;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public List<Test> getResults() {
        return entityManager.getAll();
    }

    @Override
    public String execute() throws Exception {
        System.out.println("execute");

        return "list";
    }

    public String list() throws Exception {
        System.out.println("list");

        return "list";
    }

    public String edit() throws Exception {
        System.out.println("edit");

        return "edit";
    }

    public String insert() throws Exception {
        System.out.println("insert");

        return "edit";
    }

    public String save() throws Exception {
        System.out.println("save");
        entityManager.save(entity);

        return "list";
    }

    public String update() throws Exception {
        System.out.println("update");

        entityManager.save(entity);

        return "list";
    }

    public String remove() throws Exception {
        System.out.println("remove");
        entityManager.remove(entity);

        return "list";
    }

    public String json() throws Exception {
        System.out.println("json");

        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            out.print("{json:'json'}");
        } finally {
            out.close();
        }

        return null;
    }

    public boolean acceptableParameterName(String methodName) {
        logger.info(methodName);

        return true;
    }
}
