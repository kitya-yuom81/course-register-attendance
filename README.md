# 📚 Course Registration & Attendance Manager (CRAM)

**Java 17 • MVC • JDBC • PostgreSQL • Console UI**  
A school system to manage **courses, sections, enrollments, attendance, and grades** with roles for **Admin**, **Teacher**, and **Student**. Starts as a clean console app; easy to extend to REST/React later.

---

## ✨ Features

- **Auth & Roles:** Admin / Teacher / Student, BCrypt-hashed passwords  
- **Courses & Sections:** CRUD courses; create sections with capacity & weekly schedule  
- **Enrollment:** enroll/drop, **waitlist**, **time-clash detection**  
- **Attendance:** generate class sessions; mark **PRESENT/LATE/ABSENT**  
- **Grades:** assignments, submissions, weighted final grade  
- **Reports:** transcript by term, attendance %, section fill rate  
- **Utilities (nice-to-have):** CSV import/export, .ics calendar export

---

## 🧱 Tech Stack

- **Language:** Java 17+  
- **Build:** Maven  
- **DB:** PostgreSQL 14+ (JDBC)  
- **Security:** BCrypt for password hashing  
- **Pattern:** MVC with repository → service → controller → view

---

## 🗂️ Project Structure (Tree)

```
cram/
├─ README.md
├─ pom.xml
├─ sql/
│  ├─ 001_init.sql
│  ├─ 002_seed_roles.sql
│  └─ 003_demo_data.sql
└─ src/
   ├─ main/
   │  ├─ java/com/example/cram/
   │  │  ├─ Main.java
   │  │  ├─ config/
   │  │  │  ├─ AppConfig.java
   │  │  │  └─ DbConfig.java
   │  │  ├─ model/
   │  │  │  ├─ dto/
   │  │  │  │  ├─ User.java
   │  │  │  │  ├─ Course.java
   │  │  │  │  ├─ Section.java
   │  │  │  │  ├─ Enrollment.java
   │  │  │  │  ├─ Session.java
   │  │  │  │  ├─ Attendance.java
   │  │  │  │  ├─ Assignment.java
   │  │  │  │  ├─ Submission.java
   │  │  │  │  └─ GradeSummary.java
   │  │  │  └─ enums/
   │  │  │     ├─ Role.java
   │  │  │     └─ AttendanceStatus.java
   │  │  ├─ repository/
   │  │  │  ├─ UserRepository.java
   │  │  │  ├─ CourseRepository.java
   │  │  │  ├─ SectionRepository.java
   │  │  │  ├─ EnrollmentRepository.java
   │  │  │  ├─ SessionRepository.java
   │  │  │  ├─ AttendanceRepository.java
   │  │  │  ├─ AssignmentRepository.java
   │  │  │  ├─ SubmissionRepository.java
   │  │  │  └─ jdbc/
   │  │  │     ├─ JdbcUserRepository.java
   │  │  │     ├─ JdbcCourseRepository.java
   │  │  │     ├─ JdbcSectionRepository.java
   │  │  │     ├─ JdbcEnrollmentRepository.java
   │  │  │     ├─ JdbcSessionRepository.java
   │  │  │     ├─ JdbcAttendanceRepository.java
   │  │  │     ├─ JdbcAssignmentRepository.java
   │  │  │     └─ JdbcSubmissionRepository.java
   │  │  ├─ service/
   │  │  │  ├─ AuthService.java
   │  │  │  ├─ CourseService.java
   │  │  │  ├─ SectionService.java
   │  │  │  ├─ EnrollmentService.java
   │  │  │  ├─ AttendanceService.java
   │  │  │  └─ GradeService.java
   │  │  ├─ controller/
   │  │  │  ├─ AuthController.java
   │  │  │  ├─ AdminController.java
   │  │  │  ├─ TeacherController.java
   │  │  │  └─ StudentController.java
   │  │  ├─ view/
   │  │  │  ├─ ConsoleView.java
   │  │  │  ├─ MenuView.java
   │  │  │  ├─ AdminView.java
   │  │  │  ├─ TeacherView.java
   │  │  │  └─ StudentView.java
   │  │  ├─ util/
   │  │  │  ├─ PasswordHasher.java
   │  │  │  ├─ Input.java
   │  │  │  ├─ Validation.java
   │  │  │  └─ TimeTableClash.java
   │  │  └─ exceptions/
   │  │     ├─ AppException.java
   │  │     ├─ DbException.java
   │  │     └─ ValidationException.java
   │  └─ resources/application.properties
   └─ test/java/ (optional)
```

