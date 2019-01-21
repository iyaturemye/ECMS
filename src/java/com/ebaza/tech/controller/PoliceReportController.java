/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.common.FileUpload;
import com.ebaza.tech.common.SendEmail;
import com.ebaza.tech.dao.impl.PoliceImpl;
import com.ebaza.tech.dao.impl.PoliceReportImpl;
import com.ebaza.tech.dao.impl.ThirdPartyImpl;
import com.ebaza.tech.dao.impl.VehicleImpl;
import com.ebaza.tech.domain.Police;
import com.ebaza.tech.domain.PoliceReport;
import com.ebaza.tech.domain.ThirdParty;
import com.ebaza.tech.domain.User;
import com.ebaza.tech.domain.Vehicle;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Godwin
 */
@ManagedBean
@ViewScoped
public class PoliceReportController implements Serializable {

    private Vehicle iyagonzwe = new Vehicle();
    private Vehicle iyagonze = new Vehicle();
    private PoliceReport policeReport = new PoliceReport();
    private ThirdParty thirdParty = new ThirdParty();
    private boolean isIyagonzweFound;
    private boolean isIyagonzeFound;
    private UploadedFile fileUpload;
    private User loggedInUser;

    public void searchForCar(String type) {
        try {
            Vehicle v = (type.equalsIgnoreCase("iyagonze") ? this.iyagonze : this.iyagonzwe);
            if (!v.getPlateNum().isEmpty()) {
                Vehicle v2 = new VehicleImpl().getVehicle(v.getPlateNum());
                if (v2 != null && type.equalsIgnoreCase("iyagonze")) {
                    this.iyagonze = v2;
                    this.isIyagonzeFound = true;
                } else if (v2 != null && type.equalsIgnoreCase("iyagonzwe")) {
                    this.iyagonzwe = v2;
                    this.isIyagonzweFound = true;
                }
            }
        } catch (Exception e) {

            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
        }

    }

    public void create() {
        try {
            if (!isIyagonzeFound) {
                new VehicleImpl().create(iyagonze);
            }
            if (!isIyagonzweFound) {
                new VehicleImpl().create(iyagonzwe);
            }
            this.policeReport.setIyagonzeVehicle(iyagonze);
            this.policeReport.setIyagonzweVehicle(iyagonzwe);
            new ThirdPartyImpl().create(thirdParty);
            this.policeReport.setThirdParty(thirdParty);
            this.loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
            Police p=new PoliceImpl().getModelWithMyHQL(new String[]{"userId"}, new Object[]{loggedInUser.getUserId()}, "from Police");
            policeReport.setPolice(p);
            PoliceReport re = new PoliceReportImpl().create(policeReport);
            FacesContext context = FacesContext.getCurrentInstance();
            if (re != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Have been successfull registered", null));
                isIyagonzeFound = false;
                isIyagonzweFound = false;
                this.policeReport = new PoliceReport();
                this.iyagonze = new Vehicle();
                this.iyagonzwe = new Vehicle();
                this.thirdParty = new ThirdParty();
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "There is an error please try again later", null));
            }
        } catch (Exception e) {
        }

    }

    public PoliceReportController getOne() {
        return null;
    }

    public Vehicle getIyagonzwe() {
        return iyagonzwe;
    }

    public void setIyagonzwe(Vehicle iyagonzwe) {
        this.iyagonzwe = iyagonzwe;
    }

    public Vehicle getIyagonze() {
        return iyagonze;
    }

    public void setIyagonze(Vehicle iyagonze) {
        this.iyagonze = iyagonze;
    }

    public PoliceReport getPoliceReport() {
        return policeReport;
    }

    public void setPoliceReport(PoliceReport policeReport) {
        this.policeReport = policeReport;
    }

    public ThirdParty getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(ThirdParty thirdParty) {
        this.thirdParty = thirdParty;
    }

    public boolean isIsIyagonzweFound() {
        return isIyagonzweFound;
    }

    public void setIsIyagonzweFound(boolean isIyagonzweFound) {
        this.isIyagonzweFound = isIyagonzweFound;
    }

    public boolean isIsIyagonzeFound() {
        return isIyagonzeFound;
    }

    public void setIsIyagonzeFound(boolean isIyagonzeFound) {
        this.isIyagonzeFound = isIyagonzeFound;
    }

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public void Upload(FileUploadEvent event) {
        this.policeReport.setAdditionalDocument(new FileUpload().Upload(event, "layout\\reportDocument\\"));
    }

}
