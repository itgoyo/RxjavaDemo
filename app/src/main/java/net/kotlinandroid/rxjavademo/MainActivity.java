package net.kotlinandroid.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity implements Callback<TianGouBean> {

    private List<TianGouBean.TngouBean> mList=new ArrayList<>();
    @BindView(R.id.bt1)
    Button       mBt1;
    @BindView(R.id.bt2)
    Button       mBt2;
    @BindView(R.id.tv)
    TextView     mTv;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;

    CookService cookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        垂直布局
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //瀑布流布局
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,VERTICAL);
        mRecycleview.setLayoutManager(staggeredGridLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create()).build();
        cookService = retrofit.create(CookService.class);

    }

    @OnClick({R.id.bt1, R.id.bt2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:

//                doGet();

                doRxjava();

                break;
            case R.id.bt2:
                mTv.setVisibility(View.GONE);

                doTianGou();

                break;
        }
    }

    private void doRxjava() {

//        try {
//            TianGouBean body = cookService.getTianGou(0, 1, 20).execute().body();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Observable.create(new ObservableOnSubscribe<TianGouBean>() {
            @Override
            public void subscribe(ObservableEmitter<TianGouBean> e) throws Exception {

                TianGouBean body = cookService.getTianGou(0, 1, 20).execute().body();

                e.onNext(body);

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TianGouBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TianGouBean tianGouBean) {

                        List<TianGouBean.TngouBean> tngou = tianGouBean.getTngou();
                        int size = tngou.size();

                        for (int i = 0; i <size ; i++) {
                            mList.add(tngou.get(i));
                        }




                        PictureAdapter pictureAdapter = new PictureAdapter(getApplicationContext(), mList);

                        mRecycleview.setAdapter(pictureAdapter);


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Rxjava","--- "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void doTianGou() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create()).build();
        CookService cookService = retrofit.create(CookService.class);
        Call<TianGouBean> call = cookService.getTianGou(0, 1, 20);
        call.enqueue(this);

    }

    private void doGet() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.baidu.com")
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new Converter<ResponseBody, String>() {
                            @Override
                            public String convert(ResponseBody value) throws IOException {
                                return value.string();
                            }
                        };
                    }
                }).build();

        BaiduService baiduService = retrofit.create(BaiduService.class);
        Call<String> call = baiduService.getBaiDu();
//        call.enqueue(this);

    }

//    @Override
//    public void onResponse(Call<String> call, Response<String> response) {
//        mTv.setText(response.body());
//    }
//
//    @Override
//    public void onFailure(Call<String> call, Throwable t) {
//        mTv.setText("请求失败- " + call.request().url());
//    }

    @Override
    public void onResponse(Call<TianGouBean> call, Response<TianGouBean> response) {
        List<TianGouBean.TngouBean> tngou = response.body().getTngou();
        int size = tngou.size();

        for (int i = 0; i <size ; i++) {
            mList.add(tngou.get(i));
        }



//        Gson gson = new Gson();
//
//        TianGouBean bean = gson.fromJson(response.body().toString(), TianGouBean.class);










        PictureAdapter pictureAdapter = new PictureAdapter(getApplicationContext(), mList);

        mRecycleview.setAdapter(pictureAdapter);

//        mTv.setText(tngou.get(0).getDescription());
    }

    @Override
    public void onFailure(Call<TianGouBean> call, Throwable t) {

    }
}
