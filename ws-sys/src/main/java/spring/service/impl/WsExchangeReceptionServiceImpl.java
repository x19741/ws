package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsExchangeReception;
import spring.mapper.WsExchangeReceptionMapper;
import spring.mappers.Mapper;
import spring.service.WsExchangeReceptionService;

/**
 * @author shen
 * @date 2020/8/22 12:02
 */
@Service
@Transactional
public class WsExchangeReceptionServiceImpl extends BaseServiceImpl<WsExchangeReception> implements WsExchangeReceptionService {

    @Autowired
    WsExchangeReceptionMapper wsExchangeReceptionMapper;

    @Override
    public Mapper<WsExchangeReception> getMapper() {
        return wsExchangeReceptionMapper;
    }
}
