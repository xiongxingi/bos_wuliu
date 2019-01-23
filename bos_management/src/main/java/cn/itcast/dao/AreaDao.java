package cn.itcast.dao;

import cn.itcast.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaDao extends JpaRepository<Area,String>{
}
