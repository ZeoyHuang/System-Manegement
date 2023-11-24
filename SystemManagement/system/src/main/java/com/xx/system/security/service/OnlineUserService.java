package com.xx.system.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OnlineUserService {
    private final RedisTemplate<String, Object> redisTemplate;

    // querying online users
    @Autowired
    public OnlineUserService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // exporting online users
    public Set<String> getOnlineUsers() {
        return redisTemplate.keys("user:*");
    }

    // forcing users to log out
    public void forceLogout(String username) {
        redisTemplate.delete("user:" + username);
    }
}
