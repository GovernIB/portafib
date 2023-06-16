package org.fundaciobit.pluginsib.signatureweb.fortress;

import com.viafirma.fortress.sdk.model.AccessToken;

public class TokenHolder {

    private final long expireTime;
    private final String token;

    public TokenHolder(AccessToken accessToken) {
        this.token = accessToken.getAccessToken();
        this.expireTime = System.currentTimeMillis() + ((accessToken.getExpiresIn() - 1) * 1000);
    }

    public TokenHolder() {
        this.token = null;
        this.expireTime = 0L;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }

    public String getToken() {
        return token;
    }
}
