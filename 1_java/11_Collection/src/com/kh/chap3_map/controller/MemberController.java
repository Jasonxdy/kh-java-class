package com.kh.chap3_map.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.kh.chap3_map.model.vo.Member;

public class MemberController {
	
//	- map:HashMap = new HashMap()
//			+ joinMembership(id:String, m:Member)
//			:boolean
//			+ logIn(id:String, password:String):String
//			+ changePassword(id:String, oldPw:String,
//			newPw:String):boolean
//			+ changeName(id:String,
//			newName:String):void
//			+ sameName(name:String) : Map

	
	private HashMap<String, Member> map = new HashMap<String, Member>();
	// HashMap 객체 생성
	
	public boolean joinMembership(String id, Member m){
	// 전달 받은 id가 없다면 id와 m을 map에 추가 후 true 반환
	// 있다면 false 값 반환
		if(map.containsKey(id)) {
			// map의 key값 중 전달받은 id가 포함되어 있다면
			return false;
		} else {
			map.put(id, m);
			return true;
		}
	}
	public String logIn(String id, String password){
	// 전달 받은 id가 존재하는지 확인 후 존재하면 Member에 저장된 비밀번호와
	// 사용자가 입력한 비밀번호가 같은지 비교.
	// 같다면 저장된 Member의 이름 반환, 그 외 모든 상황에는 null 반환
		if(map.containsKey(id)) {
			if(map.get(id).getPassword().equals(password)) {
				return map.get(id).getName();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	public boolean changePassword(String id, String oldPw, String newPw){
	// 아이디가 존재하면서 저장된 비밀번호와 사용자가 입력한 비밀번호(oldPw)가
	// 같을 때 새로운 비밀번호로 바꾸고 true 반환, 아니라면 false 반환
		if(map.containsKey(id)) {
			if(map.get(id).getPassword().equals(oldPw)) {
				return map.replace(id, map.get(id), new Member(newPw, map.get(id).getName()));
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public void changeName(String id, String newName) {
	//전달 받은 id를 통해 Member의 이름을 새로 입력한 이름으로 변경
		map.replace(id, new Member(map.get(id).getPassword(), newName));
	}
	public Map<String, String> sameName(String name){
	// 전달 받은 name이 저장된 데이터의 이름과 같으면
	// HashMap 객체에 해당 id와 이름 저장 후 객체 반환
	// 단, 엘리먼트 하나씩 뽑아내는 과정에서 keySet()을 쓸 것
		
		HashMap<String, String> same = new HashMap<String, String>();
		//id(String)와 이름 (String)을 저장할 해시맵을 새로 만듬
		Set<String> set = map.keySet();
		// Iterator 검색을 위해 keySet을 따로 뽑음
		Iterator<String> it = set.iterator();
		// KeySet에 접근하기 위한 Iterator 객체 생성... 굳이 Iterator 사용하는 이유?
		// 내가 보기엔 Set는 순서도 없고 중복도 없기 때문에 하나씩 지정하기 위해선 Iterator 필요
		while(it.hasNext()) {
			String key = it.next(); // it.next()하면 다음껄로 넘어가니까 일단 key 저장
			Member m = map.get(key); // 해당 key에 저장된 객체를 m에 담음
			if(m.getName().equals(name)) {
				same.put(key, name);
			}
		}
		return same;
		// 절대 VO에 있는거 그대로 전송하지 말고 값을 보낼 객체를 새로 만들어서 해당 값 담아서 보낼 것..
		
		
//		Set<String> set = map.keySet();
//		for (String s : set) {
//			if(map.get(s).getName().equals(name)){
//				return map;
//			} else {
//				return null;
//			}
//		}
//		return null; 
//		
	} 
}
