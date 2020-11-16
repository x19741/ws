package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsVisitCountryAddress;
import spring.mapper.WsVisitCountryAddressMapper;
import spring.mappers.Mapper;
import spring.service.WsVisitCountryAddressService;

/**
 * @author shen
 * @date 2020/9/22 14:37
 */
@Service
@Transactional
public class WsVisitCountryAddressServiceImpl extends BaseServiceImpl<WsVisitCountryAddress> implements WsVisitCountryAddressService {
    @Autowired
    WsVisitCountryAddressMapper wsVisitCountryAddressMapper;

    @Override
    public Mapper<WsVisitCountryAddress> getMapper() {
        return wsVisitCountryAddressMapper;
    }
}
