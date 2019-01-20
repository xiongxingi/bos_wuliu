package cn.itcast.dao;

import cn.itcast.domain.Standard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardDao extends JpaRepository<Standard,Integer> {
}
