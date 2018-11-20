package com.good.web.cms.shiroConfig;

import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.MD5;
import com.good.web.domain.Permission;
import com.good.web.domain.Role;
import com.good.web.domain.User;
import com.good.web.domain.UserResource;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ShiroDataBaseRealm extends AuthorizingRealm {
    @Autowired
    private EntityManage entityManage;

    @Autowired
    private DataCenter dataCenter;
    /**
     * 当用户进行访问链接时的授权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("Principal对象不能为空");
        }

        Iterator its = principals.fromRealm(getName()).iterator();

        if (its.hasNext() == false) {
            throw new AuthorizationException("找不到User啊，怎么没有登录？");
        }

        User user = (User) its.next();

        //获取权限
        List<String> permissions = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
       if(user.getVocation().equals("super")){//超级管理员做一个特权校验
            List<Permission> perms = dataCenter.permissions;
            for(Permission permission:perms){
                String p = permission.getPermission();
                permissions.add(p);
                sb.append(p+";");
            }
            permissions.add("resource:project:*");
            sb.append("resource:project:*;");
            permissions.add("resource:company:*");
            sb.append("resource:company:*;");
        }else{
            //通过Role获取permission
            List<Permission> perms = new ArrayList<Permission>();
            for(Role role:user.getRoles()){
                perms.addAll(role.getPermissions());
            }
            for(Permission permission:perms){
                String p = permission.getPermission();
                permissions.add(p);
                sb.append(p+";");
            }
            //通过Resource组合Shiro permission
            for(UserResource resource:user.getResources()){
                permissions.add("resource:"+resource.getRestype()+":"+resource.getRelationid());
                sb.append("resource:"+resource.getRestype()+":"+resource.getRelationid()+";");
            }
        }

        //获取角色
        List<String> roles = new ArrayList<String>();
        String[] vs = user.getVocation().split(",");
        for(String s:vs){
            roles.add(s);
            sb.append(s+";");
        }

        //打印
//        System.out.println("######## doGetAuthorizationInfo username="+user.getUsername()+" #########");
//        System.out.println(sb);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermissions(permissions);
        info.addRoles(roles);

        return info;
    }

    /**
     * 登录验证
     * */
    @Transactional
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();

        if (username == null) {
            throw new AccountException("用户名不能为空");
        }

        User user = (User)entityManage.findByProperty("User", "username", username).get(0);

        if (!username.equals(user.getUsername())) {
            throw new UnknownAccountException("用户不存在");
        }

        String password = String.valueOf(usernamePasswordToken.getPassword());
        password = MD5.toMD5(password).toUpperCase();

        if(!user.getPassword().equals(password)){
            throw new AccountException("用户密码错误");
        }
        //修改时间
        user.setLogindate(new Date());
        entityManage.update(user);

        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());

        //return null;
    }
}
