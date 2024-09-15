import java.util.*;
import java.io.*;
import java.awt.*;
/**
 * This class models a libary storing libary items.
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */
public class Library
{
    //private ArrayList<LibraryItem> itemList;
    // The array list called item list that stores objects from the library item class
    //private ArrayList<LibraryUser> userList; 
    // New field for storing library users
    
    private Map<String, LibraryUser> customerMap;
    // Map to store customers by user ID
    private Map<String, LibraryItem> itemMap;
   // Map to store items by item code
    private Map<String, LibraryReservation> libraryReservationMap;
    // Map to store library reservations by reservation number
    private int lastReservationNo;
    // Track the last reservation number assigned
    private Random randomGenerator; 
    // Field to generate random numbers
    private Set<String> usedUserIDs; 
    // Set to store used user IDs
    private Diary diary; 
    // New field for diary
    
    /**
     * Non default constructor for objects of class Library
     */
    public Library()
    {
       //itemList = new ArrayList<LibraryItem>();
       //userList = new ArrayList<LibraryUser>();
       
       // Initialise these fields
       customerMap = new HashMap<>();
       itemMap = new HashMap<>();
       libraryReservationMap = new HashMap<>();
       lastReservationNo = 0;
       randomGenerator = new Random();
       usedUserIDs = new HashSet<>(); 
       diary = new Diary(); 
    }

    // Getter
    /**
     * Getter method for random generator
     */
    public Random getRandomGenerator() {
        return randomGenerator;
    }
    
    // Setter
    /**
     * Setter method for random generator
     */
    public void setRandomGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
    
    // Methods related to reading and writing data
    /** 
     * Method to set directory using FileDialog
     */
    private String setDirectory() {
        // Create a file dialog to select the file
        FileDialog dialog = new FileDialog((Frame) null, "Select Item Data File", FileDialog.LOAD);
        dialog.setVisible(true);

        // Get the selected file
        String directory = dialog.getDirectory();
        String fileName = dialog.getFile();
        String filePath = directory + fileName;
        System.out.println("Selected file: " + filePath);

        return filePath;
    }
    
     /**
      * Method to read item data from a file
      */ 
    public void readData() {
        String filePath = setDirectory(); // Call setDirectory method to obtain filepath

        try {
            // Set up scanner to read from the selected file
            Scanner fileScanner = new Scanner(new File(filePath));
            // Local variable to keep track of type of data
            String typeOfData = "";
            
            // Read each line from the file
            while (fileScanner.hasNextLine()) {
                String lineOfText = fileScanner.nextLine().trim(); // Remove leading and trailing spaces
                if (!lineOfText.startsWith("//") && !lineOfText.isEmpty()) { // Ignore comments and blank lines
                    if (lineOfText.startsWith("[")) {   // Check if line is a flag
                        typeOfData = lineOfText; // Update typeOfData
                    } else {
                        // Create a scanner for the line
                        Scanner lineScanner = new Scanner(lineOfText);
                        lineScanner.useDelimiter("\\s*,\\s*"); // Comma separated data with optional spaces

                        // Deal with the data appropriately based on typeOfData
                        switch (typeOfData) {
                            case "[Book data]":
                                Book book = new Book();
                                book.readData(lineScanner);
                                storeItem(book);
                                break;
                            case "[periodical data]":
                                Periodical periodical = new Periodical();
                                periodical.readData(lineScanner);
                                storeItem(periodical);
                                break;
                            case "[CD data]":
                                CD cd = new CD();
                                cd.readData(lineScanner);
                                storeItem(cd);
                                break;
                            case "[DVD data]":
                                DVD dvd = new DVD();
                                dvd.readData(lineScanner);
                                storeItem(dvd);
                                break;
                            case "[Library User Data]": // Adding case for reading user data
                                LibraryUser user = new LibraryUser();
                                user.readData(lineScanner);
                                storeUser(user);
                                break;
                            default:
                                System.out.println("Unknown type of data: " + typeOfData);
                        }

                        // Close the scanner for this line
                        lineScanner.close();
                    }
                }
            }

            // Close the scanner for the file
            fileScanner.close();
        }catch (IOException e)
         {
             System.err.println("\n\n*** There is an IOException ***");
             e.printStackTrace();
             System.err.println("\n*** Unexpected error -- aborting program **");
             System.exit(1);
        }    
    }
    
