package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsExchangePresent;
import spring.mapper.WsExchangePresentMapper;
import spring.mappers.Mapper;
import spring.service.WsExchangePresentService;

/**
 * @author shen
 * @date 2020/8/18 16:59
 */
@Service
@Transactional
public class WsExchangePresentServiceImpl extends BaseServiceImpl<WsExchangePresent> implements WsExchangePresentService {

    @Autowired
    WsExchangePresentMapper wsExchangePresentMapper;

    @Override
    public Mapper<WsExchangePresent> getMapper() {
        return wsExchangePresentMapper;
    }
}
