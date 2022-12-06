package com.ute.ecwebapp.models;

import com.ute.ecwebapp.beans.Category;
import com.ute.ecwebapp.utils.DbUtils;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryModel {
//    public static List<Category> findAll(){
//        return new ArrayList<>(
//                Arrays.asList(
//                        new Category(1,"Sách"),
//                        new Category(2,"Quần áo"),
//                        new Category(3,"Điện thoại"),
//                        new Category(4,"Laptop"),
//                        new Category(5,"Loa")
//
//                )
//        );
//    }



    public static List<Category> findAll() {
        try (Connection con = DbUtils.getConnection()) {
            final String query = "SELECT * FROM categories";
            return con.createQuery(query).executeAndFetch(Category.class);
        }
    }


    public static void add(Category c){
        String insertSql = "insert into categories(CatName) values (:CatName)";

        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(insertSql)
                    .addParameter("CatName", c.getCatName())
                    .executeUpdate();
        }
    }

    public static Category findById(int id) {
        try (Connection con = DbUtils.getConnection()) {
            final String query = "SELECT * FROM categories WHERE CatID = :CatID";
            List<Category> list = con.createQuery(query).addParameter("CatID",id).executeAndFetch(Category.class);
            if (list.size() == 0){
                return null;
            }
            return list.get(0);
        }
    }

    public static void update(Category c){
        String updateSql = "UPDATE categories SET  CatName = :CatName WHERE CatID = :CatID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(updateSql)
                    .addParameter("CatID", c.getCatID())
                    .addParameter("CatName", c.getCatName())
                    .executeUpdate();
        }
    }

    public static void delete(int id){
        String deleteSql = "DELETE FROM categories WHERE CatID = :CatID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(deleteSql)
                    .addParameter("CatID", id)
                    .executeUpdate();
        }
    }

}
