package com.mikeescom.challenge.view.network;

import com.google.gson.annotations.SerializedName;

public class ChallengeModel {
    @SerializedName("channel")
    private String channel;
    @SerializedName("username")
    private String username;
    @SerializedName("text")
    private String text;
    @SerializedName("icon_emoji")
    private String icon_emoji;

    public ChallengeModel(String channel, String username, String text, String icon_emoji) {
        this.channel = channel;
        this.username = username;
        this.text = text;
        this.icon_emoji = icon_emoji;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon_emoji() {
        return icon_emoji;
    }

    public void setIcon_emoji(String icon_emoji) {
        this.icon_emoji = icon_emoji;
    }
}
