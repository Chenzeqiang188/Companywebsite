package com.good.web.cms.Controller;

import com.good.web.base.EntityManage;
import com.good.web.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private EntityManage entityManage;

    //@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(User user) {

        if (user.getUsername() != null && user.getPassword() != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), "login");
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证开始");
            try {
                currentUser.login( token );
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    logger.info("用户[" + user.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    System.out.println("用户[" + user.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");

                    return "login";
                } else {
                    token.clear();
                    System.out.println("用户[" + user.getUsername() + "]登录认证失败,重新登陆");
                    return "login";
                }
            } catch ( UnknownAccountException uae ) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-username wasn't in the system");
            } catch ( IncorrectCredentialsException ice ) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-password didn't match");
            } catch ( LockedAccountException lae ) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-account is locked in the system");
            } catch ( AuthenticationException ae ) {
                logger.error(ae.getMessage());
            }
        }
        return "login";
    }

    @Transactional
    @RequestMapping("/ById")
        public  String getUserById(int id){

         HttpServletRequest request = null;

            //保存
            User saveuser=new User();
            saveuser.setUsername("ccc");
            saveuser.setPassword("827CCB0EEA8A706C4C34A16891F84E7B");
            entityManage.save(saveuser);
            User upduser=saveuser;
            upduser.setPassword("");
            entityManage.update(upduser);
//            request.getSession().setAttribute("11","1111");
          //  request.getSession().getAttribute("11");

            //User user = userRepository.findUserById(id);
            //查询

        User user= (User)entityManage.findById(User.class,saveuser.getId());
        //List list =new LinkedList();
        //list.add(user);
        entityManage.delete(user);


        //baseDao.delete(user);
        return user.getUsername();
    }

    @RequestMapping("/freemarker")
    public String testFreemarker(Map<String, Object> map){
        map.put("descrip", "It's a springboot integrate freemarker's demo!!!!");
        return "demo";
    }



}
