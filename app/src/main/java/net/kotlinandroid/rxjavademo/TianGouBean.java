package net.kotlinandroid.rxjavademo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2017/5/11.
 * <p>
 * 更新时间 2017/5/11
 * 更新描述 ${TODO}
 */

public class TianGouBean {

    /**
     * total : 111096
     * tngou : [{"count":14,"description":"","fcount":0,"food":"","id":111182,"images":"","img":"","keywords":" ","name":"经典美味奶油泡芙","rcount":0}]
     */
    @SerializedName("total")
    private int total;
    @SerializedName("tngou")
    private List<TngouBean> tngou;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        /**
         * count : 14
         * description :
         * fcount : 0
         * food :
         * id : 111182
         * images :
         * img :
         * keywords :
         * name : 经典美味奶油泡芙
         * rcount : 0
         */
        @SerializedName("count")
        private int count;
        @SerializedName("description")
        private String description;
        @SerializedName("fcount")
        private int    fcount;
        @SerializedName("food")
        private String food;
        @SerializedName("id")
        private int    id;
        @SerializedName("images")
        private String images;
        @SerializedName("img")
        private String img;
        @SerializedName("keywords")
        private String keywords;
        @SerializedName("name")
        private String name;
        @SerializedName("rcount")
        private int    rcount;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }
    }
}
