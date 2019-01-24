package cn.itcast.Service;

import cn.itcast.domain.Standard;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.List;

public interface StandardService{

    public void save(Standard standard);

    public Page<Standard> pageQuery(int page, int rows);

    public List<Standard> findAll();

}
