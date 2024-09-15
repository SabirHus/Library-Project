import java.util.*;
import java.io.*;
/**
 * This class models a libary reservation system
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */
public class LibraryReservation {
    private String reservationNo;
    private String itemCode;
    private String userID;
    private Date startDate;
    private int noOfDays;

    public LibraryReservation(String reservationNo, String itemCode, String userID, String startDate, int noOfDays) {
        this.reservationNo = reservationNo;
        this.itemCode = itemCode;
        this.userID = userID;
        this.startDate = DateUtil.convertStringToDate(startDate);
        this.noOfDays = noOfDays;
    }
    
    public LibraryReservation()
    {
        reservationNo = null;
        itemCode = null;
        userID = null;
        startDate = null;
        noOfDays = 0;
    }

    // Getters
    public String getReservationNo() {
        return reservationNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getUserID() {
        return userID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    // Setters
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setStartDate(String startDate) {
        this.startDate = DateUtil.convertStringToDate(startDate);
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }
    
    //Other Methods
    /**
     * toString method
     */ 
    @Override
    public String toString() {
        return "Reservation No: " + reservationNo + ", User ID: " + userID + ", Item Code: " + itemCode;
    }
    
    /**
     * Print Detail Method
     */
    public void printDetails() {
        System.out.println("Reservation No: " + reservationNo);
        System.out.println("Item Code: " + itemCode);
        System.out.println("User ID: " + userID);
        System.out.println("Start Date: " + DateUtil.convertDateToShortString(startDate));
        System.out.println("Number of Days: " + noOfDays);
    }
    
    /**
     * Write Data Method
     */ 
    public void writeReservationData(PrintWriter writer) {
        writer.println(reservationNo + ", " + itemCode + ", " + userID + ", " + DateUtil.convertDateToShortString(startDate) + ", " + noOfDays);
    }

    // Read Data Method
    public void readReservationData(Scanner scanner) {
        reservationNo = scanner.next();
        itemCode = scanner.next();
        userID = scanner.next();
        String startDateString = scanner.next(); // Read as string
        startDate = DateUtil.convertStringToDate(startDateString); // Convert to Date
        noOfDays = scanner.nextInt();
    }
}