/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.common.SendEmail;
import com.ebaza.tech.dao.generic.CompledCarDao;
import com.ebaza.tech.dao.impl.BiddingImpl;
import com.ebaza.tech.dao.impl.CompletedCarImpl;
import com.ebaza.tech.dao.impl.GarageImpl;
import com.ebaza.tech.dao.impl.GarageOwnerImpl;
import com.ebaza.tech.dao.impl.LoginImpl;
import com.ebaza.tech.dao.impl.QuotationImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.dao.impl.VehicleDetailsImpl;
import com.ebaza.tech.dao.impl.VehicleImageImpl;
import com.ebaza.tech.domain.ApprovedTemplate;
import com.ebaza.tech.domain.Bidding;
import com.ebaza.tech.domain.CompletedCar;
import com.ebaza.tech.domain.Garage;
import com.ebaza.tech.domain.GarageOwner;
import com.ebaza.tech.domain.InsuranceCompany;
import com.ebaza.tech.domain.Quotation;
import com.ebaza.tech.domain.User;
import com.ebaza.tech.domain.VehicleDetail;
import com.ebaza.tech.domain.VehicleImage;
import com.ebaza.tech.dto.GaragePayment;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.json.JSONArray;

/**
 *
 * @author Godwin
 */
@ManagedBean
@SessionScoped
public class GarageController implements DbConstant, Serializable {

    private Garage garage = new Garage();
    private User user = new User();
    private GarageOwner garageOwner = new GarageOwner();
    private List<Garage> garageList = new GarageImpl().getNotApproved();
    private String searchKey;
    private GarageImpl garageImpl = new GarageImpl();
    @ManagedProperty(value = "#{loginController.loggedInUser}")
    private User user2;
    private List<VehicleImage> images = new VehicleImageImpl().allsWithGroupBy("vimageId");
    private List<Quotation> approvedList = new ArrayList<>();
    private User loggedInUser;
    private List<ApprovedTemplate> allApproved = new ArrayList<>();
    private List<Quotation> listOfBid = new ArrayList<>();
    private List<CompletedCar> listOfCompletedCar = new ArrayList<>();
    private CompletedCar completedCar = new CompletedCar();
    private List<Garage> listAllGarage = new ArrayList<>();
    private List<VehicleDetail> listNewVehicleDetails;
    private List<VehicleDetail> listOfExpectise = new ArrayList<>();
    private List<GaragePayment> notPaidList;
    private List<GaragePayment> AllTransaction;
    private List<GaragePayment> notPaidForCombine;
    private Double notpaidAmount;
    private String username = "iyaturemye";
    private String apiKey = "6cee4a31d2456e8a28d3018acdf71ca36ca8b229448cb308ce31cd0a688063dc";
    private String status = "test 2019";
    private String anotherValue = "hello";
    private String keyElement;

    @PostConstruct
    public void init() {
        doIt();
    }

    public void searchElement() {
        AllTransaction = new ArrayList<>();
        GarageOwner owners = new GarageOwnerImpl().getOwner(loggedInUser.getUserId());
        Garage loggedInGarage = new GarageImpl().getOne(owners.getOwnerId());
        for (GaragePayment x : new CompledCarDao().getAllTransaction(loggedInGarage.getGarageId())) {
            if (x.getPurchasingOrderNum().toString().startsWith(this.keyElement)) {
                AllTransaction.add(x);
            }
        }
    }

    private void doIt() {
        try {
            listAllGarage = garageImpl.getAll();
            if (user2 != null) {
                if (user2.getUserType().equalsIgnoreCase("garage")) {
                    GarageOwner owner = new GarageOwnerImpl().getOwner(user2.getUserId());
                    approvedList = new QuotationImpl().getApprovedBidd(new GarageImpl().getOne(owner.getOwnerId()).getGarageId());
                    loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoggedIn");
                    System.out.println("here we go boss wanjye");
                    if (loggedInUser != null) {
                        GarageOwner owners = new GarageOwnerImpl().getOwner(loggedInUser.getUserId());
                        Garage loggedInGarage = new GarageImpl().getOne(owners.getOwnerId());
                        this.listOfBid = new QuotationImpl().getGarageBid(loggedInGarage.getGarageId());

                        this.allApproved = new BiddingImpl().getApprovedBd(loggedInGarage.getGarageId());
                        this.listNewVehicleDetails = new VehicleDetailsImpl().expertiseGarage(loggedInGarage.getGarageId(), "new");
                        listOfExpectise = new VehicleDetailsImpl().expertiseGarage(loggedInGarage.getGarageId(), "not New");
                        this.notPaidList = new CompledCarDao().getAllNotPaid(loggedInGarage.getGarageId());
                        this.AllTransaction = new CompledCarDao().getAllTransaction(loggedInGarage.getGarageId());
                        this.notPaidForCombine = new CompledCarDao().notPaidInTotalForGarage(loggedInGarage.getGarageId());
                        this.notpaidAmount = 0.0;
                        for (GaragePayment x : notPaidList) {
                            notpaidAmount += x.getTotalAmount() + (x.getTotalAmount() * 18) / 100;
                        }

                    }
                }

            }
        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
        }

    }

