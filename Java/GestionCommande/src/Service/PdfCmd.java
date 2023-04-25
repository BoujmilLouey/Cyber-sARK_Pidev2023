/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Commande;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.scene.text.Font;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
 
public class PdfCmd {
   public void orderPdf() throws SQLException {
       { CommandeCRUD commandeS =new CommandeCRUD();
       List <Commande> commandes =  commandeS.recuperer();
        try {
            // Créer un nouveau document PDF
            Document document = new Document();
            // Créer un écrivain de document PDF
            Date d = Date.valueOf(LocalDate.now());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(d);
            int j=0;
            j++;
            PdfWriter.getInstance(document, new FileOutputStream("C:\\xampp\\htdocs\\img\\commande_" +j+'_'+ dateString + ".pdf"));
            // Ouvrir le document PDF pour écrire
            document.open();
            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            com.itextpdf.text.Font fontmini = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK);
            com.itextpdf.text.Font fontgras = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
            com.itextpdf.text.Font fontgrastitre = FontFactory.getFont(FontFactory.TIMES_BOLD, 50, BaseColor.BLACK);
             Image image = Image.getInstance("C:\\xampp\\htdocs\\img\\logo.png");
            image.scaleToFit(50, 50);
            image.setAbsolutePosition(460, 720);
            document.add(image);
            document.add(new Paragraph("\n\n"));
            Chunk chunk = new Chunk("  Liste de  Commande  ",fontgrastitre);
            document.add(chunk);
            document.add(new Paragraph("\n\n"));
           
            PdfPTable table = new PdfPTable(2);
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new com.itextpdf.text.Phrase("Date_commande\n "));
            table.addCell(cell);
            cell.setPhrase(new com.itextpdf.text.Phrase("Montant commande"));
            table.addCell(cell);
            
             
            // Add cells to table
            for (int i = 0; i < commandes.size(); i++) {
                Commande comd = commandes.get(i);
                System.out.println("000 : "+comd);

                cell.setPhrase(new com.itextpdf.text.Phrase(comd.getDate_commande().toLocaleString()));
                table.addCell(cell);
                 cell.setPhrase(new com.itextpdf.text.Phrase(comd.getMontant_commande()+"\n "));
                table.addCell(cell);
               
            }

            document.add(table);
            document.add(new Paragraph("\n\n"));

// Fermer le document PDF
            document.close();
            System.out.println("Le document PDF a été créé avec succès.");

        } catch (Exception e) {
            System.out.println("Error PDF " + e.getMessage());

        }

    }
}
}