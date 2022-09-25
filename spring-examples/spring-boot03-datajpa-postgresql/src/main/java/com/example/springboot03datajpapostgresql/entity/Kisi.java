package com.example.springboot03datajpapostgresql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kisi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class Kisi {

    @Id
    @SequenceGenerator(name = "seq_kisi", allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "adi", length = 100)
    private String adi;

    @Column(name = "soyadi", length = 100)
    private String soyadi;

    @OneToMany
    @JoinColumn(name = "kisi_adres_id")
    private List<Adres> adresleri;



}
