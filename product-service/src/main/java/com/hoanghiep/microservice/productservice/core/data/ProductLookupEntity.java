package com.hoanghiep.microservice.productservice.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product_lookup")
@AllArgsConstructor
@NoArgsConstructor
public class ProductLookupEntity implements Serializable {

    @Id
    private String productId;
    @Column
    private String title;
}
