package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.exception.EntityNotFoundSchoolDiaryException;
import com.ssitacademy.berezinvv.schooldiary.model.*;
import com.ssitacademy.berezinvv.schooldiary.repository.*;
import com.ssitacademy.berezinvv.schooldiary.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

@RestController
@RequestMapping("/api")
public class InitController {

    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private ClassGroupService classGroupService;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private PupilRepository pupilRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @RequestMapping(value = "/init")
    public String initDB() {

        //employee
        Employee director = new Employee();
        director.setId(1);
        director.setFirstName("ВІКТОРІЯ");
        director.setLastName("ГЛУЩЕНКО");
        director.setPhone("+3(066)564 85 74");
        employeeRepository.save(director);

        Employee teacher = new Employee();
        teacher.setId(2);
        teacher.setFirstName("АНАСТАСІЯ");
        teacher.setLastName("ЧЕРНИШ");
        teacher.setPhone("+3(096)024 90 36");
        employeeRepository.save(teacher);

        //school
        School school = new School();
        school.setId(1);
        school.setName("Загальноосвітня школа №1");
        school.setDirector(director);
        school.setPhone("+3(099)874 51 02");
        schoolRepository.save(school);

        //lesson
        Lesson lessonUkr = new Lesson(1, "Українська мова");
        lessonRepository.save(lessonUkr);
        Lesson lessonUkrLit = new Lesson(2, "Українська література");
        lessonRepository.save(lessonUkrLit);
        Lesson lessonMaths = new Lesson(3, "Математика");
        lessonRepository.save(lessonMaths);
        Lesson lessonPhysEd = new Lesson(4, "Фізичне виховання");
        lessonRepository.save(lessonPhysEd);
        Lesson lessonEng = new Lesson(5, "English");
        lessonRepository.save(lessonEng);
        Lesson lessonCompScience = new Lesson(6, "Інформатика");
        lessonRepository.save(lessonCompScience);
        Lesson lessonWorkTraining = new Lesson(7, "Трудове навчання");
        lessonRepository.save(lessonWorkTraining);
        Lesson lessonArt = new Lesson(8, "Образотворче мистецтво");
        lessonRepository.save(lessonArt);
        Lesson lessonWorldAround = new Lesson(9, "Навколишній світ");
        lessonRepository.save(lessonWorldAround);
        Lesson lessonIndividual = new Lesson(10, "Індивідуальний урокт");
        lessonRepository.save(lessonIndividual);

        //pupil
        Pupil pupil_1 = new Pupil(1, "МИКОЛА","МЕЛЬНИК",  "", "");
        Pupil pupil_2 = new Pupil(2, "ВОЛОДИМИР","БОЙКО",  "", "");
        Pupil pupil_3 = new Pupil(3, "ОЛЕКСАНДР","КОВАЛЕНКО",  "", "");
        Pupil pupil_4 = new Pupil(4, "ВАСИЛЬ","БОНДАРЕНКО", "", "");
        Pupil pupil_5 = new Pupil(5, "ЮРІЙ","САВЧУК",  "", "");
        Pupil pupil_6 = new Pupil(6, "МАРІЯ","КРАВЕЦЬ",  "", "");
        Pupil pupil_7 = new Pupil(7, "НАТАЛІЯ","ДЯЧЕНКО",  "", "");
        Pupil pupil_8 = new Pupil(8, "ВІРА","ТЕРЕЩЕНКО",  "", "");
        Pupil pupil_9 = new Pupil(9, "САВА","МІЩЕНКО",  "", "");
        Pupil pupil_10 = new Pupil(10, "ДАНИЛО","БАБИЧ",  "", "");
        Pupil pupil_11 = new Pupil(11, "ЄВГЕН","БОДНАР",  "", "");
        Pupil pupil_12 = new Pupil(12, "СВЯТОСЛАВ","АНТОНЮК",  "", "");
        Pupil pupil_13 = new Pupil(13, "ЄВА","ЛЯШЕНКО",  "", "");
        Pupil pupil_14 = new Pupil(14, "ВІКТОРІЯ","ІВАНЧЕНКО",  "", "");
        Pupil pupil_15 = new Pupil(15, "ГЕННАДІЙ","ДЗЮБА",  "", "");
        Pupil pupil_16 = new Pupil(16, "АЛЛА","МИРОНЕНКО",  "", "");
        Pupil pupil_17 = new Pupil(17, "ТАМІЛА","ЯРОШЕНКО",  "", "");
        Pupil pupil_18 = new Pupil(18, "ЛІНА","ВОЛКОВА",  "", "");
        Pupil pupil_19 = new Pupil(19, "ЛІЛІЯ","ВОЛОШИНА",  "", "");
        Pupil pupil_20 = new Pupil(20, "СОФІЯ","ШЕВЧЕНКО",  "", "");


        Set<Pupil> pupils = new HashSet<>();
        pupils.add(pupil_1);
        pupils.add(pupil_2);
        pupils.add(pupil_3);
        pupils.add(pupil_4);
        pupils.add(pupil_5);
        pupils.add(pupil_6);
        pupils.add(pupil_7);
        pupils.add(pupil_8);
        pupils.add(pupil_9);
        pupils.add(pupil_10);
        pupils.add(pupil_11);
        pupils.add(pupil_12);
        pupils.add(pupil_13);
        pupils.add(pupil_14);
        pupils.add(pupil_15);
        pupils.add(pupil_16);
        pupils.add(pupil_17);
        pupils.add(pupil_18);
        pupils.add(pupil_19);
        pupils.add(pupil_20);

        //classGroup
        Date date2018 = java.sql.Date.valueOf("2018-09-01");
        ClassGroup classGroup = new ClassGroup(1, "3-А", date2018, teacher, school);
        classGroupService.create(classGroup);
        classGroup.setPupils(pupils);

        //schedule
        Schedule schedule_MONDAY_1 = new Schedule(1, 1, classGroup, DayOfWeek.MONDAY, teacher, lessonUkrLit);
        Schedule schedule_MONDAY_2 = new Schedule(2, 2, classGroup, DayOfWeek.MONDAY, teacher, lessonMaths);
        Schedule schedule_MONDAY_3 = new Schedule(3, 3, classGroup, DayOfWeek.MONDAY, teacher, lessonEng);
        Schedule schedule_MONDAY_4 = new Schedule(4, 4, classGroup, DayOfWeek.MONDAY, teacher, lessonCompScience);
        Schedule schedule_MONDAY_5 = new Schedule(5, 5, classGroup, DayOfWeek.MONDAY, teacher, lessonPhysEd);

        Schedule schedule_TUESDAY_6 = new Schedule(6, 1, classGroup, DayOfWeek.TUESDAY, teacher, lessonUkrLit);
        Schedule schedule_TUESDAY_7 = new Schedule(7, 2, classGroup, DayOfWeek.TUESDAY, teacher, lessonMaths);
        Schedule schedule_TUESDAY_8 = new Schedule(8, 3, classGroup, DayOfWeek.TUESDAY, teacher, lessonUkr);
        Schedule schedule_TUESDAY_9 = new Schedule(9, 4, classGroup, DayOfWeek.TUESDAY, teacher, lessonWorldAround);
        Schedule schedule_TUESDAY_10 = new Schedule(10, 5, classGroup, DayOfWeek.TUESDAY, teacher, lessonIndividual);

        Schedule schedule_WEDNESDAY_11 = new Schedule(11, 1, classGroup, DayOfWeek.WEDNESDAY, teacher, lessonUkr);
        Schedule schedule_WEDNESDAY_12 = new Schedule(12, 2, classGroup, DayOfWeek.WEDNESDAY, teacher, lessonMaths);
        Schedule schedule_WEDNESDAY_13 = new Schedule(13, 3, classGroup, DayOfWeek.WEDNESDAY, teacher, lessonWorldAround);
        Schedule schedule_WEDNESDAY_14 = new Schedule(14, 4, classGroup, DayOfWeek.WEDNESDAY, teacher, lessonEng);
        Schedule schedule_WEDNESDAY_15 = new Schedule(15, 5, classGroup, DayOfWeek.WEDNESDAY, teacher, lessonArt);

        Schedule schedule_THURSDAY_16 = new Schedule(16, 1, classGroup, DayOfWeek.THURSDAY, teacher, lessonPhysEd);
        Schedule schedule_THURSDAY_17 = new Schedule(17, 2, classGroup, DayOfWeek.THURSDAY, teacher, lessonMaths);
        Schedule schedule_THURSDAY_18 = new Schedule(18, 3, classGroup, DayOfWeek.THURSDAY, teacher, lessonUkr);
        Schedule schedule_THURSDAY_19 = new Schedule(19, 4, classGroup, DayOfWeek.THURSDAY, teacher, lessonUkrLit);
        Schedule schedule_THURSDAY_20 = new Schedule(20, 5, classGroup, DayOfWeek.THURSDAY, teacher, lessonArt);

        Schedule schedule_FRIDAY_21 = new Schedule(21, 1, classGroup, DayOfWeek.FRIDAY, teacher, lessonPhysEd);
        Schedule schedule_FRIDAY_22 = new Schedule(22, 2, classGroup, DayOfWeek.FRIDAY, teacher, lessonMaths);
        Schedule schedule_FRIDAY_23 = new Schedule(23, 3, classGroup, DayOfWeek.FRIDAY, teacher, lessonUkr);
        Schedule schedule_FRIDAY_24 = new Schedule(24, 4, classGroup, DayOfWeek.FRIDAY, teacher, lessonWorldAround);
        Schedule schedule_FRIDAY_25 = new Schedule(25, 5, classGroup, DayOfWeek.FRIDAY, teacher, lessonWorkTraining);
        scheduleRepository.save(schedule_MONDAY_1);
        scheduleRepository.save(schedule_MONDAY_2);
        scheduleRepository.save(schedule_MONDAY_3);
        scheduleRepository.save(schedule_MONDAY_4);
        scheduleRepository.save(schedule_MONDAY_5);
        scheduleRepository.save(schedule_TUESDAY_6);
        scheduleRepository.save(schedule_TUESDAY_7);
        scheduleRepository.save(schedule_TUESDAY_8);
        scheduleRepository.save(schedule_TUESDAY_9);
        scheduleRepository.save(schedule_TUESDAY_10);
        scheduleRepository.save(schedule_WEDNESDAY_11);
        scheduleRepository.save(schedule_WEDNESDAY_12);
        scheduleRepository.save(schedule_WEDNESDAY_13);
        scheduleRepository.save(schedule_WEDNESDAY_14);
        scheduleRepository.save(schedule_WEDNESDAY_15);
        scheduleRepository.save(schedule_THURSDAY_16);
        scheduleRepository.save(schedule_THURSDAY_17);
        scheduleRepository.save(schedule_THURSDAY_18);
        scheduleRepository.save(schedule_THURSDAY_19);
        scheduleRepository.save(schedule_THURSDAY_20);
        scheduleRepository.save(schedule_FRIDAY_21);
        scheduleRepository.save(schedule_FRIDAY_22);
        scheduleRepository.save(schedule_FRIDAY_23);
        scheduleRepository.save(schedule_FRIDAY_24);
        scheduleRepository.save(schedule_FRIDAY_25);

        //classGroup.setSchedules(schedules);
        classGroupService.create(classGroup);


        return "Done";
    }

