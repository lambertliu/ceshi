package it_job_analize_web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSort {
	  
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("王五",32)) ;
        personList.add(new Person("张三",30)) ;
        personList.add(new Person("赵六",33)) ;
        personList.add(new Person("李四",31)) ;
        personList.add(new Person("孙七",33)) ;
        personList.add(new Person("xzz",36)) ;
        personList.add(new Person("孙七2",22)) ;
        personList.add(new Person("孙七3",11)) ;
        personList.add(new Person("孙七4",12)) ;
        personList.add(new Person("孙七5",44)) ;
        personList.add(new Person("孙七5",25)) ;
        
//        Collections.sort(personList, new Comparator<Person>() {
//            @Override
//            public int compare(Person p1, Person p2) {
//                if(p1.getAge()>p2.getAge()){
//                    return 1;
//                }
//                else if(p1.getAge()<p2.getAge()){
//                    return 0; 
//                }
//                else{
//                    return p1.getName().compareTo(p2.getName()) ; // 调用String中的compareTo()方法
//                }
//            }
//        });
        Collections.sort(personList);
        System.out.println(personList);
    }  
}
 
class Person implements Comparable<Person>{
    private String name ;
    private int age ;
     
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String name,int age){
        this.name = name ;
        this.age = age ;
    }
    
    public String toString(){
        return "姓名：" + this.name + "；年龄：" + this.age ;
    }

	@Override
	public int compareTo(Person o) {
		int i = o.getAge()-this.getAge();
		
		return i;
	}
}