---

## 📒 Project Structure (Table)

| Path | Purpose |
|---|---|
| `Main.java` | App entry; load config; route to main menu. |
| `config/AppConfig.java` | Wire controllers ↔ services ↔ repositories (simple DI). |
| `config/DbConfig.java` | Build JDBC connection / DataSource for PostgreSQL. |
| `model/dto/User.java` | User record (id, username, passwordHash, fullName, email, roleId). |
| `model/dto/Course.java` | Course (code, title, credit, department). |
| `model/dto/Section.java` | Section (courseId, teacherId, term, capacity, day/time/room). |
| `model/dto/Enrollment.java` | Enrollment (sectionId, studentId, status, enrolledAt). |
| `model/dto/Session.java` | One class meeting (sectionId, date, oneTimeCode). |
| `model/dto/Attendance.java` | Attendance row (sessionId, studentId, status, markedAt). |
| `model/dto/Assignment.java` | Assignment (title, maxScore, weight, dueDate). |
| `model/dto/Submission.java` | Submission (assignmentId, studentId, score, submittedAt). |
| `model/dto/GradeSummary.java` | Computed final grade for a student in a section. |
| `model/enums/Role.java` | ADMIN / TEACHER / STUDENT. |
| `model/enums/AttendanceStatus.java` | PRESENT / LATE / ABSENT. |
| `repository/*Repository.java` | Interfaces for CRUD & queries per entity (clean unit-testable). |
| `repository/jdbc/*` | JDBC implementations using `PreparedStatement`. |
| `service/AuthService.java` | Register/login with BCrypt; role checks. |
| `service/CourseService.java` | CRUD courses. |
| `service/SectionService.java` | Create/list sections; schedule helpers. |
| `service/EnrollmentService.java` | Enroll/drop; **waitlist**; **time-clash detection**. |
| `service/AttendanceService.java` | Generate sessions, mark attendance, stats. |
| `service/GradeService.java` | Assignments, scoring, weighted final grade. |
| `controller/*Controller.java` | Orchestrate role flows & menu actions. |
| `view/*View.java` | Console I/O: menus, prompts, pretty tables. |
| `util/PasswordHasher.java` | BCrypt hash/verify. |
| `util/Input.java` | Safe console input + retry loops. |
| `util/Validation.java` | Email/username/time/score checks. |
| `util/TimeTableClash.java` | Detect overlapping time slots. |
| `exceptions/*` | Custom exceptions for clean error handling. |
| `resources/application.properties` | DB connection settings. |
| `sql/001_init.sql` | Main schema. |
| `sql/002_seed_roles.sql` | Seed ADMIN/TEACHER/STUDENT roles. |
| `sql/003_demo_data.sql` | Optional sample users/courses/sections. |

---

## 🗃️ Database Design (PostgreSQL)

**Core tables:** `roles`, `users`, `courses`, `sections`, `enrollments`, `sessions`, `attendance`, `assignments`, `submissions`

**Mini ERD (ASCII)**

```
users (role_id → roles.id)
  └─< sections.teacher_id
courses
  └─< sections
sections
  ├─< enrollments (student_id → users.id)
  ├─< sessions
  └─< assignments
sessions
  └─< attendance (student_id → users.id)
assignments
  └─< submissions (student_id → users.id)
```

**Key columns (summary)**

| Table | Columns (key ones) |
|---|---|
| `roles` | `id`, `name` (ADMIN/TEACHER/STUDENT) |
| `users` | `id`, `username`, `password_hash`, `full_name`, `email`, `role_id` |
| `courses` | `id`, `code`, `title`, `credit`, `department` |
| `sections` | `id`, `course_id`, `teacher_id`, `term`, `capacity`, `day_of_week`, `start_time`, `end_time`, `room` |
| `enrollments` | `id`, `section_id`, `student_id`, `status`, `enrolled_at` |
| `sessions` | `id`, `section_id`, `session_date`, `one_time_code` |
| `attendance` | `id`, `session_id`, `student_id`, `status`, `marked_at` |
| `assignments` | `id`, `section_id`, `title`, `max_score`, `weight`, `due_date` |
| `submissions` | `id`, `assignment_id`, `student_id`, `score`, `submitted_at` |

