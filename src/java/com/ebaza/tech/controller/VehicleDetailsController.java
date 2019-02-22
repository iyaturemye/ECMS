/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.dao.impl.BiddingImpl;
import com.ebaza.tech.dao.impl.BrokenCarPartImpl;
import com.ebaza.tech.dao.impl.GarageImpl;
import com.ebaza.tech.dao.impl.GarageOwnerImpl;
import com.ebaza.tech.dao.impl.QuotationImpl;
import com.ebaza.tech.dao.impl.VehicleImageImpl;
import com.ebaza.tech.domain.Bidding;
import com.ebaza.tech.domain.BrokenCarPart;
import com.ebaza.tech.domain.Carsparepart;
import com.ebaza.tech.domain.Garage;
import com.ebaza.tech.domain.GarageOwner;
import com.ebaza.tech.domain.Quotation;
import com.ebaza.tech.domain.User;
import com.ebaza.tech.domain.VehicleDetail;
import com.ebaza.tech.domain.VehicleImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Godwin
 */
@ManagedBean
@SessionScoped
public class VehicleDetailsController implements Serializable {

    private VehicleDetail choosenVehicle;
    private List<Quotation> lisofQuotation = new ArrayList<>();
    private Quotation quotation = new Quotation();
    private double totalAmount = 0;
    private double tax = 0;
    private double subTotal = 0;
    @ManagedProperty(value = "#{loginController.loggedInUser}")
    private User user;
    private Date todayDate = new Date();
    private String text = "Save";
    private Garage garage;
    private List<VehicleImage> listOfVehicleImage = new ArrayList<>();
    private Date estimatedDate;
    private List<BrokenCarPart> listOfBrokenCarPart = new ArrayList<>();
    private Set<Carsparepart> listOfBrokenCarParent = new HashSet<>();
    private double quotationPrice;
    private Quotation chooseQuotationForPrice = new Quotation();

    @PostConstruct
    public void init() {
        checkIfExist();
    }

    public String checkIfExist() {
        if (this.choosenVehicle == null) {
            return "index.xhtml?faces-redirect=true";
        }
        return null;
    }

