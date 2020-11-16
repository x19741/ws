package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsVisitFundInfo;
import spring.mapper.WsVisitFundInfoMapper;
import spring.mappers.Mapper;
import spring.service.WsVisitFundInfoService;

/**
 * @author shen
 * @date 2020/9/11 15:42
 */
@Service
@Transactional
public class WsVisitFundInfoServiceImpl extends BaseServiceImpl<WsVisitFundInfo> implements WsVisitFundInfoService {

    @Autowired
    WsVisitFundInfoMapper wsVisitFundInfoMapper;

    @Override
    public Mapper<WsVisitFundInfo> getMapper() {
        return wsVisitFundInfoMapper;
    }
}
