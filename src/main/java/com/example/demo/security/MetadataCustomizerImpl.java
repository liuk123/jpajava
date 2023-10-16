package com.example.demo.security;

import com.example.demo.db.model.User;

public class MetadataCustomizerImpl implements MetadataCustomizer{
    @Override
    public void customize(User user){
        user.getMetadata().put("ip", "192.168.0.1");
    }
}
