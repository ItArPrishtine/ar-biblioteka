//package com.akropoliprishtine.security;
//
//import com.akropoliprishtine.entities.ApplicationUser;
//import com.akropoliprishtine.entities.Permission;
//import com.akropoliprishtine.services.ApplicationUserService;
//import com.akropoliprishtine.utils.JwtTokenUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Set;
//
//@Order(1)
//public class PermissionFilter implements Filter {
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Autowired
//    private ApplicationUserService applicationUserService;
//
//    @Override
//    public void doFilter
//            (ServletRequest request, ServletResponse response, FilterChain filterChain)
//            throws IOException, ServletException {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        if (auth != null && auth.isAuthenticated()) {
//            String token = httpRequest.getHeader("Authorization");
//            if (token == null) {
//                filterChain.doFilter(httpRequest, httpResponse);
//                return;
//            }
//            Map<String, Object> tokenData = jwtTokenUtil.getAllClaimsFromToken(token.split(" ")[1]);
//            Long userId =  Long.parseLong(tokenData.get("id").toString());
//            Optional<ApplicationUser> user = applicationUserService.getUserById(userId);
//            if (user.isPresent()) {
//                boolean permissionExists = true;
//                 Set<Permission> permissions = user.get().getRole().getPermissions();
//                 String apiToCompare = ((HttpServletRequest) request).getRequestURI().split("api")[1];
//                 permissionExists = permissions.stream().anyMatch(permission -> permission.getEndpoint().equals(apiToCompare));
//
//                 if (!permissionExists) {
//                     ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Nuk keni te drejte t'i shihni keto te dhena.");
//                 }
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void init(FilterConfig filterconfig) throws ServletException {
//        System.out.println("Permission Filter is initialized");
//    }
//}
