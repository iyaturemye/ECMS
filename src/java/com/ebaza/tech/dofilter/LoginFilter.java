/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dofilter;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.controller.LoginController;
import com.ebaza.tech.domain.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Godwin
 */
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter, DbConstant {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginController loginBean = (LoginController) ((HttpServletRequest) request).getSession().getAttribute("loginController");
        User userSession = (User) ((HttpServletRequest) request).getSession().getAttribute("userLoggedIn");
        String userType = (String) ((HttpServletRequest) request).getSession().getAttribute("userType");
        // For the first application request there is no loginController in the session so user needs to log in
        // For other requests loginController is present but we need to check if user has logged in successfully
        String contextPath = ((HttpServletRequest) request).getContextPath();
        HttpServletRequest req1 = ((HttpServletRequest) request);
        String currentPath = req1.getRequestURL().toString();

        if (currentPath.contains("members") && userSession == null) {
            ((HttpServletResponse) response).sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (currentPath.contains("CarRegistration") && userSession == null) {
            ((HttpServletRequest) request).getSession().
                    setAttribute("loginFrom", "carRegistration");
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (currentPath.contains("biddingPage") && userSession == null) {
            ((HttpServletRequest) request).getSession().setAttribute("loginFrom", "biddingPage");
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");

        } else if (currentPath.contains("insurance/") && userSession == null) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (currentPath.contains("garage") && userSession == null) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (currentPath.contains("client") && userSession == null) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        }else if (currentPath.contains("expert") && userSession == null) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        }
        
//        expert

        if (userSession == null && currentPath.contains("policeReport")) {
            ((HttpServletRequest) request).getSession().
                    setAttribute("loginFrom", "policeReport");
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        }

        if (userSession != null && currentPath.contains("garage") && !userType.equalsIgnoreCase("garage")) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (userSession != null && currentPath.contains("insurance") && !userType.equalsIgnoreCase("insurance")) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (userSession != null && currentPath.contains("members") && !userType.equalsIgnoreCase("admin")) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (userSession != null && currentPath.contains("biddingPage") && !userType.equalsIgnoreCase("garage")) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (userSession != null && currentPath.contains("CarRegistration") && !userType.equalsIgnoreCase("client")) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (userSession != null && currentPath.contains("client") && !userType.equalsIgnoreCase("client")) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        } else if (userSession != null && currentPath.contains("policeReport") && !userType.equalsIgnoreCase("police")) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        }else if (userSession != null && currentPath.contains("expert") && !userType.equalsIgnoreCase("Expert")) {
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/sec/login.xhtml");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
