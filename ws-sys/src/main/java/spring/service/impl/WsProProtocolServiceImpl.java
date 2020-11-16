package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsProProtocol;
import spring.mapper.WsProProtocolMapper;
import spring.mappers.Mapper;
import spring.service.WsProProtocolService;

/**
 * @author shen
 * @date 2020/8/27 9:23
 */
@Service
@Transactional
public class WsProProtocolServiceImpl extends BaseServiceImpl<WsProProtocol> implements WsProProtocolService {

    @Autowired
    WsProProtocolMapper wsProProtocolMapper;

    @Override
    public Mapper<WsProProtocol> getMapper() {
        return wsProProtocolMapper;
    }
}
