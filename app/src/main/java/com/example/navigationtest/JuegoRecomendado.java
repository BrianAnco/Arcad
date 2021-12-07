package com.example.navigationtest;

public class JuegoRecomendado {
    private String j_nombre, j_enlace, j_lanzamiento, j_genero, j_precio ,j_urlimagen;
    private int j_id;

    public JuegoRecomendado(String j_nombre, String j_enlace, String j_lanzamiento, String j_genero, String j_precio, String j_urlimagen, int j_id) {
        this.j_nombre = j_nombre;
        this.j_enlace = j_enlace;
        this.j_lanzamiento = j_lanzamiento;
        this.j_genero = j_genero;
        this.j_precio = j_precio;
        this.j_urlimagen = j_urlimagen;
        this.j_id = j_id;
    }

    public String getJ_nombre() {
        return j_nombre;
    }

    public void setJ_nombre(String j_nombre) {
        this.j_nombre = j_nombre;
    }

    public String getJ_enlace() {
        return j_enlace;
    }

    public void setJ_enlace(String j_enlace) {
        this.j_enlace = j_enlace;
    }

    public String getJ_lanzamiento() {
        return j_lanzamiento;
    }

    public void setJ_lanzamiento(String j_lanzamiento) {
        this.j_lanzamiento = j_lanzamiento;
    }

    public String getJ_genero() {
        return j_genero;
    }

    public void setJ_genero(String j_genero) {
        this.j_genero = j_genero;
    }

    public String getJ_precio() {
        return j_precio;
    }

    public void setJ_precio(String j_precio) {
        this.j_precio = j_precio;
    }

    public String getJ_urlimagen() {
        return j_urlimagen;
    }

    public void setJ_urlimagen(String j_urlimagen) {
        this.j_urlimagen = j_urlimagen;
    }

    public int getJ_id() {
        return j_id;
    }

    public void setJ_id(int j_id) {
        this.j_id = j_id;
    }
}
