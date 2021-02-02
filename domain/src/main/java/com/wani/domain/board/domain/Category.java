package com.wani.domain.board.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;


    private String categoryName;

    private Category parentCategory;

    private List<Category> childCategory;


}
