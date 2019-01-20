package cn.itcast.Service.impl;

import cn.itcast.Service.StandardService;
import cn.itcast.dao.StandardDao;
import cn.itcast.domain.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardDao standardDao;

    @Transactional
    @Override
    public void save(Standard standard) {
        standardDao.save(standard);
    }

    @Override
    public Page<Standard> pageQuery(int page, int rows) {
        PageRequest pageRequest = PageRequest.of(page-1,rows);
        return standardDao.findAll(pageRequest);
    }
}