    public void changeGarageStatus(User user) {

        String newStatus = (user.getStatus().equalsIgnoreCase("block") ? "active" : "block");
        user.setStatus(newStatus);
        User userd = new UserImpl().updateInfo(user);
        if (userd == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went Wrong please", null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Garage Have been successfull " + (newStatus.equalsIgnoreCase("active") ? "activated" : "blocked"), null));
            listAllGarage = garageImpl.getAll();
        }
    }

    public String completeWork(String biddingId) {
        try {
            Bidding bid = new Bidding();
            bid.setBidId(biddingId);
            this.completedCar.setBidding(bid);
            this.completedCar = new CompletedCarImpl().create(completedCar);
            Bidding bid2 = new BiddingImpl().getOne(biddingId);
            bid2.setStatus("completed");
            new BiddingImpl().updateInfo(bid2);
            VehicleDetail vd = bid2.getVehicleDetails();
            vd.setStatus("completed");
            new VehicleDetailsImpl().updateInfo(vd);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank u for your submition you will be notified through your phone number once their have paid you", null));
            listAllGarage = garageImpl.getAll();
            doIt();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you for your hard work ", DEPID));
            return "PurchaseOrderPreview.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            return null;
        }

    }

    public void respondToApproved(String uuid) {
        System.out.println("hello boss we");
    }

    public void search() {
        try {
            if (searchKey == "" || searchKey == null || searchKey.isEmpty()) {
                garageList = new GarageImpl().getAll();
            } else {
                this.garageList = garageImpl.searchGarage(searchKey, "name");
            }
        } catch (Exception e) {
        }
    }

    public boolean approveGrage(Garage garage) {
        try {
            garage.setStatus("approved");
            Garage g = garageImpl.updateInfo(garage);
            System.out.println(garage.getStatus());
            garage.getGarageOwner().getUser().setStatus("active");
            User user = new UserImpl().updateInfo(garage.getGarageOwner().getUser());
            StringBuilder strbuilder = new StringBuilder();
            strbuilder.append("Dear Mr/ms");
            strbuilder.append("Your Request have been successfull Approved\n");
            strbuilder.append("Now you can start bidding for any car\n");
            strbuilder.append("Thank you \n");
            strbuilder.append("Best Reggard \n");
//            new SendEmail().sendEmail(user.getUserName(), "Request Confirmation", strbuilder.toString());
            if (g == null) {
                return false;
            }
            return true;
        } catch (Exception e) {

            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            return false;
        }

    }

    public void approve(Garage garage) {
        try {
            boolean i = this.approveGrage(garage);
            AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey);
            String msg = "Your Request have been successfull approved your password is garage now you can start bidding thank you ";
            JSONArray results = gateway.sendMessage(garage.getGarageOwner().getPhoneNumber().replaceAll(" ", ""), msg);
            FacesContext context = FacesContext.getCurrentInstance();
            if (i) {
                garageList = new GarageImpl().getAll();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Have been successfull Approved", null));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "There is an error please try again", null));
            }
        } catch (Exception e) {

            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
        }
    }

    public void create() {

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            User us = new UserImpl().getModelWithMyHQL(new String[]{USERNAME}, new Object[]{
                user.getUserName()}, SELECT_USERS);
            if (us == null) {
                this.user.setPassword(new LoginImpl().criptPassword("garage"));
                this.user.setUserType("garage");
                this.user.setStatus("blocked");
                Garage g = new GarageImpl().getModelWithMyHQL(new String[]{"name"}, new Object[]{
                    this.garage.getName()}, "from Garage");
                if (g == null) {
                    User user = new UserImpl().saveIntable(this.user);
                    this.garageOwner.setUser(user);
                    this.garage.setGarageOwner(garageOwner);
                    GarageOwner gOwner = new GarageOwnerImpl().create(garageOwner);
                    Garage g2 = new GarageImpl().create(this.garage);
                    if (gOwner != null && g2 != null) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank u for your registration you will be notified when your requiest is approved", null));
                        this.garage = new Garage();
                        this.user = new User();
                        this.garageOwner = new GarageOwner();
                    } else {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "There was an error please check your internet Connection and try again", null));
                    }
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Garage Name is Already registered please make sure you write name correct", null));
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "E-mail Address  " + us.getUserName() + " Already Used", null));
            }
        } catch (Exception e) {

            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
        }

    }

    public void notPaidReport() {
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
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\images");
            path = path.substring(0, path.indexOf("\\build"));
            path = path + "\\web\\layout\\image\\logo3.png";
            Image image = Image.getInstance(path);
            image.scaleAbsolute(70, 50);
            image.setAlignment(Element.ALIGN_LEFT);
            Paragraph title = new Paragraph();
            //BEGIN page
            title.add(image);
            document.add(title);

            Font font0 = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL);
            Font font1 = new Font(Font.TIMES_ROMAN, 9, Font.ITALIC, new Color(90, 255, 20));
            Font font2 = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL, new Color(0, 0, 0));
            Font font21 = new Font(Font.TIMES_ROMAN, 9, Font.UNDERLINE, new Color(0, 0, 0));
            Font font5 = new Font(Font.TIMES_ROMAN, 10, Font.ITALIC, new Color(0, 0, 0));
            Font colorFont = new Font(Font.TIMES_ROMAN, 13, Font.BOLD, new Color(0, 0, 0));
            Font font6 = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL);
            Font rowTitleFont = new Font(Font.TIMES_ROMAN, 11, Font.BOLD, new Color(0, 0, 0));
            document.add(new Paragraph("\n"));
            Paragraph dateP = new Paragraph("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "\n", font2);
            dateP.setAlignment(Element.ALIGN_LEFT);
            document.add(dateP);
            GarageOwner owners = new GarageOwnerImpl().getOwner(loggedInUser.getUserId());
            Garage loggedInGarage = new GarageImpl().getOne(owners.getOwnerId());
            Paragraph garageP = new Paragraph("Garage Name :" + loggedInGarage.getName() + "\n", font2);
            garageP.setAlignment(Element.ALIGN_LEFT);
            document.add(garageP);
            Paragraph locationP = new Paragraph("Location :" + loggedInGarage.getLocation() + "\n", font2);
            locationP.setAlignment(Element.ALIGN_LEFT);
            document.add(locationP);
            Paragraph titlep = new Paragraph("Not paid Report\n\n", colorFont);
            titlep.setAlignment(Element.ALIGN_CENTER);
            document.add(titlep);

            // tables 
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);

            PdfPCell cell1 = new PdfPCell(new Phrase("#", rowTitleFont));
            cell1.setBorder(Rectangle.BOX);
            table.addCell(cell1);

            PdfPCell cell = new PdfPCell(new Phrase("INSURANCE NAME", rowTitleFont));
            cell.setBorder(Rectangle.BOX);
            table.addCell(cell);

            PdfPCell cell3 = new PdfPCell(new Phrase("AMOUNT", rowTitleFont));
            cell3.setBorder(Rectangle.BOX);
            table.addCell(cell3);

            PdfPCell pdfc5;
            PdfPCell pdfc1;
            PdfPCell pdfc6;
            PdfPCell pdfc7;
            PdfPCell pdfc8;
            PdfPCell pdfc9;
            PdfPCell pdfc10;
            int i = 1;
            DecimalFormat dcf = new DecimalFormat("###,###,###,###");
            double total = 0.0;
            for (GaragePayment ps : notPaidForCombine) {
                pdfc1 = new PdfPCell(new Phrase(i + "", font6));
                pdfc1.setBorder(Rectangle.BOX);
                table.addCell(pdfc1);

                pdfc5 = new PdfPCell(new Phrase(ps.getInsuranceName() + "", font6));
                pdfc5.setBorder(Rectangle.BOX);
                table.addCell(pdfc5);
                double subTotal = ps.getTotalAmount() + ((ps.getTotalAmount() * 18) / 100);
                pdfc7 = new PdfPCell(new Phrase(dcf.format(subTotal) + " ", font6));
                pdfc7.setBorder(Rectangle.BOX);
                table.addCell(pdfc7);
                total += subTotal;
                i++;
            }
            document.add(table);
            Paragraph subTotalp = new Paragraph("Total :" + dcf.format(total) + "  FRW \n", rowTitleFont);
            subTotalp.setAlignment(Element.ALIGN_RIGHT);
            document.add(subTotalp);
            document.close();

            String fileName = "Report_" + new Date().getTime() / (1000 * 3600 * 24);
            writePDFToResponse(context.getExternalContext(), baos, fileName);
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String generatePdf(Bidding bid, InsuranceCompany insurance, List<Quotation> quotation, double subTotal, double tax, double totalAmount) throws FileNotFoundException, DocumentException, BadElementException, IOException, Exception {
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

            Font font0 = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL);
            Font font1 = new Font(Font.TIMES_ROMAN, 9, Font.ITALIC, new Color(90, 255, 20));
            Font font2 = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL, new Color(0, 0, 0));
            Font font21 = new Font(Font.TIMES_ROMAN, 9, Font.UNDERLINE, new Color(0, 0, 0));
            Font font5 = new Font(Font.TIMES_ROMAN, 10, Font.ITALIC, new Color(0, 0, 0));
            Font colorFont = new Font(Font.TIMES_ROMAN, 13, Font.BOLD, new Color(0, 0, 0));
            Font font6 = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL);
            document.add(new Paragraph("\n"));
            Paragraph p2 = new Paragraph("PURCHASE ORDER" + "\n\n", colorFont);
            p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p2);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            PdfPTable tablep = new PdfPTable(5);
            tablep.setWidthPercentage(100);

            PdfPCell cell10 = new PdfPCell(new Phrase(bid.getGarage().getName().toUpperCase(), font2));
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

            PdfPCell cell41 = new PdfPCell(new Phrase("Date: " + sdf.format(bid.getCreateAt()), font2));
            cell41.setBorder(Rectangle.NO_BORDER);
            tablep.addCell(cell41);
            document.add(tablep);
