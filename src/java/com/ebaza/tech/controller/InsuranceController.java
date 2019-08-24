/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.common.FileUpload;
import com.ebaza.tech.common.SendEmail;
import com.ebaza.tech.dao.generic.BiddingDao;
import com.ebaza.tech.dao.generic.CompledCarDao;
import com.ebaza.tech.dao.generic.EmployeeDao;
import com.ebaza.tech.dao.generic.PoliceReportDao;
import com.ebaza.tech.dao.generic.VehicleDetailDao;
import com.ebaza.tech.dao.impl.AdditionalInfoImpl;
import com.ebaza.tech.dao.impl.BiddingImpl;
import com.ebaza.tech.dao.impl.BrokenCarPartImpl;
import com.ebaza.tech.dao.impl.CarsparepartImpl;
import com.ebaza.tech.dao.impl.CompletedCarImpl;
import com.ebaza.tech.dao.impl.EmployeeImpl;
import com.ebaza.tech.dao.impl.ExpectiseImpl;
import com.ebaza.tech.dao.impl.GarageImpl;
import com.ebaza.tech.dao.impl.GarageOwnerImpl;
import com.ebaza.tech.dao.impl.InsuranceImpl;
import com.ebaza.tech.dao.impl.LoginImpl;
import com.ebaza.tech.dao.impl.PoliceReportImpl;
import com.ebaza.tech.dao.impl.QuotationImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.dao.impl.VehicleDetailsImpl;
import com.ebaza.tech.dao.impl.VehicleImageImpl;
import com.ebaza.tech.domain.AdditionalBrokenCarPart;
import com.ebaza.tech.domain.ApprovedTemplate;
import com.ebaza.tech.domain.Bidding;
import com.ebaza.tech.domain.BrokenCarPart;
import com.ebaza.tech.domain.Carsparepart;
import com.ebaza.tech.domain.CompletedCar;
import com.ebaza.tech.domain.Employee;
import com.ebaza.tech.domain.ExpectiseGarage;
import com.ebaza.tech.domain.Garage;
import com.ebaza.tech.domain.GarageOwner;
import com.ebaza.tech.domain.InsuranceCompany;
import com.ebaza.tech.domain.PoliceReport;
import com.ebaza.tech.domain.Quotation;
import com.ebaza.tech.domain.TemplateClass;
import com.ebaza.tech.domain.User;
import com.ebaza.tech.domain.VehicleDetail;
import com.ebaza.tech.domain.VehicleImage;
import com.ebaza.tech.dto.GaragePayment;
import com.ebaza.tech.validation.MyValidation;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Godwin
 */
@ManagedBean
@SessionScoped
public class InsuranceController implements DbConstant, Serializable {

    private static final long serialVersionUID = 1L;
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
    private List<CompletedCar> completedList = new ArrayList<>();
    private List<ExpectiseGarage> expectiseGarage = new ArrayList<>();
    private String ExpectiseGarageId;
    private List<VehicleImage> accidentVehicleImage = new ArrayList<>();
    private List<VehicleDetail> listBrokenCar = new ArrayList<>();
    private String plateNumber;
    private String newValue;
    private List<InsuranceCompany> allInsurance = new InsuranceImpl().getAll();
    private List<GaragePayment> amountNotPaid = new ArrayList<>();
    private List<GaragePayment> allTransaction = new ArrayList<>();
    private double notPaidAmount = 0.0;
    private String keyElement;
    private String username = "iyaturemye";
    private String apiKey = "6cee4a31d2456e8a28d3018acdf71ca36ca8b229448cb308ce31cd0a688063dc";
    private List<PoliceReport> policeReportElement = new ArrayList();
    private List<Carsparepart> allParentCarsparepart = new ArrayList<>();
    private List<Carsparepart> listOfChoosenCarPart = new ArrayList<>();
    private List<Carsparepart> listOfChoosenCarPart2 = new ArrayList<>();
    private List<BrokenCarPart> listOfBrokenPart = new ArrayList<>();
    private List<BrokenCarPart> listOfCarPart = new ArrayList<>();
    private Carsparepart chooseCarspart;
    private String testText;
    private Set<Carsparepart> allBrokenPartParent = new HashSet<>();
    private int quantity;
    private String partNumber;
    private Set<BrokenCarPart> addedBrokenCar = new HashSet<>();
    private BrokenCarPart choosenBrokenPart = new BrokenCarPart();
    private String choosencareSpare;
    private List<AdditionalBrokenCarPart> additionalInfo = new ArrayList<>();
    private String descriptions;
    private List<AdditionalBrokenCarPart> choosenAdditionalInfo = new ArrayList<>();
    private List<VehicleDetail> carNeedAssignExpert = new ArrayList<>();
    private String garageId;
    private double estimatedMarketValue;
    private Employee employee = new Employee();
    private List<Employee> employeeList = new ArrayList<>();
    private String logoImage;

