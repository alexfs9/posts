package com.app.posts;

import com.app.posts.persistence.entity.PermissionEntity;
import com.app.posts.persistence.entity.RoleEntity;
import com.app.posts.persistence.entity.RoleEnum;
import com.app.posts.persistence.repository.IRoleRepository;
import com.app.posts.persistence.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class PostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IUserRepository userRepository, IRoleRepository roleRepository) {
		return args -> {
			PermissionEntity createPostPermission = PermissionEntity.builder()
					.name("CREATE_POST")
					.build();
			PermissionEntity updatePostPermission = PermissionEntity.builder()
					.name("UPDATE_POST")
					.build();
			PermissionEntity deletePostPermission = PermissionEntity.builder()
					.name("DELETE_POST")
					.build();
			PermissionEntity makeCommentPermission = PermissionEntity.builder()
					.name("MAKE_COMMENT")
					.build();

			RoleEntity basicUserRole = RoleEntity.builder()
					.roleEnum(RoleEnum.BASIC_USER)
					.permissions(Set.of(createPostPermission, updatePostPermission, deletePostPermission, makeCommentPermission))
					.build();

			roleRepository.save(basicUserRole);
		};
	}
}
