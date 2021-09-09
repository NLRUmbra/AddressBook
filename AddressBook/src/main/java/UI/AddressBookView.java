/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DTO.Address;
import java.util.List;

/**
 *
 * @author Noah McElroy
 */
public class AddressBookView {
    private UserIO io;

    public AddressBookView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add Address");
        io.print("2. Delete Address");
        io.print("3. Find Address");
        io.print("4. List Address Count");
        io.print("5. List All Addresses");

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    public Address getAddressInfo() 
    {
    String firstName = io.readString("Please Enter Fist Name:");
    String lastName = io.readString("Please enter Last Name:");
    String streetAddress = io.readString("Please enter Street Address:");
    String city = io.readString("Please enter your city");
    String state = io.readString("Please enter your state");
    int zipcode = io.readInt("Please enter your zipcode");
    Address currentAddress = new Address(firstName,lastName,streetAddress,city,state,zipcode);
    return currentAddress;
}

    
    
    public void displayCreateStudentBanner() {
    io.print("=== Create Student ===");
}
    public void displayCreateSuccessBanner() {
    io.readString(
            "Student successfully created.  Please hit enter to continue");
}
    
    public void displayAddressList(List<Address> addressList) {
    for (Address currentAddress : addressList) {
        String addressInfo = String.format("%s %s\n%s\n %s, %s, %o",
              currentAddress.getFirstName(),
              currentAddress.getLastName(),
              currentAddress.getStreetAddress(),
              currentAddress.getCity(),
              currentAddress.getState());
              
        io.print(addressInfo);
    }
    io.readString("Please hit enter to continue.");
}
    public void displayDisplayAllBanner() {
    io.print("=== Display All Students ===");
}
    public void displayDisplayStudentBanner () {
    io.print("=== Display Student ===");
}

public String getLastName() {
    return io.readString("Please enter the last name.");
}

public void displayAddress(Address address) {
    if (address != null) {
        String addressInfo = String.format("%s %s\n%s\n %s, %s, %o",
              address.getFirstName(),
              address.getLastName(),
              address.getStreetAddress(),
              address.getCity(),
              address.getState());
              
        io.print(addressInfo);
    } else {
        io.print("No such address.");
    }
    io.readString("Please hit enter to continue.");
}

public void displayRemoveStudentBanner () {
    io.print("=== Remove Student ===");
}

public void displayRemoveResult(Address addressRecord) {
    if(addressRecord != null){
      io.print("Address successfully removed.");
    }else{
      io.print("No such address.");
    }
    io.readString("Please hit enter to continue.");
}
public void displayExitBanner() {
    io.print("Good Bye!!!");
}

public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}
public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
}

    public void displayCount(int AddressCount) {
        io.print("Total addresses  = " + AddressCount);
    }
}
