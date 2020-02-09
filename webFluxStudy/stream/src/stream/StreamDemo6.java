package stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

public class StreamDemo6 {

	public static void main(String[] args) {
		List<Student> students = Arrays.asList(new Student("小明", 10, Gender.MALE, Grade.FOUR),
				new Student("小白", 8, Gender.FEMALE, Grade.ONE), new Student("小黑", 6, Gender.MALE, Grade.THREE),
				new Student("小黄", 7, Gender.FEMALE, Grade.ONE), new Student("小赤", 2, Gender.MALE, Grade.ONE),
				new Student("小玲", 4, Gender.FEMALE, Grade.TWO), new Student("大玲", 5, Gender.MALE, Grade.ONE),
				new Student("冯太后", 9, Gender.FEMALE, Grade.ONE), new Student("小五", 11, Gender.MALE, Grade.FOUR));

		// 得到所有学生的年龄列表
		//不用lambda stu -> stu.getAge()，尽量用方法引用,不会多生成一个类似lambda$0这样的函数
		List<Integer> ages = students.stream().map(Student::getAge).collect(Collectors.toList());
		System.out.println(ages);
				
		//去重
		Set<Integer> sets = students.stream().map(Student::getAge).collect(Collectors.toSet());
		System.out.println(sets);
		
		//Collectors.toCollection 自己传一个集合的构建法方法
		Set<Integer> set2 = students.stream().map(Student::getAge).collect(Collectors.toCollection(TreeSet::new));
		
		
		//统计汇总信息
		IntSummaryStatistics collect = students.stream().collect(Collectors.summarizingInt(Student::getAge));
		System.out.println("年龄汇总信息" + collect);
		
		//分块 特殊分组-只有两组
		Map<Boolean, List<Student>> genders = students.stream().collect(Collectors.partitioningBy(s -> s.getGender() == Gender.FEMALE));
		MapUtils.verbosePrint(System.out, "男女学生列表", genders);
		
		//分组
		Map<Grade, List<Student>> grades = students.stream().collect(Collectors.groupingBy(Student::getGrade));
		
		MapUtils.verbosePrint(System.out, "男女班级列表", grades);
		
		//得到所有班级学生个数
		//Collectors.counting() 返回一个Collector类型的接受元素，类型为T ，它计算输入元素的数量。 如果没有元素，结果为0。
		Map<Grade, Long> gradecount = students.stream().collect(Collectors.groupingBy(Student::getGrade,Collectors.counting()));
		MapUtils.verbosePrint(System.out, "班级学生个数列表", grades);
	}
}

class Student {
	private String name;

	private int age;

	private Gender gender;

	private Grade grade;

	public Student(String name, int age, Gender gender, Grade grade) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.grade = grade;
	}

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{"+name + age + gender + grade+"}";
	}

}

enum Gender {
	MALE, FEMALE;
}

enum Grade {
	ONE, TWO, THREE, FOUR;
}