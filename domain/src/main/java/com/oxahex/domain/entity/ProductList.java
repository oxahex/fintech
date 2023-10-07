//package com.oxahex.domain.entity;
//
//import com.oxahex.domain.converter.OrganizationCodeConverter;
//import com.oxahex.domain.type.OrganizationCode;
//import lombok.RequiredArgsConstructor;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "PRODUCT_LIST")
//@RequiredArgsConstructor
//@ToString
//public class ProductList {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long productListId;
//
//    @Column(name = "org_cd")
//    @Convert(converter = OrganizationCodeConverter.class)
//    private OrganizationCode organizationCode;
//
//
//    @Column(name = "prod_cd")
//    @OneToMany(mappedBy = "productId")
//    private List<ProductInfo> productInfoList = new ArrayList<>();
//
//    public void add(ProductInfo productInfo) {
//        this.productInfoList.add(productInfo);
//    }
//}
