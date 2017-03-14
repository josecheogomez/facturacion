package sys.model;
// Generated 01/11/2016 11:18:28 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer codUsuario;
     private String nombreUsuario;
     private float precioVenta;
     private int stockMinimo;
     private int stockActual;
     private String codBarra;
     private Set<Detallefactura> detallefacturas = new HashSet<Detallefactura>(0);

    public Usuario() {
    }

	
    public Usuario(String nombreUsuario, float precioVenta, int stockMinimo, int stockActual, String codBarra) {
        this.nombreUsuario = nombreUsuario;
        this.precioVenta = precioVenta;
        this.stockMinimo = stockMinimo;
        this.stockActual = stockActual;
        this.codBarra = codBarra;
    }
    public Usuario(String nombreUsuario, long precioVenta, int stockMinimo, int stockActual, String codBarra, Set<Detallefactura> detallefacturas) {
       this.nombreUsuario = nombreUsuario;
       this.precioVenta = precioVenta;
       this.stockMinimo = stockMinimo;
       this.stockActual = stockActual;
       this.codBarra = codBarra;
       this.detallefacturas = detallefacturas;
    }
   
    public Integer getCodUsuario() {
        return this.codUsuario;
    }
    
    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public float getPrecioVenta() {
        return  this.precioVenta;
    }
    
    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
    public int getStockMinimo() {
        return this.stockMinimo;
    }
    
    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    public int getStockActual() {
        return this.stockActual;
    }
    
    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }
    public String getCodBarra() {
        return this.codBarra;
    }
    
    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }
    public Set<Detallefactura> getDetallefacturas() {
        return this.detallefacturas;
    }
    
    public void setDetallefacturas(Set<Detallefactura> detallefacturas) {
        this.detallefacturas = detallefacturas;
    }




}

