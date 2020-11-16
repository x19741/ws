package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsVisitCountryUserHome;
import spring.mapper.WsVisitCountryUserHomeMapper;
import spring.mappers.Mapper;
import spring.service.WsVisitCountryUserHomeService;

/**
 * @author shen
 * @date 2020/9/22 15:28
 */
@Service
@Transactional
public class WsVisitCountryUserHomeServiceImpl extends BaseServiceImpl<WsVisitCountryUserHome> implements WsVisitCountryUserHomeService {

    @Autowired
    WsVisitCountryUserHomeMapper wsVisitCountryUserHomeMapper;

    @Override
    public Mapper<WsVisitCountryUserHome> getMapper() {
        return wsVisitCountryUserHomeMapper;
    }
}
