package ocm.yalinlan.hello;

/**
 *<ul>
 *<li>类名称: Person</li>
 *<li>类描述: </li>
 *<li>创建人: quanyixiang</li>
 *<li>创建时间: 2022/1/29 14:44</li>
 *</ul>
 **/
public class Person {
	private String name;
	private Integer age;

	public Person() {
	}

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}