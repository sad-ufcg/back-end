package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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

    public void createCourse(MultipartFile file) {

        File courseFile = convertToFile(file);

        Course course = new Course();

        try {
            Scanner scanner = new Scanner(courseFile);
            String courseInfo = scanner.nextLine();
            String teacherInfo = scanner.nextLine();
            String aluno = scanner.nextLine();
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

    private Teacher createTeacher(String data) {
        String[] dataArray = data.split(",");
        Teacher teacher = new Teacher(dataArray[0], dataArray[1]);
        return teacher;
    }

}
