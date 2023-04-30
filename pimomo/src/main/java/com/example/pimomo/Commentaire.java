package com.example.pimomo;

/**
 *
 * @author [me]
 */
public class Commentaire {
    
    private int id,id_cours;
    private String contenu;
    private int note;
    private Cours cours;

    public Commentaire(String contenu, int note, int coursId) {
        this.contenu = contenu;
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

    public Commentaire(int id, String contenu, int note,int id_cours) {
        this.id = id;

        this.contenu = contenu;
        this.note = note;
        this.id_cours=id_cours;
    }

    public Commentaire(String contenu, int note) {
        this.contenu = contenu;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", contenu=" + contenu + ", note=" + note + '}';
    }
    
}
