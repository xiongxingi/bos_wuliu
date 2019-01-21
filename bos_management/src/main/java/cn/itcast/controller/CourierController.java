package cn.itcast.controller;

import cn.itcast.Service.CourierService;
import cn.itcast.domain.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CourierController {

    @Autowired
    private CourierService courierService;

    @RequestMapping("/courier_save")
    public String save(Courier courier){
        courierService.save(courier);
        return "redirect:/pages/base/courier.html";
    }

    @RequestMapping("/courier_pageQuery")
    @ResponseBody
    public Map pageQuery(int page,int rows){
        Map<String,Object> map = new HashMap<String, Object>();
        Page<Courier> query = courierService.pageQuery(page, rows);
        map.put("total",query.getTotalElements());
        map.put("rows",query.getContent());
        return map;
    }

    @RequestMapping("/courier_delBatch")
    public String delBatch(int[] ids){
        courierService.delBatch(ids);
        return "redirect:/pages/base/courier.html";
    }

    @RequestMapping("/courier_returnBatch")
    public String returnBatch(int[] ids){
        courierService.returnBatch(ids);
        return "redirect:/pages/base/courier.html";
    }
}
