package com.example.blog.repository

import org.springframework.data.mongodb.repository.MongoRepository
import com.example.blog.models.User

interface UserRepository : MongoRepository<User, String> {

	fun findByName(name: String): User?
	
	fun findByEmail(email:String):User

	fun findByUserId(userId: String): User
}