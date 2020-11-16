package spring.service;

import spring.util.ReType;

import java.util.List;

public interface BaseService<T> {
    //增
    public int insert(T record) throws Exception;

    int inserts(List<T> records) throws Exception;

    public int insertBean(Object record) throws Exception;

    //删
    public int delete(T t);

    public int deleteByPrimaryKey(Object id);

    public int deleteByIds(String ids);

    //改
    public int updateByPrimaryKeySelective(T record) throws Exception;

    public int updateByPrimaryKeySelectives(List<T> items);

    public int updateBeanByPrimaryKeySelective(Object record);

    //查
    public T selectByPrimaryKey(Object id);

    public T selectOne(T t);

    public List<T> selectAll();

    public List<T> selectListByPageBean(Object t);

    public List<T> selectListByPage(T t);

    public List<T> selectListByPage2(T t);

    public ReType show(T t, int page, int limit);

    public ReType showBean(Object t, int page, int limit);

    public ReType show2(T t, int page, int limit);

    public ReType showForm(T t, int page, int limit);

    public ReType showFormBean(Object t, int page, int limit);



}
