/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.clienteDao;
import sys.imp.clienteDaoImp;
import sys.model.Cliente;
import sys.util.HibernateUtil;

/**
 *
 * @author José Gómez
 */
@ManagedBean
@ViewScoped
public class facturaBean {
    Session session=null;
    Transaction transaction=null;
   
    private Cliente cliente;
    private String codigoCliente;
    public facturaBean() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    //metodo para mostrar datos clientes por medio del dialogClientes
    public void agregarDatosClientes(Integer codCliente){
    this.session=null;
    this.transaction=null;
    try
    {
        this.session=HibernateUtil.getSessionFactory().openSession();
        clienteDao cDao = new clienteDaoImp();
        this.transaction=this.session.getTransaction();
        //obtener datos clientes objeto cliente segun codigo cliente
        this.cliente=cDao.obtenerClientePorCodigo(this.session, codCliente);
        this.transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del Cliente Agregado"));
    }
    catch(Exception e)
    {
     if(this.transaction != null)
     {  
         System.out.println("Error"+e.getMessage());
         transaction.rollback();
     }
    }
    finally
    {
        if(this.session!=null)
        {
            this.session.close();
        }
    }
    }
}
