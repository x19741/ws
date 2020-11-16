package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsCity;
import spring.mapper.WsCityMapper;
import spring.mappers.Mapper;
import spring.service.WsCityService;

/**
 * @author shen
 * @date 2020/9/11 15:37
 */
@Service
@Transactional
public class WsCityServiceImpl extends BaseServiceImpl<WsCity> implements WsCityService {

    @Autowired
    WsCityMapper wsCityMapper;

    @Override
    public Mapper<WsCity> getMapper() {
        return wsCityMapper;
    }
}
