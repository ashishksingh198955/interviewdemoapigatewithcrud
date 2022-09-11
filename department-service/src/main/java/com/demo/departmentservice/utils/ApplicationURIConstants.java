
package com.demo.departmentservice.utils;

/**
 * The <code>ApplicationURIConstants</code> class holds URI constants used the application.
 *
 * @author Ashish Kumar Singh
 *
 */
public final class ApplicationURIConstants {

	private ApplicationURIConstants() {

	}

	public static final String BASE_API = "/api";	
	public static final String ID_PATH_VARIABLE = "/{id}";
	public static final String ADD_DEPARTMENT = "/adddepartment";
	public static final String LIST_OF_DEPARTMENT = "/getlistofdepartment";
	public static final String VIEW_DEPARTMENT="/viewdepartment"+ID_PATH_VARIABLE;
	public static final String DELETE_DEPARTMENT="/deletedepartment"+ID_PATH_VARIABLE;
	public static final String UPDATE_DEPARTMENT = "/updatedepartment";

}