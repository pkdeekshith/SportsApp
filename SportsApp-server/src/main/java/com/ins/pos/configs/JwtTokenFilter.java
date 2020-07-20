
package com.ins.pos.configs;

import java.io.IOException;
import java.util.Arrays;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
	
	private static final String[] excludedEndpoints = new String[] {"/","/landing/**","/*.jpg","/*.JPG","/*.png","/*.PNG","/*.css","/*.CSS","/*.js","/*.*","/api/auth/login",
			"/api/auth/register",
			"/api/auth/validateUserName",
			"/api/admin/sendCommunications",
			"/api/booking/saveBooking",
			"/api/center/getAllCenters",
			"/api/facility/getPreferredSports",
			"/api/facility/getAllFacilities",
			"/api/facility/getFacilityForPreferredSport",
			"/api/facility/getAllFacilitiesAndPreferredSports",
			"/api/facility/checkFacilityAvailabilty",
			"/api/facility/getSubFacilitiesForFacilities",
			"/api/member/getMemberShipTypes",
			"/api/member/saveMember",
			"/api/member/forgetPassword",
			"/api/timetable/getAvailableTimeTables",
			"/api/timetable/getTimeSlotsForSubFacility",
			"/api/payment/initiatePayment",
			"/api/payment/proceedToPayment/*",
			"/api/payment/initiateMemberPayment",
			"/api/payment/processPaymentResponse"};

	private JwtTokenProvider jwtTokenProvider;
	
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } else {
        	((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	//response.sendRedirect("/SportsApp");
        }
       		
	}
	
	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
		return Arrays.stream(excludedEndpoints)
		        .anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
    }
}