package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsKongziActualBudgetPro;
import spring.mapper.WsKongziActualBudgetProMapper;
import spring.mappers.Mapper;
import spring.service.WsKongziActualBudgetProService;

/**
 * @author shen
 * @date 2020/9/9 10:34
 */
@Service
@Transactional
public class WsKongziActualBudgetProServiceImpl extends BaseServiceImpl<WsKongziActualBudgetPro> implements WsKongziActualBudgetProService {

    @Autowired
    WsKongziActualBudgetProMapper wsKongziActualBudgetProMapper;

    @Override
    public Mapper<WsKongziActualBudgetPro> getMapper() {
        return wsKongziActualBudgetProMapper;
    }
}
