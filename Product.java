package com.Advaith.damodar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {

   @Id
   @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private boolean productAvailable;
    private  int stockQuantity;


    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageDate;

 public byte[] getImageDate() {
  return imageDate;
 }

 public void setImageDate(byte[] imageDate) {
  this.imageDate = imageDate;
 }

 public String getImageType() {
  return imageType;
 }

 public void setImageType(String imageType) {
  this.imageType = imageType;
 }

 public String getImageName() {
  return imageName;
 }

 public void setImageName(String imageName) {
  this.imageName = imageName;
 }
}
