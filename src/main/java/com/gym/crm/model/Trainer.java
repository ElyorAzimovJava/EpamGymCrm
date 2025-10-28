package com.gym.crm.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Trainer extends User{
    private String specialization;
}
