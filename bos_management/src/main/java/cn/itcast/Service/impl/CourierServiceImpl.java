package cn.itcast.Service.impl;

import cn.itcast.Service.CourierService;
import cn.itcast.dao.CourierDao;
import cn.itcast.domain.Courier;
import cn.itcast.domain.Standard;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public Page<Courier> pageQuery(int page, int rows,Courier courier) {

        Specification<Courier> spec = new Specification<Courier>() {
            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                ArrayList<Predicate> params = new ArrayList<>();
                //工号
                if(StringUtils.isNotBlank(courier.getCourierNum())){
                    Predicate p1 = cb.equal(root.get("courierNum").as(String.class), courier.getCourierNum());
                    params.add(p1);
                }
                //收派标准
                Join<Courier, Standard> standardJoin = root.join("standard", JoinType.INNER);//连接查询的表
                if (courier.getStandard()!=null && StringUtils.isNotBlank(courier.getStandard().getName())){
                    Predicate p2 = cb.like(standardJoin.get("name").as(String.class), "%" + courier.getStandard().getName() + "%");
                    params.add(p2);
                }
                //单位
                if(StringUtils.isNotBlank(courier.getCompany())){
                    Predicate p3 = cb.like(root.get("company").as(String.class), "%"+courier.getCompany()+"%");
                    params.add(p3);
                }
                //类型
                if(StringUtils.isNotBlank(courier.getType())){
                    Predicate p4 = cb.like(root.get("type").as(String.class), "%"+courier.getType()+"%");
                    params.add(p4);
                }
                return cb.and(params.toArray(new Predicate[0]));

            }
        };

        PageRequest pageRequest = PageRequest.of(page-1,rows);
        return courierDao.findAll(spec, pageRequest);
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
