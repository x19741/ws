package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsExchangeDateSub;
import spring.mapper.WsExchangeDateSubMapper;
import spring.mappers.Mapper;
import spring.service.WsExchangeDateSubService;

/**
 * @author shen
 * @date 2020/8/19 17:58
 */
@Service
@Transactional
public class WsExchangeDateSubServiceImpl extends BaseServiceImpl<WsExchangeDateSub> implements WsExchangeDateSubService {

    @Autowired
    WsExchangeDateSubMapper wsExchangeDateSubMapper;
    @Override
    public Mapper<WsExchangeDateSub> getMapper() {
        return wsExchangeDateSubMapper;
    }
}
