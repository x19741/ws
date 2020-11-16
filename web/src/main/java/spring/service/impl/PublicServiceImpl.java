package spring.service.impl;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Service;
import spring.entity.SysComForm;
import spring.entity.SysUser;
import spring.exception.CustomException;
import spring.service.PublicService;
import spring.service.SysComFormService;
import spring.util.*;

import java.lang.reflect.Field;
import java.util.*;

@Service
@TestComponent
public class PublicServiceImpl implements PublicService {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SysComFormService sysComFormServiceImpl;

    @Override
    public ReType selectComfromTable(String comformInfo, Integer pageNumber, Integer pageSize) throws ClassNotFoundException {
        JSONObject jsonObject = JSONObject.parseObject(comformInfo);
        SysComForm conForm = sysComFormServiceImpl.selectByPrimaryKey((String) jsonObject.get("formId"));
        String suffixIndexOfString = StringUtils.suffixIndexOfString(conForm.getClassname(), ".");
        BaseServiceImpl<?> baseServiceImpl = (BaseServiceImpl<?>) ApplicationContextUtil.getInstance().getApplicationContext().getBean(StringUtils.first2Lower(suffixIndexOfString) + "ServiceImpl");
        Object object = JSON.toJavaObject((JSON) jsonObject.get("date"), Class.forName(conForm.getClassname()));
        return baseServiceImpl.showBean(object, pageNumber, pageSize);
    }

    @Override
    public ReType selectComfromTableForm(String comformInfo, Integer pageNumber, Integer pageSize) throws ClassNotFoundException {
        JSONObject jsonObject = JSONObject.parseObject(comformInfo);
        SysComForm conForm = sysComFormServiceImpl.selectByPrimaryKey((String) jsonObject.get("formId"));
        String suffixIndexOfString = StringUtils.suffixIndexOfString(conForm.getClassname(), ".");
        BaseServiceImpl<?> baseServiceImpl = (BaseServiceImpl<?>) ApplicationContextUtil.getInstance().getApplicationContext().getBean(StringUtils.first2Lower(suffixIndexOfString) + "ServiceImpl");
        Object object = JSON.toJavaObject((JSON) jsonObject.get("date"), Class.forName(conForm.getClassname()));
        return baseServiceImpl.showFormBean(object, pageNumber, pageSize);
    }

    @Override
    public Object selectByPrimaryKey(String comformInfo) throws ClassNotFoundException {
        JSONObject jsonObject = JSONObject.parseObject(comformInfo);
        SysComForm conForm = sysComFormServiceImpl.selectByPrimaryKey((String) jsonObject.get("formId"));
        String suffixIndexOfString = StringUtils.suffixIndexOfString(conForm.getClassname(), ".");
        BaseServiceImpl<?> baseServiceImpl = (BaseServiceImpl<?>) ApplicationContextUtil.getInstance().getApplicationContext().getBean(StringUtils.first2Lower(suffixIndexOfString) + "ServiceImpl");
        Map<String, Object> map = new HashMap<>();
        map.put("formId", jsonObject.get("formId"));
        Object object = baseServiceImpl.selectByPrimaryKey(jsonObject.get("id"));
        map.put("date", object);
        List<Object> list = selectMainformByPrimaryKey((String) jsonObject.get("formId"), (String) jsonObject.get("id"));
        map.put("mainformdates", list);
        return map;
    }

    private List<Object> selectMainformByPrimaryKey(String formId, String mainformid) throws ClassNotFoundException {
        SysComForm comfrom = new SysComForm();
        comfrom.setParentformid(formId);
        List<SysComForm> conFormlist = sysComFormServiceImpl.selectListByPage2(comfrom);
        List<Object> mainformlist = new ArrayList<>();
        for (SysComForm conForm : conFormlist) {
            String suffixIndexOfString = StringUtils.suffixIndexOfString(conForm.getClassname(), ".");
            BaseServiceImpl<?> baseServiceImpl = (BaseServiceImpl<?>) ApplicationContextUtil.getInstance().getApplicationContext().getBean(StringUtils.first2Lower(suffixIndexOfString) + "ServiceImpl");

            Map<String, Object> map = new HashMap<>();
            map.put("formId", conForm.getId());
            List<Object> objectlit = (List<Object>) baseServiceImpl.selectListByPageBean(JSON.toJavaObject(JSONObject.parseObject("{\"mainformid\":\""+mainformid+"\"}"), Class.forName(conForm.getClassname())));
            List<Map<String, Object>> datesmap = new ArrayList<>();
            for (Object object : objectlit) {
                Map<String, Object> dates = new HashMap<>();
                dates.put("date", object);

                Class class1 = Class.forName(conForm.getClassname());
                Field[] fs = class1.getDeclaredFields();
                Map<String, String> map1 = BeanRefUtil.getFieldValueMap(object);
                List<Object> list = selectMainformByPrimaryKey(conForm.getId(), map1.get("id"));
                dates.put("mainformdates", list);
                datesmap.add(dates);
            }
            map.put("dates", datesmap);
            mainformlist.add(map);
        }

        return mainformlist;
    }

