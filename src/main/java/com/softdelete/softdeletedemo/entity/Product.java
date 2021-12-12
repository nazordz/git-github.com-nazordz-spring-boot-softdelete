package com.softdelete.softdeletedemo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
@SQLDelete(sql = "UPDATE products SET is_deleted = true WHERE id = ?")
@FilterDef(name = "softDeleteFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"), defaultCondition = "is_deleted = true")
@Filter(name = "softDeleteFilter", condition = "is_deleted = :isDeleted")
// @FilterDef(name = "deletedProductFilter")
// @Filter(name = "deletedProductFilter", condition = "deleted_at is null")
// @FilterDef(name = "allProductFilter")
// @Filter(name = "allProductFilter", condition = "id is not null")
public class Product {
    
    @Id
    @GeneratedValue(generator = "uuidv4")
    @GenericGenerator(name = "uuidv4", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(precision = 15, scale = 2)
    private double price;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
