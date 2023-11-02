package com.example.Neobis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    List<Product> products;

    public Map<Long, Product> getProductMap(){
        Map<Long, Product> productMap = products
                .stream().collect(Collectors.toMap(Product::getId, p -> p));
        return productMap;
    }




}
