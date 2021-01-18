package com.anvy.shiro.realm;

import com.anvy.mybatis.entity.PetUser;
import com.anvy.mybatis.service.IPetUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Qualifier("petUserService")
    private IPetUserService petUserService;

    /**
     * 权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        if(StringUtils.isBlank(userName)){
            throw new AccountException("账户不正确！");
        }
        String password = new String((char[])authenticationToken.getCredentials());
        if(StringUtils.isBlank(password)){
            throw new AccountException("密码不正确！");
        }
        PetUser petUser = new PetUser(userName);
        QueryWrapper<PetUser> query = new QueryWrapper<PetUser>(petUser);
        PetUser one = petUserService.getOne(query);
        if(one == null){
            throw new UnknownAccountException("未知登陆用户~");
        }
        if(!password.equals(one.getUserPassword())){
            throw new AuthenticationException("密码验证错误~");
        }
        return new SimpleAuthenticationInfo(userName,password,getName());
    }
}
