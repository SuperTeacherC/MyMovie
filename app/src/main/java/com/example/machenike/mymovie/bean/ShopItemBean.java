package com.example.machenike.mymovie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Machenike on 2016/12/9.
 */
public class ShopItemBean implements Serializable {

    /**
     * list : [{"dealid":33433440,"pic":"http://p0.meituan.net/348.348.60/movie/5e38e40a722bdc5657b42ae33b6f9a35123937.jpg","price":58,"title":"《星球大战》手机壳","value":79},{"dealid":33383516,"pic":"http://p1.meituan.net/348.348.60/movie/8d3ed4329c93e5a29095118405441ae5298148.jpg","price":88,"title":"星球大战 滚动机器人BB-8公仔","value":128},{"dealid":33511123,"pic":"http://p1.meituan.net/348.348/movie/600b1359dd7687eca93c190be1b286e688256.png@60q","price":1399,"title":"星球大战 黑色系列原力光剑珍藏版","value":1499},{"dealid":33413014,"pic":"http://p1.meituan.net/348.348.60/movie/3bb39afc318668a69caffaf577eb9fc7105696.jpg","price":129,"title":"《星球大战》系列U盘16G","value":169},{"dealid":33383502,"pic":"http://p1.meituan.net/348.348.60/movie/1498f49a599efa5733b7fa3c76cb17f5285034.jpg","price":88,"title":"星球大战 风暴兵公仔模型","value":128},{"dealid":33043553,"pic":"http://p1.meituan.net/348.348.60/movie/4c4f28951a5a58fa2890d6c2d2106abf270169.jpg","price":88,"title":"星球大战 克隆兵白兵摇头公仔模型","value":128},{"dealid":33382715,"pic":"http://p0.meituan.net/348.348.60/movie/171f25c12c716d46386af99b29079605132512.jpg","price":699,"title":"《星球大战》死星悬浮音响","value":999},{"dealid":33384764,"pic":"http://p1.meituan.net/348.348/movie/40dff6d919b12892a5f217f1381b241e300580.jpg@60Q","price":88,"title":"星球大战法斯马 公仔模型","value":128},{"dealid":33384747,"pic":"http://p0.meituan.net/348.348.60/movie/cdc8be29262abfbeb564bc97cb4ac66d305873.jpg","price":88,"title":"星球大战 凯洛·伦 公仔模型","value":128},{"dealid":33499227,"pic":"http://p0.meituan.net/348.348/movie/34528b16abbe7eb69286ab7b69d1e27c113375.jpg@60q","price":119,"title":"星球大战 升级版英雄人物系列","value":149}]
     * total : 73
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int total;
        /**
         * dealid : 33433440
         * pic : http://p0.meituan.net/348.348.60/movie/5e38e40a722bdc5657b42ae33b6f9a35123937.jpg
         * price : 58
         * title : 《星球大战》手机壳
         * value : 79
         */

        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int dealid;
            private String pic;
            private int price;
            private String title;
            private int value;

            public int getDealid() {
                return dealid;
            }

            public void setDealid(int dealid) {
                this.dealid = dealid;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}
