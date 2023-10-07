package com.oxahex.domain.entity;

import com.oxahex.domain.converter.OrganizationCodeConverter;
import com.oxahex.domain.converter.ProductCodeConverter;
import com.oxahex.domain.type.OrganizationCode;
import com.oxahex.domain.type.ProductCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_INFO")
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long productId;

    @Column(name = "org_cd")
    @Convert(converter = OrganizationCodeConverter.class)
    private OrganizationCode organizationCode;

    @Column(name = "prod_cd")
    @Convert(converter = ProductCodeConverter.class)
    private ProductCode productCode;

    @Column(name = "prod_nm")
    private String productName;

    @Column(name = "prod_min_intr")
    private Double productMinimumInterest;

    @Column(name = "prod_max_intr")
    private Double productMaximumInterest;
}
