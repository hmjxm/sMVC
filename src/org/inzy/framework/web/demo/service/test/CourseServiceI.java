package org.inzy.framework.web.demo.service.test;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.demo.entity.test.CourseEntity;


public interface CourseServiceI extends CommonService{

	/**
	 * 保存课程
	 *@Author JueYue
	 *@date   2013-11-10
	 *@param  course
	 */
	void saveCourse(CourseEntity course);
	/**
	 * 更新课程
	 *@Author JueYue
	 *@date   2013-11-10
	 *@param  course
	 */
	void updateCourse(CourseEntity course);

}
