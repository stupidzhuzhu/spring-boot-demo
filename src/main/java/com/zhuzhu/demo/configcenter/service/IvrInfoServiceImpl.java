package com.zhuzhu.demo.configcenter.service;

import com.zhuzhu.demo.configcenter.ConfigCenter;
import com.zhuzhu.demo.configcenter.dao.IvrInfoDAO;
import com.zhuzhu.demo.configcenter.interfaces.IvrInfoService;
import com.zhuzhu.demo.configcenter.pojo.IvrPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class IvrInfoServiceImpl implements IvrInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IvrInfoServiceImpl.class);

    @Resource
    private IvrInfoDAO ivrInfoDAO;

    @Override
    public void addIvrInfo(IvrPO ivrPO) {
        try {
            IvrPO result = ivrInfoDAO.saveAndFlush(ivrPO);
            if(result != null && result.getId() != null) {
                ConfigCenter.getIvrPOMap().put(result.getId(), result);
            } else {
                LOGGER.warn("[DAO] addIvrInfo ivr info result:" + result + ", result id:" + result.getId());
            }
        } catch (Exception e) {
            LOGGER.error("[DAO] add ivr info failed, ivr info:" + ivrPO, e);
        }
    }

    @Override
    public void deleteIvrInfo(IvrPO ivrPO) {
        try {
            ivrInfoDAO.delete(ivrPO);
        } catch (Exception e) {
            LOGGER.error("[DAO] delete ivr info failed, ivr info:" + ivrPO, e);
        }

    }

    @Override
    public void deleteIvrInfo(Integer id) {
        try {
            ivrInfoDAO.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("[DAO] delete ivr info failed, id:" + id, e);
        }
    }

    @Override
    public void updateIvrInfo(IvrPO ivrPO) {
        try{
            IvrPO result = ivrInfoDAO.saveAndFlush(ivrPO);
            if(result != null && result.getId() != null) {
                ConfigCenter.getIvrPOMap().put(result.getId(), result);
            } else {
                LOGGER.warn("[DAO] updateIvrInfo ivr info result:" + result + ", result id:" + result.getId());
            }
        } catch (Exception e) {
            LOGGER.error("[DAO] update ivr info failed, ivr info:" + ivrPO, e);
        }
    }

    @Override
    public IvrPO getIvrInfo(Integer id) {
        IvrPO ivrInfo;
        try {
            ivrInfo = ivrInfoDAO.getOne(id);
        } catch (Exception e) {
            ivrInfo = null;
            LOGGER.error("[DAO] get ivr info failed, id:" + id, e);
        }
        return ivrInfo;
    }

    @Override
    public Set<IvrPO> getAllIvrInfo() {
        return ivrInfoDAO.getAllNemoNumber();
    }
}
