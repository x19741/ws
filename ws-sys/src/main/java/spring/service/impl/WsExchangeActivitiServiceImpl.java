package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsExchangeActiviti;
import spring.mapper.WsExchangeActivitiMapper;
import spring.mappers.Mapper;
import spring.service.WsExchangeActivitiService;

@Service
@Transactional
public class WsExchangeActivitiServiceImpl extends BaseServiceImpl<WsExchangeActiviti> implements WsExchangeActivitiService {

    @Autowired
    WsExchangeActivitiMapper wsExchangeActivitiMapper;

    @Override
    public Mapper<WsExchangeActiviti> getMapper() {
        return wsExchangeActivitiMapper;
    }


}
