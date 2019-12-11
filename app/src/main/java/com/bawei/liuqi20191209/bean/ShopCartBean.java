package com.bawei.liuqi20191209.bean;

import java.util.List;

/**
 * author : Eaves
 * desc   : bean
 * date   : 2019/12/9
 */
public class ShopCartBean {
    /**
     * code : 200
     * orderData : [{"shopId":1,"shopName":"每日蔬鲜专卖店","cartlist":[{"id":1,"shopId":1,"shopName":"每日蔬鲜专卖店","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E8%8F%A0%E8%8F%9C.png","productId":1,"productName":"菠菜","color":"颜色：白色","meal":"套餐：默认套餐","price":3,"count":1},{"id":2,"shopId":1,"shopName":"每日蔬鲜专卖店","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E8%83%A1%E8%90%9D%E5%8D%9C.png","productId":2,"productName":"胡萝卜","color":"颜色：红色","meal":"套餐：默认套餐","price":4,"count":1}]},{"shopId":2,"shopName":"鸡蛋专卖店","cartlist":[{"id":1,"shopId":2,"shopName":"鸡蛋专卖店","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E9%B8%A1%E8%9B%8B.png","productId":1,"productName":"鸡蛋","color":"颜色：白色","meal":"套餐：默认套餐","price":10,"count":1}]}]
     */

    private int code;
    private List<OrderDataBean> orderData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<OrderDataBean> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<OrderDataBean> orderData) {
        this.orderData = orderData;
    }

    public static class OrderDataBean {
        /**
         * shopId : 1
         * shopName : 每日蔬鲜专卖店
         * cartlist : [{"id":1,"shopId":1,"shopName":"每日蔬鲜专卖店","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E8%8F%A0%E8%8F%9C.png","productId":1,"productName":"菠菜","color":"颜色：白色","meal":"套餐：默认套餐","price":3,"count":1},{"id":2,"shopId":1,"shopName":"每日蔬鲜专卖店","defaultPic":"http://blog.zhaoliang5156.cn/api/images/%E8%83%A1%E8%90%9D%E5%8D%9C.png","productId":2,"productName":"胡萝卜","color":"颜色：红色","meal":"套餐：默认套餐","price":4,"count":1}]
         */

        private int shopId;
        private String shopName;
        private List<CartlistBean> cartlist;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public List<CartlistBean> getCartlist() {
            return cartlist;
        }

        public void setCartlist(List<CartlistBean> cartlist) {
            this.cartlist = cartlist;
        }

        public static class CartlistBean {
            /**
             * id : 1
             * shopId : 1
             * shopName : 每日蔬鲜专卖店
             * defaultPic : http://blog.zhaoliang5156.cn/api/images/%E8%8F%A0%E8%8F%9C.png
             * productId : 1
             * productName : 菠菜
             * color : 颜色：白色
             * meal : 套餐：默认套餐
             * price : 3
             * count : 1
             */

            private int id;
            private int shopId;
            private String shopName;
            private String defaultPic;
            private int productId;
            private String productName;
            private String color;
            private String meal;
            private int price;
            private int count;
            private boolean checked;

            public boolean isChecked() {
                return checked;
            }

            public void setChecked(boolean checked) {
                this.checked = checked;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getDefaultPic() {
                return defaultPic;
            }

            public void setDefaultPic(String defaultPic) {
                this.defaultPic = defaultPic;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getMeal() {
                return meal;
            }

            public void setMeal(String meal) {
                this.meal = meal;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
