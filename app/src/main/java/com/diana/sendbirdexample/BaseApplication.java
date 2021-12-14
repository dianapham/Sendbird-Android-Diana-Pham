package com.diana.sendbirdexample;

import android.app.Application;

import com.sendbird.android.SendBirdException;
import com.sendbird.android.handlers.InitResultHandler;
import com.sendbird.uikit.SendBirdUIKit;
import com.sendbird.uikit.adapter.SendBirdUIKitAdapter;
import com.sendbird.uikit.interfaces.UserInfo;

public class BaseApplication extends Application {

    private static final String APP_ID = "E0238A0A-3EB9-472D-921D-89D06E41944E";
    private static final String USER_ID = "kitty2";
    private static final String USER_NICKNAME = "Sophie";

    @Override
    public void onCreate() {
        super.onCreate();

        SendBirdUIKit.init(new SendBirdUIKitAdapter() {
            @Override
            public String getAppId() {
                return APP_ID;
            }

            @Override
            public String getAccessToken() {
                return "";
            }

            @Override
            public UserInfo getUserInfo() {
                return new UserInfo() {
                    @Override
                    public String getUserId() {
                        return USER_ID;  // Specify your user ID.
                    }

                    @Override
                    public String getNickname() {
                        return USER_NICKNAME;  // Specify your user nickname.
                    }

                    @Override
                    public String getProfileUrl() {
                        return "";
                    }
                };
            }

            @Override
            public InitResultHandler getInitResultHandler() {
                return new InitResultHandler() {
                    @Override
                    public void onMigrationStarted() {
                        // DB migration has started.
                    }

                    @Override
                    public void onInitFailed(SendBirdException e) {
                        // If DB migration fails, this method is called.
                    }

                    @Override
                    public void onInitSucceed() {
                        // If DB migration is successful, this method is called and you can proceed to the next step.
                        // In the sample app, the `LiveData` class notifies you on the initialization progress
                        // And observes the `MutableLiveData<InitState> initState` value in `SplashActivity()`.
                        // If successful, the `LoginActivity` screen
                        // Or the `HomeActivity` screen will show.
                    }
                };
            }
        }, this);
    }
}