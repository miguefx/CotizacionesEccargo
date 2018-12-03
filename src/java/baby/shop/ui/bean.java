/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baby.shop.ui;

import baby.shop.da.NewMain;
import baby.shop.da.dao;
import baby.shop.da.voDetalle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matc_
 */
@Named(value = "bean")
@SessionScoped
public class bean implements Serializable {

    private dao objdao;

    private String idCotizacion;
    private String fechaCotizacion;
    private String prefijoNumero;
    private String prefijo;
    private static List<voDetalle> listTable;
    private static List<voDetalle> listTableAux;
    private List<voDetalle> listTableSelection;
    private List<voDetalle> listTableSelectio4444;
    private voDetalle select;

    private String cantidad;

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public voDetalle getSelect() {
        return select;
    }

    public void setSelect(voDetalle select) {
        this.select = select;
    }

    public List<voDetalle> getListTableSelectio4444() {
        return listTableSelectio4444;
    }

    public void setListTableSelectio4444(List<voDetalle> listTableSelectio4444) {
        this.listTableSelectio4444 = listTableSelectio4444;
    }

    public List<voDetalle> getListTable() {
        return listTable;
    }

    public void setListTable(List<voDetalle> listTable) {
        this.listTable = listTable;
    }
    private String numero;
    private String razonSocial;
    private String representante;
    private String direccionEmpresa;
    private String ciudadEpresa;

    private String prefij;

    public String getPrefij() {
        return prefij;
    }

    public void setPrefij(String prefij) {
        this.prefij = prefij;
    }

    public dao getObjdao() {
        return objdao;
    }

    public void setObjdao(dao objdao) {
        this.objdao = objdao;
    }

    private String actualizado;

    public String getActualizado() {
        return actualizado;
    }

    public void setActualizado(String actualizado) {
        this.actualizado = actualizado;
    }

    public void modificarPrioridad(ActionEvent egt) {
        try {
            mensaje = "No fue posible actualizar por favor inténtelo más tarde.";
            objdao = new dao();
            if (objdao.modificarCampo(idCotizacion)) {
                Boolean bandera = false;
                for (int i = 0; i < listTableSelection.size(); i++) {
                    String fecha = listTableSelection.get(i).getFechaInicio();
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        cal.setTime(sdf.parse(fecha));
                    } catch (ParseException ex) {
                        Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int j = 30;
                    if (!listTableSelection.get(i).getMeses().isEmpty() && !listTableSelection.get(i).getMeses().equals("0")) {
                        j = 30 * Integer.parseInt(listTableSelection.get(i).getMeses());

                    }
                    cal.add(Calendar.DATE, +j);
                    sdf.setCalendar(cal);
                    fecha = sdf.format(cal.getTime());

                    voDetalle detal = new voDetalle();
                    detal.setCantidad(listTableSelection.get(i).getCantidad());
                    detal.setFechaInicio(listTableSelection.get(i).getFechaInicio());
                    detal.setCantidad(listTableSelection.get(i).getCantidad());
                    detal.setValorVenta(listTableSelection.get(i).getValorVenta());
                    detal.setMeses(listTableSelection.get(i).getMeses());

                    bandera = objdao.modificarCampoDetalle(listTableSelection.get(i).getIdCotizacionDetalle(), fecha, detal);
                }
                if (bandera) {
                    mensaje = "Cotizacion confirmada correctamente";
                }

            } else {
                mensaje = "No fue posible actualizar por favor inténtelo más tarde.";

            }

        } catch (Exception e) {
        }
    }

    public List<voDetalle> onCellEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Se realizó la modificación");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        for (int i = 0; i < listTable.size(); i++) {
            if (listTable.get(i).getIdCotizacionDetalle() == ((voDetalle) event.getObject()).getIdCotizacionDetalle()) {
                String valorContenedor1 = listTable.get(i).getValorContenedor();
                Double valorContenedorFinal = Double.valueOf(valorContenedor1);
                int valorContenedorInt = valorContenedorFinal.intValue();

                String nuevoCantidadString = ((voDetalle) event.getObject()).getCantidad();
                int nuevaCantidad = Integer.parseInt(nuevoCantidadString);

                int auxResultado = valorContenedorInt * nuevaCantidad;

                ((voDetalle) event.getObject()).setValorVenta(String.valueOf(auxResultado));
            }
        }

//        voDetalle detalle = (voDetalle) o.getRowData();
//        String prueba = detalle.getIdCotizacionDetalle();
//        System.out.println(o.getRowKey());
//        for (int i = 0; i < listTable.size(); i++) {
//            if (listTable.get(i).getIdCotizacionDetalle()==o.getRowKey()) {
//                String valorContenedor1= listTable.get(i).getValorContenedor();
//                Double valorContenedorFinal=Double.valueOf(valorContenedor1);
//                int valorContenedorInt=valorContenedorFinal.intValue();
//                
//                String nuevoCantidadString= newValue.toString();
//                int nuevaCantidad=Integer.parseInt(nuevoCantidadString);
//                
//                int auxResultado=valorContenedorInt*nuevaCantidad;
//                detalle.setValorVenta(String.valueOf(auxResultado));
//                detalle.setCantidad(newValue.toString());
//                listTable.set(i,detalle);
//            }
//            
//        }
        return listTable;

    }

    public void onCellEdit2(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(String idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public String getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(String fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public String getPrefijoNumero() {
        objdao = new dao();
        String resultado = objdao.retirarNombre(idCotizacion);
        String[] auxResultado = resultado.split("¬");
        fechaCotizacion = auxResultado[0];
        prefijoNumero = auxResultado[1];
        prefijo = auxResultado[2];
        numero = auxResultado[3];
        razonSocial = auxResultado[4];
        representante = auxResultado[5];
        direccionEmpresa = auxResultado[6];
        ciudadEpresa = auxResultado[7];
        fechaCotizacion = fechaCotizacion.substring(0, 11);
        listTable = objdao.llenarList(idCotizacion);
        return prefijoNumero;
    }

    public void setPrefijoNumero(String prefijoNumero) {
        this.prefijoNumero = prefijoNumero;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getCiudadEpresa() {
        return ciudadEpresa;
    }

    public void setCiudadEpresa(String ciudadEpresa) {
        this.ciudadEpresa = ciudadEpresa;
    }

    private String mensaje = "";

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<voDetalle> getListTableSelection() {
        listTableSelection = new dao().llenarList(idCotizacion);
        return listTableSelection;
    }

    public void setListTableSelection(List<voDetalle> listTableSelection) {
        this.listTableSelection = listTableSelection;
    }

    public bean() {
    }

}
