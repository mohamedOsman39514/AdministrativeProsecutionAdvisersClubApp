package com.example.proxy.rest.handler;

import com.example.proxy.entity.PasswordResetToken;
import com.example.proxy.entity.User;
import com.example.proxy.rest.dto.UserDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.exception.*;
import com.example.proxy.rest.entitymapper.UserMapper;
import com.example.proxy.security.PasswordUtil;
import com.example.proxy.security.UserLoggedInUtil;
import com.example.proxy.service.PasswordTokenService;
import com.example.proxy.service.UserService;
import com.example.proxy.utils.email.EmailDetails;
import com.example.proxy.utils.email.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@Log4j2
public class UserHandler {

    private UserMapper userMapper;
    private UserService userService;
    private PasswordTokenService passwordTokenService;
    private EmailService emailService;
    private PasswordUtil passwordUtil;
    private PaginationMapper paginationMapper;

    private UserLoggedInUtil userLoggedInUtil;

    public ResponseEntity<?> register(UserDto userDto) {
        try {
            log.info("create a new user");
            User user = userMapper.toEntity(userDto);
            userService.register(user);
            UserDto dto=userMapper.toDto(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SQLException(ex));
        }
    }

    public ResponseEntity<?> update(Long id, UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User userById = userService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(),id));
        userById.setName(user.getName() != null ? user.getName() : userById.getName());
        userById.setUsername(user.getUsername() != null ? user.getUsername() : userById.getUsername());
        userById.setNationalId(user.getNationalId() != null ? user.getNationalId() : userById.getNationalId());
        userById.setPhone(user.getPhone() != null ? user.getPhone() : userById.getPhone());
        userById.setJob(user.getJob() != null ? user.getJob() : userById.getJob());
        userById.setParty(user.getParty() != null ? user.getParty() : userById.getParty());
        userById.setAddress(user.getAddress() != null ? user.getAddress() : userById.getAddress());
        userService.save(userById);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userById);
    }

    public ResponseEntity<?> getById(Long id) {
        log.info("get user by id");
        User user = userService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(),id));
        UserDto userDto = userMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        log.info("get all users");
        Page<User> users = userService.getAll(pageNo, pageSize);
        List<UserDto> content= userMapper.toDto(users.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(users));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> delete(Long id) {
        log.info("delete by id");
        userService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(),id));
        try {
            userService.deleteById(id);
        } catch (Exception exception) {
            throw new ResourceRelatedException(User.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

    public ResponseEntity<?> forgetPassword(UserDto userDto, HttpServletRequest request ) {
        log.info("user user forget password ");
        User user = userMapper.toEntity(userDto);
        User userByEmail = userService.getByUsername(user.getUsername());
        if (userByEmail == null) {
            throw new ResourceNotFoundException(User.class.getSimpleName(),user.getUsername());
        }
        String token = UUID.randomUUID().toString();
        passwordTokenService.createPasswordResetTokenForUser(userByEmail, token);
        String appUrl = "http://"+ request.getServerName() + ":" + request.getServerPort() +"/user/resetpassword/"+token;
        EmailDetails details = new EmailDetails();
        details.setRecipient(user.getUsername());
        details.setSubject("Reset your password");
        details.setMsgBody("Forgot your password? Submit a PATCH request with your new password and to:" + appUrl +
                "\nIf you didn't forget your password, please ignore this email!");
        String status = emailService.sendSimpleMail(details);
        return ResponseEntity.ok().body(new Response("Mail sent Successfully"));
    }

    public ResponseEntity<?> resetPassword(UserDto userDto, String resetToken){
        log.info("user reset password");
        User user = userMapper.toEntity(userDto);
        PasswordResetToken token = passwordTokenService.getResetToken(resetToken);
        if(token.getToken().isEmpty()){
            return ResponseEntity.status(404).body(new Response("This reset token not found......"));
        }
        String result = passwordUtil.validatePasswordResetToken(resetToken);
        if(result != null){
            return  ResponseEntity.status(401).body(new Response("Reset Token Expired......"));
        }
        String email = token.getUser().getUsername();
        userService.updatePassword(email,user.getPassword());
        return ResponseEntity.status(200).body(new Response("the password changed....."));
    }

    public ResponseEntity<?> changeUserPassword(UserDto userDto, String newPassword) {
        log.info("user update password");
        User user = userMapper.toEntity(userDto);
        User currentUser = userLoggedInUtil.currentUser();
        if(currentUser == null){
            return ResponseEntity.ok(new Response("please login"));
        }
        BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        if(passwordEncoder.matches(user.getPassword(),currentUser.getPassword())){
            userService.updatePassword(currentUser.getUsername(),newPassword);
            return ResponseEntity.status(200).body(new Response("successfully updated......"));
        }
        else return ResponseEntity.status(403).body(new Response("incorrect password"));
    }

}
