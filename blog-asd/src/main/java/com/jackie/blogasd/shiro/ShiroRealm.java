package com.jackie.blogasd.shiro;

import com.jackie.blogasd.mapper.TuserMapper;
import com.jackie.blogasd.model.Tuser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    Logger log= LoggerFactory.getLogger(ShiroRealm.class);

    @Resource
    private TuserMapper userMaper;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("doGetAuthorizationInfo"+principalCollection.toString());
        //从PrincipalCollection中获取登陆用户信息
        Tuser user= (Tuser) principalCollection;
        if (user==null){
            return null;
        }
        //所有登陆用户都有普通用户的权限
        Set<String> roles=new HashSet<>();
        roles.add("user");
        //获取当前登陆用户所拥有的权限
        if (user.getLoginName()!=null){
            roles.add(user.getLoginName());
        }
        //授权SimpleAuthenticationInfo,并设置其属性
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        return info;
    }

    /**
     * 密码验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //把authenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Tuser user = userMaper.getUserByUserName(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }
        // 根据用户的情况，来构建AuthenticationInfo对象并返回，通常使用的实现类为SimpleAuthenticationInfo
        // 以下信息从数据库中获取
        // （1）principal：认证的实体信息，可以是email，也可以是数据表对应的用户的实体类对象
        Object principal = token.getUsername();
        // （2）credentials：密码
        Object credentials = user.getPassword();
        // （3）realmName：当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();
        // （4）盐值：取用户信息中唯一的字段来生成盐值，避免由于两个用户原始密码相同，加密后的密码也相同
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials,credentialsSalt, realmName);
        //返回比对结果
        return info;
    }
}
