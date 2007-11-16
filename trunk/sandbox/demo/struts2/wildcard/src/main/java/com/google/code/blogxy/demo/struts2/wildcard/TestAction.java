package com.google.code.blogxy.demo.struts2.wildcard;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


public class TestAction extends ActionSupport
    implements ServletRequestAware, ServletResponseAware {
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

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("execute");

        return SUCCESS;
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

        return "insert";
    }

    public String save() throws Exception {
        System.out.println("save");

        return "save";
    }

    public String update() throws Exception {
        System.out.println("update");

        return "update";
    }

    public String remove() throws Exception {
        System.out.println("remove");

        return "remove";
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
