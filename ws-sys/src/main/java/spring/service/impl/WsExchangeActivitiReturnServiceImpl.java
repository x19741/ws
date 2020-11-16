package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsExchangeActivitiReturn;
import spring.mapper.WsExchangeActivitiReturnMapper;
import spring.mappers.Mapper;
import spring.service.WsExchangeActivitiReturnService;

@Service
@Transactional
public class WsExchangeActivitiReturnServiceImpl extends BaseServiceImpl<WsExchangeActivitiReturn> implements WsExchangeActivitiReturnService {

    @Autowired
    WsExchangeActivitiReturnMapper wsExchangeActivitiReturnMapper;

    @Override
    public Mapper<WsExchangeActivitiReturn> getMapper() {
        return wsExchangeActivitiReturnMapper;
    }
}
