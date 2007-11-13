package com.google.code.blogxy.demo.struts2.helloworld;

import com.opensymphony.xwork2.ActionSupport;


public class HelloworldAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        System.out.println("helloworld");

        return SUCCESS;
    }
}
