package com.example.springboot03datajpapostgresql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "kisi_adres")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Adres implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_kisi_adres", allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi_adres", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "adres", length = 100)
    private String adres;

    @Enumerated
    private AdresTip adresTip;

    @Column(name = "aktif")
    private boolean aktif;

    @ManyToOne
    @JoinColumn(name = "kisi_adres_id")
    private Kisi kisi;

    public enum AdresTip{
        EV_ADRESI,
        IS_ADRESI,
        DIGER
    }

}
