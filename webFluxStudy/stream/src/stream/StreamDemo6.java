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
		List<Student> students = Arrays.asList(new Student("С��", 10, Gender.MALE, Grade.FOUR),
				new Student("С��", 8, Gender.FEMALE, Grade.ONE), new Student("С��", 6, Gender.MALE, Grade.THREE),
				new Student("С��", 7, Gender.FEMALE, Grade.ONE), new Student("С��", 2, Gender.MALE, Grade.ONE),
				new Student("С��", 4, Gender.FEMALE, Grade.TWO), new Student("����", 5, Gender.MALE, Grade.ONE),
				new Student("��̫��", 9, Gender.FEMALE, Grade.ONE), new Student("С��", 11, Gender.MALE, Grade.FOUR));

		// �õ�����ѧ���������б�
		//����lambda stu -> stu.getAge()�������÷�������,���������һ������lambda$0�����ĺ���
		List<Integer> ages = students.stream().map(Student::getAge).collect(Collectors.toList());
		System.out.println(ages);
				
		//ȥ��
		Set<Integer> sets = students.stream().map(Student::getAge).collect(Collectors.toSet());
		System.out.println(sets);
		
		//Collectors.toCollection �Լ���һ�����ϵĹ���������
		Set<Integer> set2 = students.stream().map(Student::getAge).collect(Collectors.toCollection(TreeSet::new));
		
		
		//ͳ�ƻ�����Ϣ
		IntSummaryStatistics collect = students.stream().collect(Collectors.summarizingInt(Student::getAge));
		System.out.println("���������Ϣ" + collect);
		
		//�ֿ� �������-ֻ������
		Map<Boolean, List<Student>> genders = students.stream().collect(Collectors.partitioningBy(s -> s.getGender() == Gender.FEMALE));
		MapUtils.verbosePrint(System.out, "��Ůѧ���б�", genders);
		
		//����
		Map<Grade, List<Student>> grades = students.stream().collect(Collectors.groupingBy(Student::getGrade));
		
		MapUtils.verbosePrint(System.out, "��Ů�༶�б�", grades);
		
		//�õ����а༶ѧ������
		//Collectors.counting() ����һ��Collector���͵Ľ���Ԫ�أ�����ΪT ������������Ԫ�ص������� ���û��Ԫ�أ����Ϊ0��
		Map<Grade, Long> gradecount = students.stream().collect(Collectors.groupingBy(Student::getGrade,Collectors.counting()));
		MapUtils.verbosePrint(System.out, "�༶ѧ�������б�", grades);
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