
import java.sql.*;

public class UserService {

    public static boolean register(String name, String email, String password) throws Exception {
        if(name.isBlank() || email.isBlank() || password.isBlank())
            throw new Exception("All fields are required.");

        if(!email.matches("[^@]+@[^@]+\\.[^@]+"))
            throw new Exception("Invalid email format.");

        if(!PasswordUtil.isStrong(password))
            throw new Exception("Weak password (min 8 chars, include letters & numbers).");

        try (Connection con = DB.getConnection()) {
            String sql = "INSERT INTO users(name,email,password_hash) VALUES(?,?,?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, email.toLowerCase());
                ps.setString(3, PasswordUtil.hash(password));
                ps.executeUpdate();
                return true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Email already registered.");
        }
    }

    public static boolean login(String email, String password) throws Exception {
        try (Connection con = DB.getConnection()) {
            String sql = "SELECT password_hash FROM users WHERE email=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email.toLowerCase());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) return false;
                String stored = rs.getString(1);
                return stored.equals(PasswordUtil.hash(password));
            }
        }
    }

    public static boolean resetPassword(String email, String newPassword) throws Exception {
        if(!PasswordUtil.isStrong(newPassword))
            throw new Exception("Provide a strong password (8+ chars, letters & numbers).");

        try (Connection con = DB.getConnection()) {
            String sql = "UPDATE users SET password_hash=? WHERE email=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, PasswordUtil.hash(newPassword));
                ps.setString(2, email.toLowerCase());
                int updated = ps.executeUpdate();
                // Do not reveal whether email exists — return true anyway
                return updated > 0;
            }
        }
    }
}
