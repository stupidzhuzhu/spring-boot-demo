package com.zhuzhu.demo.configcenter.pojo;


import javax.persistence.*;

@Entity
@Table(name = "libra_ivr_resource")
public class IvrPO extends BasePO {

    private static final long serialVersionUID = -4915572930379488795L;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "organization_id")
    private String organzitionId;

    @Column(name = "nemo_number")
    private String nemoNumber;

    public IvrPO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOrganzitionId() {
        return organzitionId;
    }

    public void setOrganzitionId(String organzitionId) {
        this.organzitionId = organzitionId;
    }

    public String getNemoNumber() {
        return nemoNumber;
    }

    public void setNemoNumber(String nemoNumber) {
        this.nemoNumber = nemoNumber;
    }

    @Override
    public String toString() {
        return "IvrPO{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", organzitionId='" + organzitionId + '\'' +
                ", nemoNumber='" + nemoNumber + '\'' +
                '}';
    }
}
