package pers.lbf.springbootshiro.web.product.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**产品模块
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 10:14
 */
@RestController
@RequestMapping("/product")
public class ProductController {


    @RequiresPermissions("product:get")
    @GetMapping("/get/list")
    public String getProductList() {
        return "productList";
    }

    @RequiresPermissions("product:delete")
    @GetMapping("/delete")
    public String deleteProduct() {
        return "删除产品数据";
    }
}
