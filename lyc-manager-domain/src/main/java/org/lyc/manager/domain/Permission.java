package org.lyc.manager.domain;

import javax.persistence.*;

/**
 * Created by admin on 2018/9/21.
 */
@Entity
@Table(name = "t_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perm_id")
    private Integer id;

    @Column(name = "perm_str")
    private String permStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermStr() {
        return permStr;
    }

    public void setPermStr(String permStr) {
        this.permStr = permStr;
    }
}
