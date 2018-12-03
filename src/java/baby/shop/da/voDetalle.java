/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baby.shop.da;

/**
 *
 * @author matc_
 */
public class voDetalle {
    
    private String concepto;
    private String tamañoContenedor;
    private String cantidad;
    private String valorContenedor;
    private String meses;
    private String fechaInicio;
    private String idCotizacionDetalle;
    private String valorVenta;

    public String getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(String valorVenta) {
        this.valorVenta = valorVenta;
    }

    public String getIdCotizacionDetalle() {
        return idCotizacionDetalle;
    }

    public void setIdCotizacionDetalle(String idCotizacionDetalle) {
        this.idCotizacionDetalle = idCotizacionDetalle;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getTamañoContenedor() {
        return tamañoContenedor;
    }

    public void setTamañoContenedor(String tamañoContenedor) {
        this.tamañoContenedor = tamañoContenedor;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getValorContenedor() {
        return valorContenedor;
    }

    public void setValorContenedor(String valorContenedor) {
        this.valorContenedor = valorContenedor;
    }

    public String getMeses() {
        return meses;
    }

    public void setMeses(String meses) {
        this.meses = meses;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
}
