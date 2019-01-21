package cn.itcast.Service.impl;

import cn.itcast.Service.CourierService;
import cn.itcast.dao.CourierDao;
import cn.itcast.domain.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierDao courierDao;

    @Transactional
    @Override
    public void save(Courier courier) {
        courierDao.save(courier);
    }

    @Override
    public Page<Courier> pageQuery(int page, int rows) {
        PageRequest pageRequest = PageRequest.of(page-1,rows);
        return courierDao.findAll(pageRequest);
    }

    //作废
    @Transactional
    @Override
    public void delBatch(int[] ids) {
        for (int id : ids) {
            Optional<Courier> optional = courierDao.findById(id);
            Courier courier = optional.get();
            System.out.println(courier);
            courier.setDeltag('1');
        }
    }

    //还原
    @Transactional
    @Override
    public void returnBatch(int[] ids) {
        for (int id : ids) {
            courierDao.returnBatch(id,null);
        }
    }

}
