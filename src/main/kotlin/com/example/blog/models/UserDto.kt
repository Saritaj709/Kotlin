package com.example.blog.models

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class UserDto{
	@Id
	var userId:String?=null
	var name:String
	var phone:String
	var email:String	
		
	get()=field
	set(value){
		field=value
	}
			
	constructor(name:String,phone:String,email:String){
		this.name=name
		this.phone=phone
		this.email=email
	}	
}