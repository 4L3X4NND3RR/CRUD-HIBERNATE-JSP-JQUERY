package com.alexander.model;

import com.alexander.entities.Producto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author alexanderdeleon
 */
public class ModelProducto {

    private final SessionFactory sessionFactory;

    public ModelProducto(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int getCountProductos() {
        int cantidad;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<?> query = session.createQuery("select count(*) from Producto");
            long count = (long) query.uniqueResult();
            cantidad = (int) count;
        } finally{
            session.close();
        }
        return cantidad;
    }
    
    public List<Producto> getProductos(int inicio, int fin) {
        Transaction trasaction = null;
        List<Producto> productos = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            trasaction = session.beginTransaction();
            Query<Producto> query = session.createQuery("from Producto", Producto.class);
            query.setFirstResult(inicio);
            query.setMaxResults(fin);
            productos = query.list();
            trasaction.commit();
        }catch(Exception e){
            if (trasaction != null) {
                trasaction.rollback();
            }
        }finally{
            session.close();
        }
        return productos;
    }
    
    public void saveProducto(Producto producto){
        Transaction transaction = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(producto);
            transaction.commit();
        }catch(Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }
    
    public Producto getProducto(String codigo){
        Producto producto = null;
        Transaction transaction = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "Select p from Producto p where p.codigo=:codigo";
            Query<?> query = session.createQuery(hql);
            query.setParameter("codigo", codigo);
            producto = (Producto) query.uniqueResult();
        }catch(Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return producto;
    }
    
    public void updateProducto(Producto producto){
        Transaction transaction = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(producto);
            transaction.commit();
        }catch(Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }
}
