package student;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Display reminders of students having a birthday soon.
 * @author you
 */
public class StudentApp {

	/**
	 * Print the names (and birthdays) of students having a birtday in the
	 * specified month.
	 * @param students list of students.
	 * @param predicate of generic students .
	 * @param check first name , last name , birth date and month of birth date .
	 * @param compare check before and after.
	 */
	public void filterAndPrint( List<Student> students, Predicate<Student> filter,Consumer<Student> action , Comparator<Student> compare) {
		students.stream().filter(filter).sorted(compare).forEach(action);
	}
	/*
	 * Main of student class .
	 * @param arg
	 */
	public static void main(String[] args) {
		List<Student> students = Registrar.getInstance().getStudents();
		LocalDate day = LocalDate.now();
		Predicate<Student> filter = (s) -> s.getBirthdate().getDayOfYear() >= day.getDayOfYear() && s.getBirthdate().getDayOfYear() <= day.getDayOfYear()+14 ;
		Consumer<Student> action = (s) -> System.out.println(s.getFirstname()+" "+ s.getLastname()+" will have birthday on "+s.getBirthdate().getDayOfMonth()+" "+s.getBirthdate().getMonth());
		Comparator<Student> byName = ( a , b ) -> a.getFirstname().charAt(0) - b.getFirstname().charAt(0);
		Comparator<Student> byBirthday = ( a, b) -> a.getBirthdate().getDayOfMonth() - b.getBirthdate().getDayOfMonth() ;
		StudentApp app = new StudentApp();
		app.filterAndPrint(students, filter,action, byBirthday);
	}
}
