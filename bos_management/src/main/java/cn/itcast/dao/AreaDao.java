package cn.itcast.dao;

import cn.itcast.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AreaDao extends JpaRepository<Area,String>,JpaSpecificationExecutor<Area>{
}
