package dwp;


import dwp.techtest.model.User;
import dwp.techtest.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dwp.util.LocationTestData.*;

public class UserTestData {

    public static User userInLondon = new User((long) 1, "", "", "", "", Constants.LONDON_LATITUDE, Constants.LONDON_LONGITUDE);
    protected static User userInLondon2 = new User((long) 2, "", "", "", "", Constants.LONDON_LATITUDE, Constants.LONDON_LONGITUDE);
    protected static User userWithin50MilesOfLondon = new User((long) 3, "", "", "", "", watford.getLatitude(), watford.getLongitude());
    protected static User userWithin50MilesOfLondon2 = new User((long) 4, "", "", "", "", crawley.getLatitude(), crawley.getLongitude());
    protected static User userWithin50MilesOfLondon3 = new User((long) 5, "", "", "", "", oxford.getLatitude(), oxford.getLongitude());
    protected static User userWithin50MilesOfLondon4 = new User((long) 6, "", "", "", "", nuneham.getLatitude(), nuneham.getLongitude());
    protected static User userOver50MilesOfLondon = new User((long) 7, "", "", "", "", blenheim.getLatitude(), blenheim.getLongitude());
    protected static User userOver50MilesOfLondon2 = new User((long) 8, "", "", "", "", banbury.getLatitude(), banbury.getLongitude());
    protected static User userOver50MilesOfLondon3 = new User((long) 9, "", "", "", "", birmingham.getLatitude(), birmingham.getLongitude());
    protected static User userFromGermany = new User((long) 10, "", "", "", "", germany.getLatitude(), germany.getLongitude());
    protected static User userWithInvalidLocationData2 = new User((long) 11, "", "", "", "", invalid.getLatitude(), invalid.getLongitude());
    protected static User userWithInvalidLocationData3 = new User((long) 12, "", "", "", "", invalid2.getLatitude(), invalid2.getLongitude());

    public static List<User> usersWithinLondon = new ArrayList<>(Arrays.asList(userInLondon, userInLondon2));
    public static List<User> usersInAndAroundLondon = new ArrayList<>(
            Arrays.asList(
                    userInLondon, userInLondon2,
                    userWithin50MilesOfLondon, userWithin50MilesOfLondon2,
                    userWithin50MilesOfLondon3, userWithin50MilesOfLondon4
            )
    );
    public static List<User> usersWithInvalidLocationData = new ArrayList<>(
            Arrays.asList(
                    userWithInvalidLocationData2, userWithInvalidLocationData3
            ));
    public static List<User> usersWithValidLocationDataNoDuplicates = new ArrayList<>(
            Arrays.asList(
                    userWithin50MilesOfLondon, userWithin50MilesOfLondon2,
                    userWithin50MilesOfLondon3, userWithin50MilesOfLondon4,
                    userOver50MilesOfLondon, userOver50MilesOfLondon2, userOver50MilesOfLondon3
            )
    );

    public static List<User> usersWithValidLocationDataWithDuplicates = new ArrayList<>(
            Arrays.asList(
                    userInLondon, userInLondon2,
                    userWithin50MilesOfLondon, userWithin50MilesOfLondon2,
                    userWithin50MilesOfLondon3, userWithin50MilesOfLondon4,
                    userOver50MilesOfLondon, userOver50MilesOfLondon2, userOver50MilesOfLondon3
            )
    );

    public static List<User> allUsers = new ArrayList<>(
            Arrays.asList(
                    userInLondon, userInLondon2, userWithin50MilesOfLondon, userWithin50MilesOfLondon2,
                    userWithin50MilesOfLondon3, userWithin50MilesOfLondon4,
                    userOver50MilesOfLondon, userOver50MilesOfLondon2, userOver50MilesOfLondon3,
                    userFromGermany, userWithInvalidLocationData2, userWithInvalidLocationData3
            )
    );


}
