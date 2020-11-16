package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzFireContinueTrue;
import spring.mapper.WsWzFireContinueTrueMapper;
import spring.mappers.Mapper;
import spring.service.WsWzFireContinueTrueService;

/**
 * @author shen
 * @date 2020/8/31 15:08
 */
@Service
@Transactional
public class WsWzFireContinueTrueServiceImpl extends BaseServiceImpl<WsWzFireContinueTrue> implements WsWzFireContinueTrueService {

    @Autowired
    WsWzFireContinueTrueMapper wsWzFireContinueTrueMapper;
    @Override
    public Mapper<WsWzFireContinueTrue> getMapper() {
        return wsWzFireContinueTrueMapper;
    }
}
