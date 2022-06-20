package loudsound;

import loudsound.model.User;
import loudsound.setup.TestKieSessionFactory;
import loudsound.setup.TestUtil;
import loudsound.setup.UserRulesKieStateInitializer;
import loudsound.stateinitializer.KieStateInitializer;
import org.drools.core.time.SessionPseudoClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRulesTest {
    private final static Logger logger = LoggerFactory.getLogger(UserRulesTest.class);
    private KieSession kieSession;
    private SessionPseudoClock kieClock;

    @BeforeEach
    public void initEach() {
        kieSession = TestKieSessionFactory.getSession(logger);
        kieClock = kieSession.getSessionClock();
    }

    @DisplayName("User is declared a Rising Star (song quantity, listens and likes)")
    @Test
    public void userIsDeclaredRisingStarBySongsActivity() {
        //  arrange
        KieStateInitializer initializer = UserRulesKieStateInitializer.getAnonymousUserToBeDeclaredRisingStarBySongActivityInitializer();
        initializer.initializeState(kieSession);

        //  act
        TestUtil.insertListeningEventsWithTimeApart(kieSession, 10);

        // assert
        assertUserTitle(initializer, User.Title.RISING_STAR);
    }

    @DisplayName("User is declared a RISING STAR (top chart activity)")
    @Test
    public void userIsDeclaredRisingStarByTopChartActivity() {
        //  arrange
        KieStateInitializer initializer = UserRulesKieStateInitializer.getAnonymousUserToBeDeclaredRisingStarByTopChartActivityInitializer();
        initializer.initializeState(kieSession);

        //  act
        TestUtil.insertListeningEventsWithTimeApart(kieSession, 10);

        // assert
        assertUserTitle(initializer, User.Title.RISING_STAR);
    }

    @DisplayName("User is revoked RISING STAR title after some time")
    @Test
    public void userIsRevokedTitleRisingStar() {
        //  arrange
        KieStateInitializer initializer = UserRulesKieStateInitializer.getRisingStarUserInitializer();
        initializer.initializeState(kieSession);

        //  act
        kieClock.advanceTime(5, TimeUnit.MINUTES);
        for (int i = 0; i < 4; i++) {
            TestUtil.insertListeningEventsWithTimeApart(kieSession, 10);
        }

        for (int i = 0; i < 5; i++) {
            TestUtil.likeTestSong(kieSession);
        }

        kieClock.advanceTime(6, TimeUnit.MINUTES);
        kieSession.fireAllRules();

        // assert
        assertUserTitle(initializer, User.Title.ANONYMOUS);
    }

    @DisplayName("User is not revoked RISING STAR title after some time")
    @Test
    public void userIsNotRevokedTitleRisingStar() {
        //  arrange
        KieStateInitializer initializer = UserRulesKieStateInitializer.getRisingStarUserInitializer();
        initializer.initializeState(kieSession);

        //  act
        kieClock.advanceTime(5, TimeUnit.MINUTES);
        for (int i = 0; i < 6; i++) {
            TestUtil.insertListeningEventsWithTimeApart(kieSession, 10);
        }

        for (int i = 0; i < 7; i++) {
            TestUtil.likeTestSong(kieSession);
        }

        kieClock.advanceTime(6, TimeUnit.MINUTES);
        kieSession.fireAllRules();

        // assert
        assertUserTitle(initializer, User.Title.RISING_STAR);
    }

    @DisplayName("User is declared LEGEND")
    @Test
    public void userIsDeclaredLegend() {
        //  arrange
        KieStateInitializer initializer = UserRulesKieStateInitializer.getRisingStarUserToBeDeclaredLegendInitializer();
        initializer.initializeState(kieSession);

        //  act
        TestUtil.insertListeningEventsWithTimeApart(kieSession, 10);
        TestUtil.likeTestSong(kieSession);

        // assert
        assertUserTitle(initializer, User.Title.LEGEND);
    }

    private void assertUserTitle(KieStateInitializer initializer, User.Title title) {
        User user = getUserFromSession(initializer);
        assertEquals(title, user.getTitle());
    }

    private User getUserFromSession(KieStateInitializer initializer) {
        FactHandle handle = initializer.getUserFactHandle(
                TestUtil.ARTIST);
        return (User) kieSession.getObject(handle);
    }

}
