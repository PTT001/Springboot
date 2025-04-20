package com.ray.raydemo.Service;

import com.ray.raydemo.model.User;
import com.ray.raydemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final S3Service s3Service;
    private final UserRepository userRepository;

    public String uploadAvatar(Integer userid, String username, MultipartFile file) throws IOException {
        String imageUrl = s3Service.uploadFile(userid, file);

        User user = userRepository.findByUsername(username).orElseThrow();
        user.setAvatarUrl(imageUrl);
        userRepository.save(user);

        return imageUrl;
    }
}