    @Override
    public int insertComfromTable(String comformInfo, String mainformid,String uuid) throws Exception {
        int i = 0;
        JSONObject jsonObject = JSONObject.parseObject(comformInfo);
        if("".equals((String) jsonObject.get("formId"))||(String) jsonObject.get("formId")==null){
            throw new CustomException("出现了意外的错误,请联系管理员。");
        }
        System.out.println((String) jsonObject.get("formId"));
        SysComForm conForm = sysComFormServiceImpl.selectByPrimaryKey((String) jsonObject.get("formId"));
        if(conForm==null){
            throw  new CustomException("错误的表单！");
        }
        String suffixIndexOfString = StringUtils.suffixIndexOfString(conForm.getClassname(), ".");

        BaseServiceImpl<?> baseServiceImpl = (BaseServiceImpl<?>) ApplicationContextUtil.getInstance().getApplicationContext().getBean(StringUtils.first2Lower(suffixIndexOfString) + "ServiceImpl");
        log.info("添加json字符串"+jsonObject.get("date"));
        Object object = JSON.toJavaObject((JSON) jsonObject.get("date"), Class.forName(conForm.getClassname()));

        //添加基本参数
        Map map = new HashMap<String, String>();
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        map.put("createBy", user == null ? "" : user.getId());
        map.put("createDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        map.put("id", uuid);
        map.put("parent", "0");
        BeanRefUtil.setFieldValue(object, map);
        if (jsonObject.get("mainformdates") != null)
            updateMainFormComfromTable(jsonObject.get("mainformdates").toString(), uuid);
        i = i + baseServiceImpl.insertBean(object);
        return i;
    }

    @Override
    public int updateComfromTable(String comformInfo, String mainformid) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(comformInfo);
        if((String) jsonObject.get("formId")==null||"".equals((String) jsonObject.get("formId"))){
            throw  new CustomException("数据格式错误。");
        }
        SysComForm conForm = sysComFormServiceImpl.selectByPrimaryKey((String) jsonObject.get("formId"));
        if(conForm==null){
            throw  new CustomException("formId错误！");
        }
        String suffixIndexOfString = StringUtils.suffixIndexOfString(conForm.getClassname(), ".");
        BaseServiceImpl<?> baseServiceImpl = (BaseServiceImpl<?>) ApplicationContextUtil.getInstance().getApplicationContext().getBean(StringUtils.first2Lower(suffixIndexOfString) + "ServiceImpl");
        Class class1 = Class.forName(conForm.getClassname());
        Object object = JSON.toJavaObject((JSON) jsonObject.get("date"), class1);

        Map map = new HashMap<String, String>();
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        BeanRefUtil.setFieldValue(object, map);
        Field[] fs = class1.getDeclaredFields();
        Map<String, String> map1 = BeanRefUtil.getFieldValueMap(object);
        System.out.println(map1.get("id"));
        System.out.println(jsonObject.get("date.id"));
        //jsonObject.get("mainformdates").toString();
        if (jsonObject.get("mainformdates") != null)
            updateMainFormComfromTable(jsonObject.get("mainformdates").toString(), map1.get("id"));

        return baseServiceImpl.updateBeanByPrimaryKeySelective(object);
    }

