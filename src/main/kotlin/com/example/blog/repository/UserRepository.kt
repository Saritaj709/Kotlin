package com.example.blog

import com.example.blog.models.UserDto
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository:MongoRepository<UserDto,String>{
	
	fun findByName(name: String) : UserDto?
	
	fun findByUserId(userId:String):UserDto
}