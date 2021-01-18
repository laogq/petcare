package com.anvy.mybatis.service;

import com.anvy.dto.CarePetDTO;
import com.anvy.dto.ResultVo;
import com.anvy.mybatis.entity.CarePet;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-05-10
 */
public interface ICarePetService extends IService<CarePet> {

    Page<CarePet> getPetList(CarePetDTO carePetVo);

    ResultVo getPetById(Long id);

    ResultVo add(CarePet carePet);

    ResultVo del(Long id);
}
