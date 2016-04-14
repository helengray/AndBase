package com.example.demo;

import android.os.Bundle;
import android.widget.TextView;

import com.example.demo.service.MovieService;
import com.helen.andbase.activity.HTitleActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TestRequestActivity extends HTitleActivity {
	private TextView msg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(android.R.layout.simple_list_item_1);
		setTitle("测试数据请求");
		msg=(TextView) findViewById(android.R.id.text1);
		request();
	}

	private void request() {
		String baseUrl = "https://api.douban.com/v2/movie/";
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				//.addConverterFactory(GsonConverterFactory.create())
				.build();
		MovieService movieService = retrofit.create(MovieService.class);
		Call<String> call = movieService.getTopMovie(0, 10);
		call.enqueue(new Callback<String>() {
			@Override
			public void onResponse(Call<String> call, Response<String> response) {
				msg.setText(response.body());
			}

			@Override
			public void onFailure(Call<String> call, Throwable t) {
				onLoadFail();
			}
		});
	}

	@Override
	protected void onReload() {
		request();
	}
}
