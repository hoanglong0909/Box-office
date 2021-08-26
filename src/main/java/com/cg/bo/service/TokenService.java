package com.cg.bo.service;


import com.cg.bo.model.security.Token;

public interface TokenService {

    Token createToken(Token token);

    Token findByToken(String token);
}
