package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Iterator;
import java.util.Set;

@Path("/register")
public class RegisterUserController implements PageController {

    @Resource(name = "bean/UserService")
    private UserService userService;

    @Resource(name = "bean/Validator")
    private Validator validator;


    @POST
    @Path("/user") // /hello/world -> HelloWorldController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        User user = new User();
        user.setName(request.getParameter("inputName"));
        user.setEmail(request.getParameter("inputEmail"));
        user.setPassword(request.getParameter("inputPassword"));
        user.setPhoneNumber(request.getParameter("inputPhoneNumber"));
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        if(constraintViolations.size()>0){
            StringBuilder sb = new StringBuilder();
            Iterator<ConstraintViolation<User>> it = constraintViolations.iterator();
            while (it.hasNext()){
                sb.append(it.next().getMessage());
            }
            request.setAttribute("registerresult",sb.toString());
        }else{
            boolean result = userService.register(user);
            if(result){
                request.setAttribute("registerresult","注册成功");
            }else{
                request.setAttribute("registerresult","注册失败");
            }
        }
        return "registersuccess.jsp";
    }

}
