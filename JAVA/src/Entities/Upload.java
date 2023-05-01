/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Upload {
     public String upload(File file,String dossier) throws FileNotFoundException, IOException {
         BufferedOutputStream stream = null;
        String globalPath="D:\\FINALESS\\INTEGRATION.VOL3.1\\INTEGRATION.VOL3\\LEGENDS-PIDEV\\src\\Images\\"+ dossier;
       
        String fileName = file.getName();
        
        try {
            Path p = file.toPath();
            
            byte[] bytes = Files.readAllBytes(p);
    
            File dir = new File(globalPath);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()+File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return fileName;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            return "error1";
        } catch (IOException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        }
    }
}

