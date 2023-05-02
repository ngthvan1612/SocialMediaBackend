package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.enums.UserRole;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.FollowerRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces.UserService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.StorageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.exception.ServiceExceptionFactory;
import com.hcmute.oosd.project.socialmediabackend.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FollowerRepository followerRepository;

    private void preparedFollowedForUserResponse(User loggingInUser, UserResponse toAccessUser) {
        if (loggingInUser.getId() == toAccessUser.getId())
            return;
        if (!this.followerRepository.existsByUserIdAndFollowerId(loggingInUser.getId(), toAccessUser.getId()))
            toAccessUser.setFollowed(false);
        else
            toAccessUser.setFollowed(true);
    }

    public UserServiceImpl() {

    }

    // TODO: Validate with annotation
    // TODO: check fk before create & update
    // TODO: update unique column for delete
    // TODO: swagger
    // TODO: authorize
    // TODO: hash password
    // TODO: loggggggggg

    @Override
    public ResponseBaseAbstract createUser(CreateUserRequest request) {
        // Validate
        if (this.userRepository.existsByUsername(request.getUsername())) {
            throw ServiceExceptionFactory.duplicate()
                    .addMessage("Đã tồn tại người dùng với username là " + request.getUsername());
        }

        // Check null

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDisplayName(request.getDisplayName());
        user.setBirthday(request.getBirthday());
        user.setAvatar(request.getAvatar());
        user.setProfile(request.getProfile());
        user.setGender(request.getGender());
        user.setRole(request.getRole());

        // Save to database
        this.userRepository.save(user);

        // Return

        LOG.info("Created user with id = " + user.getId());
        return SuccessResponse.builder()
                .addMessage("Tạo người dùng thành công")
                .setData(user)
                .returnUpdated();

    }

    @Override
    public ResponseBaseAbstract getUserById(Integer id, User loggindInUser) {
        if (!this.userRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy người dùng nào với id là " + id);
        }

        User user = this.userRepository.findById(id).get();
        UserResponse userDTO = new UserResponse(user);
        preparedFollowedForUserResponse(loggindInUser, userDTO);

        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(userDTO)
                .returnUpdated();

    }

    @Override
    public ResponseBaseAbstract searchUsers(Map<String, String> queries, User loggingInUser) {
        List<UserResponse> listUserResponses = this.userRepository.searchUser(queries)
                .stream().map(user -> new UserResponse(user)).toList();

        for (UserResponse userResponse : listUserResponses) {
            preparedFollowedForUserResponse(loggingInUser, userResponse);
        }

        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(listUserResponses)
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract searchUsersForPost(String pattern, Integer limit) {
        if (limit == null)
            limit = 10;

        List<User> userList = this.userRepository.searchUsersForPost(pattern, limit);
        List<SearchUsersForPost> data = userList.stream().map(SearchUsersForPost::new).toList();
        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(data)
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract updateUser(UpdateUserRequest request) {
        // Check record exists
        if (!this.userRepository.existsById(request.getUserId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy người dùng nào với id là " + request.getUserId());
        }

        // Read data from request
        User user = this.userRepository.findById(request.getUserId()).get();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDisplayName(request.getDisplayName());
        user.setBirthday(request.getBirthday());
        user.setProfile(request.getProfile());
        user.setGender(request.getGender());
        user.setRole(request.getRole());

        // Validate unique

        if (this.userRepository.existsByUsernameExceptId(request.getUsername(), request.getUserId())) {
            throw ServiceExceptionFactory.duplicate()
                    .addMessage("Đã tồn tại người dùng với tên người dùng là " + request.getUsername());
        }

        // Update last changed time
        user.setLastUpdatedAt(new Date());

        // Store
        this.userRepository.save(user);

        // Return
        UserResponse userDTO = new UserResponse(user);

        LOG.info("Updated user with id = " + user.getId());
        return SuccessResponse.builder()
                .addMessage("Cập nhật người dùng thành công")
                .setData(userDTO)
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract updateAvatarById(UpdateUserAvatarRequest request) {
        if (!this.userRepository.existsById(request.getUserId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy người dùng nào có id = " + request.getUserId());
        }

        // Save to MinIO
        InputStream preparedStream = new ByteArrayInputStream(request.getAvatarBufferByteArray());
        String newMinIOUrl = this.storageRepository.savedUploadedAvatar(
                request.getUploadFileName(),
                preparedStream,
                request.getAvatarBufferByteArray().length);

        if (newMinIOUrl == null) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Tải lên lỗi");
        }

        // Save to database
        User user = this.userRepository.findById(request.getUserId()).get();
        user.setAvatar(newMinIOUrl);

        this.userRepository.save(user);

        LOG.info("Updated avatar of user with id = " + user.getId());
        return SuccessResponse.builder()
                .addMessage("Cập nhật ảnh đại diện thành công")
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract deleteUser(Integer id) {
        if (!this.userRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy người dùng nào với id là " + id);
        }

        User user = this.userRepository.findById(id).get();
        user.setDeletedAt(new Date());

        user.setUsername(user.getUsername() + "$" + UUID.randomUUID());

        this.userRepository.save(user);

        LOG.info("Deleted user with id = " + user.getId());
        return SuccessResponse.builder()
                .addMessage("Xóa người dùng thành công")
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract authenticate(LoginRequest request) {
        User user = this.userRepository.getUserByUsername(request.getUsername());

        if (user == null) {
            throw ServiceExceptionFactory.unauthorized()
                    .addMessage("Tên đăng nhập hoặc mật khẩu không đúng");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw ServiceExceptionFactory.unauthorized()
                    .addMessage("Tên đăng nhập hoặc mật khẩu không đúng");
        }

        String accessToken = this.jwtTokenProvider.generateToken(user);

        LOG.info("User " + request.getUsername() + " has just logged in, generated jwt token is " + accessToken);
        Map<String, Object> data = new HashMap<>();
        data.put("user", new UserResponse(user));
        data.put("accessToken", accessToken);
        return SuccessResponse.builder()
                .addMessage("Đăng nhập thành công")
                .setData(data)
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract registerUser(RegisterUserRequest registerUserRequest) {
        String username = registerUserRequest.getUsername();
        String password = registerUserRequest.getPassword();
        String displayName = registerUserRequest.getDisplayName();
        String email = registerUserRequest.getEmail();

        if (username == null) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Tên đăng nhập không được trống");
        }

        username = username.trim();

        if (!username.matches("^[a-zA-Z0-9]+$")) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Tên đăng nhập chỉ được chứa chữ in hoa, chữ thường, hoặc ký tự số");
        }

        // TODO: fix hard Code
        if (username.length() > 16) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Tên đăng nhập có độ dài tối đa là 16");
        }

        if (this.userRepository.existsByUsername(username)) {
            throw ServiceExceptionFactory.duplicate()
                    .addMessage("Đã tồn tại người dùng với username là " + username);
        }

        if (password == null) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Mật khẩu không được trống");
        }

        // TODO: fix hard Code
        if (password.length() == 0 || password.length() > 16) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Mật khẩu có độ dài tối đa là 16");
        }

        if (displayName == null || displayName.length() == 0) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Họ và tên không được trống");
        }

        if (email == null) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Email không được trống");
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Email không hợp lệ");
        }

        if (this.userRepository.existsByEmail(email)) {
            throw ServiceExceptionFactory.duplicate()
                    .addMessage("Đã tồn tại người dùng với email là " + email);
        }

        // Create entity
        User user = new User();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setRole(UserRole.REGULAR);

        // Save to database
        this.userRepository.save(user);

        // Return

        LOG.info("Create user with id = " + user.getId());
        return SuccessResponse.builder()
                .addMessage("Đăng ký thành công")
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract getSuggestionsForMe(User loggingInUser) {
        return null;
    }

}
