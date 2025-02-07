package org.reist.msvc.user.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class SessionLogTest {

    private SessionLog sessionLog;
    private Username mockUsername;

    @BeforeEach
    void setUp() {
        mockUsername = new Username("testUser", "12345", sessionLog); // Configurar un objeto de Username simulado
        mockUsername.setId(1L); // Asignar atributos para pruebas si es necesario
        sessionLog = new SessionLog("TestLocation", "127.0.0.1", 3600L, mockUsername);
    }

    @Test
    void testSessionLogInitialization() {
        assertNotNull(sessionLog);
        assertEquals("TestLocation", sessionLog.getLocation());
        assertEquals("127.0.0.1", sessionLog.getIp());
        assertEquals(3600L, sessionLog.getTimeSession());
        assertNotNull(sessionLog.getDateSession());
        assertEquals(mockUsername, sessionLog.getUsername());
    }

    @Test
    void testSettersAndGetters() {
        sessionLog.setLocation("NewLocation");
        assertEquals("NewLocation", sessionLog.getLocation());

        sessionLog.setIp("192.168.1.1");
        assertEquals("192.168.1.1", sessionLog.getIp());

        sessionLog.setTimeSession(7200L);
        assertEquals(7200L, sessionLog.getTimeSession());

        Date newDate = new Date();
        sessionLog.setDateSession(newDate);
        assertEquals(newDate, sessionLog.getDateSession());

        Username newUsername = new Username();
        newUsername.setId(2L);
        newUsername.setNickname("newTestUser");
        sessionLog.setUsername(newUsername);
        assertEquals(newUsername, sessionLog.getUsername());
    }

//    @Test
//    void testEqualsAndHashCode() {
//        SessionLog anotherSessionLog = new SessionLog("TestLocation", "127.0.0.1", 3600L, mockUsername);
//        assertEquals(sessionLog, anotherSessionLog);
//        assertEquals(sessionLog.hashCode(), anotherSessionLog.hashCode());
//    }

    @Test
    void testToString() {
        String toStringOutput = sessionLog.toString();
        assertTrue(toStringOutput.contains("TestLocation"));
        assertTrue(toStringOutput.contains("127.0.0.1"));
        assertTrue(toStringOutput.contains("3600"));
    }
}