    public int numberOfCount(VehicleDetail vd) {
        return new VehicleDetailDao().getCount(vd);
    }

    public void createNewUser() throws Exception {
        User isExist = new UserImpl().getModelWithMyHQL(new String[]{"userName"},
                new Object[]{this.user.getUserName()}, "from User");
        if (isExist != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email " + this.user.getUserName() + " Already exist ", null));
        } else {
            this.user.setUserType("insuranceUser");
            this.user.setParent(this.loggedInUser);
            // default password for user is user@123
            this.user.setPassword(new LoginImpl().criptPassword("user@123"));
            this.user.setStatus("active");
            User savedUser = new UserImpl().create(user);
            this.employee.setUser(savedUser);
            new EmployeeImpl().create(employee);
            this.employee = new Employee();
            this.user = new User();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Has been successfull saved default password for user is user@123 which can be changed", null));
        }

    }

    public void deleteEmployee(Employee employee) throws Exception {
        new EmployeeImpl().delete(employee);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Has been successfull deleted", null));
    }

    public Response convertDateToTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(sdf.format(date));
        LocalDate date2 = LocalDate.parse(sdf.format(new Date()));
        Response response = new Response();

        long result = date2.until(date1, ChronoUnit.MONTHS);
        long inyear = date2.until(date1, ChronoUnit.YEARS);
        long inDays = date2.until(date1, ChronoUnit.DAYS);
        // if all ok is not overdue we get remaining days/years/months
        String output = (inyear > 0) ? inyear + " years" : (result > 0) ? result + " month" : (inDays > 0) ? inDays + " Days" : "out of date";
        long out = (inyear > 0) ? inyear : (result > 0) ? result : inDays;
        response.setStatus("fine");
// here we need to check whether is overdue or not
        // if is overdue we need to add late as status in response which means that he is late to finish job
        // we changed output value so that there is an addition of overdue 
        if (inyear < 0 || result < 0 || inDays < 0) {
            response.setStatus("late");
            output = (inyear < 0) ? " overdue of " + inyear * -1 + "year" : (result < 0) ? "overdue of " + result * -1 + " month" : (inDays < 0) ? "overdue of " + inDays * -1 + " Days" : "out of date";
        }
        // when status is fine means he is not late for finishing job

        response.setValue(out);
        response.setMessage(output);
        return response;
    }

    public Response convertDateBetweenTwoDate(Date completedDate, Date estimatedDate3) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(sdf.format(completedDate));
        LocalDate date2 = LocalDate.parse(sdf.format(estimatedDate3));
        Response response = new Response();

        long result = date1.until(date2, ChronoUnit.MONTHS);
        long inyear = date1.until(date2, ChronoUnit.YEARS);
        long inDays = date1.until(date2, ChronoUnit.DAYS);
        // if all ok is not overdue we get remaining days/years/months
        String output = (inyear > 0) ? inyear + " years" : (result > 0) ? result + " month" : (inDays > 0) ? inDays + " Days" : "out of date";
        long out = (inyear > 0) ? inyear : (result > 0) ? result : inDays;
        response.setStatus("fine");
