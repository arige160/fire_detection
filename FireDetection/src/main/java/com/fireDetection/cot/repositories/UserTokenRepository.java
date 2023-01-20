package com.fireDetection.cot.repositories;
import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import com.fireDetection.cot.security.UserToken;
import java.util.Optional;
public interface UserTokenRepository extends Repository<UserToken, String> {
    Optional<UserToken> findByEmail(String  email ) ;
    @Query("select * from UserToken where tokens.token = @refreshToken")
    Optional<UserToken> findByRefreshToken(@Param("refreshToken") String token);

    @Query("select * from UserToken where tokens.accessToken.token = @accessToken")
    Optional<UserToken> findByAccessToken(@Param("accessToken") String token);
}