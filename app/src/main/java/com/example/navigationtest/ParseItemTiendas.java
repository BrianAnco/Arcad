package com.example.navigationtest;

public class ParseItemTiendas {
    private String opinion;
    private String tienda;
    private String precio;
    private String url;

    public ParseItemTiendas(String opinion, String tienda, String precio, String url) {
        this.opinion = opinion;
        this.tienda = tienda;
        this.precio = precio;
        this.url = url;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
