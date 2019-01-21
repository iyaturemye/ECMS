/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.common.SendEmail;
import com.ebaza.tech.dao.impl.LoginImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.domain.User;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Godwin
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final transient org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private String CLASSNAME = "LoginController :: ";
    private User user = new User();
    private String whereFrom = "login";
    private User loggedInUser;
    public static final String[] USERTYPE = {"admin", "garage", "insurance", "client", "police"};
    private boolean isLoggedIn;
    private User changePass = new User();
    private String retypePassword;
    private String recentPassword;

    public String changePassword() throws Exception {

        try {

            String location = null;
            changePass.setUserName(user.getUserName());
            User u = new UserImpl().getModelWithMyHQL(new String[]{"userName", "password"}, new Object[]{
                changePass.getUserName(), new LoginImpl().criptPassword(recentPassword)}, "from User");
            FacesContext context = FacesContext.getCurrentInstance();
            if (u == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid Recent Password", null));
            } else {
                if (retypePassword.equalsIgnoreCase(changePass.getPassword())) {
                    u.setPassword(new LoginImpl().criptPassword(changePass.getPassword()));
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Your password has been changed", null));
                    new UserImpl().updateInfo(u);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userLoggedIn", null);
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your password doesn't match", null));
                }
            }
            return location;

        } catch (Exception ex) {
            
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", ex.getMessage());
            return null;
        }
    }

    public String login() throws NoSuchAlgorithmException, Exception {
        try {
            UserImpl userImpl = new UserImpl();
            User userLoggedIn = new User();
            userLoggedIn = userImpl.getModelWithMyHQL(new String[]{"userName", "password"}, new Object[]{
                user.getUserName(), new LoginImpl().criptPassword(user.getPassword())}, "from User");
            FacesContext context = FacesContext.getCurrentInstance();
            String location = "login";
            this.whereFrom = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginFrom");
            if (userLoggedIn == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid username or Password", null));
            } else {
                if (userLoggedIn.getStatus().equalsIgnoreCase("active")) {
                    this.isLoggedIn = true;
                    this.loggedInUser = userLoggedIn;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userLoggedIn", loggedInUser);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userType", loggedInUser.getUserType());
                    if (userLoggedIn.getUserType().equalsIgnoreCase(USERTYPE[0])) {
                        location = "members/dashboard.xhtml?faces-redirect=true";
                    } else if (userLoggedIn.getUserType().equalsIgnoreCase(USERTYPE[1])) {
                        if (whereFrom != null && whereFrom.equalsIgnoreCase("biddingPage")) {
                            location = "biddingPage.xhtml?faces-redirect=true";
                        } else {
                            location = "garage/dashboard.xhtml?faces-redirect=true";
                        }
                    } else if (userLoggedIn.getUserType().equalsIgnoreCase(USERTYPE[2])) {
                        if (whereFrom != null && whereFrom.equals("carRegistration")) {
                            location = "CarRegistration.xhtml?faces-redirect=true";
                        } else {
                            location = "insurance/dashboard.xhtml?faces-redirect=true";
                        }
                    } else if (userLoggedIn.getUserType().equalsIgnoreCase(USERTYPE[3])) {

                        location = "client/dashboard.xhtml?faces-redirect=true";
                        if (whereFrom == null) {
                            location = "client/dashboard.xhtml?faces-redirect=true";
                        } else if (whereFrom.equals("carRegistration")) {
                            location = "CarRegistration.xhtml?faces-redirect=true";
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginFrom", null);
                            this.whereFrom = null;
                        }
                    } else if (userLoggedIn.getUserType().equalsIgnoreCase(USERTYPE[4])) {
                        location = "police/dashboard.xhtml?faces-redirect=true";
                        if (whereFrom == null) {
                            location = "police/dashboard.xhtml?faces-redirect=true";
                        } else if (whereFrom.equals("policeReport")) {
                            location = "policeReport.xhtml?faces-redirect=true";
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginFrom", null);
                            this.whereFrom = null;
                        }
                    }
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Your Account have been blocked please you need to contact your Administrator", null));
                }
            }
            return location;
        } catch (Exception e) {
            
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            return null;
        }

    }

    public boolean isFrom() {
        String out = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginFrom");
        return out != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        this.user = null;
        context.getExternalContext().invalidateSession();
        this.isLoggedIn = true;
        loggedInUser = null;
        return "/login.xhtml?faces-redirect=true";
    }

    public String getWhereFrom() {
        return whereFrom;
    }

    public void setWhereFrom(String whereFrom) {
        this.whereFrom = whereFrom;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User LoggedInUser) {
        this.loggedInUser = LoggedInUser;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public User getChangePass() {
        return changePass;
    }

    public void setChangePass(User changePass) {
        this.changePass = changePass;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public String getRecentPassword() {
        return recentPassword;
    }

    public void setRecentPassword(String recentPassword) {
        this.recentPassword = recentPassword;
    }
}
