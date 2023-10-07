package com.oxahex.product.service;

import com.oxahex.domain.entity.ProductInfo;
import com.oxahex.domain.repository.ProductInfoRepository;
import com.oxahex.domain.type.ProductCode;
import com.oxahex.product.dto.ProductInfoDto;
import com.oxahex.domain.type.OrganizationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductInfoRepository productInfoRepository;

    public List<ProductInfoDto> getProductInfoList(OrganizationCode code) {



        return null;

    }

    public void addProductInfo(ProductInfoDto.Request request) {

        System.out.println("service:: request -> " + request);

        ProductCode productCode = ProductCode.getCode(request.getProductCode());
        OrganizationCode organizationCode = OrganizationCode.getCode(request.getOrganizationCode());

        // -> 현재 상품을 식별할 수 있는 데이터가 주어지지 않았으므로 존재 여부 확인하지 않고 그냥 모두 추가

        // 상품 추가
        ProductInfo productInfo = productInfoRepository.save(
                ProductInfo.builder()
                        .organizationCode(organizationCode)
                        .productCode(productCode)
                        .productName(request.getProductName())
                        .productMaximumInterest(request.getProductMaximumInterest())
                        .productMinimumInterest(request.getProductMinimumInterest()).build()
        );

        System.out.println("추가된 상품 -> " + productInfo);

        productInfoRepository.save(productInfo);
    }
}
