package com.google.code.blogxy.demo.struts2.crud;

import java.util.*;


public class TestManager {
    private static TestManager instance = new TestManager();
    Map<Long, Test> data = new LinkedHashMap<Long, Test>();

    public TestManager() {
        Test test;

        test = new Test();
        test.setId(1L);
        test.setName("name1");
        test.setDescn("descn1");
        data.put(1L, test);

        test = new Test();
        test.setId(2L);
        test.setName("name2");
        test.setDescn("descn2");
        data.put(2L, test);
    }

    public Test get(Long id) {
        return data.get(id);
    }

    public List<Test> getAll() {
        return new ArrayList<Test>(data.values());
    }

    public void save(Test entity) {
        if (entity.getId() == null) {
            entity.setId(System.currentTimeMillis());
        }

        data.put(entity.getId(), entity);
    }

    public void remove(Test entity) {
        data.remove(entity.getId());
    }

    public static TestManager getInstance() {
        return instance;
    }
}
