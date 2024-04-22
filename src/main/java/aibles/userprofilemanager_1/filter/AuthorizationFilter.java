//package aibles.userprofilemanager_1.filter;
//
//import aibles.userprofilemanager_1.dto.UserProfileResponse;
//import aibles.userprofilemanager_1.service.service.UserProfileService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class AuthorizationFilter extends OncePerRequestFilter {
//
//    private final UserProfileService userProfileService;
//
//    @Autowired
//    public AuthorizationFilter(UserProfileService userProfileService) {
//        this.userProfileService = userProfileService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if (authentication != null && authentication.isAuthenticated()) {
//                String username = authentication.getName();
//                UserProfileResponse userProfile = userProfileService.getUserProfileByUsername(username);
//
//                if (!"ADMIN".equals(userProfile.getRole()) &&
//                        (request.getRequestURI().contains("/api/v1/posts/") )) {
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
//                    return;
//                }
//            }
//
//            filterChain.doFilter(request, response);
//        } catch (Exception e) {
//            log.error("Error occurred during authorization filter processing", e);
//            throw new ServletException("Error occurred during authorization filter processing", e);
//        }
//    }
//
//}
