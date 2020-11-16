package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsExchangeReceptionLink;
import spring.mapper.WsExchangeReceptionLinkMapper;
import spring.mappers.Mapper;
import spring.service.WsExchangeReceptionLinkService;

/**
 * @author shen
 * @date 2020/8/22 11:56
 */
@Service
@Transactional
public class WsExchangeReceptionLinkServiceImpl extends BaseServiceImpl<WsExchangeReceptionLink> implements WsExchangeReceptionLinkService {

    @Autowired
    WsExchangeReceptionLinkMapper wsExchangeReceptionLinkMapper;

    @Override
    public Mapper<WsExchangeReceptionLink> getMapper() {
        return wsExchangeReceptionLinkMapper;
    }
}
