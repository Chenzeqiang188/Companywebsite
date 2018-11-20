package com.good.web.cms.Controller;

import com.good.web.base.EntityManage;
import com.good.web.domain.User;
import com.good.web.domain.UserResource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private EntityManage entityManage;

    private String message = "";

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @Transactional
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String index(User loginuser,String accode) {
        //验证码判断
        String ccode=(String) session.getAttribute("ccode");
        if(!accode.equals(ccode)){
            message = "验证码错误";
            return "error   ";
        }

        if (loginuser.getUsername() != null && loginuser.getPassword() != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(loginuser.getUsername(), loginuser.getPassword(), "login");
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + loginuser.getUsername() + "]进行登录验证..验证开始");
            try {
                currentUser.login( token );
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    User user = (User)entityManage.findByProperty("User", "username", loginuser.getUsername()).get(0);
                    logger.info("用户[" + loginuser.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    List<String> games = new ArrayList<String>();
                    List<String> channels = new ArrayList<String>();
                    for(UserResource res:user.getResources()){
                        if(res.getRestype().equals("project")){
                            games.add(res.getRelationid());
                        }else if(res.getRestype().equals("channel")){
                            channels.add(res.getRelationid());
                        }
                    }
                    currentUser.getSession().setAttribute("USERID", user.getId());
                    currentUser.getSession().setAttribute("USERNAME", user.getUsername());
                    currentUser.getSession().setAttribute("LOGINDATE", user.getLogindate());
                    currentUser.getSession().setAttribute("VOCATION", user.getVocation());
                    currentUser.getSession().setAttribute("RESOURCE_GAMES", games);
                    currentUser.getSession().setAttribute("RESOURCE_CHANNELS", channels);
                    return "redirect:/default.jsp";
                } else {
                    token.clear();
                    System.out.println("用户[" + loginuser.getUsername() + "]登录认证失败,重新登陆");
                    return "/login.jsp";
                }
            } catch ( AuthenticationException ae ) {
                logger.error("用户登录错误:"+ae.getMessage());
                message = ae.getMessage();
                return "/login.jsp";
            }
        }
        return "/login.jsp";
    }


    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout() {
        String ip=request.getRemoteAddr();
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println("用户<"+currentUser.getSession().getAttribute("LOGINNAME")+">登出的请求Ip"+ip);
        currentUser.getSession().setAttribute("CPName", null);
        currentUser.getSession().setAttribute("URLS", null);
        currentUser.getSession().setAttribute("CP", null);
        currentUser.getSession().setAttribute("LOGINNAME", null);
        currentUser.logout();
        //ServletActionContext.getRequest().getSession().invalidate();
        return "/login.jsp";
    }


}