    public void updateMainFormComfromTable(String mainformdates, String mainformid) throws Exception {
        //查看原来的数据
        JSONArray mainformJsonObject = JSONArray.parseArray(mainformdates);
        for (int m = 0; m < mainformJsonObject.size(); m++) {
            JSONObject jsonObject = (JSONObject) mainformJsonObject.get(m);
            SysComForm conForm = sysComFormServiceImpl.selectByPrimaryKey((String) jsonObject.get("formId"));
            String suffixIndexOfString = StringUtils.suffixIndexOfString(conForm.getClassname(), ".");
            BaseServiceImpl<?> baseServiceImpl = (BaseServiceImpl<?>) ApplicationContextUtil.getInstance().getApplicationContext().getBean(StringUtils.first2Lower(suffixIndexOfString) + "ServiceImpl");
            Map<String, String> mapobject1 = new HashMap<>();
            mapobject1.put("mainformid", mainformid);
            Class class1 = Class.forName(conForm.getClassname());
            List<Object> beanlist = (List<Object>) baseServiceImpl.selectListByPageBean(JSON.parseObject(JSON.toJSONString(mapobject1), Class.forName(conForm.getClassname())));

            //进行对比
            JSONArray jsonArray = jsonObject.getJSONArray("dates");
            for (int i = 0; i < jsonArray.size(); i++) {
                //json date中的id
                JSONObject objectdate = (JSONObject) jsonArray.get(i);
                System.out.println(objectdate);
                System.out.println(objectdate.get("date"));
                Object object = JSON.toJavaObject((JSON) objectdate.get("date"), Class.forName(conForm.getClassname()));
                String jsonid = (String) ((JSONObject)objectdate.get("date")).get("id");
                //id ==null 添加
                //有id 修改
                if (jsonid == null || "".equals(jsonid)) {
                    Map map = new HashMap<String, String>();
                    SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
                    map.put("createBy", user == null ? "" : user.getId());
                    map.put("createDate", BeanRefUtil.fmtDate(new Date()));
                    map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
                    map.put("updateBy", user == null ? "" : user.getId());
                    String uuid = IdWorker.getIdWorkerNext().toString();
                    map.put("id", uuid);
                    //map.put("mainformid", "0");
                    map.put("mainformid", mainformid);
                    BeanRefUtil.setFieldValue(object, map);
                    if(objectdate.get("mainformdates")!=null){
                        updateMainFormComfromTable(objectdate.get("mainformdates").toString(), uuid);
                    }
                    baseServiceImpl.insertBean(object);
                } else {
                    Map map = new HashMap<String, String>();
                    SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
                    map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
                    map.put("updateBy", user == null ? "" : user.getId());
                    BeanRefUtil.setFieldValue(object, map);
                    Field[] fs = class1.getDeclaredFields();
                    Map<String, String> map1 = BeanRefUtil.getFieldValueMap(object);
                    if(objectdate.get("mainformdates")!=null){
                        updateMainFormComfromTable(objectdate.get("mainformdates").toString(), map1.get("id"));
                    }

                    baseServiceImpl.updateBeanByPrimaryKeySelective(object);
                }
            }
            //删除
            for (int j = 0; j < beanlist.size(); j++) {
                //查询到的id
                Object beanlistobejct = beanlist.get(j);
                Map beanmap = JSON.parseObject(JSON.toJSONString(beanlistobejct), Map.class);
                String beanmapid = (String) beanmap.get("id");
                boolean isdel = true;
                for (int i = 0; i < jsonArray.size(); i++) {
                    //id 相同 false
                    //json date中的id
                    JSONObject objectdate = (JSONObject) jsonArray.get(i);
                    JSONObject object = (JSONObject) objectdate.get("date");
                    String jsonid = (String) ((JSONObject)object).get("id");

                    //相同的不删
                    if ( jsonid==null||jsonid.equals(beanmapid)) {
                        isdel = false;
                        break;
                    }
                }
                if(isdel){
                    baseServiceImpl.deleteByIds(beanmapid);
                }
            }
        }
    }

    @Override
    public int deleteComfromTable(String comformInfo) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(comformInfo);
        SysComForm conForm = sysComFormServiceImpl.selectByPrimaryKey((String) jsonObject.get("formId"));
        String suffixIndexOfString = StringUtils.suffixIndexOfString(conForm.getClassname(), ".");
        BaseServiceImpl<?> baseServiceImpl = (BaseServiceImpl<?>) ApplicationContextUtil.getInstance().getApplicationContext().getBean(StringUtils.first2Lower(suffixIndexOfString) + "ServiceImpl");
        return baseServiceImpl.deleteByIds((String) jsonObject.get("date"));
    }

    @Override
    public List<Object> getFformDic(String comformInfo, Integer pageNumber, Integer pageSize) {
        String[] item = comformInfo.split("|");
        //formId
        SysComForm conForm = sysComFormServiceImpl.selectByPrimaryKey(item[0]);
        //方法名称
        String item1 = item[1];
        //参数
        String[] param = item[2].split("~");
        return null;
    }
}
