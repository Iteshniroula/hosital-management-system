package project;

import java.sql.*;
import java.util.Objects;

public class Data_base {
    Connection connection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hospital_db";
        String user = "root";
        String pw = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Error " + e);
        }
        return DriverManager.getConnection(url, user, pw);
    }

    void insertUser(String uname,
                    String password,
                    String post
                    ) throws SQLException{
        String sql = "INSERT INTO user VALUES(NULL,?,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,uname);
        st.setString(2,password);
        st.setString(3,post);

        st.executeUpdate();
    }

    void deleteUser(int id) throws SQLException{
        String sql = "DELETE FROM user WHERE U_Id='"+id+"'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }

    ResultSet user(String uname) throws SQLException {
        String sql = "SELECT * FROM user WHERE Username='"+uname+"'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        return st.executeQuery(sql);
    }

    ResultSet user() throws SQLException {
        String sql = "SELECT * FROM user";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        return st.executeQuery(sql);
    }

    void insertDoctor(
            String name,
            String address,
            Date dob,
            String specialization,
            String Timing,
            String Contact,
            String gender,
            String day,
            String photo,
            int Uid
    ) throws SQLException {

        String sql = "INSERT INTO doctor VALUES(NULL,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, name);
        st.setString(2, address);
        st.setString(3,gender);
        st.setDate(4,dob);
        st.setString(5,specialization);
        st.setString(6,photo);
        st.setString(7,Contact);
        st.setString(8,day);
        st.setString(9,Timing);
        st.setInt(10,Uid);

        st.executeUpdate();
    }

    void deleteDoctor(int id) throws SQLException{
        String sql = "DELETE FROM doctor WHERE D_Id='"+id+"'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }

    void updateDoctor(
            int Id,
            String name,
            String address,
            Date date,
            String specification,
            String timing,
            String contact,
            String gender,
            String day) throws SQLException {
        String Updatequeryname = "UPDATE doctor SET D_Name='" + name + "'WHERE D_Id='" + Id + "'";
        String Updatequeryaddress = "UPDATE doctor SET D_Address='" + address + "'WHERE D_Id='" + Id + "'";
        String updatequerydate = "UPDATE doctor SET  DOB='" + date + "'WHERE D_Id='" + Id + "'";
        String updatequeryspecification = "UPDATE doctor SET  Specification='" + specification + "'WHERE D_Id='" + Id + "'";
        String updatequertiming = "UPDATE doctor SET  Timing='" + timing + "'WHERE D_Id='" + Id + "'";
        String updatequercontact = "UPDATE doctor SET  Contact='" + contact + "'WHERE D_Id='" + Id + "'";
        String updatequergender = "UPDATE doctor SET  Gender='" + gender + "'WHERE D_Id='" + Id + "'";
        String updatequerday = "UPDATE doctor SET  Days='" + day + "'WHERE D_Id='" + Id + "'";
        String sql = "SELECT * FROM doctor WHERE D_Id='" + Id + "'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate(Updatequeryname);
        st.executeUpdate(Updatequeryaddress);
        st.executeUpdate(updatequerydate);
        st.executeUpdate(updatequeryspecification);
        st.executeUpdate(updatequertiming);
        st.executeUpdate(updatequercontact);
        st.executeUpdate(updatequergender);
        if(!Objects.equals(day, "")) {
            st.executeUpdate(updatequerday);
        }
        st.executeQuery(sql);
    }

    ResultSet Doctor() throws SQLException {
        String sql = "SELECT * FROM doctor";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet Doctor(int id) throws SQLException {
        String sql = "SELECT * FROM doctor WHERE D_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet DoctorAge(int id) throws SQLException {
        String sql = "SELECT TIMESTAMPDIFF(YEAR,DOB,CURDATE()) AS Age from doctor where D_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet doctoruser(int id) throws SQLException{
        String sql = "SELECT user.U_Id, user.Username, doctor.D_Id FROM user LEFT JOIN doctor ON user.U_Id = doctor.UId WHERE user.U_Id=doctor.UId AND doctor.D_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.prepareStatement(sql);
        return  st.executeQuery(sql);
    }

    ResultSet doctoruser(String uname) throws SQLException{
        String sql = "SELECT user.U_Id, user.Username, doctor.D_Id FROM user LEFT JOIN doctor ON user.U_Id = doctor.UId WHERE user.Username='"+uname+"'";
        Connection conn = connection();
        Statement st = conn.prepareStatement(sql);
        return  st.executeQuery(sql);
    }

    ResultSet doctorauto() throws SQLException{
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE Table_schema = 'hospital_db' AND TABLE_NAME = 'doctor'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    void insertRecp(
            String name,
            String address,
            Date dob,
            String Contact,
            String gender,
            String shift,
            String photo,
            int Uid
    ) throws SQLException {

        String sql = "INSERT INTO receptionist VALUES(NULL,?,?,?,?,?,?,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, name);
        st.setString(2, address);
        st.setDate(3,dob);
        st.setString(4,Contact);
        st.setString(5,photo);
        st.setString(6,shift);
        st.setString(7,gender);
        st.setInt(8,Uid);

        st.executeUpdate();
    }

    void deleteRecp(int id) throws SQLException{
        String sql = "DELETE FROM receptionist WHERE Rec_Id='"+id+"'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }

    void updateRecp(
            int Id,
            String name,
            String address,
            Date date,
            String contact,
            String gender,
            String shift) throws SQLException {
        String Updatequeryname = "UPDATE receptionist SET Rec_Name='" + name + "'WHERE Rec_Id='" + Id + "'";
        String Updatequeryaddress = "UPDATE receptionist SET Rec_Address='" + address + "'WHERE Rec_Id='" + Id + "'";
        String updatequerydate = "UPDATE receptionist SET  DOB='" + date + "'WHERE Rec_Id='" + Id + "'";
        String updatequercontact = "UPDATE receptionist SET  Contact='" + contact + "'WHERE Rec_Id='" + Id + "'";
        String updatequergender = "UPDATE receptionist SET  Gender='" + gender + "'WHERE Rec_Id='" + Id + "'";
        String updatequershift = "UPDATE receptionist SET  Shift='" + shift + "'WHERE Rec_Id='" + Id + "'";
        String sql = "SELECT * FROM receptionist WHERE Rec_Id='" + Id + "'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate(Updatequeryname);
        st.executeUpdate(Updatequeryaddress);
        st.executeUpdate(updatequerydate);
        st.executeUpdate(updatequercontact);
        st.executeUpdate(updatequergender);
            st.executeUpdate(updatequershift);
        st.executeQuery(sql);
    }

    ResultSet Recp() throws SQLException {
        String sql = "SELECT * FROM receptionist";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet Recpuser(int id) throws SQLException{
        String sql = "SELECT user.U_Id, user.Username, receptionist.Rec_Id FROM user LEFT JOIN receptionist ON user.U_Id = receptionist.UId WHERE user.U_Id=receptionist.UId AND receptionist.Rec_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.prepareStatement(sql);
        return  st.executeQuery(sql);
    }

    ResultSet Recp(int id) throws SQLException {
        String sql = "SELECT * FROM receptionist WHERE Rec_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet recpauto() throws SQLException{
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE Table_schema = 'hospital_db' AND TABLE_NAME = 'receptionist'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    void insertPatient(
            String name,
            String address,
            Date dob,
            String Contact,
            String gender,
            String Disease,
            String photo,
            int Uid
    ) throws SQLException {

        String sql = "INSERT INTO patient VALUES(NULL,?,?,?,?,?,?,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, name);
        st.setString(2, address);
        st.setDate(4,dob);
        st.setString(5,Contact);
        st.setString(8,photo);
        st.setString(6,Disease);
        st.setString(3,gender);
        st.setInt(7,Uid);

        st.executeUpdate();
    }

    void deletePatient(int id) throws SQLException{
        String sql = "DELETE FROM patient WHERE P_Id='"+id+"'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }

    void updatePatient(
            int Id,
            String name,
            String address,
            Date date,
            String contact,
            String gender,
            String Disease) throws SQLException {
        String Updatequeryname = "UPDATE patient SET P_Name='" + name + "'WHERE P_Id='" + Id + "'";
        String Updatequeryaddress = "UPDATE patient SET P_Address='" + address + "'WHERE P_Id='" + Id + "'";
        String updatequerydate = "UPDATE patient SET  DOB='" + date + "'WHERE P_Id='" + Id + "'";
        String updatequercontact = "UPDATE patient SET  Contact='" + contact + "'WHERE P_Id='" + Id + "'";
        String updatequeryGender = "UPDATE patient SET  Gender='" + gender + "'WHERE P_Id='" + Id + "'";
        String updatequeryDisease = "UPDATE patient SET  Disease='" + Disease + "'WHERE P_Id='" + Id + "'";
        String sql = "SELECT * FROM patient WHERE P_Id='" + Id + "'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate(Updatequeryname);
        st.executeUpdate(Updatequeryaddress);
        st.executeUpdate(updatequerydate);
        st.executeUpdate(updatequercontact);
        st.executeUpdate(updatequeryGender);
        st.executeUpdate(updatequeryDisease);
        st.executeQuery(sql);
    }

    ResultSet Patient() throws SQLException {
        String sql = "SELECT * FROM patient";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet Patientuser(int id) throws SQLException{
        String sql = "SELECT user.U_Id, user.Username, patient.P_Id FROM user LEFT JOIN patient ON user.U_Id = patient.UId WHERE user.U_Id=patient.UId AND patient.P_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.prepareStatement(sql);
        return  st.executeQuery(sql);
    }

    ResultSet PatientId(String name) throws SQLException{
        String sql = "SELECT user.Username, Patient.P_Id  FROM user INNER JOIN patient ON user.U_Id = patient.UId WHERE user.Username ='"+name+"'";
        Connection conn = connection();
        Statement st = conn.prepareStatement(sql);
        return  st.executeQuery(sql);
    }

    ResultSet Patient(int id) throws SQLException {
        String sql = "SELECT * FROM patient WHERE P_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet Patient(String uname) throws SQLException {
        String sql = "SELECT * FROM patient WHERE P_Name='"+uname+"'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet PatientAuto() throws SQLException{
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE Table_schema = 'hospital_db' AND TABLE_NAME = 'patient'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    void insertStaff(
            String name,
            String address,
            Date dob,
            String Contact,
            String gender,
            String occupatation,
            String photo
    ) throws SQLException {

        String sql = "INSERT INTO staff VALUES(NULL,?,?,?,?,?,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, name);
        st.setString(2, address);
        st.setDate(3,dob);
        st.setString(4,Contact);
        st.setString(7,photo);
        st.setString(5,occupatation);
        st.setString(6,gender);

        st.executeUpdate();
    }

    void deleteStaff(int id) throws SQLException{
        String sql = "DELETE FROM staff WHERE Staff_Id='"+id+"'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }

    void updateStaff(
            int Id,
            String name,
            String address,
            Date date,
            String contact,
            String gender,
            String occupation) throws SQLException {
        String Updatequeryname = "UPDATE staff SET P_Name='" + name + "'WHERE Staff_Id='" + Id + "'";
        String Updatequeryaddress = "UPDATE staff SET P_Address='" + address + "'WHERE Staff_Id='" + Id + "'";
        String updatequerydate = "UPDATE staff SET  DOB='" + date + "'WHERE Staff_Id='" + Id + "'";
        String updatequercontact = "UPDATE staff SET  Contact='" + contact + "'WHERE Staff_Id='" + Id + "'";
        String updatequeryGender = "UPDATE staff SET  Gender='" + gender + "'WHERE Staff_Id='" + Id + "'";
        String updatequeryOccupation = "UPDATE staff SET  Disease='" + occupation + "'WHERE Staff_Id='" + Id + "'";
        String sql = "SELECT * FROM staff WHERE Staff_Id='" + Id + "'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate(Updatequeryname);
        st.executeUpdate(Updatequeryaddress);
        st.executeUpdate(updatequerydate);
        st.executeUpdate(updatequercontact);
        st.executeUpdate(updatequeryGender);
        st.executeUpdate(updatequeryOccupation);
        st.executeQuery(sql);
    }

    ResultSet Staff() throws SQLException {
        String sql = "SELECT * FROM staff";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet Staff(int id) throws SQLException {
        String sql = "SELECT * FROM staff WHERE Staff_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet StaffAuto() throws SQLException{
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE Table_schema = 'hospital_db' AND TABLE_NAME = 'staff'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    void insertBed(
            String Categories,
            String Roomno
    ) throws SQLException {

        String sql = "INSERT INTO bed VALUES(NULL,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, Categories);
        st.setString(2, Roomno);

        st.executeUpdate();
    }

    void deleteBed(int id) throws SQLException{
        String sql = "DELETE FROM bed WHERE B_Id='"+id+"'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }

    void updateBed(
            int Id,
            String categories,
            String Roomno) throws SQLException {
        String Updatequeryname = "UPDATE bed SET B_Categories='" + categories + "'WHERE B_Id='" + Id + "'";
        String Updatequeryaddress = "UPDATE bed SET B_Roomno='" + Roomno + "'WHERE B_Id='" + Id + "'";
        String sql = "SELECT * FROM bed WHERE B_Id='" + Id + "'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate(Updatequeryname);
        st.executeUpdate(Updatequeryaddress);
        st.executeUpdate(Updatequeryname);
        st.executeUpdate(Updatequeryaddress);
        st.executeQuery(sql);
    }

    ResultSet Bed() throws SQLException {
        String sql = "SELECT * FROM bed";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet Bed(int id) throws SQLException {
        String sql = "SELECT * FROM bed WHERE B_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet BedAuto() throws SQLException{
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE Table_schema = 'hospital_db' AND TABLE_NAME = 'bed'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    void insertDept(
            String name,
            String description
    ) throws SQLException {

        String sql = "INSERT INTO dept VALUES(NULL,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, name);
        st.setString(2, description);

        st.executeUpdate();
    }

    void deleteDept(int id) throws SQLException{
        String sql = "DELETE FROM bed WHERE Dept_Id='"+id+"'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }

    void updateDept(
            int Id,
            String name,
            String description) throws SQLException {
        String Updatequeryname = "UPDATE dept SET Dept_Name='" + name + "'WHERE Dept_Id='" + Id + "'";
        String Updatequeryaddress = "UPDATE dept SET Dept_Description='" + description + "'WHERE Dept_Id='" + Id + "'";
        String sql = "SELECT * FROM dept WHERE Dept_Id='" + Id + "'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate(Updatequeryname);
        st.executeUpdate(Updatequeryaddress);
        st.executeUpdate(Updatequeryname);
        st.executeUpdate(Updatequeryaddress);
        st.executeQuery(sql);
    }

    ResultSet Dept() throws SQLException {
        String sql = "SELECT * FROM dept";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet Dept(int id) throws SQLException {
        String sql = "SELECT * FROM dept WHERE Dept_Id='"+id+"'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet DeptAuto() throws SQLException{
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE Table_schema = 'hospital_db' AND TABLE_NAME = 'dept'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    void insertAppointment(
            Date date,
            String timing,
            String status
    ) throws SQLException {

        String sql = "INSERT INTO appointment VALUES(NULL,?,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setDate(1, date);
        st.setString(2, timing);
        st.setString(3,status);
        st.executeUpdate();
    }

    ResultSet appointmentAuto() throws SQLException{
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE Table_schema = 'hospital_db' AND TABLE_NAME = 'appointment'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    void updateStatus(
            int id,
            String status) throws SQLException {
        String Updatequeryname = "UPDATE appointment SET status='" + status + "'WHERE Apt_Id='" + id + "'";
        String sql = "SELECT * FROM appointment WHERE Apt_Id='" + id + "'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate(Updatequeryname);
        st.executeQuery(sql);
    }

    void insertServes(
            int apt_id,
            int doc_id,
            int pat_id
    ) throws SQLException {

        String sql = "INSERT INTO serves VALUES(?,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, doc_id);
        st.setInt(2, apt_id);
        st.setInt(3,pat_id);
        st.executeUpdate();
    }

    ResultSet patientAge(int id) throws SQLException{
        String sql = "SELECT TIMESTAMPDIFF(YEAR,DOB,CURDATE()) AS Age from patient where P_Id='"+id+"'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        return st.executeQuery();
    }

    ResultSet patientDoctorAppointment(String uname) throws SQLException{
        String sql = "SELECT appointment.Apt_Id, patient.P_Id, patient.P_Name, patient.Disease, appointment.Timing, appointment.Date, appointment.status from serves INNER JOIN doctor ON doctor.D_Id = serves.D_Id INNER JOIN patient ON patient.P_Id = serves.P_Id INNER JOIN appointment ON appointment.Apt_Id = serves.Apt_Id INNER JOIN user ON user.U_Id = doctor.UId WHERE user.Username='"+uname+"' && appointment.status='pending' || appointment.status='approve'";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        return  st.executeQuery();

    }

    ResultSet doctorPatientAppointment(String uname) throws SQLException{
        Connection conn = connection();
        String sql = "SELECT appointment.Apt_Id, doctor.D_Name, appointment.Apt_Id, patient.P_Id, patient.P_Name, patient.Disease, appointment.Timing, appointment.Date, appointment.status from serves INNER JOIN doctor ON doctor.D_Id = serves.D_Id INNER JOIN patient ON patient.P_Id = serves.P_Id INNER JOIN appointment ON appointment.Apt_Id = serves.Apt_Id INNER JOIN user ON user.U_Id = patient.UId WHERE user.Username='"+uname+"'";
        PreparedStatement st = conn.prepareStatement(sql);
        return  st.executeQuery();

    }

    ResultSet doctorPatient(String uname) throws SQLException{
        Connection conn = connection();
        String sql = "SELECT doctor.D_Id, appointment.Apt_Id, patient.P_Id, patient.P_Name, patient.Disease, appointment.Timing, appointment.Date, appointment.status from serves INNER JOIN doctor ON doctor.D_Id = serves.D_Id INNER JOIN patient ON patient.P_Id = serves.P_Id INNER JOIN appointment ON appointment.Apt_Id = serves.Apt_Id INNER JOIN user ON user.U_Id = doctor.UId WHERE user.Username='"+uname+"'";
        PreparedStatement st = conn.prepareStatement(sql);
        return  st.executeQuery();

    }

    void insertReport(
            String medicine,
            String description
    ) throws SQLException {

        String sql = "INSERT INTO report VALUES(null,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, medicine);
        st.setString(2, description);
        st.executeUpdate();
    }

    void insertCreates(
            int pat_id,
            int rep_id,
            int doc_id,
            int apt_id
    ) throws SQLException {

        String sql = "INSERT INTO creates VALUES(?,?,?,?)";
        Connection conn = connection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, pat_id);
        st.setInt(2, rep_id);
        st.setInt(3,doc_id);
        st.setInt(4,apt_id);
        st.executeUpdate();
    }

    ResultSet reportAuto() throws SQLException{
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE Table_schema = 'hospital_db' AND TABLE_NAME = 'report'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    ResultSet treatmentDetails(int apt_id) throws SQLException{
        String sql = "SELECT patient.P_Id, appointment.date, patient.P_Name, patient.Gender, patient.Disease, report.Descriptions,report.Tablets FROM creates INNER JOIN appointment ON creates.A_Id = appointment.Apt_Id INNER JOIN report ON creates.R_Id = report.R_Id INNER JOIN doctor ON creates.D_Id = doctor.D_Id INNER JOIN patient ON creates.P_Id = patient.P_Id WHERE A_Id='"+apt_id+"'";
        Connection conn = connection();
        Statement st = conn.createStatement();
        return  st.executeQuery(sql);
    }

}
