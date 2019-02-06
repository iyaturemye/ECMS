/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.common.SendEmail;
import com.ebaza.tech.dao.generic.BiddingDao;
import com.ebaza.tech.dao.generic.CompledCarDao;
import com.ebaza.tech.dao.generic.PoliceReportDao;
import com.ebaza.tech.dao.generic.VehicleDetailDao;
import com.ebaza.tech.dao.impl.BiddingImpl;
import com.ebaza.tech.dao.impl.BrokenCarPartImpl;
import com.ebaza.tech.dao.impl.CarsparepartImpl;
import com.ebaza.tech.dao.impl.CompletedCarImpl;
import com.ebaza.tech.dao.impl.ExpectiseImpl;
import com.ebaza.tech.dao.impl.GarageImpl;
import com.ebaza.tech.dao.impl.InsuranceImpl;
import com.ebaza.tech.dao.impl.LoginImpl;
import com.ebaza.tech.dao.impl.PoliceReportImpl;
import com.ebaza.tech.dao.impl.QuotationImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.dao.impl.VehicleDetailsImpl;
import com.ebaza.tech.dao.impl.VehicleImageImpl;
import com.ebaza.tech.domain.ApprovedTemplate;
import com.ebaza.tech.domain.Bidding;
import com.ebaza.tech.domain.BrokenCarPart;
import com.ebaza.tech.domain.Carsparepart;
import com.ebaza.tech.domain.CompletedCar;
import com.ebaza.tech.domain.ExpectiseGarage;
import com.ebaza.tech.domain.Garage;
import com.ebaza.tech.domain.InsuranceCompany;
import com.ebaza.tech.domain.PoliceReport;
import com.ebaza.tech.domain.Quotation;
import com.ebaza.tech.domain.TemplateClass;
import com.ebaza.tech.domain.User;
import com.ebaza.tech.domain.VehicleDetail;
import com.ebaza.tech.domain.VehicleImage;
import com.ebaza.tech.dto.GaragePayment;
import com.ebaza.tech.validation.MyValidation;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Godwin
 */
@ManagedBean
@SessionScoped
public class InsuranceController implements DbConstant, Serializable {

    private User user = new User();
    private User loggedInUser = new User();
    private InsuranceCompany insurance = new InsuranceCompany();
    private String retypePassword;
    private List<TemplateClass> list = new ArrayList<>();
    private List<TemplateClass> listCombo = new ArrayList<>();
    private List<TemplateClass> newOne = new ArrayList<>();
    private String filterByInfo;
    private Bidding billing;
    private List<Quotation> quotation;
    private double subTotal;
    private String description;
    private List<VehicleDetail> listOfVehicleDetails = new ArrayList<>();
    private List<VehicleDetail> listNewVehicleDetails = new ArrayList<>();
    private String searchElement;
    private List<ApprovedTemplate> allApproved = new ArrayList<>();
    private VehicleDetail vehicleDetail;
    private List<String> engineList = new ArrayList<>();
    private List<String> bodyList = new ArrayList<>();
    private List<String> fuelList = new ArrayList<>();
    private List<String> gearboxList = new ArrayList<>();
    private List<String> steeringList = new ArrayList<>();
    private List<String> breakingList = new ArrayList<>();
    private List<String> cluthList = new ArrayList<>();
    private List<String> coolantList = new ArrayList<>();
    private List<String> wheelsList = new ArrayList<>();
    private List<String> instrumentList = new ArrayList<>();
    private List<String> electricalList = new ArrayList<>();
    private List<String> underFameList = new ArrayList<>();
    private List<CompletedCar> completedList = new ArrayList<>();
    private List<ExpectiseGarage> expectiseGarage = new ArrayList<>();
    private String ExpectiseGarageId;
    private List<VehicleImage> accidentVehicleImage = new ArrayList<>();
    private List<VehicleDetail> listBrokenCar = new ArrayList<>();
    private String plateNumber;
    private String newValue;
    private List<InsuranceCompany> allInsurance = new InsuranceImpl().getAll();
    private List<GaragePayment> amountNotPaid = new ArrayList<>();
    private double notPaidAmount = 0.0;
    private String keyElement;
    private String username = "iyaturemye";
    private String apiKey = "6cee4a31d2456e8a28d3018acdf71ca36ca8b229448cb308ce31cd0a688063dc";
    private List<PoliceReport> policeReportElement = new ArrayList();
    private List<Carsparepart> allParentCarsparepart = new ArrayList<>();
    private List<Carsparepart> listOfChoosenCarPart = new ArrayList<>();
    private List<Carsparepart> listOfChoosenCarPart2 = new ArrayList<>();
    private List<BrokenCarPart> listOfBrokenPart = new ArrayList<>();
    private Carsparepart chooseCarspart;
    private String testText;
    private Set<Carsparepart> allBrokenPartParent = new HashSet<>();

