package com.hussain.walletapplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @NotBlank(message = "account name field cannot be empty")
    @Size(min = 2, max=30)
    private String Accountname;
    @NotBlank(message = "please fill this field")
    @Size( max=70)
    private String Accountnumber;
    @Size(min = 2, max=30)
    private String description;
    @Min(1)
    @Max(3)
    private Integer priority;
    private Double currentBalance;
    @PrePersist
    public void Setbalance() { this.currentBalance = new Double(0); }
}
