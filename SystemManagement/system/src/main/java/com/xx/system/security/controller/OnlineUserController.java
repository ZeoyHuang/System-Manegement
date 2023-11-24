package com.xx.system.security.controller;

import com.xx.system.security.service.OnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class OnlineUserController {
    private final OnlineUserService onlineUserService;

    // querying online users
    @Autowired
    public OnlineUserController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    // exporting online users
    @GetMapping("/online")
    public Set<String> getOnlineUsers() {
        return onlineUserService.getOnlineUsers();
    }

    // forcing users to log out
    @PostMapping("/forceLogout/{username}")
    public void forceLogout(@PathVariable String username) {
        onlineUserService.forceLogout(username);
    }
}
