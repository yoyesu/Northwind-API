package com.sparta.northwindapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "territories")
public class Territory {
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TerritoryID", nullable = false, length = 20)
    private String id;

    @Size(max = 50)
    @NotNull
    @Column(name = "TerritoryDescription", nullable = false, length = 50)
    private String territoryDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

}