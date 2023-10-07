package com.oxahex.domain.entity;

import com.oxahex.domain.converter.OrganizationCodeConverter;
import com.oxahex.domain.type.OrganizationCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Table(name = "PRODUCT_LIST")
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long productListId;

    @Column(name = "org_cd")
    @Convert(converter = OrganizationCodeConverter.class)
    private OrganizationCode organizationCode;


    @Column(name = "prod_cd")
    @OneToMany(mappedBy = "productId")
    private Set<ProductInfo> productInfoList = new HashSet<>();

    public void add(ProductInfo productInfo) {
        this.productInfoList.add(productInfo);
    }
}
