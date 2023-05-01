/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.List;
import Entities.User;
import Entities.Games;
import javax.mail.Message;
import javax.mail.Session;


/**
 *
 * @author achra
 */
public interface IMetier{

    /**
     *
     * @param name
     * @return
     */
    public List<Games> SearchByName(String name);
    public void sendSms(String toPhoneNumber, String messageText);
  
}
