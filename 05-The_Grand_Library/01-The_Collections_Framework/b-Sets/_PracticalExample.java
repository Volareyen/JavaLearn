/**
 * _PracticalExample.java — The Living Manuscript: Course Enrollment System
 * 
 * Uses Sets to manage student enrollment ensuring no duplicate registrations,
 * and demonstrates set operations for schedule conflict detection.
 * 
 * Compile & Run:  javac _PracticalExample.java && java _PracticalExample
 */
import java.util.*;
import java.util.stream.Collectors;

public class _PracticalExample {

    static class Course {
        private String code;
        private String name;
        private int maxCapacity;
        private Set<String> enrolledStudents;
        private Set<String> prerequisites;

        public Course(String code, String name, int maxCapacity) {
            this.code = code;
            this.name = name;
            this.maxCapacity = maxCapacity;
            this.enrolledStudents = new LinkedHashSet<>();
            this.prerequisites = new HashSet<>();
        }

        public void addPrerequisite(String courseCode) { prerequisites.add(courseCode); }

        public boolean enroll(String studentId) {
            if (enrolledStudents.size() >= maxCapacity) {
                System.out.println("  ⚠ " + code + " is full!");
                return false;
            }
            boolean added = enrolledStudents.add(studentId);
            if (!added) System.out.println("  ⚠ " + studentId + " already enrolled in " + code);
            return added;
        }

        public boolean drop(String studentId) { return enrolledStudents.remove(studentId); }
        public boolean isEnrolled(String studentId) { return enrolledStudents.contains(studentId); }
        public int getAvailableSeats() { return maxCapacity - enrolledStudents.size(); }

        public String getCode() { return code; }
        public String getName() { return name; }
        public Set<String> getEnrolledStudents() { return new HashSet<>(enrolledStudents); }
        public Set<String> getPrerequisites() { return new HashSet<>(prerequisites); }

        public void print() {
            System.out.printf("  [%s] %s — %d/%d enrolled%n", code, name,
                enrolledStudents.size(), maxCapacity);
        }
    }

    static class EnrollmentSystem {
        private Map<String, Course> courses = new LinkedHashMap<>();
        private Map<String, Set<String>> studentCompletedCourses = new HashMap<>();

        public void addCourse(Course course) { courses.put(course.getCode(), course); }

        public void registerCompleted(String studentId, String... codes) {
            studentCompletedCourses.computeIfAbsent(studentId, k -> new HashSet<>())
                .addAll(Arrays.asList(codes));
        }

        public boolean enrollStudent(String studentId, String courseCode) {
            Course course = courses.get(courseCode);
            if (course == null) { System.out.println("  ⚠ Course not found: " + courseCode); return false; }

            Set<String> completed = studentCompletedCourses.getOrDefault(studentId, Collections.emptySet());
            if (!completed.containsAll(course.getPrerequisites())) {
                Set<String> missing = new HashSet<>(course.getPrerequisites());
                missing.removeAll(completed);
                System.out.println("  ⚠ Missing prerequisites for " + courseCode + ": " + missing);
                return false;
            }
            return course.enroll(studentId);
        }

        public Set<String> getSharedStudents(String code1, String code2) {
            Set<String> s1 = courses.getOrDefault(code1, new Course("", "", 0)).getEnrolledStudents();
            Set<String> s2 = courses.getOrDefault(code2, new Course("", "", 0)).getEnrolledStudents();
            Set<String> shared = new HashSet<>(s1);
            shared.retainAll(s2);
            return shared;
        }

        public Set<String> getAllEnrolledStudents() {
            Set<String> all = new TreeSet<>();
            courses.values().forEach(c -> all.addAll(c.getEnrolledStudents()));
            return all;
        }

        public void printReport() {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║   📚 ENROLLMENT REPORT              ║");
            System.out.println("╚══════════════════════════════════════╝");
            courses.values().forEach(Course::print);
            System.out.println("  Total unique students: " + getAllEnrolledStudents().size());
        }
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║  🏛️ COURSE ENROLLMENT SYSTEM         ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        EnrollmentSystem sys = new EnrollmentSystem();

        Course java101 = new Course("CS101", "Intro to Java", 3);
        Course oop201 = new Course("CS201", "OOP Mastery", 3);
        oop201.addPrerequisite("CS101");
        Course ds301 = new Course("CS301", "Data Structures", 3);
        ds301.addPrerequisite("CS201");

        sys.addCourse(java101); sys.addCourse(oop201); sys.addCourse(ds301);

        // Register completed courses
        sys.registerCompleted("Alice", "CS101");
        sys.registerCompleted("Bob", "CS101");
        sys.registerCompleted("Charlie", "CS101", "CS201");

        // Enroll students
        System.out.println("── Enrolling Students ──");
        sys.enrollStudent("Alice", "CS101");  // Already completed but can retake
        sys.enrollStudent("Alice", "CS201");  // Has prereq
        sys.enrollStudent("Bob", "CS201");    // Has prereq
        sys.enrollStudent("Charlie", "CS301"); // Has both prereqs
        sys.enrollStudent("Diana", "CS201");  // Missing prereq!
        sys.enrollStudent("Alice", "CS201");  // Duplicate!

        sys.printReport();

        // Set operations
        System.out.println("\n── Shared Students (CS101 ∩ CS201) ──");
        System.out.println("  " + sys.getSharedStudents("CS101", "CS201"));

        System.out.println("\n── All Unique Students ──");
        System.out.println("  " + sys.getAllEnrolledStudents());

        System.out.println("\n✅ Enrollment system demo complete!");
    }
}
