package com.google.code.blogxy.demo.struts2.crud;

import java.io.Serializable;

import java.util.*;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class TestManager extends HibernateDaoSupport {
    public TestManager() {
    }

    public Test get(Long id) {
        return (Test) getHibernateTemplate().get(Test.class, id);
    }

    public List<Test> getAll() {
        return getHibernateTemplate().loadAll(Test.class);
    }

    public void save(Test entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    public void remove(Test entity) {
        getHibernateTemplate().delete(entity);
    }
}
