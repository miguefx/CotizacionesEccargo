/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baby.shop.da;

import Controller.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author matc_
 */
public class dao {

    private Connection conexion = null;
    private ResultSet rs = null;
    private PreparedStatement statment = null;
    private voDetalle objVoDetalle;

    public dao() {
        try {
            this.conexion = Conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("No se  pudo realizar la conexion");
        }
    }

    public boolean modificarCampo(String idCotizacion) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);

            statment = conexion.prepareStatement("UPDATE COTIZACIONES SET ESTADO='PREAPROBADO',FECHAPREAPROBACION='" + reportDate + "',CONFIRMADESDE ='CORREO' WHERE(IDCOTIZACION='" + idCotizacion + "')");
            int resultado = statment.executeUpdate();
            if (resultado != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean modificarCampoDetalle(String idDetalleCotizacion, String fechaFin, voDetalle objVoDetalle) {
        try {
            statment = conexion.prepareStatement("UPDATE COTIZACIONESDETALLES SET CONFIRMADO=0 , FECHATERMINACION='" + fechaFin + "',FECHAINICIO='" + objVoDetalle.getFechaInicio() + "', CANTIDAD='" + objVoDetalle.getCantidad() + "', NUMEROMESES='" + objVoDetalle.getMeses() + "', VALORVENTA='" + objVoDetalle.getValorVenta() + "' WHERE(IDCOTIZACIONDETALLE ='" + idDetalleCotizacion + "')");
            int resultado = statment.executeUpdate();
            if (resultado != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public String retirarNombre(String idCotizacion) {
        String respuesta = "";
        String nombreCompleto = "";
        String documento = "";
        String telefono = "";
        String celular = "";
        String email = "";
        String tipoReclamo = "";
        String descripcion = "";
        String prioridad = "";
        try {
            statment = conexion.prepareStatement("SELECT * FROM COTIZACIONES  WHERE (IDCOTIZACION='" + idCotizacion + "')");
            rs = statment.executeQuery();
            while (rs.next()) {
                nombreCompleto = rs.getString("FECHACOTIZACION");
                documento = rs.getString("PREFIJONUMEROYANIO");
                telefono = rs.getString("PREFIJO");
                celular = rs.getString("NUMERO");
                email = rs.getString("RAZONSOCIAL");
                tipoReclamo = rs.getString("REPRESENTANTE");
                descripcion = rs.getString("DIRECCIONENTREGA");
                prioridad = rs.getString("CIUDADEMPRESA");
            }
            respuesta = nombreCompleto + "¬" + documento + "¬" + telefono + "¬" + celular + "¬" + email + "¬" + tipoReclamo + "¬" + descripcion + "¬" + prioridad;
            return respuesta;
        } catch (Exception e) {
        }
        return respuesta;
    }

    public List<voDetalle> llenarList(String idCotizacion) {
        try {
            statment = conexion.prepareStatement("SELECT * FROM COTIZACIONESDETALLES  WHERE (IDCOTIZACION='" + idCotizacion + "')");
            rs = statment.executeQuery();
            List<voDetalle> listPrueb = new ArrayList<voDetalle>();
            String prueba = "";

            while (rs.next()) {
                objVoDetalle = new voDetalle();
                objVoDetalle.setConcepto(rs.getString("CONCEPTO"));
                objVoDetalle.setCantidad((rs.getString("CANTIDAD")));
                objVoDetalle.setIdCotizacionDetalle(rs.getString("IDCOTIZACIONDETALLE"));
                prueba = rs.getString("FECHAINICIO");
                if (prueba != null) {
                    prueba = prueba.substring(0, 10);
                }
                objVoDetalle.setFechaInicio(prueba);
                objVoDetalle.setMeses(rs.getString("NUMEROMESES"));
                objVoDetalle.setTamañoContenedor(rs.getString("TAMANOCONTENEDOR"));
                objVoDetalle.setValorContenedor(rs.getString("VALORCONCEPTO"));
                objVoDetalle.setValorVenta(rs.getString("VALORVENTA"));
                listPrueb.add(objVoDetalle);
            }
            return listPrueb;
        } catch (Exception e) {
        }
        return null;
    }
}
