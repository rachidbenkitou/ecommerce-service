package com.benkitoumiraouycoders.ecommerce.Criteria;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageCriteria {

    private Long id;
    private String name;
    private String active;
    private String isDefault;


}
