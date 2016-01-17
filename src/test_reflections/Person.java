package test_reflections;

public class Person {
	
	public String name;
	private int id;
	public double salary;
	public Person boss;
	
	public Person getBoss() {
		return boss;
	}
	public void setBoss(Person boss) {
		this.boss = boss;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	

}
