package com.kh.chap3_map.part1.run;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.kh.chap3_map.part1.model.vo.Snack;

public class Run {

	public static void main(String[] args) {
		
		// key는 String, value는 Snack으로 타입을 제한한 HashMap 객체 생성
		Map<String, Snack> map = new HashMap<String, Snack>();
		
		// 1. put(K key, V value);
		map.put("오리온", new Snack("다이제", 2500, 1105));
		map.put("해태제과", new Snack("맛동산", 1500, 420));
		map.put("농심", new Snack("바나나킥", 1500, 315));
		
		System.out.println(map); // 모든 Set, Map등은 toString이 오버라이딩 되어있음
		
		// 중복되는 키값의 데이터 추가
		
		map.put("오리온", new Snack("초코파이", 4000, 2052));
		
		System.out.println(map);
		// 다이제 -> 초코파이로 바뀜 / Key값이 중복되면 마지막에 추가된 value로 덮어씌움.
		
		// 2. get(Object key) --> Map에서 해당하는 key값의 value를 얻어오는 메소드
		System.out.println(map.get("오리온")); // -> 인덱스 번호가 아닌 key값을 적어주는 것
		
		// 3. size() --> Map에 저장되어 있는 key의 개수(객체의 개수)를 반환
		System.out.println("Map에 저장되어 있는 데이터의 개수 : " + map.size());
		
		// 4. replace(K key, V value) --> Map에 저장된 데이터 중 key값이 같은 Entry의 value를 수정
		// 여기서 Entry란? -> key = value 한 쌍을 일컫는 말
		map.replace("오리온", new Snack("다이제", 2500, 1105));
		System.out.println(map);
		
		// 이때 put도 덮어쓰기이므로 replace 사용하는 이유? :
		// put()같은 경우에는 key값이 중복되지 않으면 데이터가 추가되지만, replace()는 key값이 중복되지 않으면 데이터를 추가하지 않고 null 값을 반환한다
		
		// 5. remove(K key) --> 해당 key를 가지고 있는 Entry를 삭제
		map.remove("해태제과");
		System.out.println(map);

		
		
		
		// Map에 저장된 데이터를 하나씩 반복 접근하기
		// Iterator 반복자를 사용하는데, 그 이유는 Key가 Set의 특징을 지니므로!
		
		// 1. KeySet() 활용 --> Map에 저장된 데이터 중 key 값들만 Set으로 추출
		Set<String> keySet = map.keySet();		
		Iterator<String> it = keySet.iterator();
		// keySet이 참조하고 있는 Set의 값들을 Iterator를 이용하여 반복자에 담는 과정
		
		// 반복문을 이용하여 key와 value 출력하기
		while(it.hasNext()) {
			// 반복자를 이용하여 key값을 알 수 있음.
			
			String key = it.next(); // it.next()는 호출 할때마다 다음것으로 넘어가서 한번 호출 시 값을 따로 빼놔야 함. (key가 자료형이 String이므로)
			System.out.println("key : " + key);
			System.out.println("value : " + map.get(key));
		}
		
		// 2. keySet() + 향상된 for문 사용
		System.out.println();
		for (String key : keySet) { // keySet이라는 Collection에 담겨있는 객체를 string이라는 참조변수로 하나씩 참조함
			System.out.println("key : " + key);
			System.out.println("value : " + map.get(key));
		}
		
		
		// 3. entrySet() 활용
		// Map 인터페이스 내부의 인터페이스인 Entry 활용..
		System.out.println();
		Set<Entry<String, Snack>> entrySet = map.entrySet();
		Iterator<Entry<String, Snack>> it2 = entrySet.iterator();
		while (it2.hasNext()) {
			Entry<String, Snack> entry = it2.next(); // Entry<String, Snack> 타입으로 반환
			System.out.println("key : " + entry.getKey());
			System.out.println("value : " + entry.getValue());
		}
		
		// Map의 장점 : key를 이용하기 때문에 value를 노출시키지 않는다
		
		
	}

}
