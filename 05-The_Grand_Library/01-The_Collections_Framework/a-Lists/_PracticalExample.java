/**
 * _PracticalExample.java — The Living Manuscript: Student Grade Manager
 * 
 * A practical system that uses Lists to manage student grades,
 * demonstrating real-world List operations in a meaningful context.
 * 
 * Compile & Run:
 *   javac _PracticalExample.java
 *   java _PracticalExample
 */

import java.util.*;
import java.util.stream.Collectors;

public class _PracticalExample {

    // ── Student Record ──
    static class Student {
        private String name;
        private List<Integer> grades;

        public Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        public void addGrade(int grade) {
            if (grade < 0 || grade > 100) {
                System.out.println("  ⚠ Invalid grade: " + grade);
                return;
            }
            grades.add(grade);
        }

        public double getAverage() {
            if (grades.isEmpty()) return 0.0;
            return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }

        public int getHighest() {
            return grades.isEmpty() ? 0 : Collections.max(grades);
        }

        public int getLowest() {
            return grades.isEmpty() ? 0 : Collections.min(grades);
        }

        public String getLetterGrade() {
            double avg = getAverage();
            if (avg >= 90) return "A";
            if (avg >= 80) return "B";
            if (avg >= 70) return "C";
            if (avg >= 60) return "D";
            return "F";
        }

        public String getName() { return name; }
        public List<Integer> getGrades() { return new ArrayList<>(grades); }

        @Override
        public String toString() {
            return String.format("%s: %s (avg: %.1f, grade: %s)",
                name, grades, getAverage(), getLetterGrade());
        }
    }

    // ── Grade Book ──
    static class GradeBook {
        private List<Student> students;
        private String courseName;

        public GradeBook(String courseName) {
            this.courseName = courseName;
            this.students = new ArrayList<>();
        }

        public void addStudent(Student student) {
            students.add(student);
        }

        public void removeStudent(String name) {
            students.removeIf(s -> s.getName().equals(name));
        }

        public Optional<Student> findStudent(String name) {
            return students.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst();
        }

        public List<Student> getTopStudents(int count) {
            return students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverage).reversed())
                .limit(count)
                .collect(Collectors.toList());
        }

        public List<Student> getStudentsByGrade(String letterGrade) {
            return students.stream()
                .filter(s -> s.getLetterGrade().equals(letterGrade))
                .collect(Collectors.toList());
        }

        public double getClassAverage() {
            return students.stream()
                .mapToDouble(Student::getAverage)
                .average().orElse(0.0);
        }

        public void printReport() {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║   📊 " + courseName + " — Grade Report");
            System.out.println("╠══════════════════════════════════════════╣");

            // Sort by average descending
            List<Student> ranked = new ArrayList<>(students);
            ranked.sort(Comparator.comparingDouble(Student::getAverage).reversed());

            int rank = 1;
            for (Student s : ranked) {
                System.out.printf("║ #%d %-12s │ Avg: %5.1f │ %s%n",
                    rank++, s.getName(), s.getAverage(), s.getLetterGrade());
            }

            System.out.println("╠══════════════════════════════════════════╣");
            System.out.printf("║ Class Average: %.1f%n", getClassAverage());
            System.out.printf("║ Total Students: %d%n", students.size());
            System.out.println("╚══════════════════════════════════════════╝");
        }
    }

    // ── Main Demo ──
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   🏛️ THE LIVING MANUSCRIPT: GRADE BOOK   ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        GradeBook book = new GradeBook("Java OOP Mastery 101");

        // Create students and add grades
        Student alice = new Student("Alice");
        alice.addGrade(95); alice.addGrade(88); alice.addGrade(92); alice.addGrade(97);

        Student bob = new Student("Bob");
        bob.addGrade(78); bob.addGrade(82); bob.addGrade(75); bob.addGrade(80);

        Student charlie = new Student("Charlie");
        charlie.addGrade(91); charlie.addGrade(89); charlie.addGrade(94); charlie.addGrade(87);

        Student diana = new Student("Diana");
        diana.addGrade(65); diana.addGrade(72); diana.addGrade(68); diana.addGrade(71);

        Student eve = new Student("Eve");
        eve.addGrade(88); eve.addGrade(93); eve.addGrade(85); eve.addGrade(90);

        book.addStudent(alice);
        book.addStudent(bob);
        book.addStudent(charlie);
        book.addStudent(diana);
        book.addStudent(eve);

        // Print full report
        book.printReport();

        // Find top 3
        System.out.println("\n🏆 Top 3 Students:");
        book.getTopStudents(3).forEach(s ->
            System.out.println("  ★ " + s));

        // Find A-grade students
        System.out.println("\n🅰️ A-Grade Students:");
        book.getStudentsByGrade("A").forEach(s ->
            System.out.println("  → " + s));

        // Search for a student
        System.out.println("\n🔍 Searching for 'Bob':");
        book.findStudent("Bob").ifPresentOrElse(
            s -> System.out.println("  Found: " + s),
            () -> System.out.println("  Not found!")
        );

        System.out.println("\n✅ Grade Book demonstration complete!");
    }
}
