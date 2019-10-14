package com.hg.jeedev.modules.zhdd.jcbk.service;


import org.springframework.stereotype.Component;

import qjm.rpc.anno.RpcService;

@RpcService(PersonService.class)
@Component
public class PersonServiceImpl implements PersonService{

	public Person getInfo() {
		Person person = new Person();
		person.setAge(22);
		person.setName("qjm");
		person.setSex("男");
		System.out.println(222);
		return person;
	}

	public boolean printInfo(Person person) {
		System.out.println(111);
		if(person != null){
			System.out.println(person);
			return true;
		}
		return false;
	}

}
