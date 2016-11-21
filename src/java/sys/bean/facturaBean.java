/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.clienteDao;
import sys.dao.productoDao;
import sys.imp.clienteDaoImp;
import sys.imp.productoDaoImp;
import sys.model.Cliente;
import sys.model.Detallefactura;
import sys.model.Factura;
import sys.model.Producto;
import sys.util.HibernateUtil;

/**
 *
 * @author José Gómez
 */
@ManagedBean
@ViewScoped
public class facturaBean{
    Session session=null;
    Transaction transaction=null;
   
    private Cliente cliente;
    private Integer codigoCliente;
    private Producto producto;
    private String codigoBarra;
    //detalle de la factura
    private List<Detallefactura> listaDetalleFactura;
    private Integer CantidadProducto;
    private String productoSeleccionado;
    private Factura factura;
    
    public facturaBean() {
        listaDetalleFactura=new ArrayList<>();
        factura=new Factura();
        
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public List<Detallefactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<Detallefactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }

    public Integer getCantidadProducto() {
        return CantidadProducto;
    }

    public void setCantidadProducto(Integer CantidadProducto) {
        this.CantidadProducto = CantidadProducto;
    }

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
   
    //metodo para mostrar datos clientes por medio del dialogClientes
    public void agregarDatosClientes(Integer codCliente){
    this.session=null;
    this.transaction=null;
    try
    {
        this.session=HibernateUtil.getSessionFactory().openSession();
        clienteDao cDao = new clienteDaoImp();
        this.transaction=this.session.beginTransaction();
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
    //metodo para mostrar datos clientes por medio del codigo
    public void agregarDatosClientes2(){
    this.session=null;
    this.transaction=null;
    try
    {
        if(codigoCliente==null)
        {
            return;
        }
        this.session=HibernateUtil.getSessionFactory().openSession();
        clienteDao cDao = new clienteDaoImp();
        this.transaction=this.session.beginTransaction();
        //obtener datos clientes objeto cliente segun codigo cliente
        this.cliente=cDao.obtenerClientePorCodigo(this.session, codigoCliente);
        if(this.cliente!=null)
        {
            this.codigoCliente=null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del Cliente Agregado"));
        }else
        {
            this.codigoCliente=null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Datos del Cliente No encontrado"));
        }
        this.transaction.commit();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del Cliente Agregado"));
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
     //metodo para solicitar cantidad producto
    public void pedirCantidadProducto(String codBarra)
    {
        this.productoSeleccionado=codBarra;
    }
    //metodo para agregar datos productos por medio del dialogProductos
    public void agregarDatosProductos(){
    this.session=null;
    this.transaction=null;
    try
    {
        this.session=HibernateUtil.getSessionFactory().openSession();
        productoDao pDao = new productoDaoImp();
        this.transaction=this.session.beginTransaction();
        //obtener datos producot
        this.producto=pDao.obtenerProductoPorCodBarra(this.session, productoSeleccionado);
        this.listaDetalleFactura.add(new Detallefactura(null,null, this.producto.getCodBarra(),this.producto.getNombreProducto(),
                this.producto.getPrecioVenta(),this.CantidadProducto,new Float(this.CantidadProducto.floatValue()*this.producto.getPrecioVenta())));
        this.transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del Cliente Agregado"));
        this.CantidadProducto=null;
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
    //metodo para mostrar datos clientes por medio del codigo
    public void agregarDatosProductos2(){
    this.session=null;
    this.transaction=null;
    try
    {
        if(codigoBarra.equals(""))
        {
            return;
        }
        this.session=HibernateUtil.getSessionFactory().openSession();
        productoDao pDao = new productoDaoImp();
        this.transaction=this.session.beginTransaction();
        //obtener datos clientes objeto producto segun codigo barra
        this.producto=pDao.obtenerProductoPorCodBarra(this.session, codigoBarra);
        //evaluacion
        if(this.producto!=null)
        {
            //aqui se cargaran los datos del producto despues de tipiar el codigodeBarra
        this.listaDetalleFactura.add(new Detallefactura(null,null, this.producto.getCodBarra(),this.producto.getNombreProducto(),this.producto.getPrecioVenta(),0,new Float(0)));
       
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del Producto Agregado"));
             //limpia
        this.codigoBarra=null;
        }else
        {
             //limpia
            this.codigoBarra=null;
            this.codigoBarra=null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Datos del Producto No encontrado"));
        }
        this.transaction.commit();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del Cliente Agregado"));
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
    //metodo para calcular venta
    public void totalFactura()
    {
        Float totalFacturaVenta = new Float("0");
        try
        {
           for(Detallefactura item: listaDetalleFactura)
           {
              Float totalVentaPorProducto=item.getPrecioVenta() * ((item.getCantidad()));
              item.setTotal(totalVentaPorProducto);
              totalFacturaVenta=totalVentaPorProducto;
           } 
          // this.factura.setTotalVenta(totalFacturaVenta);
        }
        catch(Exception e)
        {
            System.out.println("Error"+e.getMessage());
        }
    }
}
