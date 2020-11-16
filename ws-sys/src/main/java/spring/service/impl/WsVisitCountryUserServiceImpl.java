package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsVisitCountryUser;
import spring.mapper.WsVisitCountryUserMapper;
import spring.mappers.Mapper;
import spring.service.WsVisitCountryUserService;

/**
 * @author shen
 * @date 2020/9/22 15:24
 */
@Service
@Transactional
public class WsVisitCountryUserServiceImpl extends BaseServiceImpl<WsVisitCountryUser> implements WsVisitCountryUserService {

    @Autowired
    WsVisitCountryUserMapper wsVisitCountryUserMapper;

    @Override
    public Mapper<WsVisitCountryUser> getMapper() {
        return wsVisitCountryUserMapper;
    }
}
