package com.benkitoucoders.myecommerce.dtos.user;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserRequestDto implements Serializable {
    private Long id;

    private String firstName;

    private String secondName;

    private String phone;

    private Date birthDayDate;
}
