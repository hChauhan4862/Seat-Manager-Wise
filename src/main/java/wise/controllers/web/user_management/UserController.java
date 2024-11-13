package wise.controllers.web.user_management;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.UserEntity;
import wise.models.request.user_management.UserModel;
import wise.models.response.user_management.UserResponse;
import wise.services.user_management.UsersService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "users")
@Tag(name = "User Management", description = "User-specific APIs")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UsersService usersService;
    private final ResponseService responseService;

    public UserController(UsersService usersService, ResponseService responseService) {
        this.usersService = usersService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<ApiResponseModel> createUser(@Valid @RequestBody UserModel userDto) {
        UserEntity userEntity = RequestUtil.NewPatchDataModifier(UserEntity.class, userDto);
        usersService.createUser(userEntity);
        UserResponse response = RequestUtil.NewPatchDataModifier(UserResponse.class, userEntity);
        logger.info("User created successfully: ID={}", userEntity.getUserId());
        return ResponseEntity.ok(responseService.successResponse("users.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<ApiResponseModel> getAllUsers(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(usersService.getAllUsers(pageable)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    public ResponseEntity<ApiResponseModel> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(responseService.successResponse(usersService.getUserById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user by ID")
    public ResponseEntity<ApiResponseModel> updateUser(@PathVariable String id, @Valid @RequestBody UserModel userDto) {
        UserEntity existingUser = usersService.getUserById(id);
        RequestUtil.PatchDataModifier(existingUser, userDto);
        usersService.updateUser(existingUser);
        UserResponse response = RequestUtil.NewPatchDataModifier(UserResponse.class, existingUser);
        logger.info("User updated successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("users.updated", response));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a user by ID")
    public ResponseEntity<ApiResponseModel> patchUpdateUser(@PathVariable String id,
            @Valid @RequestBody UserModel userPatchDto) {
        UserEntity existingUser = usersService.getUserById(id);
        RequestUtil.PatchDataModifier(existingUser, userPatchDto, true);
        UserResponse response = RequestUtil.NewPatchDataModifier(UserResponse.class, existingUser);
        logger.info("User patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("users.updated", response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    public ResponseEntity<ApiResponseModel> deleteUser(@PathVariable String id) {
        usersService.deleteUser(id);
        logger.info("User deleted successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("users.deleted", null));
    }
}
