package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzMaintain;
import spring.mapper.WsWzMaintainMapper;
import spring.mappers.Mapper;
import spring.service.WsWzMaintainService;

/**
 * @author shen
 * @date 2020/9/9 10:05
 */
@Service
@Transactional
public class WsWzMaintainServiceImpl extends BaseServiceImpl<WsWzMaintain> implements WsWzMaintainService {

    @Autowired
    WsWzMaintainMapper wsWzMaintainMapper;

    @Override
    public Mapper<WsWzMaintain> getMapper() {
        return wsWzMaintainMapper;
    }
}
