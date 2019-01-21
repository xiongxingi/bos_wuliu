package cn.itcast.controller;

import cn.itcast.Service.StandardService;
import cn.itcast.domain.Courier;
import cn.itcast.domain.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StandardController {

    @Autowired
    private StandardService standardService;

    @RequestMapping("/standard_save")
    public String save(Standard standard){
        standardService.save(standard);
        return "redirect:/pages/base/standard.html";
    }

    @RequestMapping("/standard_pageQuery")
    @ResponseBody
    public Map pageQuery(int page,int rows){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<Standard> query = standardService.pageQuery(page, rows);
        map.put("total",query.getTotalElements());
        map.put("rows",query.getContent());

        return map;
    }

    @RequestMapping("/standard_findAll")
    @ResponseBody
    public List<Standard> findAll(){
        List<Standard> standardList = standardService.findAll();
        return standardList;
    }
}
