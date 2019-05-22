package com.example.blog.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import com.example.blog.service.UserService
import com.example.blog.models.UserDto
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import javax.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PathVariable
import com.example.blog.UserRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.PutMapping

@RestController
class UserController {

	@Autowired
	lateinit var userService: UserService

	@Autowired
	lateinit var userRepository: UserRepository

	val logger = LogFactory.getLog(UserController::class.java)!!

	@GetMapping("/")
	fun blog(model: Model): String {
		println("kotlin app")
		model["title"] = "My First Kotlin Application"
		return "blog"
	}

	@PostMapping("/kotlin/postData")
	fun postUser(@RequestBody user: UserDto): UserDto {

		return userService.postUser(user)
	}

	@GetMapping("/kotlin/getData")
	fun findByUserName(@RequestParam name: String, response: HttpServletResponse): UserDto? =
		userService.findByUserName(name, response) ?: notFound(response)

	@GetMapping("/kotlin/getAllUsers")
	fun getUsers(): List<UserDto> = userService.findAll()

	@GetMapping("/{name}")
	fun getPost(@PathVariable name: String, response: HttpServletResponse): UserDto? =
		userRepository.findByName(name) ?: notFound(response)

	@DeleteMapping("/{name}")
	fun deleteUser(@PathVariable name: String): ResponseEntity<HttpStatus> {
		if (userRepository.findByName(name) != null) {
			userRepository.delete(userRepository.findByName(name))
			return ResponseEntity(HttpStatus.ACCEPTED)
		} else {
			logger.info("User not found : $name")
			return ResponseEntity(HttpStatus.NOT_FOUND)
		}

	}

	@DeleteMapping("/kotlin/{name}")
	fun deleteUser1(@PathVariable name: String): ResponseEntity<HttpStatus> {
		if (userService.deleteUser(name) != null.toString()) {
			return ResponseEntity(HttpStatus.ACCEPTED)
		} else {
			logger.info("User not found : $name")
			return ResponseEntity(HttpStatus.NOT_FOUND)
		}
	}

	@PutMapping("/kotlin/{userId}")
	fun modifyUser(@PathVariable userId: String, @RequestParam name: String, @RequestParam phone: String): ResponseEntity<UserDto> {

		return ResponseEntity(userService.modifyUser(userId, name, phone), HttpStatus.OK)

	}


	private fun <T> notFound(response: HttpServletResponse): T? {
		response.status = 404
		return null
	}


}
