package com.example.list_view_nang_cao;

public class Fruit {
    private String nameFruit;
    private String descFruit;
    private int imgFruit;

    public Fruit(String name_fruit, String mota_traicay, int image) {
        this.nameFruit = name_fruit;
        this.descFruit = mota_traicay;
        this.imgFruit = image;
    }

    public String getNameFruit() {
        return nameFruit;
    }

    public void setNameFruit(String nameFruit) {
        this.nameFruit = nameFruit;
    }

    public String getDescFruit() {
        return descFruit;
    }

    public void setDescFruit(String descFruit) {
        this.descFruit = descFruit;
    }

    public int getImgFruit() {
        return imgFruit;
    }

    public void setImgFruit(int imgFruit) {
        this.imgFruit = imgFruit;
    }
}