    public boolean checkIfAlreadyDone() {
        try {
            user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
            if (user != null) {
                System.out.println("user is ----------------------" + this.user.getUserName());
//                user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
                GarageOwner gowner = new GarageOwnerImpl().getOwner(user.getUserId());
                Garage g = new GarageImpl().getOne(gowner.getOwnerId());
                return (new QuotationImpl().validateIfThereAnotherBidding(choosenVehicle.getUuid(), g.getGarageId()).isEmpty());
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public Garage garageOwner() {
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
        GarageOwner gowner = new GarageOwnerImpl().getOwner(user.getUserId());
        this.garage = new GarageImpl().getOne(gowner.getOwnerId());
        return new GarageImpl().getOne(gowner.getOwnerId());

    }

    public void addToQuotation() {
        this.subTotal += (chooseQuotationForPrice.getPrice() * chooseQuotationForPrice.getBrokenCarPart().getQuantity());
        this.tax = (subTotal * 18) / 100;
        this.totalAmount = subTotal + this.tax;
        for (Quotation z : lisofQuotation) {
            if (z.equals(chooseQuotationForPrice)) {
                z.setPrice(chooseQuotationForPrice.getPrice());
            }
        }
        chooseQuotationForPrice = new Quotation();
    }

    public void chooseAction() {
        if (this.text.equalsIgnoreCase("save")) {
            this.addToQuotation();
        } else {
            this.update();
        }
    }

    public void update() {
        int i = 0;
        int toRemoved = -1;
        for (Quotation q : lisofQuotation) {
            if (q.equals(quotation)) {
                toRemoved = i;
            }
            i++;
        }

        if (toRemoved != -1) {
            this.subTotal -= (lisofQuotation.get(toRemoved).getPrice() * lisofQuotation.get(toRemoved).getBrokenCarPart().getQuantity());
            this.subTotal += quotation.getPrice() * quotation.getBrokenCarPart().getQuantity();
            this.tax = (subTotal * 18) / 100;
            this.totalAmount = subTotal + this.tax;
            lisofQuotation.set(toRemoved, quotation);
            System.out.println(toRemoved + "    " + lisofQuotation.get(toRemoved));
//            lisofQuotation.remove(toRemoved);
        }

    }

    public void edit(Quotation q) {
        this.quotation = q;
        this.text = "Update";
        System.out.println("here we go boss we");
    }

    public void addPriceToQuotation(Quotation q) {
        this.chooseQuotationForPrice = q;
    }

    public void removeFromQuotation(Quotation quotation) {
        int i = 0;
        int toRemoved = -1;
        for (Quotation q : lisofQuotation) {
            if (q.equals(quotation)) {
                toRemoved = i;
            }
            i++;
        }

        if (toRemoved != -1) {
            this.subTotal -= (lisofQuotation.get(toRemoved).getPrice() * lisofQuotation.get(toRemoved).getBrokenCarPart().getQuantity());
            this.tax = (subTotal * 18) / 100;
            this.totalAmount = subTotal + this.tax;
            lisofQuotation.remove(toRemoved);
        }

    }

    public void bid() {
        if (lisofQuotation.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Your "
                    + "Please first must add your bidding", text));
        } else {
            Bidding bid = new Bidding();
            bid.setGarage(garage);
            bid.setEstimatedDate(estimatedDate);
            bid.setVehicleDetails(choosenVehicle);
            new BiddingImpl().create(bid);
            for (Quotation q : lisofQuotation) {
                q.setBidding(bid);
                new QuotationImpl().create(q);
            }
            quotation = new Quotation();
            lisofQuotation = new ArrayList<>();
            subTotal = 0;
            tax = 0;
            totalAmount = 0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Your "
                    + "Bidding have been successfull processed you will be notified", text));
        }
    }

    public String initCar(VehicleDetail vehicleDetail) throws Exception {
        this.choosenVehicle = vehicleDetail;
        this.listOfVehicleImage = new VehicleImageImpl().getAllChoosenImg(choosenVehicle.getUuid());
        listOfBrokenCarPart = new BrokenCarPartImpl().getBrokenCarPart(vehicleDetail.getUuid());

        for (BrokenCarPart x : listOfBrokenCarPart) {
            this.listOfBrokenCarParent.add(x.getCarsparepart().getCarsparepart());
            Quotation q = new Quotation();
            q.setBrokenCarPart(x);
            this.lisofQuotation.add(q);
        }

        return "carDetails.xhtml?faces-redirect=true";
    }

    public VehicleDetail getChoosenVehicle() {
        return choosenVehicle;
    }

    public void setChoosenVehicle(VehicleDetail choosenVehicle) {
        this.choosenVehicle = choosenVehicle;
    }

    public List<Quotation> getLisofQuotation() {
        return lisofQuotation;
    }

    public void setLisofQuotation(List<Quotation> lisofQuotation) {
        this.lisofQuotation = lisofQuotation;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Date getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public List<VehicleImage> getListOfVehicleImage() {
        return listOfVehicleImage;
    }

    public void setListOfVehicleImage(List<VehicleImage> listOfVehicleImage) {
        this.listOfVehicleImage = listOfVehicleImage;
    }

    public Date getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(Date estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public List<BrokenCarPart> getListOfBrokenCarPart() {
        return listOfBrokenCarPart;
    }

    public void setListOfBrokenCarPart(List<BrokenCarPart> listOfBrokenCarPart) {
        this.listOfBrokenCarPart = listOfBrokenCarPart;
    }

    public Set<Carsparepart> getListOfBrokenCarParent() {
        return listOfBrokenCarParent;
    }

    public void setListOfBrokenCarParent(Set<Carsparepart> listOfBrokenCarParent) {
        this.listOfBrokenCarParent = listOfBrokenCarParent;
    }

    public double getQuotationPrice() {
        return quotationPrice;
    }

    public void setQuotationPrice(double quotationPrice) {
        this.quotationPrice = quotationPrice;
    }

    public Quotation getChooseQuotationForPrice() {
        return chooseQuotationForPrice;
    }

    public void setChooseQuotationForPrice(Quotation chooseQuotationForPrice) {
        this.chooseQuotationForPrice = chooseQuotationForPrice;
    }

}
