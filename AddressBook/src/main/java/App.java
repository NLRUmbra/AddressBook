
import Controller.AddressBookController;
import Dao.AddressBookDao;
import Dao.AddressBookDaoFileImpl;
import UI.AddressBookView;
import UI.UserIO;
import UI.UserIOConsoleImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noah McElroy
 */
public class App {
    public static void main(String[] args){
        UserIO myIo = new UserIOConsoleImpl();
        AddressBookView myView = new AddressBookView(myIo);
        AddressBookDao myDao = new AddressBookDaoFileImpl();
        AddressBookController controller = new AddressBookController(myView, myDao);
        controller.run();
        
    }
}
