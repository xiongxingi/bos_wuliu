package cn.itcast.controller;

import cn.itcast.Service.AreaService;
import cn.itcast.domain.Area;
import cn.itcast.utils.PinYin4jUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/area_save")
    public String save(Area area){
        //城市编码
        area.setCitycode(PinYin4jUtils.hanziToPinyin(area.getCity().substring(0,area.getCity().length()-1),""));

        //简码
        String shortCode =area.getProvince().substring(0,area.getProvince().length()-1);
        shortCode += area.getCity().substring(0,area.getCity().length()-1);
        shortCode += area.getDistrict().substring(0,area.getDistrict().length()-1);

        area.setShortcode(PinYin4jUtils.stringArrayToString(PinYin4jUtils.getHeadByString(shortCode)));
        areaService.save(area);
        return "redirect:pages/base/area.html";
    }

    @RequestMapping("/area_pageQuery")
    @ResponseBody
    public Map pageQuery(int page,int rows,Area area){
        Map<String, Object> map = new HashMap<>();
        Page<Area> query = areaService.pageQuery(page, rows,area);
        map.put("total",query.getTotalElements());
        map.put("rows",query.getContent());
        return map;
    }

    @RequestMapping("/area_batchImport")
    public String batchImport(@RequestBody MultipartFile file) 9throws IOException {
        // 编写解析代码逻辑
        // 基于.xls 格式解析 HSSF
        // 1、 加载Excel文件对象
        List<Area> areas = new ArrayList<Area>();
        // 2、 读取一个sheet
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook(file.getInputStream());
        // 3、 读取sheet中每一行
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        // 一行数据 对应 一个区域对象
        for (Row row : sheet) {
            // 一行数据 对应 一个区域对象
            if (row.getRowNum() == 0){
                //第一行跳过
                continue;
            }
            // 跳过空行
            if (row.getCell(0)==null || StringUtils.isBlank(row.getCell(0).getStringCellValue())){
                continue;
            }
            Area area = new Area();
            area.setId(row.getCell(0).getStringCellValue());
            area.setProvince(row.getCell(1).getStringCellValue());
            area.setCity(row.getCell(2).getStringCellValue());
            area.setDistrict(row.getCell(3).getStringCellValue());
            area.setPostcode(row.getCell(4).getStringCellValue());
            //城市编码
            area.setCitycode(PinYin4jUtils.hanziToPinyin(area.getCity().substring(0,area.getCity().length()-1),""));

            //简码
            String shortCode =area.getProvince().substring(0,area.getProvince().length()-1);
            shortCode += area.getCity().substring(0,area.getCity().length()-1);
            shortCode += area.getDistrict().substring(0,area.getDistrict().length()-1);

            area.setShortcode(PinYin4jUtils.stringArrayToString(PinYin4jUtils.getHeadByString(shortCode)));

            areas.add(area);
        }
        areaService.batchImport(areas);
        return "redirect:/pages/base/area.html";
    }

    @RequestMapping("area_delete")
    public String delete(String[] ids){
        areaService.delete(ids);
        return "redirect:/pages/base/area.html";
    }
}
