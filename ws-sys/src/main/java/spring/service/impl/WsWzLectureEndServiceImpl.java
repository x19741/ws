package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzLectureEnd;
import spring.mapper.WsWzLectureEndMapper;
import spring.mappers.Mapper;
import spring.service.WsWzLectureEndService;

/**
 * @author shen
 * @date 2020/9/7 15:09
 */
@Service
@Transactional
public class WsWzLectureEndServiceImpl extends BaseServiceImpl<WsWzLectureEnd> implements WsWzLectureEndService {

    @Autowired
    WsWzLectureEndMapper wsWzLectureEndMapper;

    @Override
    public Mapper<WsWzLectureEnd> getMapper() {
        return wsWzLectureEndMapper;
    }
}
