package com.example.Neobis.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "baskets_products",
        joinColumns = @JoinColumn(name = "basket_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;
    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;


    public Map<Long, Product> getProductMap(){
        Map<Long, Product> productMap = products
                .stream().collect(Collectors.toMap(Product::getId, p -> p));
        return productMap;
    }





}
