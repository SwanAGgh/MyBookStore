package hibernate;

import org.hibernate.Session;
import javax.persistence.EntityTransaction;
import java.util.List;

public class BooksDAO {

    private Session session;
    public BooksDAO(Session session){
        this.session = session;
    }

    public List<Books> findAllBooks(){
        return  session.createQuery("FROM Books", Books.class).getResultList();
    }

    public List<Books> findByTitle(String title){
        return session.createQuery("FROM Books book WHERE book.title = '" + title +"'", Books.class).getResultList();
    }

    public Books findByIsbn(String isbn){
        //return session.find(Books.class, isbn);
        return session.createQuery("FROM Books book WHERE book.isbn = '" + isbn +"'", Books.class).getSingleResult();
    }

    public long getMuzonsCount(){ return session.createQuery("SELECT count(muz) FROM Muzons muz", Long.class).getSingleResult(); }

    public Books addMuzons(Books books){
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()){ transaction.begin(); }
            session.persist(books);
            transaction.commit();
            return books;
        } catch (Exception e) {
            if(transaction != null){ transaction.rollback(); }
            return null;
        }
    }
    public Long removeMuzons(Long id){
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()){ transaction.begin(); }
            Books books = session.load(Books.class, id);
            session.remove(books);
            transaction.commit();
            return id;
        } catch (Exception e) {
            if(transaction != null){ transaction.rollback(); }
            return null;
        }
    }
    public String toString(Books i){
        return  i.getIsbn() + ", " +
                i.getTitle() + ", " +
                i.getDescription() + ", " +
                i.getAuthor() + ", " +
                i.getPublisher() + ", " +
                i.getPages() + ", " +
                i.getPublishingYear(); }

    public static void printMenu(){
        System.out.println("Menu");
        System.out.println("1. Search book");
        System.out.println("2. Add Book");
        System.out.println("3. Remove Book");
        System.out.println("4. Get Book info");
        System.out.println("5. List Available Books");
        System.out.println("Press Q to quit ");
    }
}