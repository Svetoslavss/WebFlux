package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "customer")
public class Customer {
    @Id
    private Long id;
    @Column(value = "firstname")
    private String firstName;
    @Column(value = "lastname")
    private String lastName;
    private int age;
}