// here we need to check whether is overdue or not
        // if is overdue we need to add late as status in response which means that he is late to finish job
        // we changed output value so that there is an addition of overdue 
        if (inyear < 0 || result < 0 || inDays < 0) {
            response.setStatus("late");
            output = (inyear < 0) ? " overdue of " + inyear * -1 + "year" : (result < 0) ? "overdue of " + result * -1 + " month" : (inDays < 0) ? "overdue of " + inDays * -1 + " Days" : "out of date";
        }
        // when status is fine means he is not late for finishing job

        // to check where all are ok brings car to the right time and second
        if (inyear == 0 && result == 0 && inDays == 0) {
            output = "right time";
            response.setStatus("fine");
        }
        response.setValue(out);
        response.setMessage(output);
        return response;
    }

    public void addToList() throws Exception {
        for (BrokenCarPart x : listOfBrokenPart) {
            addedBrokenCar.add(x);
        }

        this.listOfBrokenPart = new ArrayList<>();
    }

    // to assign vehicledetails to be used in popup to assign expert to the car;
    public void initVehicleDetailst(VehicleDetail vehicleDetails) {
        this.vehicleDetail = vehicleDetails;
    }

    public void assignExpertToVehicleDetails() {
        ExpectiseGarage expertGarage = new ExpectiseGarage();
        expertGarage.setUuid(garageId);
        this.vehicleDetail.setExpectiseGarage(expertGarage);
        new VehicleDetailsImpl().updateInfo(vehicleDetail);
        this.vehicleDetail = new VehicleDetail();
        this.carNeedAssignExpert = new VehicleDetailsImpl().getCarNeedExpertise(insurance.getUuid());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Have been successfull assigned", null));

    }

    public void removingBrokenPart() {
        Carsparepart part = new Carsparepart();
        part.setId(choosencareSpare);
        BrokenCarPart z = new BrokenCarPart();
        for (BrokenCarPart x : listOfBrokenPart) {
            if (x.getCarsparepart().getId().equalsIgnoreCase(choosencareSpare)) {
                z = x;
            }
        }
        this.listOfBrokenPart.remove(z);
    }

    public void additToList() throws Exception {
        Carsparepart part;
        part = new CarsparepartImpl().getModelWithMyHQL(new String[]{"id"}, new Object[]{choosencareSpare}, "from Carsparepart");
        choosenBrokenPart.setCarsparepart(part);
        this.listOfBrokenPart.add(choosenBrokenPart);
        this.choosenBrokenPart = new BrokenCarPart();
    }

    private boolean checkIfExistIn(Carsparepart object) {
        for (Carsparepart x : this.listOfChoosenCarPart2) {
            if (x.getId().equalsIgnoreCase(object.getId())) {
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

    public PoliceReport policeReport() {

        return null;
    }

    public void removeFromList(BrokenCarPart x) {
        this.addedBrokenCar.remove(x);
    }

    public InsuranceController() throws Exception {
        allMagic();
        listOfRepairCar();
        listCompled();
        this.allParentCarsparepart = new CarsparepartImpl().allParentsparepart();
//        this.allChildCarsparepart = new CarsparepartImpl().allChildCarsparepart();
    }

    public void refresh(String type) {
        if (type.equals("notification")) {
            this.newOne = new BiddingImpl().newOne(insurance.getUuid());
            this.listNewVehicleDetails = new VehicleDetailsImpl().getNewAccident(insurance.getUuid(), "new");
            this.carNeedAssignExpert = new VehicleDetailsImpl().getCarNeedExpertise(insurance.getUuid());
        } else if (type.equals("dashboard")) {
            allMagic();
            listOfRepairCar();
            listCompled();
        }

//        this.allParentCarsparepart = new CarsparepartImpl().allParentsparepart();
    }

    public void removeNotification() throws Exception {

        if (!this.newOne.isEmpty()) {
            for (TemplateClass template : this.newOne) {
                Bidding bid = new BiddingImpl().getModelWithMyHQL(new String[]{"bidId"}, new Object[]{template.getBiddingId()}, "from Bidding");
                bid.setStatus("read");
                new BiddingImpl().updateInfo(bid);
                this.newOne = new BiddingImpl().newOne(insurance.getUuid());
            }
        }

        if (!this.listNewVehicleDetails.isEmpty()) {
            for (VehicleDetail x : this.listNewVehicleDetails) {
                x.setReadOrUnread("read");
                new VehicleDetailsImpl().updateInfo(x);
            }
            this.listNewVehicleDetails = new VehicleDetailsImpl().getNewAccident(insurance.getUuid(), "new");
        }
        System.out.println("here we go ");
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
            // e.printStackTrace();
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
            //e.printStackTrace();
        }

    }

    public void listCompled() {
        try {
            loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
            if (loggedInUser != null) {
                if (loggedInUser.getUserType().equalsIgnoreCase("insurance") || loggedInUser.getUserType().equalsIgnoreCase("insuranceUser")) {
                    insurance = loggedInUser.getUserType().equalsIgnoreCase("insurance") ? new InsuranceImpl().getOne(loggedInUser.getUserId()) : new InsuranceImpl().getOne(loggedInUser.getParent().getUserId());
                    this.completedList = new CompletedCarImpl().getAllCompleted(insurance.getUuid());
                }
            }
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            //e.printStackTrace();
        }

    }

    public void Upload(FileUploadEvent event) {
        String newName = new FileUpload().Upload(event, "layout/image/companyLogo/");
        logoImage = newName;
    }

    public void changeProfile() {
        this.insurance.setLogo(logoImage);
        new UserImpl().updateInfo(user);
        new InsuranceImpl().updateInfo(insurance);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Has been successfull changed", null));
    }

    public void addAdditionalInfo() {
        AdditionalBrokenCarPart additional = new AdditionalBrokenCarPart();
        additional.setDescription(descriptions);
        additionalInfo.add(additional);
        this.descriptions = null;

    }

    public void removeAdditionalInfo(AdditionalBrokenCarPart addition) {
        this.additionalInfo.remove(addition);
    }

    public String approveCar(String location) throws ParseException {
        try {
            if (this.addedBrokenCar.isEmpty() && this.additionalInfo.isEmpty()) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "your must atleast choose which problem car have and then continue"
                                + " ", null));
                return null;
            } else {
                this.vehicleDetail.setStatus("pending");
                this.vehicleDetail.setReadOrUnread("unread");
                this.vehicleDetail.setEstamedMarketValue(estimatedMarketValue);

                new VehicleDetailsImpl().updateInfo(vehicleDetail);

                for (BrokenCarPart x : this.addedBrokenCar) {
                    x.setVehicleDetails(vehicleDetail);
                    new BrokenCarPartImpl().create(x);
                }
                for (AdditionalBrokenCarPart z : this.additionalInfo) {
                    new AdditionalInfoImpl().create(z);
                    BrokenCarPart y = new BrokenCarPart();
                    y.setCarsparepart(null);
                    y.setAdditionInfo(z);
                    y.setVehicleDetails(vehicleDetail);
                    new BrokenCarPartImpl().create(y);
                }

                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "have been successsfull approved",
                                null));
                this.addedBrokenCar = new HashSet<>();
                this.additionalInfo = new ArrayList<>();
                System.out.println(loggedInUser.getUserName() + loggedInUser.getUserId());
                GarageOwner gowner = new GarageOwnerImpl().getOwner(loggedInUser.getUserId());
                Garage loggedInGarage = new GarageImpl().getOne(gowner.getOwnerId());
                System.out.println(loggedInGarage.getName());
                this.listNewVehicleDetails = new VehicleDetailsImpl().expertiseGarage(loggedInGarage.getGarageId(), "new");

            }
            this.listNewVehicleDetails = new VehicleDetailsImpl().getNewAccident(insurance.getUuid(), "new");

            return location;
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
            for (PoliceReport x : this.policeReportElement) {
                x.setStatus("proccessed");
                new PoliceReportImpl().updateInfo(x);
            }
            this.policeReportElement = new ArrayList<>();
            this.listNewVehicleDetails = new VehicleDetailsImpl().getNewAccident(insurance.getUuid(), "new");
            sendSmsForNewCar();
            return "dashboard.xhtml?faces-redirect=true";
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

   

    public void pay(GaragePayment garagePayment) {
        try {
            CompletedCar completedCar = new CompletedCarImpl().getModelWithMyHQL(new String[]{"purchaseOrdernum"}, new Object[]{garagePayment.getPurchasingOrderNum()}, "From CompletedCar");
            completedCar.setIsPaid(true);
            completedCar.setUpdatedAt(new Date());
            new CompletedCarImpl().updateInfo(completedCar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "thank you for your payment", null));
            this.amountNotPaid = new CompledCarDao().notPaidInsurance(insurance.getUuid());
            this.allTransaction = new CompledCarDao().paidInsurance(insurance.getUuid());
            AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey);
            String msg = "you  received " + new DecimalFormat("###,###,###").format(garagePayment.getTotalAmount()) + " from Insurance  " + completedCar.getBidding().getVehicleDetails().getInsurance().getName()
                    + "Purchase Order # " + completedCar.getPurchaseOrdernum() + " which can be used as reference code later, thank you";
            Garage g = new GarageImpl().getModelWithMyHQL(new String[]{"name"}, new String[]{completedCar.getBidding().getGarage().getGarageId()}, "from Garage");
            JSONArray results = gateway.sendMessage(g.getGarageOwner().getPhoneNumber().replaceAll(" ", ""), msg);
            FacesContext context = FacesContext.getCurrentInstance();
            this.completedList = new CompletedCarImpl().getAllCompleted(insurance.getUuid());
            for (GaragePayment x : amountNotPaid) {
                notPaidAmount += x.getTotalAmount() + (x.getTotalAmount() * 18) / 100;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
                    this.allTransaction = new CompledCarDao().paidInsurance(insurance.getUuid());
                    this.carNeedAssignExpert = new VehicleDetailsImpl().getCarNeedExpertise(insurance.getUuid());
                    notPaidAmount = 0.0;
                    for (GaragePayment x : amountNotPaid) {
                        notPaidAmount += x.getTotalAmount() + (x.getTotalAmount() * 18) / 100;
                    }
                    employeeList = new EmployeeDao().getEmployee(loggedInUser);

                } else if (loggedInUser.getUserType().equalsIgnoreCase("insuranceUser")) {
                    User parentUser = loggedInUser.getParent();
                    insurance = new InsuranceImpl().getOne(parentUser.getUserId());
                    this.list = new BiddingImpl().listOfBidding(insurance.getUuid());
                    this.listCombo = new BiddingDao().listOfForCombo(insurance.getUuid());
                    this.newOne = new BiddingImpl().newOne(insurance.getUuid());
                    this.listNewVehicleDetails = new VehicleDetailsImpl().getNewAccident(insurance.getUuid(), "new");
                    this.expectiseGarage = new ExpectiseImpl().getSomeOne(insurance.getUuid());
                    this.listBrokenCar = new VehicleDetailsImpl().getNewAccident(insurance.getUuid(), "old");
                    this.amountNotPaid = new CompledCarDao().notPaidInsurance(insurance.getUuid());
                    this.allTransaction = new CompledCarDao().paidInsurance(insurance.getUuid());
                    this.carNeedAssignExpert = new VehicleDetailsImpl().getCarNeedExpertise(insurance.getUuid());
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
                if (x.getCarsparepart() != null) {
                    this.allBrokenPartParent.add(x.getCarsparepart().getCarsparepart());
                } else {
                    this.choosenAdditionalInfo.add(x.getAdditionInfo());
                }

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

                if (loggedInUser.getUserType().equalsIgnoreCase("insurance") || loggedInUser.getUserType().equalsIgnoreCase("insuranceUser")) {
                    insurance = loggedInUser.getUserType().equalsIgnoreCase("insurance") ? new InsuranceImpl().getOne(loggedInUser.getUserId()) : new InsuranceImpl().getOne(loggedInUser.getParent().getUserId());
                    Garage loggedInGarage = new GarageImpl().getOne(loggedInUser.getUserId());
//                this.allApproved = new BiddingImpl().getApprovedBd(loggedInGarage.getGarageId());
                    // this was car to be repair 
                    this.listOfVehicleDetails = new VehicleDetailsImpl().getListOfVehicleDetails(insurance.getUuid());
//                this.listOfVehicleDetails = new InsuranceCompany_VehicleDetailsImpl().getOneElement(insurance.getUuid());
                } else if (loggedInUser.getUserType().equalsIgnoreCase("insuranceUser")) {

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
                subTotal += (q.getBrokenCarPart().getCarsparepart() != null) ? q.getPrice() * q.getBrokenCarPart().getQuantity() : q.getPrice();
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
    
    
    

    /*
    this function is for generating pdf which being used as contract between client,Expert,Garage fo insuring that 
    the broken car part did what they have to do 
    
     */
    public String generateContract() throws FileNotFoundException, DocumentException, BadElementException, IOException, Exception {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Document document = new Document();
            Rectangle rect = new Rectangle(20, 20, 580, 500);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            writer.setBoxSize("art", rect);
            document.setPageSize(rect);
            if (!document.isOpen()) {
                document.open();
            }
            if (this.billing.getGarage().getLogo() != null) {
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\layout");
               String paths = path.substring(0, path.indexOf("\\build"));
                path = paths + "\\web\\layout\\image\\companyLogo/" + this.billing.getGarage().getLogo();
               // String path2 = paths + "\\web\\layout\\image\\companyLogo/" + this.billing.getVehicleDetails().getInsurance().getLogo();
                Image image = Image.getInstance(path);
                
                image.scaleAbsolute(50, 50);
                image.setAlignment(Element.ALIGN_LEFT);
                Paragraph title = new Paragraph();
                //BEGIN page
                title.add(image);
                document.add(title);

            }

            Font font0 = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL);
            Font font1 = new Font(Font.TIMES_ROMAN, 9, Font.ITALIC, new Color(90, 255, 20));
            Font font2 = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL, new Color(0, 0, 0));
            Font font21 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, new Color(0, 0, 0));
            Font font5 = new Font(Font.TIMES_ROMAN, 10, Font.ITALIC, new Color(0, 0, 0));
            Font colorFont = new Font(Font.TIMES_ROMAN, 13, Font.BOLD, new Color(0, 0, 0));
            Font font6 = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL);
            document.add(new Paragraph("\n"));
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            PdfPTable tablep = new PdfPTable(5);
            tablep.setWidthPercentage(100);

            PdfPCell cell10 = new PdfPCell(new Phrase(this.billing.getGarage().getName().toUpperCase(), font2));
            cell10.setBorder(Rectangle.NO_BORDER);
            tablep.addCell(cell10);

            PdfPCell cell12 = new PdfPCell(new Phrase("", font2));
            cell12.setBorder(Rectangle.NO_BORDER);
            tablep.addCell(cell12);

            PdfPCell cell21 = new PdfPCell(new Phrase("", font2));
            cell21.setBorder(Rectangle.NO_BORDER);
            tablep.addCell(cell21);

            PdfPCell cell31 = new PdfPCell(new Phrase("", font2));
            cell31.setBorder(Rectangle.NO_BORDER);
            tablep.addCell(cell31);

            PdfPCell cell41 = new PdfPCell(new Phrase("Date: " + sdf.format(new Date()), font2));
            cell41.setBorder(Rectangle.NO_BORDER);
            tablep.addCell(cell41);
            document.add(tablep);
