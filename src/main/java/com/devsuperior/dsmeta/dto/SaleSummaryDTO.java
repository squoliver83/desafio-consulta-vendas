package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

import java.util.List;

public class SaleSummaryDTO {

    private String sellerName;

    private Double total;

    public SaleSummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleSummaryDTO(Seller entity) {
        sellerName = entity.getName();
        List<SaleMinDTO> sales = entity.getSales().stream().map(SaleMinDTO::new).toList();
        total = 0.0;
        if(!sales.isEmpty()) {
            for(SaleMinDTO s: sales) {
                total += s.getAmount();
            }
        }
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
