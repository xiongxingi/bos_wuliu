package cn.itcast.dao;

import cn.itcast.domain.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CourierDao extends JpaRepository<Courier,Integer> ,JpaSpecificationExecutor<Courier> {

    @Query(value = "update Courier set deltag = ?2 where c_id = ?1")
    @Modifying
    public void returnBatch(int id , Character status);
}
