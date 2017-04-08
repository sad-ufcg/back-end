package com.github.sadufcg.services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.CourseStudent;
import com.github.sadufcg.pojo.Student;
import com.github.sadufcg.pojo.Teacher;

/**
 * @author Antunes Dantas
 */
@Service
public class CsvClassCreatorImpl implements CsvClassCreator {

    CourseService courseService;
    TeacherService teacherService;
    StudentService studentService;

    @Autowired
    public CsvClassCreatorImpl(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    /**
     * The document is structured in the following way:
     *              courseName, courseNumber, semester
     *              siape, teacher's name
     *              name, lastname, email
     * @param file
     */
    public void createCourse(MultipartFile file) throws Exception {

        File courseFile = convertToFile(file);

        Scanner scanner = new Scanner(courseFile);
        Course course = createCourse(scanner.nextLine());
        Teacher teacher = createTeacher(scanner.nextLine());
        course.setTeacher(teacher);
        String studentData = scanner.nextLine();

        while(studentData != null) {
            Student student = createStudent(scanner.nextLine());
            CourseStudent courseStudent = new CourseStudent(student, course);
            if (student.getCourseStudent() == null) {
                student.setCourseStudent(new HashSet<CourseStudent>());
            }
            student.getCourseStudent().add(courseStudent);
            if (course.getCourseStudent() == null) {
                course.setCourseStudent(new HashSet<CourseStudent>());
            }
            course.getCourseStudent().add(courseStudent);
            studentService.update(student);
            courseService.update(course);
            studentData = scanner.nextLine();
        }
        scanner.close();
    }

    private File convertToFile(MultipartFile file) throws Exception {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private Course createCourse(String data) {
        String[] dataArray = data.split(",");
        String courseId = (dataArray[0] + dataArray[2] + dataArray[1]).toLowerCase();
        Course course = courseService.findById(courseId);
        if (course == null) {
            course = courseService.create(new Course(dataArray[0], Integer.getInteger(dataArray[1]), dataArray[2]));
        }
        return course;
    }

    private Teacher createTeacher(String data) {
        String[] dataArray = data.split(",");
        Teacher teacher = teacherService.findById(dataArray[0]);
        if (teacher == null) {
            teacher = teacherService.create(new Teacher(dataArray[0], dataArray[1]));
        }
        return teacher;
    }

    private Student createStudent(String data) {
        String[] dataArray = data.split(",");
        Student student = studentService.findByEmail(dataArray[2].trim());
        if (student == null) {
            String studanteName = dataArray[0] + " " + dataArray[1];
            student = new Student(studanteName, dataArray[2].trim());
            student = studentService.create(student);
        }
        return student;
    }

}
