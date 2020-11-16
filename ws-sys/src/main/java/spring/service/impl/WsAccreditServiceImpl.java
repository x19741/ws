package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsAccredit;
import spring.mapper.WsAccreditMapper;
import spring.mappers.Mapper;
import spring.service.WsAccreditService;

import java.util.List;

/**
 * @author shen
 * @date 2020/8/19 15:16
 */
@Service
@Transactional
public class WsAccreditServiceImpl extends BaseServiceImpl<WsAccredit> implements WsAccreditService {

    @Autowired
    WsAccreditMapper wsAccreditMapper;

    @Override
    public Mapper<WsAccredit> getMapper() {
        return wsAccreditMapper;
    }


}
