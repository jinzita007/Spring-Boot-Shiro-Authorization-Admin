package com.demo.shiro;

import com.demo.model.Permission;
import com.demo.model.Role;
import com.demo.service.PermissionService;
import com.demo.service.RoleService;
import org.apache.shiro.SecurityUtils;
import com.demo.model.User;
import com.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;



/**
 * @author zhouyulong
 * @date 2018/10/30 上午9:27.
 */
public class MyRealm extends AuthorizingRealm {

    private final static Logger logger= (Logger) LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;



    /**
     * 获取用户角色和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();

        logger.info("用户" + username + "获取权限");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();


        // 获取用户角色集
        List<Role> roleList = this.roleService.findUserRole(username);

        for (Role role : roleList) {
            simpleAuthorizationInfo.addRole(role.getName());
        }

        // 获取用户权限集
        List<Permission> permissions = this.permissionService.findUserPermissions(username);
        for (Permission permission : permissions) {
            simpleAuthorizationInfo.addStringPermission(permission.getCode());
        }

        logger.info("用户"+user.getUsername()+"具有的角色:"+simpleAuthorizationInfo.getRoles());
        logger.info("用户"+user.getUsername()+"具有的权限："+simpleAuthorizationInfo.getStringPermissions());

        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("正在验证身份...");
        //获取用户账号
        String username = (String) token.getPrincipal();

        logger.info("----->"+username);

        //从数据库中查找用户信息
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }

        //账号冻结
        if (user.getStatus().equals(0)) {
            throw new LockedAccountException("账号已被锁定，请联系管理员！");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());

        return authenticationInfo;

    }
}
