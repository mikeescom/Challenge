package com.mikeescom.challenge.view.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ChallengeService {
    @FormUrlEncoded
    //@POST("/services/T042FMKHH/B1LF1T12L/HoTvYOUSovFpfl9LI59GBKnq")
    @POST("/services/T01DRPKPA57/B01CYCWQVU7/6Z2vIIPQ5EAX2Y8xdr5nkSGJ")
    Call<String> sendMessage(@Field("payload") String payload);
}
