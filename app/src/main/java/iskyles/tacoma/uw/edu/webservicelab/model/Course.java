package iskyles.tacoma.uw.edu.webservicelab.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ian Skyles on 4/25/2016.
 */
public class Course implements Serializable{
    private String mCourseId;
    private String mShortDescription;
    private String mLongDescription;
    private String mPrereqs;

    public static final String ID = "id", SHORT_DESC = "shortDesc"
            , LONG_DESC = "longDesc", PRE_REQS = "prereqs";


    public Course(String theMCourseId, String theMShortDescription, String theMLongDescription, String theMPrereqs) {
        mCourseId = theMCourseId;
        mShortDescription=theMShortDescription;
        mLongDescription=theMLongDescription;
        mPrereqs=theMPrereqs;
    }
    /**
     * Parses the json string, returns an error message if unsuccessful.
     * Returns course list if success.
     * @param courseJSON
     * @return reason or null if successful.
     */
    public static String parseCourseJSON(String courseJSON, List<Course> courseList) {
        String reason = null;
        if (courseJSON != null) {
            try {
                JSONArray arr = new JSONArray(courseJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    Course course = new Course(obj.getString(Course.ID), obj.getString(Course.SHORT_DESC)
                            , obj.getString(Course.LONG_DESC), obj.getString(Course.PRE_REQS));
                    courseList.add(course);
                }
            } catch (JSONException e) {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }

        }
        return reason;
    }


    public void setMCourseID(String theNewID) {
        mCourseId=theNewID;
    }
    public String getMCourseId() {
        return mCourseId;
    }
    public String getMShortDescription() {
        return mShortDescription;
    }
    public String getMLongDescription() {
        return mLongDescription;
    }
    public String getMPrereqs() {
        return mLongDescription;
    }
}
