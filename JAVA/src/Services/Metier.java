/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import Entities.Games;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Date;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Properties;
import java.time.LocalDate;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;

import javax.mail.PasswordAuthentication;
import Entities.Games;
import Entities.User;
import Utils.MyDB;
/**
 *
 * @author achra
 */

public class Metier implements IMetier {
    Statement ste;
    Connection cnx = MyDB.getInstance().getCnx();
    
    
    
    
    
    
    
    @Override
    public List<Games> SearchByName(String name)  {
        List<Games> pers = new ArrayList<>();
        try {
            PreparedStatement pre = cnx.prepareStatement("select * from `game` WHERE `name` LIKE ?");
            pre.setString(1, "%" + name + "%");
            ResultSet result = pre.executeQuery();
            ste = cnx.createStatement();
        System.out.println(result);
        while (result.next()) {
            Games resultPerson = new Games(result.getString("name"),result.getString("description"),result.getString("image"));
            pers.add(resultPerson);
        }
        System.out.println(pers);
      
        } catch (SQLException ex) {
            System.out.println(ex);   
        }
        return pers;

    }
    
    private static final String ACCOUNT_SID = "AC0886d26043788d5615fff2e3af17cecd";
  private static final String AUTH_TOKEN = "ae0704c67dd9b55f29d01bdc60eb70cd";
  private static final String TWILIO_PHONE_NUMBER = "+16205269527";

//   Define the method to send the SMS message
  @Override
  public void sendSms(String toPhoneNumber, String messageText) {
  // Initialize the Twilio API client
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    // Set the phone numbers for the SMS message
    PhoneNumber to = new PhoneNumber(toPhoneNumber);
    PhoneNumber from = new PhoneNumber(TWILIO_PHONE_NUMBER);

    // Use the Message creator to send the SMS message
    com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(to, from, messageText).create();
  
  }
  
  
//  @Override
//    public void sendMail( String mail ) throws Exception {
//        String mailContent = "<!DOCTYPE html>\n"
//                + "\n"
//                + "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n"
//                + "\n"
//                + "<head>\n"
//                + "	<title></title>\n"
//                + "	<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n"
//                + "	<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\n"
//                + "	<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n"
//                + "	<!--[if !mso]><!-->\n"
//                + "	<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\" />\n"
//                + "	<!--<![endif]-->\n"
//                + "	<style>\n"
//                + "		* {\n"
//                + "			box-sizing: border-box;\n"
//                + "		}\n"
//                + "\n"
//                + "		body {\n"
//                + "			margin: 0;\n"
//                + "			padding: 0;\n"
//                + "		}\n"
//                + "\n"
//                + "		a[x-apple-data-detectors] {\n"
//                + "			color: inherit !important;\n"
//                + "			text-decoration: inherit !important;\n"
//                + "		}\n"
//                + "\n"
//                + "		#MessageViewBody a {\n"
//                + "			color: inherit;\n"
//                + "			text-decoration: none;\n"
//                + "		}\n"
//                + "\n"
//                + "		p {\n"
//                + "			line-height: inherit\n"
//                + "		}\n"
//                + "\n"
//                + "		@media (max-width:670px) {\n"
//                + "			.icons-inner {\n"
//                + "				text-align: center;\n"
//                + "			}\n"
//                + "\n"
//                + "			.icons-inner td {\n"
//                + "				margin: 0 auto;\n"
//                + "			}\n"
//                + "\n"
//                + "			.row-content {\n"
//                + "				width: 100% !important;\n"
//                + "			}\n"
//                + "\n"
//                + "			.column .border {\n"
//                + "				display: none;\n"
//                + "			}\n"
//                + "\n"
//                + "			.stack .column {\n"
//                + "				width: 100%;\n"
//                + "				display: block;\n"
//                + "			}\n"
//                + "		}\n"
//                + "	</style>\n"
//                + "</head>\n"
//                + "\n"
//                + "<body style=\"background-color: #F5F5F5; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n"
//                + "	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n"
//                + "		style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #F5F5F5;\" width=\"100%\">\n"
//                + "		<tbody>\n"
//                + "			<tr>\n"
//                + "				<td>\n"
//                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\"\n"
//                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
//                + "						<tbody>\n"
//                + "							<tr>\n"
//                + "								<td>\n"
//                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
//                + "										class=\"row-content stack\" role=\"presentation\"\n"
//                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;\"\n"
//                + "										width=\"650\">\n"
//                + "										<tbody>\n"
//                + "											<tr>\n"
//                + "												<td class=\"column column-1\"\n"
//                + "													style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n"
//                + "													width=\"100%\">\n"
//                + "													<div class=\"spacer_block\"\n"
//                + "														style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n"
//                + "												</td>\n"
//                + "											</tr>\n"
//                + "										</tbody>\n"
//                + "									</table>\n"
//                + "								</td>\n"
//                + "							</tr>\n"
//                + "						</tbody>\n"
//                + "					</table>\n"
//                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\"\n"
//                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
//                + "						<tbody>\n"
//                + "							<tr>\n"
//                + "								<td>\n"
//                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
//                + "										class=\"row-content stack\" role=\"presentation\"\n"
//                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #D6E7F0; color: #000000; width: 650px;\"\n"
//                + "										width=\"650\">\n"
//                + "										<tbody>\n"
//                + "											<tr>\n"
//                + "												<td class=\"column column-1\"\n"
//                + "													style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-left: 25px; padding-right: 25px; padding-top: 5px; padding-bottom: 60px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n"
//                + "													width=\"100%\">\n"
//                + "													<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\"\n"
//                + "														role=\"presentation\"\n"
//                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n"
//                + "														width=\"100%\">\n"
//                + "														<tr>\n"
//                + "															<td\n"
//                + "																style=\"padding-left:15px;padding-right:10px;padding-top:20px;\">\n"
//                + "																<div style=\"font-family: sans-serif\">\n"
//                + "																	<div\n"
//                + "																		style=\"font-size: 12px; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif; mso-line-height-alt: 18px; color: #052d3d; line-height: 1.5;\">\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 75px;\">\n"
//                + "																			<span style=\"font-size:50px;\"><strong><span\n"
//                + "																						style=\"font-size:50px;\"><span\n"
//                + "																							style=\"font-size:38px;\">WELCOME\n"
//                + "																							TO\n"
//                + "																							AUTODOC</span></span></strong></span>\n"
//                + "																		</p>\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 51px;\">\n"
//                + "																			<span style=\"font-size:34px;\"><strong><span\n"
//                + "																						style=\"font-size:34px;\"><span\n"
//                + "																							style=\"color:#2190e3;font-size:34px;\"></span></span></strong></span>\n"
//                + "																		</p>\n"
//                + "																	</div>\n"
//                + "																</div>\n"
//                + "															</td>\n"
//                + "														</tr>\n"
//                + "													</table>\n"
//                + "													<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n"
//                + "														class=\"text_block\" role=\"presentation\"\n"
//                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n"
//                + "														width=\"100%\">\n"
//                + "														<tr>\n"
//                + "															<td>\n"
//                + "																<div style=\"font-family: sans-serif\">\n"
//                + "																	<div\n"
//                + "																		style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif;\">\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
//                + "																			<span\n"
//                + "																				style=\"font-size:18px;color:#000000;\">Thanks\n"
//                + "																				for purchasing our service.</span></p>\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
//                + "																			<span\n"
//                + "																				style=\"font-size:18px;color:#000000;\">This\n"
//                + "																				mail contains all the infos (do no\n"
//                + "																				reply).</span></p>\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
//                + "																			<span\n"
//                + "																				style=\"font-size:18px;color:#000000;\"><br>\n"
//                + "																				"+"<br>Purchase Date : "+Date.valueOf(LocalDate.now())+"<br>Expiration Date : "+Date.valueOf(java.time.LocalDate.now().plusYears(1))+"<br> </span></p>\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
//                + "																			<span\n"
//                + "																				style=\"font-size:18px;color:#000000;\"><br>Details:<br> Company name : "+"AutoDoc"+"</span>\n"
//                + "																		</p>\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
//                + "																			<span\n"
//                + "																				style=\"font-size:18px;color:#000000;\"><br>Purchased\n"
//                + "																				"
//                + "                                                                                                                                                            <p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
//                + "																			<span\n"
//                + "																				style=\"font-size:18px;color:#000000;\"><br>Purchased\n"
//                + "																				by : </span></p>\n"
//                + "																		<p\n"
//                +"                                                                                                                                                             by : </span></p>\n"                             
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 14.399999999999999px;\">\n"
//                + "																			 </p>\n"
//                + "																	</div>\n"
//                + "																</div>\n"
//                + "															</td>\n"
//                + "														</tr>\n"
//                + "													</table>\n"
//                + "												</td>\n"
//                + "											</tr>\n"
//                + "										</tbody>\n"
//                + "									</table>\n"
//                + "								</td>\n"
//                + "							</tr>\n"
//                + "						</tbody>\n"
//                + "					</table>\n"
//                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\"\n"
//                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
//                + "						<tbody>\n"
//                + "							<tr>\n"
//                + "								<td>\n"
//                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
//                + "										class=\"row-content stack\" role=\"presentation\"\n"
//                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;\"\n"
//                + "										width=\"650\">\n"
//                + "										<tbody>\n"
//                + "											<tr>\n"
//                + "												<td class=\"column column-1\"\n"
//                + "													style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 20px; padding-bottom: 60px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n"
//                + "													width=\"100%\">\n"
//                + "													<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n"
//                + "														class=\"text_block\" role=\"presentation\"\n"
//                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n"
//                + "														width=\"100%\">\n"
//                + "														<tr>\n"
//                + "															<td>\n"
//                + "																<div style=\"font-family: sans-serif\">\n"
//                + "																	<div\n"
//                + "																		style=\"font-size: 12px; mso-line-height-alt: 18px; color: #555555; line-height: 1.5; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif;\">\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
//                + "																			MasterHR © -  Your favorite company tool.\n"
//                + "																		</p>\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
//                + "																			Tunis, Tunisia.</p>\n"
//                + "																	</div>\n"
//                + "																</div>\n"
//                + "															</td>\n"
//                + "														</tr>\n"
//                + "													</table>\n"
//                + "													<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n"
//                + "														class=\"divider_block\" role=\"presentation\"\n"
//                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n"
//                + "														width=\"100%\">\n"
//                + "														<tr>\n"
//                + "															<td>\n"
//                + "																<div align=\"center\">\n"
//                + "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
//                + "																		role=\"presentation\"\n"
//                + "																		style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n"
//                + "																		width=\"60%\">\n"
//                + "																		<tr>\n"
//                + "																			<td class=\"divider_inner\"\n"
//                + "																				style=\"font-size: 1px; line-height: 1px; border-top: 1px dotted #C4C4C4;\">\n"
//                + "																				<span> </span></td>\n"
//                + "																		</tr>\n"
//                + "																	</table>\n"
//                + "																</div>\n"
//                + "															</td>\n"
//                + "														</tr>\n"
//                + "													</table>\n"
//                + "													<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n"
//                + "														class=\"text_block\" role=\"presentation\"\n"
//                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n"
//                + "														width=\"100%\">\n"
//                + "														<tr>\n"
//                + "															<td>\n"
//                + "																<div style=\"font-family: sans-serif\">\n"
//                + "																	<div\n"
//                + "																		style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #4F4F4F; line-height: 1.2; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif;\">\n"
//                + "																		<p\n"
//                + "																			style=\"margin: 0; font-size: 12px; text-align: center;\">\n"
//                + "																			<span style=\"font-size:14px;\"><strong>Support\n"
//                + "																					:\n"
//                + "																					masterhrcomapny@gmail.com</strong></span>\n"
//                + "																		</p>\n"
//                + "																	</div>\n"
//                + "																</div>\n"
//                + "															</td>\n"
//                + "														</tr>\n"
//                + "													</table>\n"
//                + "												</td>\n"
//                + "											</tr>\n"
//                + "										</tbody>\n"
//                + "									</table>\n"
//                + "								</td>\n"
//                + "							</tr>\n"
//                + "						</tbody>\n"
//                + "					</table>\n"
//                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\"\n"
//                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
//                + "						<tbody>\n"
//                + "							<tr>\n"
//                + "								<td>\n"
//                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
//                + "										class=\"row-content stack\" role=\"presentation\"\n"
//                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;\"\n"
//                + "										width=\"650\">\n"
//                + "										<tbody>\n"
//                + "											<tr>\n"
//                + "												<td class=\"column column-1\"\n"
//                + "													style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n"
//                + "													width=\"100%\">\n"
//                + "													<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
//                + "														class=\"icons_block\" role=\"presentation\"\n"
//                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n"
//                + "														width=\"100%\">\n"
//                + "														<tr>\n"
//                + "															<td\n"
//                + "																style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n"
//                + "																<table cellpadding=\"0\" cellspacing=\"0\"\n"
//                + "																	role=\"presentation\"\n"
//                + "																	style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n"
//                + "																	width=\"100%\">\n"
//                + "																	<tr>\n"
//                + "																		<td\n"
//                + "																			style=\"vertical-align: middle; text-align: center;\">\n"
//                + "																			<!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n"
//                + "																			<!--[if !vml]><!-->\n"
//                + "																			<table cellpadding=\"0\" cellspacing=\"0\"\n"
//                + "																				class=\"icons-inner\" role=\"presentation\"\n"
//                + "																				style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\">\n"
//                + "																				<!--<![endif]-->\n"
//                + "																				<tr>\n"
//                + "																				</tr>\n"
//                + "																			</table>\n"
//                + "																		</td>\n"
//                + "																	</tr>\n"
//                + "																</table>\n"
//                + "															</td>\n"
//                + "														</tr>\n"
//                + "													</table>\n"
//                + "												</td>\n"
//                + "											</tr>\n"
//                + "										</tbody>\n"
//                + "									</table>\n"
//                + "								</td>\n"
//                + "							</tr>\n"
//                + "						</tbody>\n"
//                + "					</table>\n"
//                + "				</td>\n"
//                + "			</tr>\n"
//                + "		</tbody>\n"
//                + "	</table><!-- End -->\n"
//                + "</body>\n"
//                + "\n"
//                + "</html>";
//        String myAccountEmail = "alarassaa147@gmail.com";
//        String password = "xwgasmdtzmkoiltb";
//        System.out.println("Preparing to send email");
//        Properties p = new Properties();
//
//        p.put("mail.smtp.auth", "true");
//        p.put("mail.smtp.ssl.enable", "true");
//        p.put("mail.smtp.host", "smtp.gmail.com");
//        p.put("mail.smtp.port", "465");
//
//        Session session = Session.getInstance(p, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//
//            Message message = prepareMessage(session, myAccountEmail,mail, mailContent);
//
//        Transport.send(message);
//        System.out.println("Message sent successfully");
//        });
//
//      
//
//    }
//     private static Message prepareMessage(Session session, String myAccountEmail, String recipient , String mailContent) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//            message.setSubject("Welcome to AutoDco");
//            message.setContent(mailContent, "text/html");
//            return message;
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }
//        return null;
//    }



//    @Override
//    public void sendMail(String mail) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//   
       
    
}
