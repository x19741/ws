package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysPost;
import spring.mapper.SysPostMapper;
import spring.mappers.Mapper;
import spring.service.SysPostService;

/**
 * @author shen
 * @date 2020/8/22 10:03
 */
@Service
@Transactional
public class SysPostServiceImpl extends BaseServiceImpl<SysPost> implements SysPostService {

    @Autowired
    SysPostMapper sysPostMapper;

    @Override
    public Mapper<SysPost> getMapper() {
        return sysPostMapper;
    }
}
