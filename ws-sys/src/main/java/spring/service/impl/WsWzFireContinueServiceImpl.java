package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzFireContinue;
import spring.mapper.WsWzFireContinueMapper;
import spring.mappers.Mapper;
import spring.service.WsWzFireContinueService;

/**
 * @author shen
 * @date 2020/8/31 15:04
 */
@Service
@Transactional
public class WsWzFireContinueServiceImpl extends BaseServiceImpl<WsWzFireContinue> implements WsWzFireContinueService {

    @Autowired
    WsWzFireContinueMapper wsWzFireContinueMapper;
    @Override
    public Mapper<WsWzFireContinue> getMapper() {
        return wsWzFireContinueMapper;
    }
}