    public int numberOfCount(VehicleDetail vd) {
        return new VehicleDetailDao().getCount(vd);
    }

    public void addToList() throws Exception {
        for (String x : this.engineList) {
            Carsparepart c = new CarsparepartImpl().getModelWithMyHQL(new String[]{"id"}, new Object[]{x}, "from Carsparepart");
            if (!checkIfExistIn(c)) {
                this.listOfChoosenCarPart2.add(c);
            }
        }
        this.listOfChoosenCarPart = new ArrayList<>();
    }

    private boolean checkIfExistIn(Carsparepart object) {

        for (Carsparepart x : this.listOfChoosenCarPart2) {
            if (x.getId().equalsIgnoreCase(object.getId())) {
                System.out.println("here we go bos wwwwwwwwwwwwwwwwww");
                return true;
            }
        }
        return false;
    }

    public void initi(Carsparepart cars) throws Exception {
        this.chooseCarspart = cars;
        this.listOfChoosenCarPart = new CarsparepartImpl().getGenericListWithHQLParameter(new String[]{"parent"}, new Object[]{cars.getId()}, "Carsparepart");

    }

    public void changeInsuranceStatus(User user) {
        String newStatus = (user.getStatus().equalsIgnoreCase("block") ? "active" : "block");
        user.setStatus(newStatus);
        User userd = new UserImpl().updateInfo(user);
        if (userd == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something Wrong please", null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Insurance Have been successfull " + (newStatus.equalsIgnoreCase("active") ? "activated" : "blocked"), null));
            allInsurance = new InsuranceImpl().getAll();
        }
    }

    public String convertIntoJson() throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("engine", (engineList.isEmpty()) ? null : engineList);
        obj.put("body", (bodyList.isEmpty()) ? null : bodyList);
        obj.put("fuel", (fuelList.isEmpty()) ? null : fuelList);
        obj.put("break", (breakingList.isEmpty()) ? null : breakingList);
        obj.put("cluth", (cluthList.isEmpty()) ? null : cluthList);
        obj.put("coolant", (coolantList.isEmpty()) ? null : coolantList);
        obj.put("wheelsList", (wheelsList.isEmpty()) ? null : wheelsList);
        obj.put("instrument", (instrumentList.isEmpty()) ? null : instrumentList);
        obj.put("electrical", (electricalList.isEmpty()) ? null : electricalList);
        obj.put("underFame", (underFameList.isEmpty()) ? null : underFameList);
