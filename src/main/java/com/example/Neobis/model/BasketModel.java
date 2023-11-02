package com.example.Neobis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketModel {
    private Long id;
    private List<ProductModel> products;
}
