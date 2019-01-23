package cn.itcast.Service.impl;

import cn.itcast.Service.AreaService;
import cn.itcast.dao.AreaDao;
import cn.itcast.domain.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AreaServiceImpl implements AreaService{

    @Autowired
    private AreaDao areaDao;

    @Transactional
    @Override
    public void save(Area area) {
        areaDao.save(area);
    }
}
