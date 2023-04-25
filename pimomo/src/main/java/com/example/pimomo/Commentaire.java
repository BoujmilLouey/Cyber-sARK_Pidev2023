package com.example.pimomo;

/**
 *
 * @author [me]
 */
public class Commentaire {
    
    private int id,id_cours;
    private String description;
    private int note;
    private Cours cours;

    public Commentaire(String desc, int note, int coursId) {
        this.description = desc;
        this.note = note;
        this.id_cours = coursId;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public Commentaire() {
    }

    public Commentaire(int id, String description, int note,int id_cours) {
        this.id = id;

        this.description = description;
        this.note = note;
        this.id_cours=id_cours;
    }

    public Commentaire(String description, int note) {
        this.description = description;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", description=" + description + ", note=" + note + '}';
    }
    
}
