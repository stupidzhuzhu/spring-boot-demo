package com.zhuzhu.demo.configcenter.dao;

import com.zhuzhu.demo.configcenter.pojo.IvrPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IvrInfoDAO extends JpaRepository<IvrPO, Integer> {

    @Query(value = "select * from libra_ivr_resource", nativeQuery = true)
    Set<IvrPO> getAllNemoNumber();

}
