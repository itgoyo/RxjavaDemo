package net.kotlinandroid.rxjavademo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by admin on 2017/5/11.
 * <p>
 * 更新时间 2017/5/11
 * 更新描述 ${TODO}
 */

public interface CookService {
    @GET("/api/cook/list")
    Call<TianGouBean> getTianGou(@Query("id") int id, @Query("page") int page, @Query("rows") int rows);
}
