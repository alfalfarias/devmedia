/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author simon
 */

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import play.db.ebean.Model;

@Entity
@Table(name="sale_products")
public class SaleProduct extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(nullable=false)
    public String name;
    @Column(nullable=false)
    public Double price;
    
    @OneToOne(cascade = CascadeType.ALL)
    public Sale sale; 
    
    @OneToMany(mappedBy = "saleProduct", cascade=CascadeType.ALL)
    public List<SaleProductSupply> saleProductSupplies = new ArrayList<SaleProductSupply>();

    public static List<Product> all() {
      return find.all();
    }
    
    public static void create(SaleProduct saleProduct) {
      saleProduct.save();
    }
    
    public static Model.Finder<Long,Product> find = new Model.Finder(
        Long.class, SaleProduct.class
    );
}