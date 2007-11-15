package com.google.code.blogxy.demo.struts2.crud;

public class Test {
    private Long id;
    private String name;
    private String descn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        System.out.println(this);
        System.out.println(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }
}
