package net.kotlinandroid.rxjavademo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 2017/5/11.
 * <p>
 * 更新时间 2017/5/11
 * 更新描述 ${TODO}
 */

public interface BaiduService {
   @GET("/")
   Call<String> getBaiDu();
}
