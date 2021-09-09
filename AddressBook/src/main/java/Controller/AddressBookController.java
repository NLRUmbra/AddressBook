/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.Address;
import Dao.AddressBookDao;
import Dao.AddressBookDaoException;
import UI.AddressBookView;
import UI.UserIO;
import UI.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Noah McElroy
 */
public class AddressBookController {
    private AddressBookView view;
    private UserIO io = new UserIOConsoleImpl();
    private AddressBookDao dao;

    public AddressBookController(AddressBookView view, AddressBookDao dao) {
        this.view = view;
        this.dao = dao;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
            while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    this.createAddress();
                    break;
                case 2:
                    this.removeAddress();
                    break;
                case 3:
                    this.findAddress();
                    break;
                case 4:
                    this.getAddressCount();
                    break;
                case 5:
                    this.listAddresses();
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
            
        }catch(AddressBookDaoException e){
            view.displayErrorMessage(e.getMessage());
        }

    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createAddress() throws AddressBookDaoException
    {
        view.displayCreateStudentBanner();//need to update this
        Address newAddress = view.getAddressInfo();
        dao.AddAddress(newAddress.getLastName(), newAddress);
        view.displayCreateSuccessBanner();//needs updating
    }
    
    private void listAddresses() throws AddressBookDaoException
    {
        view.displayDisplayAllBanner();//need to update banner
        List<Address> addressList = dao.ListAllAddresses();
        view.displayAddressList(addressList);//need to update view most likely
    }
    private void findAddress() throws AddressBookDaoException{
        view.displayDisplayStudentBanner();
        String lastName = view.getLastName();
        Address address = dao.FindAddress(lastName);
        view.displayAddress(address);//needs to be updated
    }
    private void getAddressCount() throws AddressBookDaoException{
        view.displayDisplayAllBanner();
        view.displayCount(dao.AddressCount());
    }
    
    private void removeAddress()throws AddressBookDaoException {
        view.displayRemoveStudentBanner();
        String lastName = view.getLastName();
        Address removedAddress = dao.DeleteAddress(lastName);
        view.displayRemoveResult(removedAddress);
    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
