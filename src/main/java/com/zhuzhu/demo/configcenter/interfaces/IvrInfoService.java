package com.zhuzhu.demo.configcenter.interfaces;

import com.zhuzhu.demo.configcenter.pojo.IvrPO;

public interface IvrInfoService {

    void addIvrInfo(IvrPO ivrPO);

    void deleteIvrInfo(IvrPO ivrPO);

    void deleteIvrInfo(Integer id);

    void updateIvrInfo(IvrPO ivrPO);

    IvrPO getIvrInfo(Integer id);

}
