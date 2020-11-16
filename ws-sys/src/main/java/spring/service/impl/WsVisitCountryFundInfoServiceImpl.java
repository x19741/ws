package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsVisitCountryFundInfo;
import spring.mapper.WsVisitCountryFundInfoMapper;
import spring.mappers.Mapper;
import spring.service.WsVisitCountryFundInfoService;

/**
 * @author shen
 * @date 2020/9/22 14:42
 */
@Service
@Transactional
public class WsVisitCountryFundInfoServiceImpl extends BaseServiceImpl<WsVisitCountryFundInfo> implements WsVisitCountryFundInfoService {

    @Autowired
    WsVisitCountryFundInfoMapper wsVisitCountryFundInfoMapper;

    @Override
    public Mapper<WsVisitCountryFundInfo> getMapper() {
        return wsVisitCountryFundInfoMapper;
    }
}
