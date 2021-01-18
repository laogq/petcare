package com.anvy.mybatis.service.impl;

import com.anvy.dto.CarePetDTO;
import com.anvy.dto.ResultVo;
import com.anvy.mybatis.entity.CarePet;
import com.anvy.mybatis.mapper.CarePetMapper;
import com.anvy.mybatis.service.ICarePetService;
import com.anvy.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carePetService")
public class CarePetServiceImpl extends ServiceImpl<CarePetMapper, CarePet> implements ICarePetService {

    private Logger log = LoggerFactory.getLogger(CarePetServiceImpl.class);

    @Autowired
    private CarePetMapper carePetMapper;

    @Autowired
    private RedisUtil redis;

    @Override
    public Page<CarePet> getPetList(CarePetDTO carePetVo) {

        QueryWrapper<CarePet> queryWrapper = new QueryWrapper<>();
        Page<CarePet> page = new Page<>();
        page.setCurrent(carePetVo.getPage());
        page.setSize(carePetVo.getLimit());
        Page<CarePet> petPage = carePetMapper.selectPage(page, queryWrapper);
        return petPage;
    }

    @Override
    public ResultVo getPetById(Long id) {
        CarePet carePet = carePetMapper.selectById(id);
        return ResultVo.success().data(carePet);
    }

    @Override
    public ResultVo add(CarePet carePet) {
        Long id = redis.idGenerator("CARE_PET");
        carePet.setId(id);
        int insert = carePetMapper.insert(carePet);
        if(insert == 1){
            return ResultVo.success().message("添加成功！");
        }
        return ResultVo.error().message("添加失败！");
    }

    @Override
    public ResultVo del(Long id) {
        try {
            int i = carePetMapper.deleteById(id);
            if(i == 1){
                return ResultVo.success().data(id);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResultVo.error().message("删除失败！");
        }
        return ResultVo.error().message("删除失败！");
    }
}
