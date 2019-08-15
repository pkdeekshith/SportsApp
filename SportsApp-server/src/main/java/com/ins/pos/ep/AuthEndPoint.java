package com.ins.pos.ep;

import static org.springframework.http.ResponseEntity.ok;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.configs.JwtTokenProvider;
import com.ins.pos.dto.FacilityTypeJsonDTO;
import com.ins.pos.dto.LoginJsonDTO;
import com.ins.pos.dto.MemberDetailsJsonDTO;
import com.ins.pos.dto.MemberShipTypeJsonDTO;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.MemberFacility;
import com.ins.pos.entity.Role;
import com.ins.pos.entity.User;
import com.ins.pos.entity.UserRoles;
import com.ins.pos.repository.MemberFacilityRepository;
import com.ins.pos.repository.MemberRepository;
import com.ins.pos.repository.UserRepository;
import com.ins.pos.repository.UserRolesRepository;
import com.ins.pos.service.impl.CustomUserDetailsServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthEndPoint {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository users;
	
	@Autowired
	UserRolesRepository userRolesRepository;

	@Autowired
	private CustomUserDetailsServiceImpl userService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberFacilityRepository memberFacilityRepository;

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginJsonDTO data) {
		try {
			String username = data.getEmail();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			User user = users.findByEmail(username);
			List<UserRoles> userRoles = userRolesRepository.findByUserAndActive(user, true);
			Set<Role> roleSet = new HashSet<Role>();
			for(UserRoles usrRole:userRoles) {
				roleSet.add(usrRole.getRole());
			}
			String token = jwtTokenProvider.createToken(username, roleSet);
			
			MemberDetailsJsonDTO memberDetailsJsonDTO = new MemberDetailsJsonDTO();
			List<Member> memberList = memberRepository.findByUserName(username);
			Member member = memberList.get(0);
			List<FacilityTypeJsonDTO> facilityTypeList = new ArrayList<FacilityTypeJsonDTO>();
			memberDetailsJsonDTO.setFacilityType(facilityTypeList);
			if(member!=null) {
				MemberShipTypeJsonDTO memberShipTypeJsonDTO = new MemberShipTypeJsonDTO();
				BeanUtils.copyProperties(member, memberDetailsJsonDTO);
				BeanUtils.copyProperties(member.getMemberShipTypeId(), memberShipTypeJsonDTO);
				memberDetailsJsonDTO.setMemberShipType(memberShipTypeJsonDTO);
				List<MemberFacility> memberFacList = memberFacilityRepository.findByMemberAndActive(member, true);
				for(MemberFacility memberFacility:memberFacList) {
					FacilityTypeJsonDTO facilityTypeJsonDTO = new FacilityTypeJsonDTO();
					memberFacility.getFacilityType();
					BeanUtils.copyProperties(memberFacility.getFacilityType(), facilityTypeJsonDTO);
					facilityTypeList.add(facilityTypeJsonDTO);
				}
			}
			if (memberDetailsJsonDTO.getMemberPhoto() != null) {
				try {
					byte[] fileContent = FileUtils.readFileToByteArray(new File(memberDetailsJsonDTO.getMemberPhoto()));
					String byteString = new String(fileContent);
					memberDetailsJsonDTO.setMemberPhoto(byteString);
				} catch (Exception e) {

				}
			}
			
			HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.set("token",token);
		    responseHeaders.set("status","Success");
		 
		    return ResponseEntity.ok()
		    	      .headers(responseHeaders)
		    	      .body(memberDetailsJsonDTO);
			
			
		} catch (AuthenticationException e) {
			HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.set("status","Failure");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).body("Invalid Username or Password!");
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody User user) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
		}
		userService.saveUser(user);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User registered successfully");
		return ok(model);
	}
}