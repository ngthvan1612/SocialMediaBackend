package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces.UserService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.StorageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    public UserServiceImpl() {

    }

    //TODO: Validate with annotation
    //TODO: check fk before create & update
    //TODO: update unique column for delete
    //TODO: swagger
    //TODO: authorize
    //TODO: hash password
    //TODO: loggggggggg

    @Override
    public SuccessfulResponse createUser(CreateUserRequest request) {
        //Validate
        if (this.userRepository.existsByUsername(request.getUsername())) {
            throw ServiceExceptionFactory.duplicate()
                    .addMessage("Đã tồn tại người dùng với username là " + request.getUsername());
        }

        //Check null

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDisplayName(request.getDisplayName());
        user.setBirthday(request.getBirthday());
        user.setAvatar(request.getAvatar());
        user.setProfile(request.getProfile());
        user.setGender(request.getGender());
        user.setRole(request.getRole());

        //Save to database
        this.userRepository.save(user);

        //Return
        UserResponse userDTO = new UserResponse(user);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(userDTO);
        response.addMessage("Tạo người dùng thành công");

        LOG.info("Created user with id = " + user.getId());
        return response;
    }

    @Override
    public GetUserResponse getUserById(Integer id) {
        if (!this.userRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy người dùng nào với id là " + id);
        }

        User user = this.userRepository.findById(id).get();
        UserResponse userDTO = new UserResponse(user);
        GetUserResponse response = new GetUserResponse(userDTO);

        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public ListUserResponse searchUsers(Map<String, String> queries) {
        List<UserResponse> listUserResponses = this.userRepository.searchUser(queries)
                .stream().map(user -> new UserResponse(user)).toList();

        ListUserResponse response = new ListUserResponse(listUserResponses);
        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public SuccessfulResponse updateUser(UpdateUserRequest request) {
        //Check record exists
        if (!this.userRepository.existsById(request.getUserId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy người dùng nào với id là " + request.getUserId());
        }

        //Read data from request
        User user = this.userRepository.findById(request.getUserId()).get();


        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDisplayName(request.getDisplayName());
        user.setBirthday(request.getBirthday());
        user.setAvatar(request.getAvatar());
        user.setProfile(request.getProfile());
        user.setGender(request.getGender());
        user.setRole(request.getRole());

        //Validate unique

        if (this.userRepository.existsByUsernameExceptId(request.getUsername(), request.getUserId())) {
            throw ServiceExceptionFactory.duplicate()
                    .addMessage("Đã tồn tại người dùng với tên người dùng là " + request.getUsername());
        }


        //Update last changed time
        user.setLastUpdatedAt(new Date());

        //Store
        this.userRepository.save(user);

        //Return
        UserResponse userDTO = new UserResponse(user);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(userDTO);
        response.addMessage("Cập nhật người dùng thành công");

        LOG.info("Updated user with id = " + user.getId());
        return response;
    }

    @Override
    public SuccessfulResponse updateAvatarById(UpdateUserAvatarRequest request) {
        if (!this.userRepository.existsById(request.getUserId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy người dùng nào có id = " + request.getUserId());
        }

        //Save to MinIO
        InputStream preparedStream = new ByteArrayInputStream(request.getAvatarBufferByteArray());
        String newMinIOUrl = this.storageRepository.saveUploadedStream(
                request.getUploadFileName(),
                preparedStream,
                request.getAvatarBufferByteArray().length
        );

        if (newMinIOUrl == null) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Tải lên lỗi");
        }

        //Save to database
        User user = this.userRepository.findById(request.getUserId()).get();
        user.setAvatar(newMinIOUrl);

        this.userRepository.save(user);

        SuccessfulResponse successResponse = new SuccessfulResponse(HttpStatus.OK);
        successResponse.addMessage("Cập nhật ảnh đại diện thành công");

        LOG.info("Updated avatar of user with id = " + user.getId());
        return successResponse;
    }


    @Override
    public SuccessfulResponse deleteUser(Integer id) {
        if (!this.userRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy người dùng nào với id là " + id);
        }

        User user = this.userRepository.findById(id).get();
        user.setDeletedAt(new Date());

        user.setUsername(user.getUsername() + "$" + UUID.randomUUID());

        this.userRepository.save(user);

        SuccessfulResponse response = new SuccessfulResponse();
        response.addMessage("Xóa người dùng thành công");

        LOG.info("Deleted user with id = " + user.getId());
        return response;
    }

    @Override
    public LoginResponse authenticate(LoginRequest request) {
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
        LoginResponse response = new LoginResponse(new UserResponse(user), accessToken);

        response.addMessage("Đăng nhập thành công");

        return response;
    }

}
  