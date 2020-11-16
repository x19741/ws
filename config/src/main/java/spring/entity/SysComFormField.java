package spring.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "SYS_COM_FORM_FIELD")
public class SysComFormField {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ENNAME")
    private String enname;

    @Column(name = "FORMID")
    private String formid;

    @Column(name = "ORDERID")
    private Integer orderid;

    @Column(name = "MATECHEXT")
    private String matechext;

    @Column(name = "PROPERTY")
    private String property;

    @Column(name = "PARENTFORMID")
    private String parentformid;

    @Column(name = "ORDERBY")
    private String orderby;

    @Column(name = "MODELFIELD")
    private String modelfield;

    @Column(name = "MODELTABLE")
    private String modeltable;




}