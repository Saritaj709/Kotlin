package com.example.blog.service

import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletResponse
import com.example.blog.models.User
import org.springframework.beans.factory.annotation.Autowired
import com.example.blog.repository.UserRepository

@Service("userService")
class UserServiceImpl : UserService {

	@Autowired
	lateinit var userRepository: UserRepository

	lateinit var user: User

	override fun postUser(user: User): User {

		//if (user.email?=userRepository.findByEmail(user.email)!=null) {
			//return user;
		//}
		userRepository.save(user);
		return user;
	}

	//override fun findByUserName(name: String, response: HttpServletResponse): User? =
	//	userRepository.findByName(name) ?: notFound(response);

	override fun findByEmail(email: String): User {
		return userRepository.findByEmail(email)
	}

	override fun findAll(): List<User> {
		return userRepository.findAll()
	}

	override fun deleteUser(email: String): String {
		userRepository.delete(userRepository.findByEmail(email))
		return "user deleted"
	}

	override fun modifyUser(email: String, name: String, phone: String): User {
		user = userRepository.findByEmail(email)

		user.name = name
		user.phone = phone

		userRepository.save(user)

		return user

	}

}