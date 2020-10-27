package com.mikeescom.challenge.view.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChallengeRepository {
    private static final String BASE_URL = "https://hooks.slack.com/";
    final private ChallengeService challengeService;

    public ChallengeRepository() {
        challengeService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ChallengeService.class);
    }

    public interface ChallengeCallback {
        void onResponse(String message);
        void onError(String error);
    }

    public void sendMessage(String challengeModel, ChallengeCallback callback) {
        challengeService.sendMessage(challengeModel)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() != null) {
                            callback.onResponse(response.body());
                        } else {
                            callback.onResponse(response.raw().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }
}