    @RequestMapping(value = "/initDiaryRandom/{date}")
    public String initDiaryRandom(@PathVariable String date) {

        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        java.util.Date dt1 = null;
        try {
            dt1 = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat sdf = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Date sqlStartDate = new java.sql.Date(dt1.getTime());
        String finalDay = sdf.format(dt1).toUpperCase();

        ClassGroup classGroup = classGroupService.findById(1L).orElseThrow(() -> new EntityNotFoundSchoolDiaryException(1L, "schedule"));
        List<Schedule> schedules = scheduleRepository.findAllByClassGroupAndDay(classGroup, DayOfWeek.valueOf(finalDay));
        List<Pupil> pupils = pupilRepository.findAllPupilByClassGroup(classGroup);

        Random random = new Random();
        for (Schedule schedule : schedules) {
            for (int i = 0; i < 5; i++) {

                int index = random.nextInt(pupils.size() - 1);
                int grade = random.nextInt(10) + 3;

                Diary currentDiary = new Diary();
                currentDiary.setClassGroup(classGroup);
                currentDiary.setDate(sqlStartDate);
                currentDiary.setGrade(grade);
                currentDiary.setLesson(schedule.getLesson());
                currentDiary.setPupil(pupils.get(index));
                currentDiary.setTeacher(schedule.getTeacher());
                try {
                    diaryRepository.save(currentDiary);
                }catch (RuntimeException e){

                }
            }
        }

        return "Done " + date + " " + finalDay;
    }
}
