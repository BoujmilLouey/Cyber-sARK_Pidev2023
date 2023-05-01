package Services;

import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entities.Event;

public class EventService {






    private Connection con = MyDB.getInstance().getCnx();
    public void ajouter(Event event1) throws SQLException {
        String requete = "INSERT INTO calendar (title, start) VALUES (?, ?)";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, event1.getTitle());
        pst.setDate(2, new java.sql.Date(event1.getDate().getTime()));
        pst.executeUpdate();
        System.out.println("Event ajouté avec succès !");
    }

    public void update(Event event) throws SQLException {
        String requete = "UPDATE calendar SET title = ?, start = ? WHERE id = ?";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, event.getTitle());
        pst.setDate(2, new java.sql.Date(event.getDate().getTime()));
        pst.setInt(3, event.getId());
        pst.executeUpdate();
        System.out.println("Event mis à jour avec succès !");
    }


    public void delete(Event event) throws SQLException {
        String query = "DELETE FROM calendar WHERE id = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, event.getId());
        pst.executeUpdate();
        System.out.println("Event supprimé avec succès !");
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> eventList = new ArrayList<>();

        String query = "SELECT * FROM calendar";
        try (PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                java.sql.Date date = rs.getDate("start");

                Event event = new Event(id, title, date);
                eventList.add(event);
            }
        }

        return eventList;
    }




}
