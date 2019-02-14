package com.cloud.course.service;

import com.cloud.course.dto.CourseDTO;

import java.util.List;

/**
 * Created by Michael on 2017/11/3.
 */
public interface ICourseService {

    List<CourseDTO> courseList();
}
