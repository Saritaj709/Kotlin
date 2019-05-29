package com.example.blog.service

import com.example.blog.models.UserDto
import javax.servlet.http.HttpServletResponse

interface UserService {

	fun postUser(user:UserDto): UserDto
	fun findByUserName(name: String,response:HttpServletResponse): UserDto?
	
	fun findAll():List<UserDto>
	
	fun deleteUser(name:String):String
	
	fun modifyUser(userId:String,name:String,phone:String):UserDto
}