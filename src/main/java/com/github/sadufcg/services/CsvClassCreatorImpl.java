package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

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
    public void createCourse(MultipartFile file) {

        File courseFile = convertToFile(file);

        try {
            Scanner scanner = new Scanner(courseFile);
            Course course = createCourse(scanner.nextLine());
            Teacher teacher = createTeacher(scanner.nextLine());
            course.setTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File convertToFile(MultipartFile file) {
        try {
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return convFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

}
