package com.anvy.petcare.modules.user.controller;


import com.anvy.dto.ResultVo;
import com.anvy.mybatis.entity.PetUser;
import com.anvy.mybatis.service.IPetUserService;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Anvy Lao
 * @since 2021-01-16
 */
@Api("登陆控制")
@Slf4j
@RestController
public class PetUserController {

    @Resource
    private IPetUserService petUserService;


    @ApiOperation(value = "用户登录",notes = "用户")
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultVo login(PetUser user){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getUserPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return ResultVo.error().message("未知账户");
        } catch (IncorrectCredentialsException ice) {
            return ResultVo.error().message("密码不正确~");
        } catch (LockedAccountException lae) {
            return ResultVo.error().message("账户已锁定~");
        } catch (ExcessiveAttemptsException eae) {
            return ResultVo.error().message("用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            return ResultVo.error().message("用户名或密码不正确！");
        }
        if (subject.isAuthenticated()) {
            return ResultVo.success().message("登录成功~");
        } else {
            token.clear();
            return ResultVo.error().message("登录失败~");
        }
    }

    @ApiOperation(value = "用户信息更新",notes = "用户")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResultVo update(PetUser user){
        UpdateChainWrapper<PetUser> update = petUserService.updateInfo();
        return null;
    }

}
