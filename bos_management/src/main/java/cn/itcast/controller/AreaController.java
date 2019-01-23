package cn.itcast.controller;

import cn.itcast.Service.AreaService;
import cn.itcast.domain.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/area_save")
    public String save(Area area){
        areaService.save(area);
        return "redirect:/pages/base/area.html";
    }
}
