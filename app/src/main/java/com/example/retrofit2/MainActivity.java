package com.example.retrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.retrofit2.model.ResponseItem;

import java.security.cert.CertificateException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView mg;
    String url = "https://jsonplaceholder.typicode.com/";
    String image = "https://cdn.pixabay.com/photo/2016/11/04/11/46/robot-1797548_960_720.png";
    String weather = "https://mocki.io/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        mg = findViewById(R.id.image);
        tv.setText("");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(weather)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient().build())
                .build();

        Myapi api = retrofit.create(Myapi.class);
        Call<List<ResponseItem>> call = api.getResponseItem();

        Call<List<ResponseItem>> call1 = api.getResponseItem();
        call1.enqueue(new Callback<List<ResponseItem>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<ResponseItem>> call, Response<List<ResponseItem>> response) {
                if (response.code() != 200) {
                    Glide.with(MainActivity.this)
                            .load(image)
                            .fitCenter()
                            .circleCrop()
                            .into(mg);
                    return;
                }
                List<ResponseItem> data2 = response.body();
                for (int i = 0; i < data2.size(); i++) {
                    Glide.with(MainActivity.this)
                            .load(data2.get(i).getDt())
                            .fitCenter()
                            .circleCrop()
                            .into(mg);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });

        call.enqueue(new Callback<List<ResponseItem>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<ResponseItem>> call, Response<List<ResponseItem>> response) {
                if (response.code() != 200) {
                    String t = "Check connection";
                    tv.setText(t);
                    return;
                }
                List<ResponseItem> data = response.body();
                for (int i = 0; i < data.size(); i++) {
                    tv.append("No :" + data.get(i).getDt() + "\n" + "Title:" + data.get(i).getDt() + "\n\n\n");

                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {
                tv.setText(t.getMessage());

            }
        });
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @SuppressLint("BadHostnameVerifier")
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}