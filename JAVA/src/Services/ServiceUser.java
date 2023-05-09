package Services;

import Entities.User;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class ServiceUser implements IServices<User> {

    private Statement ste;
    Connection cnx;

    public User getUserByEmail(String email) {
        User user = null;
        try {
            cnx = (Connection) MyDB.getInstance().getCnx();

            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            ste = cnx.createStatement();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setFullname(rs.getString("fullname"));
                user.setUsername(rs.getString("username"));
                user.setTelephone(rs.getInt("telephone"));
                user.setAdresse(rs.getString("adresse"));
                user.setPassword(rs.getString("password"));
                user.setCin(rs.getInt("cin"));
                user.setEmail(rs.getString("email"));
                               user.setImage(rs.getString("Image"));
               user.setGUserName(rs.getString("GUserName"));
               user.setIs_banned(rs.getInt("is_banned"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    /*      public User getUserByEmail (String Email)  {
     List<User> users = new ArrayList<>();
      User u = new User();
   
          String req = "SELECT * from `user` where Email='"+Email+"';";
            try {

            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                   u.setId(rs.getInt(1));
               u.setNom(rs.getString("nom"));
               u.setPrenom(rs.getString("prenom"));
                   u.setCin(rs.getInt("cin"));
                      u.setEmail(rs.getString("email"));
                        u.setMdp(rs.getString("mdp"));
                  u.setAdresse(rs.getString("adresse"));
                u.setTelephone(rs.getInt("telephone"));
              
              
                
            
              
                 users.add(u) ;                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;    
    }*/

    public void updateUserPassword(String mail, String newPassword) {
        try {
            String qry = "UPDATE user SET `password`='" + newPassword + "' WHERE email = '" + mail + "';";
            ste = cnx.createStatement();
            ste.executeUpdate(qry);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void add(User t) {
        try {
            String qry = "INSERT INTO `user`( `fullname`, `username`, `cin`, `role`,`email`, `password` , `adresse`, `telephone`,'Image','Github_UserName')  VALUES ('" + t.getFullname() + "','" + t.getUsername() + "','" + t.getCin() + "','" + t.getRole() + "','" + t.getEmail() + "','" + t.getPassword() + "','" + t.getAdresse() + "','" + t.getTelephone() + "'"+t.getImage()+"','"+ t.getGUserName()+"')";
            cnx = MyDB.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
      

    @Override
    public List<User> afficher() {
        List<User> users = new ArrayList<>();
        try {
            String qry = "SELECT * FROM `user`  WHERE `archive`='" + 0 + "' ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                User p = new User();
                p.setId(rs.getInt(1));
                p.setFullname(rs.getString("fullname"));
                p.setUsername(rs.getString("username"));

                p.setTelephone(rs.getInt("telephone"));
                p.setAdresse(rs.getString("adresse"));
                p.setRole(rs.getString("role"));
                p.setPassword(rs.getString("password"));
                p.setCin(rs.getInt("cin"));
                p.setEmail(rs.getString("email"));
                p.setImage(rs.getString("Image"));
                p.setGUserName(rs.getString("Github_UserName"));
                p.setIs_banned(rs.getInt("is_banned"));

                users.add(p);
            }
            return users;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;

    }

    @Override
    public void modifier(User t) {

        try {
            String qry = "UPDATE user SET fullname = '" + t.getFullname() + "',username='" + t.getUsername() + "', cin='" + t.getCin() + "', role='" + t.getRole() + "', email='" + t.getEmail() + "', password =' " + t.getPassword() + " ' , adresse='" + t.getAdresse() + "' ,telephone='" + t.getTelephone() + "',Image="+t.getImage()+"',Github_UserName="+t.getGUserName()+"' WHERE id=" + t.getId() + ";";

            cnx = MyDB.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(User t) {

        try {
            String qry = "UPDATE `user` SET archive ='" + "1" + "' WHERE id = '" + t.getId() + "' ";

            cnx = MyDB.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean validate(String email, String password) {
    try {
        cnx = MyDB.getInstance().getCnx();
        PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM user WHERE email=?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            String hashedPassword = rs.getString("password");
            // Check if password matches using bcrypt
            if (BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified) {
                return true ;
            }
        }
        
       
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return  false;
}

   
    public String check(String email, String password) {
    try {
        cnx = MyDB.getInstance().getCnx();
        PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM user WHERE email=?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            
            String hashedPassword = rs.getString("password");
            // Check if password matches using bcrypt
         
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
            if (result.verified) {
                
                return rs.getString("role");
            }
        }
        
        // Password didn't match or user not found
        return "lehna el la9ta ";
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return "invalid";
}
    public User takout(String Uname, String Upassword) {
        String vd = "SELECT * FROM user WHERE email='" + Uname + "';";
User user = null;
try {
    cnx = MyDB.getInstance().getCnx();
    PreparedStatement stmt = cnx.prepareStatement(vd);
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
        String hashedPassword = rs.getString("password");
        // Check if password matches using bcrypt
        if (BCrypt.verifyer().verify(Upassword.toCharArray(), hashedPassword).verified) {
            User p = new User();
            p.setId(rs.getInt(1));
            p.setFullname(rs.getString("fullname"));
            p.setUsername(rs.getString("username"));
            p.setTelephone(rs.getInt("telephone"));
            p.setAdresse(rs.getString("adresse"));
            p.setRole(rs.getString("role"));
            p.setPassword(rs.getString("password"));
            p.setCin(rs.getInt("cin"));
            p.setEmail(rs.getString("email"));
            p.setImage(rs.getString("Image"));
            p.setGUserName(rs.getString("Github_UserName"));
            p.setIs_banned(rs.getInt("is_banned"));
            user = p;
        }
    }
} catch (SQLException ex) {
    System.err.println(ex.getMessage());
}
return user;}
    @Override
    public void modifierr(int id, User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addU(User u) {
     
        try {
            String qry = "INSERT INTO `user`( `fullname`, `username`, `cin`, `role`,`email`, `password` , `adresse`, `telephone`)  VALUES ('" + u.getFullname() + "','" + u.getUsername() + "','" + u.getCin() + "','" + u.getRole() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getAdresse() + "','" + u.getTelephone() + "')";
            cnx = MyDB.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
    public void modifierr(User t) {

        try {
            String qry = "UPDATE user SET fullname = '" + t.getFullname() + "',username='" + t.getUsername() + "', cin='" + t.getCin() + "', role='" + t.getRole() + "', email='" + t.getEmail() + "', password =' " + t.getPassword() + " ' , adresse='" + t.getAdresse() + "' ,telephone='" + t.getTelephone() + "' WHERE id=" + t.getId() + ";";

            cnx = MyDB.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   public void banUser(int userId) {
    try {
        // Get the database connection from your database utility class
        Connection cnx = MyDB.getInstance().getCnx();

        // Prepare an update statement to set the "is_banned" field for the user to 1
        String qry = "UPDATE user SET is_banned = 1 WHERE id = " + userId;
        Statement stm = cnx.createStatement();

        // Execute the update statement
        int rowsUpdated = stm.executeUpdate(qry);

        // Check if the update was successful
        if (rowsUpdated == 0) {
            // Handle the case where the user ID was not found in the database
            System.out.println("Could not ban user with ID " + userId + " - user not found.");
        } else {
            // Log the ban action
            System.out.println("User with ID " + userId + " has been banned.");
        }
    } catch (SQLException ex) {
        // Handle any exceptions that may occur while connecting to or interacting with the database
        System.out.println(ex.getMessage());
    }
}
   public void unbanUser(int userId) {
    try {
        // Get the database connection from your database utility class
        Connection cnx = MyDB.getInstance().getCnx();

        // Prepare an update statement to set the "is_banned" field for the user to 0
        String qry = "UPDATE user SET is_banned = 0 WHERE id = " + userId;
        Statement stm = cnx.createStatement();

        // Execute the update statement
        int rowsUpdated = stm.executeUpdate(qry);

        // Check if the update was successful
        if (rowsUpdated == 0) {
            // Handle the case where the user ID was not found in the database
            System.out.println("Could not unban user with ID " + userId + " - user not found.");
        } else {
            // Log the unban action
            System.out.println("User with ID " + userId + " has been unbanned.");
        }
    } catch (SQLException ex) {
        // Handle any exceptions that may occur while connecting to or interacting with the database
        System.out.println(ex.getMessage());
    }
}









    
    
    
    
    }

