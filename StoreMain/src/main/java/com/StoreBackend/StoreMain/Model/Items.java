package com.StoreBackend.StoreMain.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int itemName;
    private int itemDescription;
    private int itemPrice;
    private int remainingQuantity;

}
