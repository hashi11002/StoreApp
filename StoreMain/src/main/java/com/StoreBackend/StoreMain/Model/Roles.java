package com.StoreBackend.StoreMain.Model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
}
