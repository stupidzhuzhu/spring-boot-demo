package com.zhuzhu.demo.configcenter.dao;

import com.zhuzhu.demo.configcenter.pojo.IvrPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IvrInfoDAO extends JpaRepository<IvrPO, Integer> {

}
