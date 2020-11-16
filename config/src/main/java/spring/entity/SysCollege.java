package spring.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public class SysCollege {
    @Id
    private String id;

    private String cName;


}