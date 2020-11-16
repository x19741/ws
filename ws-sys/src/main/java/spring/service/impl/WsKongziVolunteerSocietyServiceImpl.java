package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsKongziVolunteerSociety;
import spring.mapper.WsKongziVolunteerSocietyMapper;
import spring.mappers.Mapper;
import spring.service.WsKongziVolunteerSocietyService;

/**
 * @author shen
 * @date 2020/9/8 11:47
 */
@Service
@Transactional
public class WsKongziVolunteerSocietyServiceImpl extends BaseServiceImpl<WsKongziVolunteerSociety> implements WsKongziVolunteerSocietyService {

    @Autowired
    WsKongziVolunteerSocietyMapper wsKongziVolunteerSocietyMapper;

    @Override
    public Mapper<WsKongziVolunteerSociety> getMapper() {
        return wsKongziVolunteerSocietyMapper;
    }
}
