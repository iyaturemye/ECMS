/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.common.FileUpload;
import com.ebaza.tech.common.SendEmail;
import com.ebaza.tech.dao.impl.ClientImpl;
import com.ebaza.tech.dao.impl.DriverImpl;
import com.ebaza.tech.dao.impl.ExpectiseImpl;
import com.ebaza.tech.dao.impl.InsuranceImpl;
import com.ebaza.tech.dao.impl.LoginImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.dao.impl.VehicleDetailsImpl;
import com.ebaza.tech.dao.impl.VehicleImageImpl;
import com.ebaza.tech.dao.impl.VehicleImpl;
import com.ebaza.tech.domain.Client;
import com.ebaza.tech.domain.Driver;
import com.ebaza.tech.domain.ExpectiseGarage;
import com.ebaza.tech.domain.InsuranceCompany;
import com.ebaza.tech.domain.User;
import com.ebaza.tech.domain.Vehicle;
import com.ebaza.tech.domain.VehicleDetail;
import com.ebaza.tech.domain.VehicleImage;
import com.ebaza.tech.validation.MyValidation;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Godwin
 */
@ManagedBean
@ViewScoped
public class VehicleController implements DbConstant, Serializable {

    private static final long serialVersionUID = 1L;
    private Client client = new Client();
    private Vehicle vehicle = new Vehicle();
    private Vehicle vehicleb = new Vehicle();
    private Driver driver = new Driver();
    private VehicleDetail vehicleDetails = new VehicleDetail();
    private VehicleImage vehicleImage = new VehicleImage();
    private boolean isClientFound = false;
    private UploadedFile fileUpload;
    private List<String> listOfImages = new ArrayList<>();
    private VehicleImpl vehicleImpl = new VehicleImpl();
    private User user = new User();
    private String retypePassword;
    private User loggedInUser;
    private List<VehicleDetail> brokenCar = new VehicleDetailsImpl().getBrokenCar(4);
    private List<VehicleDetail> allBrokenCar = new VehicleDetailsImpl().getAllBrokenCar();
    private List<VehicleImage> images = new VehicleImageImpl().allsWithGroupBy("vehicleDetail");
    private VehicleDetail chooseVehicle = new VehicleDetail();
    private List<VehicleDetail> brokenCar2 = new VehicleDetailsImpl().getBrokenCar(3);
    private String others;
    private String insurance;
    private String insuranceOfVehicleB;
    private List<InsuranceCompany> insuranceList = new InsuranceImpl().getAll();
    private String insuranceId;
    private List<ExpectiseGarage> garageExpectise = new ArrayList<>();
    private String expectiseId;
    private String searchKey;
    private String username = "iyaturemye";
    private String apiKey = "6cee4a31d2456e8a28d3018acdf71ca36ca8b229448cb308ce31cd0a688063dc";

    @PostConstruct
    public void testing() {
        this.loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
    }

    public void searchExpectise() {
        try {
            if (insurance == "") {
                this.garageExpectise = new ExpectiseImpl().getSomeOne(insurance);
            } else {
                this.garageExpectise = new ExpectiseImpl().SearckValue(insurance, searchKey);
            }
        } catch (Exception e) {

            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
        }

    }

    public void expectiseGarage() {
        try {
            this.garageExpectise = new ExpectiseImpl().getSomeOne(insurance);
        } catch (Exception e) {

            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
        }

    }

    public void checkIfExist() {

        try {
            User user = new UserImpl().getModelWithMyHQL(new String[]{USERNAME}, new Object[]{this.user.getUserName()}, SELECT_USERS);
            if (user != null) {
                this.user = user;
                Client client2 = new ClientImpl().searchElement(this.user.getUserId(), "userId");
                if (client2 != null) {
                    this.client = client2;
                    this.isClientFound = true;
                }
            }
        } catch (Exception e) {

            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
        }

    }

    public JSONObject decodeJson(String data) throws ParseException {
        JSONParser parser = new JSONParser();
        Object objs = parser.parse(data);
        JSONObject objss = new JSONObject((Map) objs);
        return objss;
    }

