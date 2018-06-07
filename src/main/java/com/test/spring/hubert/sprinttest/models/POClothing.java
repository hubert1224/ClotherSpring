package com.test.spring.hubert.sprinttest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "poclothing")
public class POClothing
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private Date purchaseDate;

    @NotNull
    private double wornTimeHours = 0;

    @OneToOne
    private ClothingCategory category;

    @OneToOne
    private ClothingState state;

    @ManyToOne
    private User owner;
}