---

## 🚀 Quick Start

### 1) Prerequisites
- **Java 17+**, **Maven 3.9+**
- **PostgreSQL 14+** (or Docker)

### 2) Create DB & User (psql)
```sql
CREATE DATABASE cram;
CREATE USER cram_user WITH PASSWORD 'secret';
GRANT ALL PRIVILEGES ON DATABASE cram TO cram_user;
```

### 3) Configure app
`src/main/resources/application.properties`
```properties
db.url=jdbc:postgresql://localhost:5432/cram
db.user=cram_user
db.password=secret
```

### 4) Initialize schema & seed
Run the SQL files in order:
```
sql/001_init.sql
sql/002_seed_roles.sql
sql/003_demo_data.sql   # optional
```

### 5) Build & Run
```bash
# build
mvn -q -DskipTests package

# run (with exec plugin)
mvn -q exec:java -Dexec.mainClass="com.example.cram.Main"
```

**Add to `pom.xml` if needed:**
```xml
<dependencies>
  <dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.4</version>
  </dependency>
  <dependency>
    <groupId>org.mindrot</groupId>
    <artifactId>jbcrypt</artifactId>
    <version>0.4</version>
  </dependency>
</dependencies>

<build>
  <plugins>
    <plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>exec-maven-plugin</artifactId>
      <version>3.5.0</version>
      <configuration>
        <mainClass>com.example.cram.Main</mainClass>
      </configuration>
    </plugin>
  </plugins>
</build>
```

### 🐳 Docker (optional)
```yaml
services:
  pg:
    image: postgres:16
    environment:
      POSTGRES_DB: cram
      POSTGRES_USER: cram_user
      POSTGRES_PASSWORD: secret
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
volumes:
  pgdata:
```

---

## 🧭 Console Usage (Example Flow)

**Admin**
1. Login as Admin  
2. Create Teacher/Student accounts  
3. Create Courses & Sections  
4. View reports (section fill rate, attendance overview)

**Teacher**
1. Login as Teacher  
2. Open “My Sections” → Roster  
3. Generate sessions for the term  
4. Mark attendance each class  
5. Create assignments and score submissions

**Student**
1. Login as Student  
2. Browse courses/sections → Enroll  
3. See weekly schedule  
4. View grades & attendance history

---

## 🔌 Sample Interfaces (Signatures Only)

```java
// DTOs as records (simple & immutable)
public record User(long id, String username, String passwordHash, String fullName, String email, long roleId) {}
public record Course(long id, String code, String title, int credit, String department) {}
public record Section(long id, long courseId, long teacherId, String term, int capacity,
                      int dayOfWeek, String startTime, String endTime, String room) {}
```

```java
// Repositories
public interface EnrollmentRepository {
  boolean existsClash(long studentId, int dayOfWeek, String startTime, String endTime);
  int capacityUsed(long sectionId);
  void add(Enrollment e);
  void updateStatus(long enrollmentId, String status);
  // ...
}
```

```java
// Services
public interface EnrollmentService {
  void enroll(long studentId, long sectionId);     // waitlist if full; check clash
  void drop(long studentId, long sectionId);
  void autoEnrollFromWaitlist(long sectionId);
}
```

---

## 🛡️ Security Notes

- Store **BCrypt hashes** only (no plain passwords).  
- Always use **PreparedStatement** to avoid SQL injection.  
- Validate inputs (lengths, ranges, time formats).  
- Limit role access in controllers (Admin vs Teacher vs Student).

---

## 🛣️ Roadmap

- Export **.ics** schedule per student  
- CSV import/export for courses/users  
- REST API layer → React UI  
- Role-based authorization checks & audit logs

---

## 🧪 Testing (optional)

- Unit test services with in-memory DB or a test schema  
- Use testcontainers for PostgreSQL if you prefer real DB tests

---

