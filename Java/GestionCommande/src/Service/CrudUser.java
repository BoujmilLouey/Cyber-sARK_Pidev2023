/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author BAZINFO
 */
public class CrudUser {
//     public void SignUp(User P) {
//        try {
//
//            String requete = "INSERT INTO User(email,roles,password,is_verified,is_banned,username,fulname,naissance)" + "VALUES (?,?,?,?,?,?,?,?)";
//            PreparedStatement pst = new MyDB().conn.prepareStatement(requete);
//            pst.setString(1, P.getEmail());
//            pst.setString(2, P.getFullname());
//            pst.setInt(3, P.getIs_banned());
//            pst.setInt(4, P.getIs_verified());
//            pst.setString(5, P.getRoles());
//            pst.setString(6, User.hash(P.getPassword()));
//            pst.setString(8, P.getUsername());
//            pst.executeUpdate();
//
//            System.out.println("Patient ajouté!");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
//
//    public String checkRole(int is_verified) {
//        String default_return = "ND";
//        try {
//            String req = "select Role from user where is_verified=?";
//            PreparedStatement pst = new MyDB().conn.prepareStatement(req);
//            pst.setInt(1, is_verified);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                if (rs.getString(1).equals("joueur")) {
//
//                    return "joueur";
//                } else if (rs.getString(1).equals("admin")) {
//                    return "Admin";
//                } else if (rs.getString(1).equals("utilisateur")) {
//                    return "utilisateur";
//                }
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return default_return;
//    }
//
//   
//
//    public boolean chercheruserInt(int is_verified, String password) {
//        boolean test = false;
//        String storedPassword = "";
//        try {
//            String requete = "SELECT * FROM user WHERE is_verified= ?";
//            PreparedStatement pst = new MyDB().conn.prepareStatement(requete);
//            pst.setInt(1, is_verified);
//
//            ResultSet rs = pst.executeQuery();
//
//           
//            if (rs.next()) {
//                storedPassword = rs.getString("password");
//                User u = this.findById(rs.getInt("id"));
//         
//                System.out.println(storedPassword);
//            }
//            String hashedInputPassword = User.hash(password);
//            if (hashedInputPassword.equals(storedPassword)) {
//                // mot de passe correct, l'utilisateur est authentifié
//                test = true;
//                System.out.println(test);
//            } else {
//                System.out.println(test);
//                // mot de passe incorrect, l'utilisateur n'est pas authentifié
//                return test;
//
//            }
//
//            // test=true ; 
//        } catch (SQLException ex) {
//            // System.out.println("fin"); 
//            System.err.println(ex.getMessage());
//        }
//        return test;
//    }
//
//    public boolean isPhoneNumberUnique(int num) throws ClassNotFoundException, SQLException {
//        try {
//            String requete = "SELECT * FROM user WHERE Phone= ?";
//            PreparedStatement pst = new MyDB().conn.prepareStatement(requete);
//            pst.setInt(1, num);
//            ResultSet rs = pst.executeQuery();
//            return !rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    public User findById(int id) {
//        User p = null;
//        try {
//            String req = "select  * from user where id=? ";
//            PreparedStatement pst = new MyDB().conn.prepareStatement(req);
//            pst.setInt(1, id);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next())
//                p = new User(rs.getInt("id"),rs.getString("email"), rs.getString("roles"), rs.getString("password"), rs.getInt("is_verified"), rs.getInt("is_banned"),rs.getString("username"), rs.getString("fulname"),rs.getDate("naissance") );
//            } catch (SQLException ex) {
//             Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
//         }
//        
//        return p;
//    }
//
//    //CRUD USER 
//    public void addUser(User P) {
//        try {
//            String requete = "INSERT INTO users (email,roles,password,is_verified,is_banned,username,fulname,naissance)" + "VALUES (?,?,?,?,?,?)";
//            PreparedStatement pst = new MyDB().conn.prepareStatement(requete);
//            pst.setString(1, P.getEmail());
//            pst.setString(2, P.getFullname());
//            pst.setInt(3, P.getIs_banned());
//            pst.setInt(4, P.getIs_verified());
//            pst.setString(5, P.getRoles());
//            pst.setString(6, User.hash(P.getPassword()));
//            pst.setString(8, P.getUsername());
//            pst.executeUpdate();
//
//            System.out.println("Utilisateur  ajouté!");
//        } catch (SQLException ex) {
//            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
////    public ObservableList<User> showUser() {
////        ObservableList<User> list = FXCollections.observableArrayList();
////        try {
////            String requete = "SELECT * FROM user";
////            PreparedStatement pst = new MyConnection().conn.prepareStatement(requete);
////            ResultSet rs = pst.executeQuery();
////
////            while (rs.next()) {
////               User u = new User(rs.getInt("id"),rs.getString("email"), rs.getString("roles"), rs.getString("password"), rs.getInt("is_verified"), rs.getInt("is_banned"),rs.getString("username"), rs.getString("fulname"),rs.getDate("naissance") );
////
////                list.add(u);
////
////            }
////
////        } catch (SQLException ex) {
////            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
////        }
////        return list;
////    }
//
////    public void editUser(User P, int Phone) {
////        try {
////
////            String requete = "UPDATE user SET Firstname= ? ,lastname=?,Email = ? ,Addresse= ? ,mdp= ? WHERE Phone= ? ";
////            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
////            pst.setString(1, P.getFirstname());
////            pst.setString(2, P.getLastname());
////            pst.setString(3, P.getEmail());
////            pst.setString(4, P.getAdresse());
////            pst.setString(5, P.getMdp());
////            pst.setInt(6, Phone);
////            //    pst.setString(6, P.getMdp());
////
////            pst.executeUpdate();
////
////            System.out.println("Utilisateur modifié!");
////
////        } catch (SQLException ex) {
////            Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
////        }
////
////    }
//
////    public void deleteUser(int Phone) {
////
////        try {
////            String requete = "DELETE FROM users WHERE Phone = ?";
////            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
////            pst.setInt(1, Phone);
////            pst.executeUpdate();
////        } catch (SQLException ex) {
////            Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
////        }
////
////    }
////
////    public boolean phoneExists(int phone) {
////        String query = "SELECT * FROM users WHERE Phone = ?";
////        try (PreparedStatement stmt = new MyConnection().cn.prepareStatement(query)) {
////            stmt.setInt(1, phone);
////            ResultSet rs = stmt.executeQuery();
////            return rs.next();
////        } catch (SQLException ex) {
////            System.err.println("Error checking if phone exists: " + ex.getMessage());
////            return false;
////        }
////    }
}
