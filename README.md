# RxjavaDemo
Rxjava+Retrofit项目练习

```
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

```

![](http://omvbl46i3.bkt.clouddn.com/17-6-15/48432287.jpg)
