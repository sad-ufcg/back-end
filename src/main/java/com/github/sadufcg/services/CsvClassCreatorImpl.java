package com.github.sadufcg.services;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.CourseStudent;
import com.github.sadufcg.pojo.Student;
import com.github.sadufcg.pojo.Teacher;
import com.github.sadufcg.repositories.CourseRepository;
import com.github.sadufcg.repositories.CourseStudentRepository;
import com.github.sadufcg.repositories.StudentRepository;
import com.github.sadufcg.repositories.TeacherRepository;

/**
 * @author Antunes Dantas
 */
@Service
public class CsvClassCreatorImpl implements CsvClassCreator {

    CourseRepository courseRepository;
    TeacherRepository teacherService;
    StudentRepository studentRepository;
    CourseStudentRepository courseStudentRepository;

    @Autowired
    public CsvClassCreatorImpl(CourseRepository courseService, TeacherRepository teacherService, StudentRepository studentService, CourseStudentRepository courseStudentRepository) {
        this.courseRepository = courseService;
        this.teacherService = teacherService;
        this.studentRepository = studentService;
        this.courseStudentRepository = courseStudentRepository;
    }

    /**
     * The document is structured in the following way:
     *              courseName, courseNumber, semester
     *              siape, teacher's name
     *              name, lastname, email
     * @param file
     */
    public void createCourse(MultipartFile file) throws Exception {

        Scanner scanner = new Scanner(new ByteArrayInputStream(file.getBytes()));
        String courseData = scanner.nextLine();
        Teacher teacher = createTeacher(scanner.nextLine());
        Course course = createCourse(courseData, teacher);
        while(scanner.hasNextLine()) {
            Student student = createStudent(scanner.nextLine());
            CourseStudent courseStudent = new CourseStudent(student, course);
            courseStudentRepository.save(courseStudent);
        }
        scanner.close();
    }

    private Course createCourse(String data, Teacher teacher) {
        String[] dataArray = data.trim().split(",");
        String name = dataArray[0];
        int number = Integer.parseInt(dataArray[1]);
        String semester = dataArray[2];
        Course course = courseRepository.save(new Course(name, number, semester, teacher));
        return course;
    }

    private Teacher createTeacher(String data) {
    	if (data == null || data.trim().isEmpty()) {
    		return null;
    	}
        String[] dataArray = data.split(",");
        Teacher teacher = teacherService.findOne(dataArray[0]);
        if (teacher == null) {        	
            teacher = teacherService.save(new Teacher(dataArray[0], dataArray[1]));
        }
        return teacher;
    }

    private Student createStudent(String data) {
        String[] dataArray = data.split(",");
        Student student = studentRepository.findByEmail(dataArray[2].trim());
        if (student == null) {
            String studanteName = dataArray[0] + " " + dataArray[1];
            student = new Student(studanteName, dataArray[2].trim());
            student = studentRepository.save(student);
        }
        return student;
    }

}
