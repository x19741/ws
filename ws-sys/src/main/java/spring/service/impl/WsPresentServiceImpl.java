package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysUser;
import spring.entity.WsPresent;
import spring.mapper.WsPresentMapper;
import spring.mappers.Mapper;
import spring.service.WsPresentService;
import spring.util.BeanRefUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shen
 * @date 2020/9/14 18:48
 */
@Service
@Transactional
public class WsPresentServiceImpl extends BaseServiceImpl<WsPresent> implements WsPresentService {

    @Autowired
    WsPresentMapper wsPresentMapper;

    @Override
    public Mapper<WsPresent> getMapper() {
        return wsPresentMapper;
    }

    //增\
    @Override
    public int insert(WsPresent record) throws Exception {
        Map map = new HashMap<String, String>();
        SysUser user =getUser();
        map.put("createBy", user == null ? "" : user.getId());
        map.put("createDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        BeanRefUtil.setFieldValue(record, map);
        record.setIssueUser(user.getId());
        record.setIssueDate(new Date());
        return getMapper().insertSelective(record);
    }
}
