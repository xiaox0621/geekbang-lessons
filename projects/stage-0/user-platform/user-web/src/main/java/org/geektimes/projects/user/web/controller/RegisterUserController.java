package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/register")
public class RegisterUserController implements PageController {

    @Resource(name = "bean/UserService")
    private UserService userService;

    @POST
    @Path("/user") // /hello/world -> HelloWorldController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        User user = new User();
        user.setName(request.getParameter("inputName"));
        user.setEmail(request.getParameter("inputEmail"));
        user.setPassword(request.getParameter("inputPassword"));
        user.setPhoneNumber(request.getParameter("inputPhoneNumber"));
        userService.register(user);
        return "registersuccess.jsp";
    }

}
