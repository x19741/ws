package spring.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class SysAction {
    @Id
    private Integer actionid;

    private String actionName;

    private String actionMenuid;

    private String actionCode;

    private String viewmode;

    private List<SysAction> sysActions;

}