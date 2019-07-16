package com.xpf.dao;

import com.xpf.domian.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductDao {

    @Select("select * from product")
    public List<Product> findAll() throws Exception ;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)" +
            " values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})" )
    public void save(Product product);

    @Select("select *from product where id = #{id}")
    public Product findById(String id)throws  Exception;

    @Delete("delete from product where id =#{id}")
    void deleteProduct(String id) throws Exception;

    @Update("update product set productNum=#{productNum},productName=#{productName},departureTime=#{departureTime},cityName=#{cityName}," +
            "productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
    void updateProduct(Product product)throws Exception;
}
