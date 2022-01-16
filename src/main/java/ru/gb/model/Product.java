package ru.gb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Product {

    private int id;
    private String title;
    private double cost;
}
