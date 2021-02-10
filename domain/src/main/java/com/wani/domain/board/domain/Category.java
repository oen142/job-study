package com.wani.domain.board.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;


    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Category> child = new ArrayList<>();


    public void addChildCategory(Category child) {
        this.child.add(child);
        child.addParent(this);
    }

    private void addParent(Category category) {
        this.parent.addParent(category);
    }*/
}
