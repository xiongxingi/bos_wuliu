package cn.itcast.Service;

import cn.itcast.domain.Area;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.List;

public interface AreaService {

    public void save(Area area);

    public Page<Area> pageQuery(int page, int rows,Area area);

    public void batchImport(List<Area> list);

    public void delete(String[] ids);
}
