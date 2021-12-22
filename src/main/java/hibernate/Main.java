package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory())
        {
            Session session = sessionFactory.openSession();
            BooksDAO booksDAO = new BooksDAO(session);

            while (true) {
                BooksDAO.printMenu();
                String input = scan.nextLine();

                if ("q".equalsIgnoreCase(input)){
                    System.out.println("Beidzam darbu");
                    break;
                }

                switch (input){

                    case "1":
                        System.out.println("Searching for");
                        System.out.println("Enter book name:");
                        String bookTitle = scan.nextLine();
                        int counter=0;
                        for (Books i : booksDAO.findByTitle(bookTitle)){
                            counter++;
                            System.out.println(booksDAO.toString(i));
                        }
                        System.out.println(counter + " record(s) found with title " + bookTitle);
                        break;

                    case "4":
                        System.out.println("Searching for");
                        System.out.println("Enter book isbn:");
                        String bookIsbn = scan.nextLine();
                        Books i = booksDAO.findByIsbn(bookIsbn);
                        System.out.println(booksDAO.toString(i));

                        break;

                    case "5":
                        for (Books allBooks : booksDAO.findAllBooks()){
                            System.out.println(booksDAO.toString(allBooks));
                        }
                        break;

                }
            }
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