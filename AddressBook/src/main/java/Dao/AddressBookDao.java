/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DTO.Address;
import java.util.List;

/**
 *
 * @author Noah McElroy
 */
public interface AddressBookDao  {
    Address AddAddress(String lastName,Address address) throws AddressBookDaoException;
    
    Address DeleteAddress(String lastName) throws AddressBookDaoException;
    
    Address FindAddress(String lastName) throws AddressBookDaoException;
    
    int AddressCount() throws AddressBookDaoException;
    
    List<Address> ListAllAddresses() throws AddressBookDaoException;
    
}
