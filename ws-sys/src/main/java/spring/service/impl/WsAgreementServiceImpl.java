package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysUser;
import spring.entity.WsAgreement;
import spring.entity.WsPresent;
import spring.mapper.WsAgreementMapper;
import spring.mappers.Mapper;
import spring.service.WsAgreementService;
import spring.util.BeanRefUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shen
 * @date 2020/9/11 17:46
 */
@Service
@Transactional
public class WsAgreementServiceImpl extends BaseServiceImpl<WsAgreement> implements WsAgreementService {

    @Autowired
    WsAgreementMapper wsAgreementMapper;

    @Override
    public Mapper<WsAgreement> getMapper() {
        return wsAgreementMapper;
    }

    //增\
    @Override
    public int insert(WsAgreement record) throws Exception {
        Map map = new HashMap<String, String>();
        SysUser user =getUser();
        map.put("createBy", user == null ? "" : user.getId());
        map.put("createDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        BeanRefUtil.setFieldValue(record, map);
        return getMapper().insertSelective(record);
    }
}
