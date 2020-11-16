package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzLecture;
import spring.mapper.WsWzLectureMapper;
import spring.mappers.Mapper;
import spring.service.BaseService;
import spring.service.WsWzLectureService;

/**
 * @author shen
 * @date 2020/9/7 15:01
 */
@Service
@Transactional
public class WsWzLectureServiceImpl extends BaseServiceImpl<WsWzLecture> implements WsWzLectureService {

    @Autowired
    WsWzLectureMapper wsWzLectureMapper;

    @Override
    public Mapper<WsWzLecture> getMapper() {
        return wsWzLectureMapper;
    }
}
