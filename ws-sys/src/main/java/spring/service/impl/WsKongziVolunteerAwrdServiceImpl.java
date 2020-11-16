package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsKongziVolunteerAwrd;
import spring.mapper.WsKongziVolunteerAwrdMapper;
import spring.mappers.Mapper;
import spring.service.WsKongziVolunteerAwrdService;

/**
 * @author shen
 * @date 2020/9/8 11:07
 */
@Service
@Transactional
public class WsKongziVolunteerAwrdServiceImpl extends BaseServiceImpl<WsKongziVolunteerAwrd> implements WsKongziVolunteerAwrdService {

    @Autowired
    WsKongziVolunteerAwrdMapper wsKongziVolunteerAwrdMapper;

    @Override
    public Mapper<WsKongziVolunteerAwrd> getMapper() {
        return wsKongziVolunteerAwrdMapper;
    }
}
