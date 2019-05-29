package com.example.blog.service

import org.springframework.stereotype.Service
import java.util.Scanner
import com.example.blog.models.UserDto
import org.springframework.beans.factory.annotation.Autowired
import com.example.blog.UserRepository
import javax.servlet.http.HttpServletResponse

@Service("userService")
class UserServiceImpl : UserService {

	@Autowired
	lateinit var userRepository: UserRepository

	lateinit var user: UserDto

	override fun postUser(user: UserDto): UserDto {

		if(userRepository.findByName(user.name)!=null){
			return user;
		}
		userRepository.save(user);
		return user;
	}

	override fun findByUserName(name: String, response: HttpServletResponse): UserDto? =
		userRepository.findByName(name) ?: notFound(response);

	override fun findAll(): List<UserDto> {
		return userRepository.findAll()
	}

	override fun deleteUser(name: String): String {
		if (userRepository.findByName(name) != null) {
			userRepository.delete(userRepository.findByName(name))
			return "user deleted"
		} else {
			return null.toString()
		}
	}

	override fun modifyUser(userId: String, name: String, phone: String): UserDto {
		user = userRepository.findByUserId(userId)
		
		user.name=name
		user.phone=phone
		
		userRepository.save(user)
		//userRepository.delete(user)

		return user

	}

	private fun <T> notFound(response: HttpServletResponse): T? {
		response.status = 404
		return null
	}

}