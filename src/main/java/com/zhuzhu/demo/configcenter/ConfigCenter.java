package com.zhuzhu.demo.configcenter;


import com.zhuzhu.demo.configcenter.pojo.IvrPO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigCenter {

//    private static Map<String, OrganizationConfigPO> organizationConfigMap = new ConcurrentHashMap<>();
//    private static Map<String, NemoNumberConfigPO> nemoNumberConfigPOMap = new ConcurrentHashMap<>();
//    private static Map<String, VirtualSeatPO> virtualSeatPOMap = new ConcurrentHashMap<>();
    private static Map<Integer, IvrPO> ivrPOMap = new ConcurrentHashMap<>();
    private static Map<String, String> nemoNumberOrganizationMap = new ConcurrentHashMap<>();
    private static Map<String, Set<String>> nemoNumberSeatUrisMap = new ConcurrentHashMap<>();
    private static Map<String, List<String>> nemoNumberIvrMap = new ConcurrentHashMap<>();

//    public static Map<String, OrganizationConfigPO> getOrganizationConfigMap() {
//        return organizationConfigMap;
//    }
//
//    public static Map<String, NemoNumberConfigPO> getNemoNumberConfigPOMap() {
//        return nemoNumberConfigPOMap;
//    }
//
//    public static Map<String, VirtualSeatPO> getVirtualSeatPOMap() {
//        return virtualSeatPOMap;
//    }

    public static Map<Integer, IvrPO> getIvrPOMap() {
        return ivrPOMap;
    }

    public static Map<String, String> getNemoNumberOrganizationMap() {
        return nemoNumberOrganizationMap;
    }

    public static Map<String, Set<String>> getNemoNumberSeatUrisMap() {
        return nemoNumberSeatUrisMap;
    }

    public static Map<String, List<String>> getNemoNumberIvrMap() {
        return nemoNumberIvrMap;
    }
}
