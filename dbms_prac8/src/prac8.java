import java.sql.*;
import java.util.*;

class prac8 {
    // Modify insert method to include marks, address, and age
    void insert(Statement st) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 'stop' to stop insertion");
        String query;

        while (true) {
            System.out.print("Enter Student Name to insert into table: ");
            String name = sc.next();
            if (name.equalsIgnoreCase("stop")) {
                break;
            }

            System.out.print("Enter Student Marks: ");
            int marks = sc.nextInt();

            System.out.print("Enter Student Address: ");
            String address = sc.next();

            System.out.print("Enter Student Age: ");
            int age = sc.nextInt();

            query = "INSERT INTO stud(s_name, marks, address, age) VALUES('" + name + "', " + marks + ", '" + address
                    + "', " + age + ")";
            st.executeUpdate(query);
        }
        System.out.println("Records inserted successfully!");
    }

    // Modify delete method to support deletion by multiple fields if needed
    void delete(Statement st) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter column name for deletion (s_id, s_name, marks, address, age): ");
        String col = sc.next();
        System.out.print("Enter value to delete record from table: ");
        String val = sc.next();
        String query = "DELETE FROM stud WHERE " + col + "='" + val + "'";
        st.executeUpdate(query);
        System.out.println("Record deleted successfully");
    }

    // Modify update method to support updating of new fields
    void update(Statement st) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter s_id of record to be updated: ");
        String id = sc.next();
        System.out.print("Enter column name for updation (s_name, marks, address, age): ");
        String col = sc.next();
        System.out.print("Enter new value for " + col + " to update record: ");
        String val = sc.next();
        String query = "UPDATE stud SET " + col + "='" + val + "' WHERE s_id =" + id;
        st.executeUpdate(query);
        System.out.println("Records updated successfully");
    }

    void fetch(Statement st) throws SQLException {
        ResultSet rs;
        String query = "SELECT * FROM stud";
        System.out.println("Contents of Table stud are as follows: ");
        rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("s_id") + ", Name: " +
                    rs.getString("s_name") + ", Marks: " +
                    rs.getInt("marks") + ", Address: " +
                    rs.getString("address") + ", Age: " +
                    rs.getInt("age"));
        }
        rs.close();
    }

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Scanner sc = new Scanner(System.in);

            String url = "jdbc:mysql://localhost:3306/misbah_db";
            String username = "root";
            String pw = "mishu@777";

            Connection con = DriverManager.getConnection(url, username, pw);
            System.out.println("Connection established");
            Statement st = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS stud(" +
                    "s_id INT AUTO_INCREMENT, " +
                    "s_name VARCHAR(50), " +
                    "marks INT, " +
                    "address VARCHAR(255), " +
                    "age INT, " +
                    "PRIMARY KEY(s_id))";
            st.executeUpdate(query);

            prac8 obj = new prac8();
            obj.insert(st);
            obj.fetch(st);

            Integer choice;
            char ans = 'n';
            while (ans == 'n') {
                System.out.println(
                        "Menu \n1. Fetch records \n2. Insert records \n3. Delete records \n4. Update records \n5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        obj.fetch(st);
                        break;

                    case 2:
                        obj.insert(st);
                        break;

                    case 3:
                        obj.delete(st);
                        break;

                    case 4:
                        obj.update(st);
                        break;

                    case 5:
                        ans = 'y';
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            query = "DROP TABLE IF EXISTS stud";
            st.executeUpdate(query);

            st.close();
            con.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// String url = "jdbc:mysql://10.10.13.97/te31455_db"; // table details
// String username = "te31455"; // MySQL credentials
// String password = "te31455";