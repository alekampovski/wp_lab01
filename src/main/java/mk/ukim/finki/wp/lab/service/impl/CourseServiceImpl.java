package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumeration.Type;
import mk.ukim.finki.wp.lab.model.exception.NoSuchCourseException;
import mk.ukim.finki.wp.lab.model.exception.NoSuchStudentException;
import mk.ukim.finki.wp.lab.model.exception.StudentAlreadyExistInCourseException;
import mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherService = teacherService;
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long courseId) {
        if (Objects.nonNull(courseId)) {
            return this.courseRepository.findById(courseId);
        } else throw new IllegalArgumentException();
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        if (Objects.nonNull(courseId)) {
            return this.courseRepository.findAllStudentsByCourseId(courseId);
        } else throw new IllegalArgumentException();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if (Strings.isEmpty(username) && Objects.isNull(courseId)) {
            throw new IllegalArgumentException();
        }
        Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchCourseException(courseId));
        Student student = studentRepository.findById(username)
                .orElseThrow(() -> new NoSuchStudentException(username));
        List<Student> students = course.getStudents();
        if (students.stream().anyMatch(i -> i.getUsername().equals(username)))
            throw new StudentAlreadyExistInCourseException(username);
        students.add(student);
        return this.courseRepository.save(course);
    }

//    @Override
//    public void addCoursesToStudents() {
//        List<Student> students = this.studentRepository.findAllStudents();
//        students.forEach(student -> {
//            List<Course> foundCourses = this.courseRepository.isCourseListenedByStudent(student);
//            if (Objects.nonNull(foundCourses) && !foundCourses.isEmpty()) {
//                student.getCourseList().removeIf(foundCourses::contains);
//                student.getCourseList().addAll(foundCourses);
//            }
//        });
//    }

    @Override
    public Optional<Course> save(String name, String description, Long teacherId, Type type) {
        Teacher teacher = this.teacherService.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException(teacherId));
        return Optional.of(this.courseRepository.save(new Course(name, description, teacher, type)));
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> edit(Long id, String name, String description, Long teacherId, Type type) {
        Optional<Course> course = findById(id);
        if (course.isEmpty()) {
            throw new NoSuchCourseException(id);
        }
        Course courseObj = course.get();
        courseObj.setName(name);
        courseObj.setDescription(description);
        Optional<Teacher> teacher = teacherService.findById(teacherId);
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException(teacherId);
        }
        courseObj.setTeacher(teacher.get());
        courseObj.setType(type);
        return Optional.of(courseRepository.save(courseObj));
    }
}
