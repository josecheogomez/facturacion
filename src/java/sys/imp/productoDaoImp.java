/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.productoDao;
import sys.model.Producto;
import sys.util.HibernateUtil;

/**
 *
 * @author José Gómez
 */
public class productoDaoImp implements productoDao{

    @Override
    public List<Producto> listarProducto() {
        //lista nula
       List<Producto> lista=null;
       //se abre la session
       Session session=HibernateUtil.getSessionFactory().openSession();
       //se prepara la transaccion
       Transaction t=session.beginTransaction();
       //sentencia hql
       String hql="FROM Producto";
       try
       {
           lista=session.createQuery(hql).list();
           t.commit();
           session.close();
       }
       catch(Exception e)
       {
       t.rollback();
       System.out.println("--Error--"+e.getMessage());
       }
       return lista; 
    }

    @Override
    public void newProducto(Producto producto) {
     Session session=null;
        try
        {
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(producto);
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        finally
        {
            if(session != null)
            {
                session.close();
            }
        }
    }

    @Override
    public void updateProducto(Producto producto) {
        Session session=null;
        try
        {
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(producto);
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        finally
        {
            if(session != null)
            {
                session.close();
            }
        }
    }

    @Override
    public void deleteProducto(Producto producto) {
            Session session=null;
        try
        {
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(producto);
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        finally
        {
            if(session != null)
            {
                session.close();
            }
        }
    }
    
}
