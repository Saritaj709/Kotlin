package com.example.blog.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import com.example.blog.service.UserService
import com.example.blog.repository.UserRepository
import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import com.example.blog.models.User
import javax.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PutMapping

@RestController
class UserController {

	@Autowired
	lateinit var userService: UserService

	val logger = LogFactory.getLog(UserController::class.java)!!
	
	@GetMapping("/")
	fun hello(): String {
		return "hello kotlin"
	}

	@PostMapping("/kotlin/postData")
	fun postUser(@RequestBody user: User): User {

		return userService.postUser(user)
	}


	@GetMapping("/kotlin/getData/{email}")
	fun findByUserEmail(@PathVariable email: String): User
	{
		return userService.findByEmail(email)
	}

	@GetMapping("/kotlin/getAllUsers")
	fun getUsers(): List<User> = userService.findAll()

	@DeleteMapping("/kotlin/deleteUser/{email}")
	fun deleteUser(@PathVariable email: String): ResponseEntity<HttpStatus> {
		if (userService.deleteUser(email) != null.toString()) {
			return ResponseEntity(HttpStatus.ACCEPTED)
		} else {
			logger.info("User not found : $email")
			return ResponseEntity(HttpStatus.NOT_FOUND)
		}
	}

	@PutMapping("/kotlin/modifyDetails/{email}")
	fun modifyUser(@PathVariable email: String, @RequestParam name: String, @RequestParam phone: String): ResponseEntity<User> {
		return ResponseEntity(userService.modifyUser(email, name, phone), HttpStatus.OK)
	}


	private fun <T> notFound(response: HttpServletResponse): T? {
		response.status = 404
		return null
	}


}