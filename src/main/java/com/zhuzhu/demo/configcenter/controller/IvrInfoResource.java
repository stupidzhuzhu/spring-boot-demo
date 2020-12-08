package com.zhuzhu.demo.configcenter.controller;

import com.zhuzhu.demo.configcenter.interfaces.IvrInfoService;
import com.zhuzhu.demo.configcenter.pojo.IvrPO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

@RestController
@RequestMapping("/callcenter/api/rest/internal/v1/configcenter/")
public class IvrInfoResource {

    @Resource
    private IvrInfoService ivrInfoService;

    @RequestMapping(value = "addIvrInfo", method = RequestMethod.POST)
    public void addIvrInfo(@RequestBody IvrPO ivrPO) {
        ivrInfoService.addIvrInfo(ivrPO);
    }

    @RequestMapping(value = "deleteIvrInfo", method = RequestMethod.DELETE)
    public void deleteIvrInfo(@RequestBody IvrPO ivrPO) {
        ivrInfoService.deleteIvrInfo(ivrPO);
    }

    @RequestMapping(value = "deleteIvrInfoById", method = RequestMethod.DELETE)
    public void deleteIvrInfoById(Integer id) {
        ivrInfoService.deleteIvrInfo(id);
    }

    @RequestMapping(value = "updateIvrInfo", method = RequestMethod.PUT)
    public void updateIvrInfo(@RequestBody IvrPO ivrPO) {

    }

    @RequestMapping(value = "getIvrInfo", method = RequestMethod.GET)
    public Set<IvrPO> getIvrInfo() {
        return ivrInfoService.getAllIvrInfo();
    }

}
