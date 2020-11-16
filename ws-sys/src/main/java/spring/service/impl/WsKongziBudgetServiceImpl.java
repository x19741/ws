package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsKongziBudget;
import spring.mapper.WsKongziBudgetMapper;
import spring.mappers.Mapper;
import spring.service.WsKongziBudgetService;

/**
 * @author shen
 * @date 2020/8/19 19:38
 */
@Service
@Transactional
public class WsKongziBudgetServiceImpl extends BaseServiceImpl<WsKongziBudget> implements WsKongziBudgetService {

    @Autowired
    WsKongziBudgetMapper wsKongziBudgetMapper;

    @Override
    public Mapper<WsKongziBudget> getMapper() {
        return wsKongziBudgetMapper;
    }
}
