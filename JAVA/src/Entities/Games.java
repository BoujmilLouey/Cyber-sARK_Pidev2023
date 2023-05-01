 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Games {
   
    private int id;
    private String name,description,image;
    private Game_category Game_category;
    private int idCat;
    private String nomCat;
    private String color;
    private double note;

    public Games(int id, String name, String description, String image, int idCat) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.idCat = idCat;
    }

    public Games(String name, String description, String image, int idCat) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.idCat = idCat;
    }

    public Games(int id, String name, String description, String image, Game_category Game_category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.Game_category = Game_category;
    }

    public Games(String name, String description, String image, Game_category Game_category) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.Game_category = Game_category;
    }

    public Games(int id, String name, String description, int idCat) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idCat = idCat;
    }

    

    public Games(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;

    }

    public Games(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;

    }

    public Games(int id, String name, String description, String image, int idCat, double note) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.idCat = idCat;
        this.note = note;
    }

    public Games(String name, String description, String image, int idCat, double note) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.idCat = idCat;
        this.note = note;
    }

    public Games(int id, double note) {
        this.id = id;
        this.note = note;
    }

    


    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
    

    public Games() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setGame_category(Game_category Game_category) {
        this.Game_category = Game_category;
    }

    public String getImage() {
        return image;
    }

    public int getIdCat() {
        return idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public Game_category getGame_category() {
        return Game_category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Games other = (Games) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.Game_category, other.Game_category)) {
            return false;
        }
        return true;
    }

    
   

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.image);
        hash = 53 * hash + Objects.hashCode(this.Game_category);
        return hash;
    }

    

    public void add(List<Games> game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Games{" + "id=" + id + ", name=" + name + ", description=" + description + ", image=" + image + ", Game_category=" + Game_category + ", note=" + note + '}';
    }

   

    
    
    

    
}
