package com.example.blog.service

import com.example.blog.models.User
import javax.servlet.http.HttpServletResponse

interface UserService {

	fun postUser(user:User): User
	//fun findByUserName(name: String,response:HttpServletResponse): User?
	
	fun findByEmail(email:String):User
	
	fun findAll():List<User>
	
	fun deleteUser(email:String):String
	
	fun modifyUser(email:String,name:String,phone:String):User
}