package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsNotice;
import spring.mapper.WsNoticeMapper;
import spring.mappers.Mapper;
import spring.service.WsNoticeService;

/**
 * @author shen
 * @date 2020/9/14 18:46
 */
@Service
@Transactional
public class WsNoticeServiceImpl extends BaseServiceImpl<WsNotice> implements WsNoticeService {

    @Autowired
    WsNoticeMapper wsNoticeMapper;

    @Override
    public Mapper<WsNotice> getMapper() {
        return wsNoticeMapper;
    }
}
