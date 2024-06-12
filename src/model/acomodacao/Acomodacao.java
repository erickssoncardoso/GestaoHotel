/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.acomodacao;




// Classe abstrata Acomodacao
public abstract class Acomodacao {
    private int id;
    private String tipo;
    private int quantidadeLeitos;
    private double precoBase;
    private int idHotel;

    // Construtor
    public Acomodacao(int id,String tipo, int quantidadeLeitos, double precoBase, int idhotel) {
        this.id=id;
        this.tipo = tipo;
        this.quantidadeLeitos = quantidadeLeitos;
        this.precoBase = precoBase;
        this.idHotel=idhotel;
        
    }

    // Getters e setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidadeLeitos() {
        return quantidadeLeitos;
    }

    public void setQuantidadeLeitos(int quantidadeLeitos) {
        this.quantidadeLeitos = quantidadeLeitos;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idHotel
     */
    public int getIdHotel() {
        return idHotel;
    }

    /**
     * @param idHotel the idHotel to set
     */
    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }
}
