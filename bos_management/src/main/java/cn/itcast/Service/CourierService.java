package cn.itcast.Service;

import cn.itcast.domain.Courier;
import org.springframework.data.domain.Page;

public interface CourierService {

    public void save(Courier courier);

    public Page<Courier> pageQuery(int page, int rows);

    public void delBatch(int[] ids);

    public void returnBatch(int[] ids);
}