//        obj.put("others", others);
        return obj.toString();
    }

    public PoliceReport policeReport() {

        return null;
    }

    public void addNewToList(String value) {
        if (value.equals("addforeng")) {
            this.engineList.add(newValue);
            newValue = null;
        } else if (value.equals("addforbody")) {
            this.bodyList.add(newValue);
            newValue = null;
        } else if (value.equals("addforfuel")) {
            this.fuelList.add(newValue);
            newValue = null;
        } else if (value.equals("addforgear")) {
            this.gearboxList.add(newValue);
            newValue = null;
        } else if (value.equals("addforsteer")) {
            this.steeringList.add(newValue);
            newValue = null;
        } else if (value.equals("addforbreak")) {
            this.breakingList.add(newValue);
            newValue = null;
        } else if (value.equals("addforwheel")) {
            this.wheelsList.add(newValue);
            newValue = null;
        } else if (value.equals("addforcoolant")) {
            this.coolantList.add(newValue);
            newValue = null;
        } else if (value.equals("addforcluth")) {
            this.cluthList.add(newValue);
            newValue = null;
        } else if (value.equals("addinstrument")) {
            this.instrumentList.add(newValue);
            newValue = null;
        } else if (value.equals("addelectrical")) {
            this.electricalList.add(newValue);
            this.newValue = null;
        } else if (value.equals("addunder")) {
            this.underFameList.add(newValue);
            this.newValue = null;
        }
    }

    public void removeFromList(Carsparepart x) {
        this.listOfChoosenCarPart2.remove(x);
        System.out.println("done hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }

    public InsuranceController() throws Exception {
        allMagic();
        listOfRepairCar();
        listCompled();
        this.allParentCarsparepart = new CarsparepartImpl().allParentsparepart();
//        this.allChildCarsparepart = new CarsparepartImpl().allChildCarsparepart();
    }

    public List<VehicleImage> getImages(VehicleDetail vd) {
        return new VehicleImageImpl().getAllChoosenImg(vd.getUuid());
    }

    public void chooseAction(String action, ExpectiseGarage ex) {

        try {
            if (action.equalsIgnoreCase("delete")) {
                new ExpectiseImpl().deleteOne(ex);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Have been successfull deleted", null));

            } else {
                String status = (action.equalsIgnoreCase("active") ? "block" : "active");
                ex.setStatus(status);
                ExpectiseGarage exe = new ExpectiseImpl().updateInfo(ex);
                if (exe == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "There is an error please try again", null));

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The Garage is now " + (status.equalsIgnoreCase("active") ? "activated" : "blocked"), null));

                }
            }
            this.expectiseGarage = new ExpectiseImpl().getSomeOne(insurance.getUuid());
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }

    }

    public void createExpectise() {

        try {
            ExpectiseGarage ex = new ExpectiseGarage();
            Garage g = new Garage();
            g.setGarageId(ExpectiseGarageId);
            ex.setGarage(g);
            ex.setInsurance(insurance);
            ex = new ExpectiseImpl().create(ex);
            if (ex == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "There is an error please try again later", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Have been successfull registered", null));
                this.expectiseGarage = new ExpectiseImpl().getSomeOne(insurance.getUuid());
            }
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }

    }

    public void listCompled() {
        try {
            loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
            if (loggedInUser != null) {
                if (loggedInUser.getUserType().equalsIgnoreCase("insurance")) {
                    insurance = new InsuranceImpl().getOne(loggedInUser.getUserId());
                    this.completedList = new CompletedCarImpl().getAllCompleted(insurance.getUuid());
                }
            }
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }

    }

    public void changeProfile() {
        if (!loggedInUser.getUserName().isEmpty()) {

        }
    }

    public String approveCar() throws ParseException {

        try {
            if (this.engineList.isEmpty()) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "your must atleast choose which problem car have and then continue"
                                + " ", null));
                return null;
            } else {
                this.vehicleDetail.setStatus("pending");
                this.vehicleDetail.setReadOrUnread("unread");
                new VehicleDetailsImpl().updateInfo(vehicleDetail);

                for (Carsparepart x : this.listOfChoosenCarPart2) {
                    BrokenCarPart obj = new BrokenCarPart();
                    obj.setVehicleDetails(vehicleDetail);
                    obj.setCarsparepart(x);
                    new BrokenCarPartImpl().create(obj);
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "have been successsfull approved", null));
            }

            return "dashboard.xhtml?faces-redirect=true";
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public String approved() {
        try {
            this.vehicleDetail.setStatus("proccessed");
            new VehicleDetailsImpl().updateInfo(vehicleDetail);
            this.listNewVehicleDetails = new VehicleDetailsImpl().getNewAccident(insurance.getUuid(), "new");
            for (PoliceReport x : this.policeReportElement) {
                x.setStatus("proccessed");
                new PoliceReportImpl().updateInfo(x);
            }
            this.policeReportElement = new ArrayList<>();
            sendSmsForNewCar();
            return "listOfNotApprovedCar.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            return null;
        }

    }

    private void sendSmsForNewCar() throws Exception {
        List<User> listOfUser = new UserImpl().getGenericListWithHQLParameter(new String[]{"userType"}, new Object[]{"garage"}, "User");
        for (User x : listOfUser) {
            new SendEmail().sendEmail(x.getUserName(), "new Car ", "Dear Customer we have new Car You can now add your own price it is still open from now");
        }
    }

    public void searchElement() {
        amountNotPaid = new ArrayList<>();
        for (GaragePayment x : new CompledCarDao().notPaidInsurance(insurance.getUuid())) {
            if (x.getPurchasingOrderNum().toString().startsWith(this.keyElement)) {
                amountNotPaid.add(x);
            }
            System.out.println("irahagera");
        }
    }

    public void refresh() {
        System.out.println("here we go boss we");
        this.amountNotPaid = new CompledCarDao().notPaidInsurance(insurance.getUuid());
    }

    public void pay(GaragePayment garagePayment) throws Exception {
        CompletedCar compCar = new CompletedCarImpl().getModelWithMyHQL(new String[]{"purchaseOrdernum"}, new Object[]{garagePayment.getPurchasingOrderNum()}, " from CompletedCar");
        compCar.setIsPaid(true);
        compCar.setUpdatedAt(new Date());
        new CompletedCarImpl().updateInfo(compCar);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "thank you for your payment", null));
        this.amountNotPaid = new CompledCarDao().notPaidInsurance(insurance.getUuid());
        AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey);
        String msg = "you have receive money " + new DecimalFormat("###,###,###").format(garagePayment.getTotalAmount()) + " "
                + "Purchase Order # " + garagePayment.getPurchasingOrderNum() + " which can be used as reference code later";
        Garage g = new GarageImpl().getModelWithMyHQL(new String[]{"name"}, new String[]{garagePayment.getInsuranceName()}, "from Garage");
        //GarageOwner gowner=new GarageOwnerImpl().getModelWithMyHQL(new String[]{"ownerId"},new Object[]{g.get}, keyElement)
        JSONArray results = gateway.sendMessage(g.getGarageOwner().getPhoneNumber().replaceAll(" ", ""), msg);
        FacesContext context = FacesContext.getCurrentInstance();
    }

    // this method is for update our list or select All information we need
    public void allMagic() {
        try {
            loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
            if (loggedInUser != null) {
                if (loggedInUser.getUserType().equalsIgnoreCase("insurance")) {
                    insurance = new InsuranceImpl().getOne(loggedInUser.getUserId());

                    this.list = new BiddingImpl().listOfBidding(insurance.getUuid());
                    this.listCombo = new BiddingDao().listOfForCombo(insurance.getUuid());
                    this.newOne = new BiddingImpl().newOne(insurance.getUuid());
                    this.listNewVehicleDetails = new VehicleDetailsImpl().getNewAccident(insurance.getUuid(), "new");
                    this.expectiseGarage = new ExpectiseImpl().getSomeOne(insurance.getUuid());
                    this.listBrokenCar = new VehicleDetailsImpl().getNewAccident(insurance.getUuid(), "old");
                    this.amountNotPaid = new CompledCarDao().notPaidInsurance(insurance.getUuid());
                    notPaidAmount = 0.0;
                    for (GaragePayment x : amountNotPaid) {
                        notPaidAmount += x.getTotalAmount() + (x.getTotalAmount() * 18) / 100;
                    }
                }

            }
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }

    }

    public String readMore(VehicleDetail v) {
        try {
            v.setReadOrUnread("read");
            this.vehicleDetail = v;
            this.accidentVehicleImage = new VehicleImageImpl().getAllChoosenImg(v.getUuid());
            listOfBrokenPart = new BrokenCarPartImpl().getBrokenCarPart(v.getUuid());
            for (BrokenCarPart x : listOfBrokenPart) {
                this.allBrokenPartParent.add(x.getCarsparepart().getCarsparepart());
            }
            new VehicleDetailsImpl().updateInfo(vehicleDetail);
            this.policeReportElement = new PoliceReportDao().getPoliceReport(this.vehicleDetail.getVehicle().getVehicleId());
            return "BrokenInsuranceInfo.xhtml?faces-redirect=true";
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public void listOfRepairCar() {
        try {
            loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
            if (loggedInUser != null) {

                if (loggedInUser.getUserType().equalsIgnoreCase("insurance")) {
                    insurance = new InsuranceImpl().getOne(loggedInUser.getUserId());
                    Garage loggedInGarage = new GarageImpl().getOne(loggedInUser.getUserId());
//                this.allApproved = new BiddingImpl().getApprovedBd(loggedInGarage.getGarageId());
                    // this was car to be repair 
                    this.listOfVehicleDetails = new VehicleDetailsImpl().getListOfVehicleDetails(insurance.getUuid());
//                this.listOfVehicleDetails = new InsuranceCompany_VehicleDetailsImpl().getOneElement(insurance.getUuid());
                }
            }
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }
    }

    public void search() {
        try {
            user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
            if (user != null) {
                insurance = new InsuranceImpl().getOne(user.getUserId());
                System.out.println(insurance.getUuid() + "    " + searchElement);
//            this.listOfVehicleDetails = new InsuranceCompany_VehicleDetailsImpl().search(insurance.getUuid(), searchElement);
            }
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }

    }

    public void readMore(String billid, String where) {
        try {
            billing = new BiddingImpl().getOne(billid);
            this.quotation = new QuotationImpl().getOne(billing.getBidId());
            this.subTotal = 0;
            for (Quotation q : quotation) {
                subTotal += q.getPrice() * q.getQuantity();
            }
            if (where.equalsIgnoreCase("insurance")) {
                billing.setStatus("read");
                new BiddingImpl().updateInfo(billing);
            }

            allMagic();
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }

    }

    public double calculatePercent(double money) {
        return (money * 18) / 100;
    }

    // for approving bidding
    public void approve() {
        try {
            billing.setComment(description);
            billing.setStatus("unread");
            billing.setIsApproved(true);
            new BiddingImpl().updateInfo(billing);
            VehicleDetail vd = billing.getVehicleDetails();
            vd.setStatus("done");
            AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey);
            StringBuilder strbuilder = new StringBuilder();
            strbuilder.append("Dear,Customer ");
            strbuilder.append(billing.getVehicleDetails().getClient().getFname());
            strbuilder.append(billing.getVehicleDetails().getClient().getLname());
            strbuilder.append("Your Car which have Plate Number :");
            strbuilder.append(billing.getVehicleDetails().getVehicle().getPlateNum());
            strbuilder.append("Chasis Number :");
            strbuilder.append(billing.getVehicleDetails().getVehicle().getChasisNum());
            strbuilder.append("Have been completed now you can  get it from Garage :");
            strbuilder.append(billing.getGarage().getName());
            strbuilder.append("Located At ");
            strbuilder.append(billing.getGarage().getLocation());
            strbuilder.append("phone Number:");
            strbuilder.append(billing.getGarage().getGarageOwner().getPhoneNumber());
            JSONArray results = gateway.sendMessage(billing.getVehicleDetails().getClient().getPhoneNumber(), strbuilder.toString());
            new VehicleDetailsImpl().updateInfo(vd);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Thank u "
                    + "for using our System", USERS));
            allMagic();
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }
    }

    public void filterBy() {
        try {
            user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
            if (filterByInfo.equalsIgnoreCase("low price")) {
                insurance = new InsuranceImpl().getOne(user.getUserId());
                this.list = new BiddingImpl().orderByAsc(insurance.getUuid(), plateNumber);
            } else {
                insurance = new InsuranceImpl().getOne(user.getUserId());
                this.list = new BiddingImpl().orderByDesc(insurance.getUuid(), plateNumber);

            }
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }

    }

    public void searchByName() {

        try {

            this.expectiseGarage = new ExpectiseImpl().SearckName(this.searchElement);

        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }

    }

    public String create() {
        try {
            String location = "";
            String pattern = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";
            FacesContext context = FacesContext.getCurrentInstance();
            if (!new MyValidation().validWithParthen(pattern, user.getUserName())) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid Email Address", null));
            } else {
                User users = new UserImpl().getModelWithMyHQL(new String[]{USERNAME, PASSWORD}, new Object[]{user.getUserName(), new LoginImpl().criptPassword(user.getPassword())}, SELECT_USERS);
                if (users == null) {
                    if (!user.getPassword().equals(retypePassword)) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Password doesn't match", location));
                    } else {
                        user.setPassword(new LoginImpl().criptPassword(user.getPassword()));
                        user.setStatus("active");
                        user.setUserType("insurance");
                        new UserImpl().create(user);
                        insurance.setUser(user);
                        InsuranceCompany ins = new InsuranceImpl().create(insurance);
                        if (ins != null) {
//                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userLoggedIn", user);
//                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isInsurance", "yes");
//                            String whereCommingFrom = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginFrom");
//                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginFrom", null);
//                            location = (whereCommingFrom == null) ? "" : (whereCommingFrom.equalsIgnoreCase("carRegistration") ? "CarRegistration.xhtml?faces-redirect=true" : null);
//                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Have been successfull registered", location));
                            this.user = new User();
                            this.insurance = new InsuranceCompany();
                        }
                    }
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "User " + user.getUserName() + " Already Exist,login or user Another Email", location));
                }
            }

            return location;

        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InsuranceCompany getInsurance() {
        return insurance;
    }

    public void setInsurance(InsuranceCompany insurance) {
        this.insurance = insurance;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public List<TemplateClass> getList() {
        return list;
    }

    public void setList(List<TemplateClass> list) {
        this.list = list;
    }

    public List<TemplateClass> getNewOne() {
        return newOne;
    }

    public void setNewOne(List<TemplateClass> newOne) {
        this.newOne = newOne;
    }

    public String getFilterByInfo() {
        return filterByInfo;
    }

    public void setFilterByInfo(String filterByInfo) {
        this.filterByInfo = filterByInfo;
    }

    public Bidding getBilling() {
        return billing;
    }

    public void setBilling(Bidding billing) {
        this.billing = billing;
    }

    public List<Quotation> getQuotation() {
        return quotation;
    }

    public void setQuotation(List<Quotation> quotation) {
        this.quotation = quotation;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSearchElement() {
        return searchElement;
    }

    public void setSearchElement(String searchElement) {
        this.searchElement = searchElement;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<ApprovedTemplate> getAllApproved() {
        return allApproved;
    }

    public void setAllApproved(List<ApprovedTemplate> allApproved) {
        this.allApproved = allApproved;
    }

    public List<VehicleDetail> getListOfVehicleDetails() {
        return listOfVehicleDetails;
    }

    public void setListOfVehicleDetails(List<VehicleDetail> listOfVehicleDetails) {
        this.listOfVehicleDetails = listOfVehicleDetails;
    }

    public List<VehicleDetail> getListNewVehicleDetails() {
        return listNewVehicleDetails;
    }

    public void setListNewVehicleDetails(List<VehicleDetail> listNewVehicleDetails) {
        this.listNewVehicleDetails = listNewVehicleDetails;
    }

    public VehicleDetail getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(VehicleDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }

    public List<String> getEngineList() {
        return engineList;
    }

    public void setEngineList(List<String> engineList) {
        this.engineList = engineList;
    }

    public List<String> getBodyList() {
        return bodyList;
    }

    public void setBodyList(List<String> bodyList) {
        this.bodyList = bodyList;
    }

    public List<String> getFuelList() {
        return fuelList;
    }

    public void setFuelList(List<String> fuelList) {
        this.fuelList = fuelList;
    }

    public List<String> getGearboxList() {
        return gearboxList;
    }

    public void setGearboxList(List<String> gearboxList) {
        this.gearboxList = gearboxList;
    }

    public List<String> getSteeringList() {
        return steeringList;
    }

    public void setSteeringList(List<String> steeringList) {
        this.steeringList = steeringList;
    }

    public List<String> getBreakingList() {
        return breakingList;
    }

    public void setBreakingList(List<String> breakingList) {
        this.breakingList = breakingList;
    }

    public List<String> getCluthList() {
        return cluthList;
    }

    public void setCluthList(List<String> cluthList) {
        this.cluthList = cluthList;
    }

    public List<String> getCoolantList() {
        return coolantList;
    }

    public void setCoolantList(List<String> coolantList) {
        this.coolantList = coolantList;
    }

    public List<String> getWheelsList() {
        return wheelsList;
    }

    public void setWheelsList(List<String> wheelsList) {
        this.wheelsList = wheelsList;
    }

    public List<String> getInstrumentList() {
        return instrumentList;
    }

    public void setInstrumentList(List<String> instrumentList) {
        this.instrumentList = instrumentList;
    }

    public List<String> getElectricalList() {
        return electricalList;
    }

    public void setElectricalList(List<String> electricalList) {
        this.electricalList = electricalList;
    }

    public List<String> getUnderFameList() {
        return underFameList;
    }

    public void setUnderFameList(List<String> underFameList) {
        this.underFameList = underFameList;
    }

    public List<CompletedCar> getCompletedList() {
        return completedList;
    }

    public void setCompletedList(List<CompletedCar> completedList) {
        this.completedList = completedList;
    }

    public List<ExpectiseGarage> getExpectiseGarage() {
        return expectiseGarage;
    }

    public void setExpectiseGarage(List<ExpectiseGarage> expectiseGarage) {
        this.expectiseGarage = expectiseGarage;
    }

    public String getExpectiseGarageId() {
        return ExpectiseGarageId;
    }

    public void setExpectiseGarageId(String ExpectiseGarageId) {
        this.ExpectiseGarageId = ExpectiseGarageId;
    }

    public List<VehicleImage> getAccidentVehicleImage() {
        return accidentVehicleImage;
    }

    public void setAccidentVehicleImage(List<VehicleImage> accidentVehicleImage) {
        this.accidentVehicleImage = accidentVehicleImage;
    }

    public List<VehicleDetail> getListBrokenCar() {
        return listBrokenCar;
    }

    public void setListBrokenCar(List<VehicleDetail> listBrokenCar) {
        this.listBrokenCar = listBrokenCar;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public List<TemplateClass> getListCombo() {
        return listCombo;
    }

    public void setListCombo(List<TemplateClass> listCombo) {
        this.listCombo = listCombo;
    }

    public List<InsuranceCompany> getAllInsurance() {
        return allInsurance;
    }

    public void setAllInsurance(List<InsuranceCompany> allInsurance) {
        this.allInsurance = allInsurance;
    }

    public List<GaragePayment> getAmountNotPaid() {
        return amountNotPaid;
    }

    public void setAmountNotPaid(List<GaragePayment> amountNotPaid) {
        this.amountNotPaid = amountNotPaid;
    }

    public double getNotPaidAmount() {
        return notPaidAmount;
    }

    public void setNotPaidAmount(double notPaidAmount) {
        this.notPaidAmount = notPaidAmount;
    }

    public String getKeyElement() {
        return keyElement;
    }

    public void setKeyElement(String keyElement) {
        this.keyElement = keyElement;
    }

    public List<PoliceReport> getPoliceReportElement() {
        return policeReportElement;
    }

    public void setPoliceReportElement(List<PoliceReport> policeReportElement) {
        this.policeReportElement = policeReportElement;
    }

    public List<Carsparepart> getAllParentCarsparepart() {
        return allParentCarsparepart;
    }

    public void setAllParentCarsparepart(List<Carsparepart> allParentCarsparepart) {
        this.allParentCarsparepart = allParentCarsparepart;
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

    public List<Carsparepart> getListOfChoosenCarPart() {
        return listOfChoosenCarPart;
    }

    public void setListOfChoosenCarPart(List<Carsparepart> listOfChoosenCarPart) {
        this.listOfChoosenCarPart = listOfChoosenCarPart;
    }

    public Carsparepart getChooseCarspart() {
        return chooseCarspart;
    }

    public void setChooseCarspart(Carsparepart chooseCarspart) {
        this.chooseCarspart = chooseCarspart;
    }

    public String getTestText() {
        return testText;
    }

    public void setTestText(String testText) {
        this.testText = testText;
    }

    public List<Carsparepart> getListOfChoosenCarPart2() {
        return listOfChoosenCarPart2;
    }

    public void setListOfChoosenCarPart2(List<Carsparepart> listOfChoosenCarPart2) {
        this.listOfChoosenCarPart2 = listOfChoosenCarPart2;
    }

    public List<BrokenCarPart> getListOfBrokenPart() {
        return listOfBrokenPart;
    }

    public void setListOfBrokenPart(List<BrokenCarPart> listOfBrokenPart) {
        this.listOfBrokenPart = listOfBrokenPart;
    }

    public Set<Carsparepart> getAllBrokenPartParent() {
        return allBrokenPartParent;
    }

    public void setAllBrokenPartParent(Set<Carsparepart> allBrokenPartParent) {
        this.allBrokenPartParent = allBrokenPartParent;
    }

}