//            Paragraph p6 = new Paragraph(bid.getGarage().getName().toUpperCase() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Date:" + sdf.format(bid.getCreateAt()) + "\n", font0);
//            p6.setAlignment(Element.ALIGN_LEFT);
//            document.add(p6);
            Paragraph p8 = new Paragraph("Purchasing Order Number(#): " + this.completedCar.getPurchaseOrdernum(), font0);
            p8.setAlignment(Element.ALIGN_LEFT);
            document.add(p8);
            PdfPTable tablep1 = new PdfPTable(5);
            tablep1.setWidthPercentage(100);

            PdfPCell cell101 = new PdfPCell(new Phrase("Insurance", font21));
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

            PdfPCell cell102 = new PdfPCell(new Phrase("Insurance Name", font2));
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

            PdfPCell cell4112 = new PdfPCell(new Phrase(bid.getVehicleDetails().getVehicle().getName().toUpperCase(), font2));
            cell4112.setBorder(Rectangle.NO_BORDER);
            tablep12.addCell(cell4112);
            document.add(tablep12);

            Paragraph vehicle = new Paragraph("Policy Number " + bid.getVehicleDetails().getVehicle().getPolicyNumber().toUpperCase() + "\n", font0);
            vehicle.setAlignment(Element.ALIGN_RIGHT);
            document.add(vehicle);

            Paragraph vehiclePlate = new Paragraph("Plate Number " + bid.getVehicleDetails().getVehicle().getPlateNum() + "\n\n", font0);
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

            PdfPCell cell3 = new PdfPCell(new Phrase("UNIT PRICE", font2));
            cell3.setBorder(Rectangle.BOX);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Phrase("TOTAL PRICE", font2));
            cell4.setBorder(Rectangle.BOX);
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
                pdfc1 = new PdfPCell(new Phrase(i + "", font6));
                pdfc1.setBorder(Rectangle.BOX);
                table.addCell(pdfc1);

                pdfc5 = new PdfPCell(new Phrase(ps.getJobDescription() + "", font6));
                pdfc5.setBorder(Rectangle.BOX);
                table.addCell(pdfc5);

                pdfc6 = new PdfPCell(new Phrase(ps.getQuantity() + "", font6));
                pdfc6.setBorder(Rectangle.BOX);
                table.addCell(pdfc6);

                pdfc7 = new PdfPCell(new Phrase(dcf.format(ps.getPrice()) + " ", font6));
                pdfc7.setBorder(Rectangle.BOX);
                table.addCell(pdfc7);

                pdfc8 = new PdfPCell(new Phrase(dcf.format(ps.getPrice() * ps.getQuantity()), font6));
                pdfc8.setBorder(Rectangle.BOX);
                table.addCell(pdfc8);
                i++;
            }
            document.add(table);
            Paragraph subTotalp = new Paragraph("SubTotal:" + dcf.format(subTotal) + "\n", font0);
            subTotalp.setAlignment(Element.ALIGN_RIGHT);
            document.add(subTotalp);
            Paragraph taxp = new Paragraph("Tax: " + dcf.format(tax) + "\n", font0);
            taxp.setAlignment(Element.ALIGN_RIGHT);
            document.add(taxp);
            Paragraph total = new Paragraph("Total: " + dcf.format(totalAmount) + "\n", font0);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);
            Paragraph par = new Paragraph("\n\nPrinted On: " + sdf.format(new Date()), font1);
            par.setAlignment(Element.ALIGN_LEFT);
            document.close();
            System.out.println("here we go end");
            String fileName = "Report_" + new Date().getTime() / (1000 * 3600 * 24);
            writePDFToResponse(context.getExternalContext(), baos, fileName);
            System.out.println("here we go end 1");
            context.responseComplete();
            System.out.println("here we go end 2");
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
            System.out.println("here we go boss we---------------");
            return "dashboard.xhtml?faces-redirect=true";
        } catch (IOException e) {
            return null;
        }
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GarageOwner getGarageOwner() {
        return garageOwner;
    }

    public void setGarageOwner(GarageOwner garageOwner) {
        this.garageOwner = garageOwner;
    }

    public List<Garage> getGarageList() {
        return garageList;
    }

    public void setGarageList(List<Garage> garageList) {
        this.garageList = garageList;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public List<VehicleImage> getImages() {
        return images;
    }

    public void setImages(List<VehicleImage> images) {
        this.images = images;
    }

    public GarageImpl getGarageImpl() {
        return garageImpl;
    }

    public void setGarageImpl(GarageImpl garageImpl) {
        this.garageImpl = garageImpl;
    }

    public List<Quotation> getApprovedList() {
        return approvedList;
    }

    public void setApprovedList(List<Quotation> approvedList) {
        this.approvedList = approvedList;
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

    public List<Quotation> getListOfBid() {
        return listOfBid;
    }

    public void setListOfBid(List<Quotation> listOfBid) {
        this.listOfBid = listOfBid;
    }

    public List<CompletedCar> getListOfCompletedCar() {
        return listOfCompletedCar;
    }

    public void setListOfCompletedCar(List<CompletedCar> listOfCompletedCar) {
        this.listOfCompletedCar = listOfCompletedCar;
    }

    public CompletedCar getCompletedCar() {
        return completedCar;
    }

    public void setCompletedCar(CompletedCar completedCar) {
        this.completedCar = completedCar;
    }

    public List<Garage> getListAllGarage() {
        return listAllGarage;
    }

    public void setListAllGarage(List<Garage> listAllGarage) {
        this.listAllGarage = listAllGarage;
    }

    public List<VehicleDetail> getListNewVehicleDetails() {
        return listNewVehicleDetails;
    }

    public void setListNewVehicleDetails(List<VehicleDetail> listNewVehicleDetails) {
        this.listNewVehicleDetails = listNewVehicleDetails;
    }

    public List<VehicleDetail> getListOfExpectise() {
        return listOfExpectise;
    }

    public void setListOfExpectise(List<VehicleDetail> listOfExpectise) {
        this.listOfExpectise = listOfExpectise;
    }

    public List<GaragePayment> getNotPaidList() {
        return notPaidList;
    }

    public void setNotPaidList(List<GaragePayment> notPaidList) {
        this.notPaidList = notPaidList;
    }

    public Double getNotpaidAmount() {
        return notpaidAmount;
    }

    public void setNotpaidAmount(Double notpaidAmount) {
        this.notpaidAmount = notpaidAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnotherValue() {
        return anotherValue;
    }

    public void setAnotherValue(String anotherValue) {
        this.anotherValue = anotherValue;
    }

    public List<GaragePayment> getAllTransaction() {
        return AllTransaction;
    }

    public void setAllTransaction(List<GaragePayment> AllTransaction) {
        this.AllTransaction = AllTransaction;
    }

    public List<GaragePayment> getNotPaidForCombine() {
        return notPaidForCombine;
    }

    public void setNotPaidForCombine(List<GaragePayment> notPaidForCombine) {
        this.notPaidForCombine = notPaidForCombine;
    }

    public String getKeyElement() {
        return keyElement;
    }

    public void setKeyElement(String keyElement) {
        this.keyElement = keyElement;
    }

}
