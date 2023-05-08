package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping("/{id}/previous")
    public Iterable<Course> getParentCourses(@PathVariable long id) {
        Course course = courseRepository.findById(id);
        List<Long> parentIdList = getIdListByCourse(course);
        return parentIdList == null ? List.of() : getCourseListByIdList(parentIdList);
    }

    private List<Long> getIdListByCourse(Course course) {
        String path = course.getPath();
        if (path == null) return null;
        return Arrays.stream(path.split("\\."))
                .map(Long::parseLong)
                .toList();
    }

    private Iterable<Course> getCourseListByIdList(List<Long> idList) {
        return courseRepository.findAllById(idList);
    }
    // END

}
