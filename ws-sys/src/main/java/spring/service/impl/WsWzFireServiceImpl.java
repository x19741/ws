package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzFire;
import spring.mapper.WsWzFireMapper;
import spring.mappers.Mapper;
import spring.service.WsWzFireService;

/**
 * @author shen
 * @date 2020/8/28 15:19
 */
@Service
@Transactional
public class WsWzFireServiceImpl extends BaseServiceImpl<WsWzFire> implements WsWzFireService {

    @Autowired
    WsWzFireMapper wsWzFireMapper;
    @Override
    public Mapper<WsWzFire> getMapper() {
        return wsWzFireMapper;
    }
}
