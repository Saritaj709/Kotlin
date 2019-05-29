package com.example.blog.models

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id

@Document(collection = "user")
class User {
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