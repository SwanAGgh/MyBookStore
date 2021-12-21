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


    public Books findById(long id){return session.find(Books.class, id);}
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
        return  i.getId() + ", " +
                i.getTitle() + ", " +
                i.getDescription() + ", " +
                i.getAuthor() + ", " +
                i.getPublisher() + ", " +
                i.getPages() + ", " +
                i.getPublishingYear(); }
}