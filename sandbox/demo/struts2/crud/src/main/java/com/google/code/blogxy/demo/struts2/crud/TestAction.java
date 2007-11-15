package com.google.code.blogxy.demo.struts2.crud;

import java.io.PrintWriter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


public class TestAction extends ActionSupport
    implements ServletRequestAware, ServletResponseAware, Preparable,
        ModelDriven<Test> {
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
    private TestManager entityManager = TestManager.getInstance();
    private long entityId;
    private Test entity = new Test();

    public void prepare() throws Exception {
        logger.info("do prepare, and the id is : " + entityId);
        logger.info(entity);
        logger.info(request.getParameter("entityId"));

        try {
            entityId = Long.parseLong(request.getParameter("entityId"));
        } catch (Exception ex) {
            entityId = 0L;
        }

        if (entityId == 0L) {
            entity = new Test();
        } else {
            entity = entityManager.get(entityId);
        }
    }

    public Test getModel() {
        logger.info("getModel()");

        return entity;
    }

    public void setEntityId(long entityId) {
        logger.info(entityId);
        this.entityId = entityId;
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
        entity = entityManager.get(entityId);

        return "edit";
    }

    public String insert() throws Exception {
        System.out.println("insert");
        entity = new Test();

        return "edit";
    }

    public String save() throws Exception {
        System.out.println("save");
        logger.info(entityId);
        entityManager.save(entity);

        return "list";
    }

    public String update() throws Exception {
        System.out.println("update");
        logger.info(entityId);

        entityManager.save(entityManager.get(entityId));

        return "list";
    }

    public String remove() throws Exception {
        System.out.println("remove");
        entityManager.remove(entityManager.get(entityId));

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
}
