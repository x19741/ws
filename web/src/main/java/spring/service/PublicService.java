package spring.service;

import spring.util.ReType;

import java.util.List;

public interface PublicService {

    ReType selectComfromTable(String comformInfo, Integer pageNumber, Integer pageSize) throws ClassNotFoundException;

    int insertComfromTable(String comformInfo,String mainformid,String uuid) throws Exception;
    int updateComfromTable(String comformInfo,String mainformid) throws Exception;

    int deleteComfromTable(String comformInfo) throws Exception;

    List<Object> getFformDic(String comformInfo, Integer pageNumber, Integer pageSize);

    ReType selectComfromTableForm(String comformInfo, Integer pageNumber, Integer pageSize) throws ClassNotFoundException;

    public Object selectByPrimaryKey(String comformInfo) throws ClassNotFoundException;



}
