package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzFireContinueFalse;
import spring.mapper.WsWzFireContinueFalseMapper;
import spring.mappers.Mapper;
import spring.service.WsWzFireContinueFalseService;

/**
 * @author shen
 * @date 2020/8/31 15:17
 */
@Service
@Transactional
public class WsWzFireContinueFalseServiceImpl extends BaseServiceImpl<WsWzFireContinueFalse> implements WsWzFireContinueFalseService {

    @Autowired
    WsWzFireContinueFalseMapper wsWzFireContinueFalseMapper;
    @Override
    public Mapper<WsWzFireContinueFalse> getMapper() {
        return wsWzFireContinueFalseMapper;
    }
}
