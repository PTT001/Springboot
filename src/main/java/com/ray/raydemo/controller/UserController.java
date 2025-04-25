package com.ray.raydemo.controller;

import com.ray.raydemo.Service.*;
import com.ray.raydemo.dto.*;
import com.ray.raydemo.model.GameRecord;
import com.ray.raydemo.model.Gamers;
import com.ray.raydemo.model.Message;
import com.ray.raydemo.model.User;
import com.ray.raydemo.security.MyUserDetails;
import com.ray.raydemo.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final GamerService gamerService;
    private final ProfileService profileService;
    private final GameRecordService gameRecordService;
    private final MessageService messageService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // 使用 AuthenticationManager 進行認證
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 認證成功，生成 JWT
            String jwt = jwtUtil.generateToken(authentication);

            return ResponseEntity.ok(new LoginResponse(jwt, "登錄成功"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(
                    new LoginResponse(null, "登錄失敗：" + e.getMessage()));
        }
    }

    @GetMapping("/Profile")
    public ResponseEntity<UserProfileDto> getProfile() {
        // 從SecurityContext取得目前登入的使用者名稱
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 根據username查詢profile資料
        UserProfileDto profile = userService.getUserProfileByUsername(username);

        return ResponseEntity.ok(profile);
    }

    @PostMapping("/Profile")
    public ResponseEntity<String> changePassword(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        userService.changePassword(username, loginRequest.getPassword());
        return ResponseEntity.ok("密碼更改成功");
    }

    @GetMapping("/getAllUser")
    public List<UserProfileDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam("file") MultipartFile file,
                                          @AuthenticationPrincipal MyUserDetails myUserDetails) throws IOException {

        Integer userId = myUserDetails.getId();
        String userName = myUserDetails.getUsername();
        String imageUrl = profileService.uploadAvatar(userId, userName, file);

        return ResponseEntity.ok(Map.of("avatarUrl", imageUrl));
    }

    @GetMapping("/gamers")
    public List<GamerDto> getAllGamer() {
        return gamerService.getAllGamers();
    }

    @PostMapping("/gamers")
    public ResponseEntity<Gamers> SignInGamer(@RequestBody Gamers gamers) {
        try {
            Gamers gamer = gamerService.SignInGamer(gamers);
            return ResponseEntity.ok(gamer);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/gamers/{username}")
    public ResponseEntity<Void> deleteGamer(@PathVariable String username) {
        try {
            gamerService.deleteGamer(username);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/game-records")
    public GameRecord createGameRecord(@RequestBody GameRecord gameRecord) {
        return gameRecordService.saveGameRecord(gameRecord);
    }

    @GetMapping("/game-records")
    public List<GameRecord> getAllRecord() {
        return gameRecordService.findAllGameRecords();
    }

    @PostMapping("/message")
    public Message createMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/message")
    public List<Message> getAllMessage() {
        return messageService.findAllMessages();
    }
}