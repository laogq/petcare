package com.anvy.petcare.modules.controller;


import com.anvy.dto.ResultVo;
import com.anvy.mybatis.entity.CarePet;
import com.anvy.mybatis.service.ICarePetService;
import com.anvy.dto.CarePetDTO;
import com.anvy.utils.ToolUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
* @Description: 宠物管理
* @author Anvy Lao
* @date 2020/5/12 16:06
*/
@RestController
@RequestMapping("/pet")
public class CarePetController {

    @Autowired
    @Qualifier("carePetService")
    private ICarePetService carePetService;

    @PostMapping("/getPetList")
    @ResponseBody
    private Page<CarePet> getPetList(@RequestBody CarePetDTO carePetVo){
        return carePetService.getPetList(carePetVo);
    }

    @RequestMapping("/getPetById/{id}")
    public ResultVo getPetById(@PathVariable("id")Long id){
        return carePetService.getPetById(id);
    }

    @RequestMapping("/add")
    public ResultVo add(@RequestBody CarePet carePet){
        if(ToolUtils.isBlank(carePet.getPetName())){
            return ResultVo.error().message("宠物姓名不能为空！");
        }
        if(ToolUtils.isBlank(carePet.getPetSex())){
            return ResultVo.error().message("宠物性别不能为空！");
        }
        if(ToolUtils.isBlank(carePet.getPetHealth())){
            return ResultVo.error().message("宠物健康状况不能为空！");
        }
        if(ToolUtils.isBlank(carePet.getCategoryCode())){
            return ResultVo.error().message("宠物所属类目不能为空！");
        }
        if(ToolUtils.isBlank(carePet.getPetBirth())){
            return ResultVo.error().message("宠物出生日期不能为空！");
        }
        return carePetService.add(carePet);

    }

    @RequestMapping("/del/{id}")
    public ResultVo del(@PathVariable("id") Long id){
        return carePetService.del(id);

    }
}
