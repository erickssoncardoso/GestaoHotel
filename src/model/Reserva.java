package model;

import java.util.Date;

public class Reserva {
    private int id;
    private int idCliente;
    private int idHotel;
    private int idAcomodacao;
    private Date dataCheckin;
    private Date dataCheckout;

    public Reserva() {
    }

    public Reserva(int idCliente, int idHotel, int idAcomodacao, Date dataCheckin, Date dataCheckout) {
        this.idCliente = idCliente;
        this.idHotel = idHotel;
        this.idAcomodacao = idAcomodacao;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdAcomodacao() {
        return idAcomodacao;
    }

    public void setIdAcomodacao(int idAcomodacao) {
        this.idAcomodacao = idAcomodacao;
    }

    public Date getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(Date dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public Date getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(Date dataCheckout) {
        this.dataCheckout = dataCheckout;
    }
}
