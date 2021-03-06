package com.telefonica.architecture.tokenmanager.manager.str;

import com.telefonica.architecture.tokenmanager.manager.exception.TokenException;
import com.telefonica.architecture.tokenmanager.manager.model.TokenResponse;
import com.telefonica.architecture.tokenmanager.redis.RedisClient;

import redis.clients.jedis.Jedis;

public class TokenResponseStr {

    public TokenResponse getToken(RedisClient redis, TokenResponseStrItf str) 
        throws TokenException {
            Jedis con = null;

            try {
                con = redis.getConnection();
                return str.get(con);
            } catch (Exception e) { 
                throw new TokenException(e);
            } finally {
                if (con != null) {
                    try {
                        redis.returnConnection(con);
                        con = null;
                    } catch (Exception e) { 
                        throw new TokenException(e);
                    }
                }
            }   
    }

}