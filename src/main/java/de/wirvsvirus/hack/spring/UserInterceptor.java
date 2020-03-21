package de.wirvsvirus.hack.spring;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class UserInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInterceptor.class);

    private static final String HEADER_USER_ID = "X-User-ID";

    private static final ThreadLocal<UUID> USER_ID_PER_THREAD = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {

        if (request.getServletPath().startsWith("/error")) {
            return true;
        }

        if (request.getServletPath().startsWith("/registration/")) {
            LOGGER.info("Request for unauthenticated endpoint {}", request.getServletPath());
            Preconditions.checkState(request.getHeader(HEADER_USER_ID) == null,
                    "Unexpected user id header for request %s", request.getRequestURL());
            return true;
        }

        final String userIdRaw = request.getHeader(HEADER_USER_ID);
        Preconditions.checkNotNull(userIdRaw, "Header for user id missing in request %s", request.getRequestURL());

        USER_ID_PER_THREAD.set(UUID.fromString(userIdRaw));

        LOGGER.info("Request for authenticated endpoint {} with userID {}", request.getServletPath(), userIdRaw);

        return true;
    }

    public static UUID getCurrentUserId() {
        final UUID userId = USER_ID_PER_THREAD.get();
        Preconditions.checkNotNull(userId, "UserId for this thread/request not set!");
        return userId;
    }

}