//            Paragraph p6 = new Paragraph(bid.getGarage().getName().toUpperCase() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Date:" + sdf.format(bid.getCreateAt()) + "\n", font0);
//            p6.setAlignment(Element.ALIGN_LEFT);
//            document.add(p6);

            PdfPTable tablep1 = new PdfPTable(5);
            tablep1.setWidthPercentage(100);

            PdfPCell cell101 = new PdfPCell(new Phrase("Insurance\n", font21));
            cell101.setBorder(Rectangle.NO_BORDER);
            tablep1.addCell(cell101);

            PdfPCell cell121 = new PdfPCell(new Phrase("", font2));
            cell121.setBorder(Rectangle.NO_BORDER);
            tablep1.addCell(cell121);

            PdfPCell cell211 = new PdfPCell(new Phrase("", font2));
            cell211.setBorder(Rectangle.NO_BORDER);
            tablep1.addCell(cell211);

            PdfPCell cell311 = new PdfPCell(new Phrase("", font2));
            cell311.setBorder(Rectangle.NO_BORDER);
            tablep1.addCell(cell311);

            PdfPCell cell411 = new PdfPCell(new Phrase("Car Information", font21));
            cell411.setBorder(Rectangle.NO_BORDER);
            tablep1.addCell(cell411);
            document.add(tablep1);

            PdfPTable tablep12 = new PdfPTable(5);
            tablep12.setWidthPercentage(100);

            PdfPCell cell102 = new PdfPCell(new Phrase(this.billing.getVehicleDetails().getInsurance().getName(), font2));
            cell102.setBorder(Rectangle.NO_BORDER);
            tablep12.addCell(cell102);

            PdfPCell cell1212 = new PdfPCell(new Phrase("", font2));
            cell1212.setBorder(Rectangle.NO_BORDER);
            tablep12.addCell(cell1212);

            PdfPCell cell2112 = new PdfPCell(new Phrase("", font2));
            cell2112.setBorder(Rectangle.NO_BORDER);
            tablep12.addCell(cell2112);

            PdfPCell cell3112 = new PdfPCell(new Phrase("", font2));
            cell3112.setBorder(Rectangle.NO_BORDER);
            tablep12.addCell(cell3112);

            PdfPCell cell4112 = new PdfPCell(new Phrase(this.billing.getVehicleDetails().getVehicle().getName().toUpperCase(), font2));
            cell4112.setBorder(Rectangle.NO_BORDER);
            tablep12.addCell(cell4112);
            document.add(tablep12);

            Paragraph vehicle = new Paragraph("Policy Number " + billing.getVehicleDetails().getVehicle().getPolicyNumber().toUpperCase() + "\n", font0);
            vehicle.setAlignment(Element.ALIGN_RIGHT);
            document.add(vehicle);

            Paragraph vehiclePlate = new Paragraph("Plate Number " + billing.getVehicleDetails().getVehicle().getPlateNum() + "\n\n", font0);
            vehiclePlate.setAlignment(Element.ALIGN_RIGHT);
            document.add(vehiclePlate);
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            PdfPCell cell1 = new PdfPCell(new Phrase("#", font2));
            cell1.setBorder(Rectangle.BOX);
            table.addCell(cell1);

            PdfPCell cell = new PdfPCell(new Phrase("DESCRIPTION", font2));
            cell.setBorder(Rectangle.BOX);
            table.addCell(cell);

            PdfPCell cell2 = new PdfPCell(new Phrase("QUANTITY", font2));
            cell2.setBorder(Rectangle.BOX);
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Phrase("CONDITION", font2));
            cell3.setBorder(Rectangle.BOX);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Phrase("Verfication", font2));
            cell3.setBorder(Rectangle.BOX);
            table.addCell(cell4);

            PdfPCell pdfc5;
            PdfPCell pdfc1;
            PdfPCell pdfc6;
            PdfPCell pdfc7;
            PdfPCell pdfc8;
            PdfPCell pdfc9;
            PdfPCell pdfc10;
            int i = 1;
            DecimalFormat dcf = new DecimalFormat("###,###,###,###");
            for (Quotation ps : quotation) {
                if (ps.getBrokenCarPart().getCarsparepart() != null) {

                    pdfc1 = new PdfPCell(new Phrase(i + "", font6));
                    pdfc1.setBorder(Rectangle.BOX);
                    table.addCell(pdfc1);

                    pdfc5 = new PdfPCell(new Phrase((ps.getBrokenCarPart().getCarsparepart() != null) ? ps.getBrokenCarPart().getCarsparepart().getName() : ps.getBrokenCarPart().getAdditionInfo().getDescription() + "", font6));
                    pdfc5.setBorder(Rectangle.BOX);
                    table.addCell(pdfc5);

                    pdfc6 = new PdfPCell(new Phrase(ps.getBrokenCarPart().getQuantity() + "", font6));
                    pdfc6.setBorder(Rectangle.BOX);
                    table.addCell(pdfc6);

                    pdfc7 = new PdfPCell(new Phrase(ps.getBrokenCarPart().getStatus(), font6));
                    pdfc7.setBorder(Rectangle.BOX);
                    table.addCell(pdfc7);
                    pdfc8 = new PdfPCell(new Phrase("", font6));
                    pdfc8.setBorder(Rectangle.BOX);
                    table.addCell(pdfc8);

                    i++;
                }
            }
            document.add(table);
            Paragraph others = new Paragraph("\n\n Others \n\n", font21);
            vehiclePlate.setAlignment(Element.ALIGN_LEFT);
            document.add(others);
            PdfPTable table2 = new PdfPTable(3);
            table2.setWidthPercentage(100);

            PdfPCell numCell = new PdfPCell(new Phrase("#", font2));
            cell1.setBorder(Rectangle.BOX);
            table2.addCell(numCell);

            PdfPCell descriptionNum = new PdfPCell(new Phrase("DESCRIPTION", font2));
            cell.setBorder(Rectangle.BOX);
            table2.addCell(descriptionNum);

            PdfPCell verfication = new PdfPCell(new Phrase("Verfication", font2));
            cell.setBorder(Rectangle.BOX);
            table2.addCell(verfication);

            PdfPCell pdfc25;
            PdfPCell pdfc21;
            PdfPCell pdfc26;
            PdfPCell pdfc27;
            PdfPCell pdfc28;
            PdfPCell pdfc29;
            PdfPCell pdfc210;
            int x = 1;
            //DecimalFormat dcf = new DecimalFormat("###,###,###,###");
            for (Quotation ps : quotation) {
                if (ps.getBrokenCarPart().getCarsparepart() == null) {

                    pdfc21 = new PdfPCell(new Phrase(x + "", font6));
                    pdfc21.setBorder(Rectangle.BOX);
                    table2.addCell(pdfc21);

                    pdfc25 = new PdfPCell(new Phrase((ps.getBrokenCarPart().getCarsparepart() != null) ? ps.getBrokenCarPart().getCarsparepart().getName() : ps.getBrokenCarPart().getAdditionInfo().getDescription() + "", font6));
                    pdfc25.setBorder(Rectangle.BOX);
                    table2.addCell(pdfc25);

                    pdfc7 = new PdfPCell(new Phrase("", font6));
                    pdfc7.setBorder(Rectangle.BOX);
                    table2.addCell(pdfc7);

                    x++;
                }
            }
            document.add(table2);
            Paragraph p6 = new Paragraph("\n\n\n");
            p6.setAlignment(Element.ALIGN_LEFT);
            document.add(p6);

            Paragraph par = new Paragraph("Garage Name \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Signature", font21);
            par.setAlignment(Element.ALIGN_LEFT);
            document.add(par);

            Paragraph par2 = new Paragraph(this.billing.getGarage().getName() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ", font2);
            par2.setAlignment(Element.ALIGN_LEFT);
            document.add(par2);

            Paragraph par3 = new Paragraph("Expert Name \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Signature ", font21);
            par3.setAlignment(Element.ALIGN_LEFT);
            document.add(par3);

            Paragraph par4 = new Paragraph("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n ", font2);
            par4.setAlignment(Element.ALIGN_LEFT);
            document.add(par4);

            Paragraph par5 = new Paragraph("Client Name \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Signature  ", font21);
            par5.setAlignment(Element.ALIGN_LEFT);
            document.add(par5);

            Paragraph par6 = new Paragraph(this.billing.getVehicleDetails().getClient().getFname() + " " + this.billing.getVehicleDetails().getClient().getLname() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n ", font2);
            par6.setAlignment(Element.ALIGN_LEFT);
            document.add(par6);

            document.close();
            String fileName = "Contract_" + new Date().getTime() / (1000 * 3600 * 24);
            writePDFToResponse(context.getExternalContext(), baos, fileName);
            context.responseComplete();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank u for your submittion", null));
            return "";
        } catch (DocumentException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "" + e, ""));
            return null;
        }
    }

    private String writePDFToResponse(ExternalContext externalContext, ByteArrayOutputStream baos, String fileName) {
        try {
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf");
            externalContext.setResponseHeader("Expires", "0");
            externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            externalContext.setResponseHeader("Pragma", "public");
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
            externalContext.setResponseContentLength(baos.size());
            OutputStream out = externalContext.getResponseOutputStream();
            baos.writeTo(out);
            externalContext.responseFlushBuffer();
            return "dashboard.xhtml?faces-redirect=true";
        } catch (IOException e) {
            return null;
        }
    }

    public void winnerReadMore(Bidding bidding) {
        try {

            this.billing = new BiddingImpl().getModelWithMyHQL(new String[]{"bidId"},
                    new Object[]{bidding.getBidId()}, "from Bidding");
            this.billing = new BiddingImpl().getModelWithMyHQL(new String[]{"status", "isApproved", "vehicleDetailsId"},
                    new Object[]{"completed", true, billing.getVehicleDetails().getUuid()}, "from Bidding");
            this.quotation = new QuotationImpl().getOne(billing.getBidId());
            this.subTotal = 0;
            for (Quotation q : quotation) {
                subTotal += q.getPrice() * q.getBrokenCarPart().getQuantity();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public double calculatePercent(double money) {
        return (money * 18) / 100;
    }

    public String approve(String location) {
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
            insurance = new InsuranceImpl().getOne(loggedInUser.getUserId());
            this.list = new BiddingImpl().listOfBidding(insurance.getUuid());
            System.out.println("----------------" + list.size());
            System.out.println("here we go boss wanjye we");
//            allMagic();
        } catch (Exception e) {
            // new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            e.printStackTrace();
        }
        return location;
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

    public List<BrokenCarPart> getListOfCarPart() {
        return listOfCarPart;
    }

    public void setListOfCarPart(List<BrokenCarPart> listOfCarPart) {
        this.listOfCarPart = listOfCarPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Set<BrokenCarPart> getAddedBrokenCar() {
        return addedBrokenCar;
    }

    public void setAddedBrokenCar(Set<BrokenCarPart> addedBrokenCar) {
        this.addedBrokenCar = addedBrokenCar;
    }

    public BrokenCarPart getChoosenBrokenPart() {
        return choosenBrokenPart;
    }

    public void setChoosenBrokenPart(BrokenCarPart choosenBrokenPart) {
        this.choosenBrokenPart = choosenBrokenPart;
    }

    public String getChoosencareSpare() {
        return choosencareSpare;
    }

    public void setChoosencareSpare(String choosencareSpare) {
        this.choosencareSpare = choosencareSpare;
    }

    public List<GaragePayment> getAllTransaction() {
        return allTransaction;
    }

    public void setAllTransaction(List<GaragePayment> allTransaction) {
        this.allTransaction = allTransaction;
    }

    public List<AdditionalBrokenCarPart> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<AdditionalBrokenCarPart> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public List<AdditionalBrokenCarPart> getChoosenAdditionalInfo() {
        return choosenAdditionalInfo;
    }

    public void setChoosenAdditionalInfo(List<AdditionalBrokenCarPart> choosenAdditionalInfo) {
        this.choosenAdditionalInfo = choosenAdditionalInfo;
    }

    public List<VehicleDetail> getCarNeedAssignExpert() {
        return carNeedAssignExpert;
    }

    public void setCarNeedAssignExpert(List<VehicleDetail> carNeedAssignExpert) {
        this.carNeedAssignExpert = carNeedAssignExpert;
    }

    public String getGarageId() {
        return garageId;
    }

    public void setGarageId(String garageId) {
        this.garageId = garageId;
    }

    public double getEstimatedMarketValue() {
        return estimatedMarketValue;
    }

    public void setEstimatedMarketValue(double estimatedMarketValue) {
        this.estimatedMarketValue = estimatedMarketValue;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

}
