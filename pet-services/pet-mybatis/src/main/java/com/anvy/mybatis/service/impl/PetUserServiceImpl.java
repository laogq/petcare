package com.anvy.mybatis.service.impl;

import com.anvy.mybatis.entity.PetUser;
import com.anvy.mybatis.mapper.PetUserMapper;
import com.anvy.mybatis.service.IPetUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Anvy Lao
 * @since 2021-01-16
 */
@Service("petUserService")
public class PetUserServiceImpl extends ServiceImpl<PetUserMapper, PetUser> implements IPetUserService {

}
