/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DTO.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Noah McElroy
 */
public class AddressBookDaoFileImpl implements AddressBookDao {
    Map<String, Address> addresses = new HashMap<>();
    public static final String Book_FILE = "Book.txt";
    public static final String DELIMITER = "::";
    @Override
    public Address AddAddress(String lastName,Address address) throws AddressBookDaoException {
            loadBook();
            Address newAddress = addresses.put(lastName, address);
            writeBook();
            return newAddress;
    }

    @Override
    public Address DeleteAddress(String lastName) throws AddressBookDaoException {
            loadBook();
            Address removedAddress = addresses.remove(lastName);
            writeBook();
            return removedAddress;
    }

    @Override
    public Address FindAddress(String lastName) throws AddressBookDaoException {
            loadBook();
            return addresses.get(lastName);
    }

    @Override
    public int AddressCount() throws AddressBookDaoException {
        loadBook();
        return addresses.values().size();
    }

    @Override
    public List<Address> ListAllAddresses() throws AddressBookDaoException {
        loadBook();
        return new ArrayList(addresses.values());
    }
    private Address unmarshallAddress(String addressAsText){
  
        String[] addressTokens = addressAsText.split(DELIMITER);
        Address addressFromFile = new Address(addressTokens[0],addressTokens[1],addressTokens[2],addressTokens[3],addressTokens[4],Integer.parseInt(addressTokens[5]));
        return addressFromFile;
    
    }
    private String marshallAddress(Address aAddress){

    String addressAsText = aAddress.getFirstName() + DELIMITER;

    addressAsText += aAddress.getLastName() + DELIMITER;

    addressAsText += aAddress.getStreetAddress() + DELIMITER;

    addressAsText += aAddress.getCity() + DELIMITER;
    addressAsText += aAddress.getState() + DELIMITER;
    addressAsText += aAddress.getZipcode();

    
        return addressAsText;
    } 
    private void loadBook() throws AddressBookDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(Book_FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookDaoException(
                    "-_- Could not load book data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Address currentAddress;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentAddress = unmarshallAddress(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            addresses.put(currentAddress.getLastName(), currentAddress);
        }
        // close scanner
        scanner.close();
    }
    private void writeBook() throws AddressBookDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(Book_FILE));
        } catch (IOException e) {
            throw new AddressBookDaoException(
                    "Could not save address data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String addressAsText;
        List<Address> addressList = this.ListAllAddresses();
        for (Address currentAddress : addressList) {
            // turn a Student into a String
            addressAsText = marshallAddress(currentAddress);
            // write the Student object to the file
            out.println(addressAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
