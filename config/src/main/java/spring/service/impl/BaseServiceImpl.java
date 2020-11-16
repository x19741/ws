package spring.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import spring.entity.SysUser;
import spring.mappers.Mapper;
import spring.service.BaseService;
import spring.util.BeanRefUtil;
import spring.util.ReType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    public abstract Mapper<T> getMapper();

    //增
    public int insert(T record) throws Exception {
        Map map = new HashMap<String, String>();
        SysUser user =getUser();
        map.put("createBy", user == null ? "" : user.getId());
        map.put("createDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        BeanRefUtil.setFieldValue(record, map);
        return getMapper().insertSelective(record);
    }

    @Override
    public int inserts(List<T> records) throws Exception {
        int i = 0;
        for (T item:records) {
            i+=insert(item);
        }
        return i;
    }

    public int insertBean(Object record) throws Exception {
        return getMapper().insertSelective((T) record);
    }

    //删
    public int delete(T t) {
        return getMapper().delete(t);
    }

    public int deleteByPrimaryKey(Object id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    public int deleteByIds(String ids) {
        int i = 0;
        String[] idlist = ids.split(",");
        for (String id : idlist) {
            i += getMapper().deleteByPrimaryKey(id);
        }
        return i;
    }

    //改
    public int updateByPrimaryKeySelective(T record) throws Exception {
        Map map = new HashMap<String, String>();
        SysUser user =getUser();
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        BeanRefUtil.setFieldValue(record, map);
        return getMapper().updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeySelectives(List<T> records) {
        Map map = new HashMap<String, String>();
        SysUser user =getUser();
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        int count=0;
        for (T item :records) {
            BeanRefUtil.setFieldValue(item, map);
            count=count+getMapper().updateByPrimaryKeySelective(item);
        }
        return count;
    }

    public int updateBeanByPrimaryKeySelective(Object record) {
        return getMapper().updateByPrimaryKeySelective((T) record);
    }

    //查
    public T selectByPrimaryKey(Object id) {
        return getMapper().selectByPrimaryKey(id);
    }

    public T selectOne(T t) {
        return getMapper().selectOne(t);
    }

    public List<T> selectAll() {
        return getMapper().selectAll();
    }

    public List<T> selectListByPageBean(Object t) {
        return getMapper().selectListByPage((T)t);
    }
    public List<T> selectListByPage(T t) {
        return getMapper().selectListByPage(t);
    }

    public List<T> selectListByPage2(T t) {
        return getMapper().selectListByPage2(t);
    }

    /**
     * 公共展示类
     *
     * @param t     实体
     * @param page  页
     * @param limit 行
     * @return
     */
    @Override
    public ReType show(T t, int page, int limit) {
        List<T> tList = null;
        Page<T> tPage = PageHelper.startPage(page, limit);
        try {
            tList = getMapper().selectListByPage(t);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public ReType showBean(Object t, int page, int limit) {
        List<T> tList = null;
        Page<T> tPage = PageHelper.startPage(page, limit);
        try {
            tList = getMapper().selectListByPage((T) t);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public ReType show2(T t, int page, int limit) {
        List<T> tList = null;
        Page<T> tPage = PageHelper.startPage(page, limit);
        try {
            tList = getMapper().selectListByPage2(t);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public ReType showForm(T t, int page, int limit) {
        List<T> tList = null;
        Page<T> tPage = PageHelper.startPage(page, limit);
        try {
            tList = getMapper().selectListByPageForm((T) t);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public ReType showFormBean(Object t, int page, int limit) {
        List<T> tList = null;
        Page<T> tPage = PageHelper.startPage(page, limit);
        try {
            tList = getMapper().selectListByPage((T) t);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    //获取User
    public SysUser getUser(){
        SysUser sysUser=null;
        try {
            sysUser=(SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        }catch (Exception e){
            e.printStackTrace();
        }
        return sysUser;
    }

}
