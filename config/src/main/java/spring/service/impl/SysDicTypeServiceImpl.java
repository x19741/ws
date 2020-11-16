package spring.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import spring.entity.SysDicType;
import spring.mapper.SysDicTypeMapper;
import spring.mappers.Mapper;
import spring.service.SysDicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysDicTypeServiceImpl extends BaseServiceImpl<SysDicType> implements SysDicTypeService {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SysDicTypeMapper sysDicTypeMapper;

    @Override
    public Mapper<SysDicType> getMapper() {
        return sysDicTypeMapper;
    }


    @Override
    public ReType show3(SysDicType dicType, Integer pageNumber, Integer pageSize) {
        List<SysDicType> tList = null;
        Page<SysDicType> tPage = PageHelper.startPage(pageNumber, pageSize);
        try {
            tList = recursionShow(getMapper().selectListByPage(dicType));
        } catch (Exception e) {
            log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    public List<SysDicType>  recursionShow(List<SysDicType> list){
        if(list==null)
            return  null;
        List<SysDicType> tList =  new ArrayList<SysDicType>();
        for (SysDicType dicType : list) {
            SysDicType dicType1=new SysDicType();
            dicType1.setParentid(dicType.getTypeid())  ;
            dicType.setDicTypeList(getMapper().selectListByPage(dicType1));
            tList.add(dicType);
        }
        return  tList;
    }

}
