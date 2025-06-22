package bg.sofia.uni.fmi.javaweb.sports_marketplace.jwt_util;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class JwtPrincipalHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        UserDetails user = (UserDetails) attributes.get("user");
        if (user != null) {
            return user::getUsername; // Principal name = username or email
        }
        return null;
    }
}
