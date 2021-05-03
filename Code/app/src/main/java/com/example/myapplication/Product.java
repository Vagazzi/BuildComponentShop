package com.example.myapplication;

public class Product {

    public String name;
    public int price;
    public int image;
    public boolean box;


    public Product(String _describe, int _price, int _image, boolean _box) {
        name = _describe;
        price = _price;
        image = _image;
        box = _box;
    }
}