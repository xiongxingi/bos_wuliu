package cn.itcast.Service.impl;

import cn.itcast.Service.AreaService;
import cn.itcast.dao.AreaDao;
import cn.itcast.domain.Area;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AreaServiceImpl implements AreaService{

    @Autowired
    private AreaDao areaDao;

    @Override
    public void save(Area area) {
        areaDao.save(area);
    }

    @Override
    public Page<Area> pageQuery(int page, int rows,Area area) {
        Specification specification = new Specification() {

            List<Predicate> params = new ArrayList<Predicate>();
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                //省份
                if (StringUtils.isNotBlank(area.getProvince())){
                    Predicate p1 = cb.like(root.get("province").as(String.class), "%" + area.getProvince() + "%");
                    params.add(p1);
                }
                //城市
                if (StringUtils.isNotBlank(area.getCity())){
                    Predicate p2 = cb.like(root.get("city").as(String.class), "%" + area.getCity() + "%");
                    params.add(p2);
                }
                //区域
                if (StringUtils.isNotBlank(area.getDistrict())){
                    Predicate p3 = cb.like(root.get("district").as(String.class), "%" + area.getDistrict() + "%");
                    params.add(p3);
                }
                return cb.and(params.toArray(new Predicate[0]));
            }
        };

        PageRequest pageRequest = PageRequest.of(page-1, rows);
        return areaDao.findAll(specification,pageRequest);
    }

    @Override
    public void batchImport(List<Area> list) {
        List<Area> areaList = areaDao.saveAll(list);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            Optional<Area> optional = areaDao.findById(id);
            Area area = optional.get();
            areaDao.delete(area);
        }
    }
}
