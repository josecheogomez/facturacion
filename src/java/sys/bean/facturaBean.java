/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
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
public class facturaBean {

    Session session = null;
    Transaction transaction = null;

    private Cliente cliente;
    private Integer codigoCliente;
    private Producto producto;
    private String codigoBarra;
    //detalle de la factura
    private List<Detallefactura> listaDetalleFactura;
    private String CantidadProducto;
    private String productoSeleccionado;
    private Factura factura;

    private String CantidadProducto2;

    public facturaBean() {
        listaDetalleFactura = new ArrayList<>();
        factura = new Factura();

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

    public String getCantidadProducto() {
        return CantidadProducto;
    }

    public void setCantidadProducto(String CantidadProducto) {
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

    public String getCantidadProducto2() {
        return CantidadProducto2;
    }

    public void setCantidadProducto2(String CantidadProducto2) {
        this.CantidadProducto2 = CantidadProducto2;
    }

    //metodo para mostrar datos clientes por medio del dialogClientes
    public void agregarDatosClientes(Integer codCliente) {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();
            //obtener datos clientes objeto cliente segun codigo cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, codCliente);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del Cliente Agregado"));
        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println("Error" + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //metodo para mostrar datos clientes por medio del codigo
    public void agregarDatosClientes2() {
        this.session = null;
        this.transaction = null;
        try {
            if (codigoCliente == null) {
                return;
            }
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();
            //obtener datos clientes objeto cliente segun codigo cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, codigoCliente);
            if (this.cliente != null) {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del Cliente Agregado"));
            } else {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos del Cliente No encontrado"));
            }
            this.transaction.commit();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del Cliente Agregado"));
        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println("Error" + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //metodo para solicitar cantidad producto
    public void pedirCantidadProducto(String codBarra) {
        this.productoSeleccionado = codBarra;
    }

    //metodo para agregar datos productos por medio del dialogProductos
    public void agregarDatosProductos() {
        this.session = null;
        this.transaction = null;
        try {
            //comienza la condicion para validar cantidad de productos
            if (!(this.CantidadProducto.matches("[0-9]*")) || this.CantidadProducto.equals("0") || this.CantidadProducto.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad es incorrecta"));
                this.CantidadProducto = "";
            } else {
                this.session = HibernateUtil.getSessionFactory().openSession();
                productoDao pDao = new productoDaoImp();
                this.transaction = this.session.beginTransaction();
                //obtener datos producot
                this.producto = pDao.obtenerProductoPorCodBarra(this.session, productoSeleccionado);
                this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(), this.producto.getNombreProducto(),
                        this.producto.getPrecioVenta(), Integer.parseInt(CantidadProducto), new Double(Integer.parseInt(CantidadProducto) * this.producto.getPrecioVenta())));
                this.transaction.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto Agregado"));
                //llamada al metodo totalFacturaVenta
                this.totalFacturaVenta();
                //limpiamos cantidad producto
                this.CantidadProducto = "";
            }

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println("Error" + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //metodo mostrar el dialogo cantidadproducto2
    public void mostrarCantidadProducto2() {
        this.session = null;
        this.transaction = null;
        try {
            if (codigoBarra.equals("")) {
                return;
            }
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pDao = new productoDaoImp();
            this.transaction = this.session.beginTransaction();
            //obtener datos clientes objeto producto segun codigo barra
            this.producto = pDao.obtenerProductoPorCodBarra(this.session, codigoBarra);
            //evaluacion
            if (this.producto != null) {
                //solicitar que muestre dialogo cantidad de productos 2
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dialogCantidadProducto2').show();");

                this.codigoBarra = null;
            //aqui se cargaran los datos del producto despues de tipiar el codigodeBarra
                // this.listaDetalleFactura.add(new Detallefactura(null,null, this.producto.getCodBarra(),this.producto.getNombreProducto(),this.producto.getPrecioVenta(),0,new Double(0)));

                //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del Producto Agregado"));
                //limpia
                //     this.codigoBarra=null;
            } else {
                //limpia
                this.codigoBarra = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos del Producto No encontrado"));
            }
            this.transaction.commit();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del Cliente Agregado"));
        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println("Error" + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //metodo para mostrar datos clientes por medio del codigo
    public void agregarDatosProductos2() {

        if (!(this.CantidadProducto2.matches("[0-9]*")) || this.CantidadProducto2.equals("0") || this.CantidadProducto2.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad es incorrecta"));
            this.CantidadProducto2 = "";
        } else {
            //aqui se cargaran los datos del producto despues de tipiar el codigodeBarra
            this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(), this.producto.getNombreProducto(),
                    this.producto.getPrecioVenta(), Integer.parseInt(CantidadProducto2), new Double(Integer.parseInt(CantidadProducto2) * this.producto.getPrecioVenta())));
            this.CantidadProducto2 = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del Producto Agregado"));
            //llamar al metodo calcular total factura
            this.totalFacturaVenta();
        }
    }

    //metodo para calcular venta
    public void totalFacturaVenta() {
        Double totalFacturaVenta = new Double("0");
        try {
            for (Detallefactura item : listaDetalleFactura) {
                /*Esta porqueria no funciono
                 BigDecimal totalVentaPorProducto=item.getPrecioVenta() * ((item.getCantidad()));
                 item.setTotal(totalVentaPorProducto);
                 totalFacturaVenta=totalFacturaVenta.add(totalVentaPorFactura);*/
                /*esto lo cree yo jajaja
                 primero multiplica el precio del producto por la cantidad*/
                float totalVentaPorProducto = item.getPrecioVenta() * item.getCantidad();
                /*comienza a trabajar el bucle*/
                item.setTotal(totalVentaPorProducto);
                /*se define el valor del total*/
                totalFacturaVenta = Double.sum(totalFacturaVenta, totalVentaPorProducto);
            }
            this.factura.setTotalVenta(totalFacturaVenta);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    //metodo para quitar producto de la factura
    public void quitarProductoDetalleFactura(String codBarra, Integer filaSeleccionada) {
        try {
            int i = 0;
            for (Detallefactura item : this.listaDetalleFactura) {
                //pregunta si el codigo de barra y la posicion del registro coinciden
                //por que o sino elimina el primero que encuentra y no en el que se posiciona
                if (item.getCodBarra().equals(codBarra)&&filaSeleccionada.equals(i)) {
                    this.listaDetalleFactura.remove(i);
                    break;
                }
                i++;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Información", "Se retiro el producto de la factura"));
            //recalcula el valor de la factura para actualizar el total a vender
            this.totalFacturaVenta();
            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
}
