package com.example.navigationtest;

public class ParseItem {

    private String imgUrl;
    private String title;
    private String description;
    private String precio;
    private String durl;

    public ParseItem() {
    }

    public ParseItem(String imgUrl, String title, String description, String precio, String durl) {
        imgUrl = imgUrl.replace(")","");
        StringBuffer nuevaImagen = new StringBuffer(imgUrl);
        nuevaImagen.replace(0,22,"");
        String imagenStr = nuevaImagen.toString();
        this.imgUrl = imagenStr;
        this.title = title;
        this.description = description;
        this.precio = precio;
        this.durl = durl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDurl() {
        return durl;
    }

    public void setDurl(String durl) {
        this.durl = durl;
    }
}