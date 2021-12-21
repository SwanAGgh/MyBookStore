package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        final String NAME = "Grāmata 1";

        try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory())
        {
            Session session = sessionFactory.openSession();
            BooksDAO booksDAO = new BooksDAO(session);

            //Find all
            for (Books i : booksDAO.findAllBooks()){
                System.out.println(booksDAO.toString(i));
            }

            //Find by Grupa
            int counter=0;
            for (Books i : booksDAO.findByTitle(NAME)){
                counter++;
                System.out.println(booksDAO.toString(i));
            }
            System.out.println(counter + " record(s) found with title " + NAME);
/*
            //Find by ID
            Muzons i = muzonsDAO.findById(ID);
            if (i!=null) {System.out.println(muzonsDAO.toString(i));}
            else {System.out.println("Record not found!");};

            //Get count
            System.out.println("Count: " + muzonsDAO.getMuzonsCount());

            //Add record
            Muzons muzons = new Muzons()
                    .setGrupa("Grupa jauna X")
                    .setAlbums("Albums  Y")
                    .setRelease_date(LocalDate.parse("2022-11-02"));
            muzonsDAO.addMuzons(muzons);

            //Remove by ID
            Muzons x = muzonsDAO.findById(ID);
            if (x!=null) {
                muzonsDAO.removeMuzons((long)ID);
                System.out.println("Record " + ID + " removed.");
            } else {System.out.println("Record with ID " + ID + " does not exist!");};
*/
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}