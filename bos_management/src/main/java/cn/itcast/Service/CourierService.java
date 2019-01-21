package cn.itcast.Service;

import cn.itcast.domain.Courier;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CourierService {

    public void save(Courier courier);

    public Page<Courier> pageQuery(int page, int rows,Courier courier);

    public void delBatch(int[] ids);

    public void returnBatch(int[] ids);

}