     /**
      * Method to write user data to a text file
      */ 
    public void writeUserData() {
        String filepath = setDirectory(); // Call setDirectory method to obtain filepath

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filepath));

            // Iterate through each user and delegate the writing to LibraryUser class
        for (Map.Entry<String, LibraryUser> entry : customerMap.entrySet()) {
            LibraryUser user = entry.getValue();
            user.writeData(writer);
        }
        
        writer.close(); // Close the PrintWriter
        System.out.println("User data has been written to " + filepath);
        } catch (IOException e) {
            System.err.println("Error writing user data to file: " + e.getMessage());
        }
    }
    
    /**
     *  Method to write library reservation data to a text file
     */
        public void writeLibraryReservationData() {  
        String filepath = setDirectory(); // Call setDirectory method to obtain filepath

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filepath));

            for (LibraryReservation reservation : libraryReservationMap.values()) {
                reservation.writeReservationData(writer);
            }

            writer.close(); // Close the PrintWriter
            System.out.println("User data has been written to " + filepath);
        } catch (IOException e) {
            System.err.println("Error writing user data to file: " + e.getMessage());
        }
    }
    
    /**
     * Method to read library reservation data from a text file
     */
    public void readLibraryReservationData() {
        String filePath = setDirectory(); // Call setDirectory method to obtain filepath

        try {
            // Set up scanner to read from the selected file
            Scanner fileScanner = new Scanner(new File(filePath));
            
            // Read each line from the file
            while (fileScanner.hasNextLine()) {
                String lineOfText = fileScanner.nextLine().trim(); // Remove leading and trailing spaces
                Scanner lineScanner = new Scanner(lineOfText);

                LibraryReservation reservation = new LibraryReservation();
                reservation.readReservationData(lineScanner);
                libraryReservationMap.put(reservation.getReservationNo(), reservation);
            }

            // Close the scanner for the file
            fileScanner.close();
        }catch (IOException e)
         {
             System.err.println("\n\n*** There is an IOException ***");
             e.printStackTrace();
             System.err.println("\n*** Unexpected error -- aborting program **");
             System.exit(1);
        }    
    }
         
    // Storing Items
    /** 
     * Methods related to managing items
     */
    public void storeItem(LibraryItem item) {
        //itemList.add(item);
        itemMap.put(item.getItemCode(), item);
    }
    
    /** 
     * Methods related to managing users
     */
    public void storeUser(LibraryUser user) {
        if (user.getUserID().equals("unknown")) {
            String newUserID = generateUniqueUserID("AB-", 6); // Generate a new unique user ID
            user.setUserID(newUserID); // Set the generated user ID
        }
        //userList.add(user); // Add the user to the list of library users
        customerMap.put(user.getUserID(), user);
    }
    
    /**
     * Method that will make a library reservation
     */
    public boolean makeLibraryReservation(String userID, String itemCode, String startDate, int noOfDays) {
        // Check if the user ID exists in the library
        if (!itemMap.containsKey(itemCode)) {
            System.out.println("Item with code " + itemCode + " does not exist in the library.");
            return false;
        }

        if (!customerMap.containsKey(userID)) {
            System.out.println("User with ID " + userID + " does not exist in the library records.");
            return false;
        }

        // Convert startDate to Date object
        Date reservationStartDate = DateUtil.convertStringToDate(startDate);

        // Check if the item is already reserved for all or part of the reservation period
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reservationStartDate);
    
        for (int day = 0; day < noOfDays; day++) {
            // Check if the current date is already reserved
            if (libraryReservationMap.containsValue(DateUtil.convertDateToShortString(calendar.getTime()))) {
                System.out.println("Item with code " + itemCode + " is already reserved for the specified period.");
                return false;
            }
        
            // Increment the date by one day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    
        // Generate reservation number
        String reservationNo = generateReservationNo();

        // Create LibraryReservation object
        LibraryReservation reservation = new LibraryReservation(reservationNo, itemCode, userID, startDate, noOfDays);

        // Store reservation
        storeLibraryReservation(reservation);

        System.out.println("Reservation made successfully. Reservation No: " + reservationNo);
        return true;
    }
    
    /**
     * Method to delete a library reservation.
     */
    public boolean deleteLibraryReservation(String reservationNo) {
        // Check if the reservation exists
        if (!libraryReservationMap.containsKey(reservationNo)) {
            System.out.println("Reservation with number " + reservationNo + " does not exist.");
            return false;
        }

        // Get the reservation
        LibraryReservation reservation = libraryReservationMap.get(reservationNo);

        // Delete the reservation from the diary
        diary.deleteReservation(reservation);

        // Remove the reservation from the reservation map
        libraryReservationMap.remove(reservationNo);

        System.out.println("Reservation with number " + reservationNo + " successfully deleted.");
        return true;
    }
    
    /**
     * Method to store a library reservation
     */
     public void storeLibraryReservation(LibraryReservation reservation) {
        libraryReservationMap.put(reservation.getReservationNo(), reservation);
        diary.addReservation(reservation); // Add reservation to the diary
    }
    
    /**
     * Method to retrieve a library reservation by reservation number.
     */
    public LibraryReservation getLibraryReservation(String reservationNo) {
        return libraryReservationMap.get(reservationNo);
    }
    
    //Other Methods
     /**
      * Method to generate a user ID
      */
    private String generateUserID(String prefix, int numDigits) {
        StringBuilder userID = new StringBuilder(prefix);
        for (int i = 0; i < numDigits; i++) {
            userID.append(randomGenerator.nextInt(10)); // Append a random digit (0-9)
        }
        return userID.toString();
    }
    
    /** 
     * Method to generate a unique userID
     */
    private String generateUniqueUserID(String prefix, int numDigits) {
        String userID = generateUserID(prefix, numDigits);
        while (usedUserIDs.contains(userID)) {
            userID = generateUserID(prefix, numDigits);
        }
        usedUserIDs.add(userID);
        return userID;
    }
      
    /** 
     * Method to print details of all items
     */
    public void printAll() {
        System.out.println("Items:");
        for (LibraryItem item : itemMap.values()) {
            item.printDetails();
        }
        
        System.out.println("Users:");
        for (LibraryUser user : customerMap.values()) {
            user.printDetails();
        }
    }    
    
    /**
     * Method to generate a reservation number
     */
    private String generateReservationNo() {
        lastReservationNo++;
        String paddedNumber = String.format("%06d", lastReservationNo);
        return paddedNumber;
    }
        
    /**
     * Method to print details of all library reservations
     */
     public void printLibraryReservations() {
        for (LibraryReservation reservation : libraryReservationMap.values()) {
            reservation.printDetails();
            System.out.println();
        }
    }
    
    /**
     * Method to print diary entries for a specified period
     */ 
    public void printDiaryEntries(Date startDate, Date endDate) {
        diary.printEntries(startDate, endDate);
    }
    
    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        Library library = new Library();
        library.readData();
        library.printAll();
        library.writeUserData();
    }
}