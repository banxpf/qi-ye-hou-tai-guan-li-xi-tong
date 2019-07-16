package com.xpf.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.xpf.dao.IProductDao;
import com.xpf.domian.Product;
import com.xpf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
//    查询所有产品
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page,size);

        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("productList",pageInfo);
        mv.setViewName("product-list");
        return mv;

    }

//    添加产品
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {

         productService.save(product);
         return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Product product= productService.findById(id);
        mv.addObject("product",product);
        mv.setViewName("product-update");
        return mv;
    }
    //修改产品内容
    @RequestMapping("/updateProduct.do")
    public String updateProduct(Product product) throws Exception {
        productService.updateProduct(product);
        return "redirect:findAll.do";
    }
//    删除产品
    @RequestMapping("/deleteProduct.do")
    public String deleteProduct(String id) throws Exception {
        productService.deleteProduct(id);
        return "redirect:findAll.do";

    }


}