    public String next() throws NoSuchAlgorithmException {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            String pattern = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";
            String location = "broken.xhtml";
            if (!new MyValidation().validWithParthen(pattern, user.getUserName())) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid Email Address", null));
            } else {
                if (isClientFound) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your Already Have an account did you forget your password please go to the forget passwrod link", null));
                } else {
                    if (user.getPassword().equalsIgnoreCase(retypePassword)) {
                        user.setPassword(new LoginImpl().criptPassword(user.getPassword()));
                        user.setUserType("client");
                        user.setStatus("active");
                        new UserImpl().create(user);
                        client.setUser(user);
                        Client cl = new ClientImpl().create(client);
                        String whereFrom = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginFrom");
                        if (whereFrom != null) {
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userLoggedIn", user);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userType", user.getUserType());
                            this.loggedInUser = user;
                            location = "CarRegistration.xhtml?faces-redirect=true";
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginFrom", null);
                        }
                        if (cl != null) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank u for your registration", null));
                        } else {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "There is an error please try again", null));
                        }
                    } else {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Password  doesn't match", null));
                    }
                }

            }
            return location;
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            return null;
        }

    }

    public void Upload(FileUploadEvent event) {
        System.out.println("---------------------------------------------------");
        String newName = new FileUpload().Upload(event, "layout/image/blockenCar/");
        listOfImages.add(newName);
    }

    public void searchForCar(String vehicletype) {

        try {
            System.out.println("");
            Vehicle vb = (vehicletype.equalsIgnoreCase("vehicleb") ? this.vehicleb : this.vehicle);
            if (!vb.getPlateNum().isEmpty()) {
                Vehicle v = new VehicleImpl().getVehicle(vb.getPlateNum());
                if (v != null) {
                    if (vehicletype.equalsIgnoreCase("vehicleb")) {
                        this.vehicleb = v;
                    } else {
                        this.vehicle = v;
                    }
                }
            }
        } catch (Exception e) {

            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
        }

    }

    public String create() throws Exception {
        try {
            if (this.vehicle.getPlateNum().equalsIgnoreCase(this.vehicleb.getPlateNum())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Car B can't be the same as Car A please verify your information", null));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                String isInsurance = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("isInsurance");
                if (listOfImages.size() > 0) {
                    Vehicle v2 = new VehicleImpl().getModelWithMyHQL(new String[]{"plateNum"}, new Object[]{vehicle.getPlateNum()}, "from Vehicle");
                    Vehicle v3 = new Vehicle();
                    if (vehicleb.getPlateNum() != null) {
                        v3 = new VehicleImpl().getModelWithMyHQL(new String[]{"plateNum"}, new Object[]{vehicleb.getPlateNum()}, "from Vehicle");
                    }
                    if (v2 == null) {
                        new VehicleImpl().create(vehicle);
                    } else {
                        vehicle = v2;
                    }
                    if (v3 == null && vehicleb.getPlateNum() != null) {
                        new VehicleImpl().create(vehicleb);
                    } else {
                        vehicleb = v3;
                    }
                    InsuranceCompany ins = new InsuranceCompany();
                    ins.setUuid(insurance);
                    InsuranceCompany ins2 = new InsuranceCompany();
                    ins2.setUuid((insuranceOfVehicleB != null) ? insuranceOfVehicleB : null);
                    System.out.println((insuranceOfVehicleB != null) ? insuranceOfVehicleB : "ok");

                    vehicleDetails.setVehicleb(this.vehicleb);

                    vehicleDetails.setInsurance(ins);
                    vehicleDetails.setInsuranceOfvehicleb((insuranceOfVehicleB == null) ? ins2 : null);
                    vehicleDetails.setStatus("start");
                    vehicleDetails.setVehicle(vehicle);
                    vehicleDetails.setStatementOfVehicle(null);
                    // to setup a driver to 
                    if (!this.driver.getNationalId().isEmpty()) {
                        Driver d = new DriverImpl().getModelWithMyHQL(new String[]{"nationalId"}, new Object[]{this.driver.getNationalId()}, "from Driver");
                        if (d == null) {
                            d = new DriverImpl().create(driver);
                        }
                        vehicleDetails.setDriver(d);
                    } else {
                        vehicleDetails.setDriver(null);
                    }
                    vehicleDetails.setExpectiseGarage(null);
                    ExpectiseGarage ex = new ExpectiseImpl().getModelWithMyHQL(new String[]{"uuid"}, new Object[]{expectiseId}, "from ExpectiseGarage");
                    Client client = new Client();
                    InsuranceCompany insc = new InsuranceCompany();
                    if (isInsurance != null && !isInsurance.equalsIgnoreCase("yes")) {
                    } else {
                        insc = new InsuranceImpl().getModelWithMyHQL(new String[]{"userId"}, new Object[]{loggedInUser.getUserId()}, "from InsuranceCompany");
                    }
                    client = new ClientImpl().getModelWithMyHQL(new String[]{"userId"}, new Object[]{loggedInUser.getUserId()}, "from Client");
                    vehicleDetails.setClient(client);
                    new VehicleDetailsImpl().create(vehicleDetails);
                    for (String v : listOfImages) {
                        VehicleImage vim = new VehicleImage();
                        vim.setVehicleDetail(vehicleDetails);
                        vim.setImage(v);
                        new VehicleImageImpl().create(vim);
                    }
                    if (isInsurance != null && !isInsurance.equalsIgnoreCase("yes")) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Have been successfull registered", null));
                        client = new Client();
                    } else {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank u for you report will notify when your car is ready to repaired", null));
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isInsurance", "no");
                    }
                    AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey);
                    try {
                        String message = "";
                        StringBuilder strbuilder = new StringBuilder();
                        strbuilder.append("Mr/Mrs ");
                        strbuilder.append(client.getFname());
                        strbuilder.append(client.getLname());
                        strbuilder.append("your Car");
                        strbuilder.append(vehicleDetails.getVehicle().getName());
                        strbuilder.append(" with plate Number of ");
                        strbuilder.append(vehicleDetails.getVehicle().getPlateNum());
                        strbuilder.append("with chassis Number of ");
                        strbuilder.append(vehicleDetails.getVehicle().getChasisNum());
                        // here is where we put sommething like where car should be moved to kuyisurirayo
                        strbuilder.append(" thank you for your declaration we will be notified from day 1 to the end ");
//                        String garageMsg = "Sir, There is new broken car you need to do expectise with the following information "
//                                + " Names " + client.getFname() + "   " + client.getLname() + " with vehicle " + vehicleDetails.getVehicle().getName() + " plate number of "
//                                + vehicleDetails.getVehicle().getPlateNum() + " owner PhoneNumber " + client.getPhoneNumber();
                        System.out.println("here we go boss wanjye----------"+client.getPhoneNumber());
                        
                        JSONArray results = gateway.sendMessage(client.getPhoneNumber().replaceAll(" ", ""), strbuilder.toString());
                        //System.out.println(garageMsg + " this is msg for garage   " + ex.getGarage().getGarageOwner().getPhoneNumber());
//                        new SendEmail().sendEmail(ex.getGarage().getGarageOwner().getUser().getUserName(), "New Broken car for Expectise", garageMsg);
//                        JSONArray garageOutput = gateway.sendMessage(ex.getGarage().getGarageOwner().getPhoneNumber().replaceAll(" ", ""), garageMsg);
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
                    }
                    vehicleDetails = new VehicleDetail();
                    vehicle = new Vehicle();
                    listOfImages = new ArrayList<>();
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "You must atleast add image", null));
                }

            }
            return "CarRegistration.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            return null;
        }

    }

    public void expirtise() {

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleDetail getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetail vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public VehicleImage getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(VehicleImage vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public boolean isIsClientFound() {
        return isClientFound;
    }

    public void setIsClientFound(boolean isClientFound) {
        this.isClientFound = isClientFound;
    }

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public List<String> getListOfImages() {
        return listOfImages;
    }

    public void setListOfImages(List<String> listOfImages) {
        this.listOfImages = listOfImages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<VehicleDetail> getBrokenCar() {
        return brokenCar;
    }

    public void setBrokenCar(List<VehicleDetail> brokenCar) {
        this.brokenCar = brokenCar;
    }

    public List<VehicleImage> getImages() {
        return images;
    }

    public void setImages(List<VehicleImage> images) {
        this.images = images;
    }

    public List<VehicleDetail> getAllBrokenCar() {
        return allBrokenCar;
    }

    public void setAllBrokenCar(List<VehicleDetail> allBrokenCar) {
        this.allBrokenCar = allBrokenCar;
    }

    public VehicleDetail getChooseVehicle() {
        return chooseVehicle;
    }

    public void setChooseVehicle(VehicleDetail chooseVehicle) {
        this.chooseVehicle = chooseVehicle;
    }

    public VehicleImpl getVehicleImpl() {
        return vehicleImpl;
    }

    public void setVehicleImpl(VehicleImpl vehicleImpl) {
        this.vehicleImpl = vehicleImpl;
    }

    public List<VehicleDetail> getBrokenCar2() {
        return brokenCar2;
    }

    public void setBrokenCar2(List<VehicleDetail> brokenCar2) {
        this.brokenCar2 = brokenCar2;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public List<InsuranceCompany> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<InsuranceCompany> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public List<ExpectiseGarage> getGarageExpectise() {
        return garageExpectise;
    }

    public void setGarageExpectise(List<ExpectiseGarage> garageExpectise) {
        this.garageExpectise = garageExpectise;
    }

    public String getExpectiseId() {
        return expectiseId;
    }

    public void setExpectiseId(String expectiseId) {
        this.expectiseId = expectiseId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Vehicle getVehicleb() {
        return vehicleb;
    }

    public void setVehicleb(Vehicle vehicleb) {
        this.vehicleb = vehicleb;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getInsuranceOfVehicleB() {
        return insuranceOfVehicleB;
    }

    public void setInsuranceOfVehicleB(String insuranceOfVehicleB) {
        this.insuranceOfVehicleB = insuranceOfVehicleB;
    }

}
