package com.oxahex.product.service;

import com.oxahex.domain.entity.ProductInfo;
import com.oxahex.domain.repository.ProductInfoRepository;
import com.oxahex.domain.type.ProductCode;
import com.oxahex.product.dto.ProductInfoDto;
import com.oxahex.domain.type.OrganizationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductInfoRepository productInfoRepository;

    public List<ProductInfoDto.Response> getProductInfoList(OrganizationCode organizationCode) {

        // 기관명과 일치하는 상품을 리스트에서 찾음
        List<ProductInfo> productList = productInfoRepository.findAllByOrganizationCode(organizationCode);




        return productList.stream().map(x -> ProductInfoDto.Response.builder()
                .productName(x.getProductName())
                .productCode(x.getProductCode())
                .productMaximumInterest(x.getProductMaximumInterest())
                .productMinimumInterest(x.getProductMinimumInterest())
                .organizationCode(x.getOrganizationCode()).build()).collect(Collectors.toList());

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

        // 상품 정보 추가
        // 상품 id를 상품 리스트에 추가
        productInfoRepository.save(productInfo);

        System.out.println("추가된 상품 -> " + productInfo);
    }
}
