package iskyles.tacoma.uw.edu.webservicelab;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.Random;

import iskyles.tacoma.uw.edu.webservicelab.authenticate.SignInActivity;

/**
 * Created by Ian Skyles on 5/9/2016.
 */
public class CourseActivityTest extends ActivityInstrumentationTestCase2<CourseActivity> {
    private Solo solo;

    public CourseActivityTest() {
        super(CourseActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testCourseList() {
        boolean fragmentLoaded = solo.searchText("short description");
        assertTrue("Course List fragment loaded", fragmentLoaded);
    }

    public void testAddWorks() {
        solo.clickOnView(getActivity().findViewById(R.id.fab));
        boolean textFound = solo.searchText("Course");
        assertTrue("Add a course fragment loaded", textFound);
    }

    public void testCourseAddButton() {
        solo.clickOnView(getActivity().findViewById(R.id.fab));
        Random random = new Random();
        //Generate a course number
        String courseNumber = "TCSS" + (random.nextInt(4) + 1)
                + (random.nextInt(4) + 1) + (random.nextInt(4) + 1);
        solo.enterText(0, courseNumber);
        solo.enterText(1, "I am a short description");
        solo.enterText(2, "I am a long description");
        solo.enterText(3, "I am a prereq");
        solo.clickOnButton("Add Course");
        boolean textFound = solo.searchText(courseNumber);
        assertTrue("Course add failed", textFound);
    }


    public void testLogout() {
        solo.clickOnView(getActivity().findViewById(R.id.action_logout));
        boolean textFound = solo.searchText("Enter your userid");
        assertTrue("Login fragment loaded", textFound);
        solo.enterText(0, "userid@");
        solo.enterText(1, "somepassword");
        solo.clickOnButton("SIGN IN");
        boolean worked = solo.searchText("computational worlds");
        assertTrue("Sign in worked!", worked);

    }


    public void testCourseDetail() {
        solo.clickInRecyclerView(0);
        boolean foundCourseDetail = solo.searchText("tcss 450");
        assertTrue("Course Detail fragment loaded", foundCourseDetail);
        solo.goBack();
        boolean foundCourseList = solo.searchText("tcss");
        assertTrue("Back to List works!", foundCourseList);
    }


    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();

    }

}
