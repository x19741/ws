package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsVisitCountryRegion;
import spring.mapper.WsVisitCountryRegionMapper;
import spring.mappers.Mapper;
import spring.service.WsVisitCountryRegionService;

/**
 * @author shen
 * @date 2020/9/22 15:16
 */
@Service
@Transactional
public class WsVisitCountryRegionServiceImpl extends BaseServiceImpl<WsVisitCountryRegion> implements WsVisitCountryRegionService {

    @Autowired
    WsVisitCountryRegionMapper wsVisitCountryRegionMapper;

    @Override
    public Mapper<WsVisitCountryRegion> getMapper() {
        return wsVisitCountryRegionMapper;
    }
}
