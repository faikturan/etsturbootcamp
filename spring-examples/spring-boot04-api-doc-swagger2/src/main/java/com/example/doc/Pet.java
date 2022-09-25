package com.example.doc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Benim Pet nesnem", description = "Benim Pet description")
public class Pet {
    @ApiModelProperty(value = "Pet nesnesinin Tekil id alanı")
    private int id;

    @ApiModelProperty(value = "Pet nesnesinin ad alanı")
    private String name;

    @ApiModelProperty(value = "Pet nesnesinin tarih alanı")
    private Date date;

}
