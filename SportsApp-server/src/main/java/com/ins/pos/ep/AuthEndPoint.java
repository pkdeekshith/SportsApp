package com.ins.pos.ep;

import static org.springframework.http.ResponseEntity.ok;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.ins.pos.dto.RoleJsonDTO;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.MemberFacility;
import com.ins.pos.entity.PaymentOrderStatus;
import com.ins.pos.entity.Role;
import com.ins.pos.entity.User;
import com.ins.pos.entity.UserRoles;
import com.ins.pos.repository.MemberFacilityRepository;
import com.ins.pos.repository.MemberRepository;
import com.ins.pos.repository.PaymentOrderStatusRepository;
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
	
	@Autowired
	private PaymentOrderStatusRepository paymentOrderStatusRepository;
	
	@Value("${sportsapp.renewal.reminder.days}")
	private long reminderDays;

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginJsonDTO data) {
		try {
			String username = data.getEmail();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			User user = users.findByEmail(username);
			List<UserRoles> userRoles = userRolesRepository.findByUserAndActive(user, true);
			Set<Role> roleSet = new HashSet<Role>();
			List<RoleJsonDTO> rolesList = new ArrayList<RoleJsonDTO>();
			for (UserRoles usrRole : userRoles) {
				roleSet.add(usrRole.getRole());
				RoleJsonDTO roleJsonDTO = new RoleJsonDTO();
				roleJsonDTO.setRoleId(usrRole.getRole().getRoleId());
				roleJsonDTO.setRoleName(usrRole.getRole().getRoleName());
				rolesList.add(roleJsonDTO);
			}
			String token = jwtTokenProvider.createToken(username, roleSet);

			MemberDetailsJsonDTO memberDetailsJsonDTO = new MemberDetailsJsonDTO();
			List<Member> memberList = memberRepository.findByUserNameAndActive(username,true);
			Member member = memberList.get(0);
			List<FacilityTypeJsonDTO> facilityTypeList = new ArrayList<FacilityTypeJsonDTO>();
			memberDetailsJsonDTO.setFacilityType(facilityTypeList);
			if (member != null) {
				MemberShipTypeJsonDTO memberShipTypeJsonDTO = new MemberShipTypeJsonDTO();
				BeanUtils.copyProperties(member, memberDetailsJsonDTO);
				BeanUtils.copyProperties(member.getMemberShipTypeId(), memberShipTypeJsonDTO);
				memberDetailsJsonDTO.setMemberShipType(memberShipTypeJsonDTO);
				List<MemberFacility> memberFacList = memberFacilityRepository.findByMemberAndActive(member, true);
				for (MemberFacility memberFacility : memberFacList) {
					FacilityTypeJsonDTO facilityTypeJsonDTO = new FacilityTypeJsonDTO();
					memberFacility.getFacilityType();
					BeanUtils.copyProperties(memberFacility.getFacilityType(), facilityTypeJsonDTO);
					facilityTypeList.add(facilityTypeJsonDTO);
				}
			}
			Date today = new Date();
			long duration  = member.getMemberTypeValidity().getTime() - today.getTime();
			long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
			if(diffInDays<reminderDays) {
				memberDetailsJsonDTO.setIsRenewalPending(true);
			}else {
				memberDetailsJsonDTO.setIsRenewalPending(false);
			}
			if (memberDetailsJsonDTO.getMemberPhoto() != null) {
				try {
					byte[] fileContent = FileUtils.readFileToByteArray(new File(memberDetailsJsonDTO.getMemberPhoto()));
					String byteString = new String(fileContent);
					memberDetailsJsonDTO.setMemberPhoto(byteString);
				} catch (Exception e) {

				}
			}
			memberDetailsJsonDTO.setRoles(rolesList);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("auth-token", token);
			responseHeaders.set("status", "Success");

			return ResponseEntity.ok().headers(responseHeaders).body(memberDetailsJsonDTO);

		} catch (AuthenticationException e) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("status", "Failure");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(responseHeaders)
					.body("Invalid Username or Password!");
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

	@PostMapping("/validateUserName")
	public ResponseEntity validateUserName(@RequestBody String data) {
		JSONObject requestJSON = new JSONObject(data);
		JSONObject responseJSON = new JSONObject();
		String email = requestJSON.getString("email");
		User userExists = userService.findUserByEmail(email);

		if (userExists != null) {
			responseJSON.put("isValid", false);
			responseJSON.put("message", "User with username: " + email + " already exists");
		} else {
			responseJSON.put("isValid", true);
		}

		return ResponseEntity.ok().body(responseJSON.toString());

	}
	
	@PostMapping("/getMemberCred")
	public ResponseEntity getMemberCred(@RequestBody String data) {
		JSONObject requestJSON = new JSONObject(data);
		JSONObject responseJSON = new JSONObject();
		String encodedString = requestJSON.getString("orderId");
		String restString = encodedString.substring(6);
		String orderId = restString.substring(0, restString.length() - 6);
		boolean status = false;
		String roleName = "";
		Member member = null;
		PaymentOrderStatus paymenOrderStatus = paymentOrderStatusRepository.findByOrderId(orderId);
		if(paymenOrderStatus!=null&&("S".equals(paymenOrderStatus.getStatus())||"F".equals(paymenOrderStatus.getStatus()))) {
			member = paymenOrderStatus.getMemberId();
			if(member!=null&&!member.getFirstLogin()) {
				status=true;
				User user = users.findByEmail(member.getEmail());
				List<UserRoles> userRoles = userRolesRepository.findByUserAndActive(user, true);
				roleName = userRoles.get(0).getRole().getRoleName();
			}
			
		}
		if(status) {
			responseJSON.put("status", "S");
			responseJSON.put("memberId", paymenOrderStatus.getMemberId().getMemberId());
			responseJSON.put("memberRole",roleName);
		} else {
			responseJSON.put("status", "F");
		}
		return ResponseEntity.ok().body(responseJSON.toString());

	}
}