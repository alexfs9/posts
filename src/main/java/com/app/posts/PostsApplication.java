package com.app.posts;

import com.app.posts.persistence.entity.PermissionEntity;
import com.app.posts.persistence.entity.RoleEntity;
import com.app.posts.persistence.entity.RoleEnum;
import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.persistence.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class PostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IUserRepository userRepository) {
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

			RoleEntity basicUserRole = RoleEntity.builder()
					.roleEnum(RoleEnum.BASIC_USER)
					.permissions(Set.of(createPostPermission, updatePostPermission, deletePostPermission))
					.build();

			UserEntity user = UserEntity.builder()
					.username("alexfs9")
					.email("alex@gmail.com")
					.password(new BCryptPasswordEncoder().encode("12345"))
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(basicUserRole))
					.build();

			userRepository.save(user);
		};
	}
}
