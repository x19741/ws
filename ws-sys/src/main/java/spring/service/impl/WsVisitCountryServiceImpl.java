package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsVisitCountry;
import spring.mapper.WsVisitCountryMapper;
import spring.mappers.Mapper;
import spring.service.WsVisitCountryService;

/**
 * @author shen
 * @date 2020/9/22 14:31
 */
@Service
@Transactional
public class WsVisitCountryServiceImpl extends BaseServiceImpl<WsVisitCountry> implements WsVisitCountryService {

    @Autowired
    WsVisitCountryMapper wsVisitCountryMapper;

    @Override
    public Mapper<WsVisitCountry> getMapper() {
        return wsVisitCountryMapper;
    }
}
