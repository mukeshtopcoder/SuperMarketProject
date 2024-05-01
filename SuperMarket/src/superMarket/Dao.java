package superMarket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dao {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/supermarket";
	private static final String pwd = "root123";
	private static final String user = "root";
	private static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(url,user,pwd);
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean DeleteProduct(int pid) {
		try {
			String sql = "DELETE FROM product WHERE pid=?";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, pid);
			int ar = pst.executeUpdate();
			if(ar>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static Products getProductById(int pid) {
		Products p = null;
		try {
			String sql = "SELECT * FROM product WHERE pid=?";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				p = new Products();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setQty(rs.getInt(3));
				p.setPrice(rs.getInt(4));
			}
			return p;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	public static ArrayList<Users> ViewUsers(String roles){
		ArrayList<Users> al = new ArrayList<>();
		Users u = null;
		try {
			String sql = "SELECT * FROM users WHERE roles=?";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setString(1, roles);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				u = new Users();
				u.setUid(rs.getInt(1));
				u.setFname(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPwd(rs.getString(4));
				u.setRoles(rs.getString(5));
				al.add(u);
			}
			return al;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	public static boolean AddUser(Users u) {
		try {
			String sql = "INSERT INTO users(fname,email,pwd,roles) VALUE(?,?,?,?);";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,u.getFname());
			ps.setString(2,u.getEmail());
			ps.setString(3,u.getPwd());
			ps.setString(4,u.getRoles());
			int ar = ps.executeUpdate();
			if(ar>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static Users CheckUser(String email,String pwd,String roles) {
		try {
			Users u = null;
			String sql = "SELECT * FROM users WHERE email=? AND pwd=? AND roles=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pwd);
			ps.setString(3, roles);
			int flag = 0;
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				flag = 1;
				u = new Users();
				u.setUid(rs.getInt(1));
				u.setFname(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPwd(rs.getString(4));
				u.setRoles(rs.getString(5));
			}
			if(flag==1)
				return u;
			else
				return null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean AddProduct(Products p) {
		try {
			String str = "INSERT INTO product(pname,qty,price) VALUE(?,?,?)";
			PreparedStatement pst = getConnection().prepareStatement(str);
			pst.setString(1,p.getPname());
			pst.setInt(2,p.getQty());
			pst.setInt(3,p.getPrice());
			int ar = pst.executeUpdate();
			if(ar>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static ArrayList<Products> ViewProducts() {
		ArrayList<Products> al = new ArrayList<>();
		Products p = null;
		try {
			String sql = "SELECT * FROM product";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				p = new Products();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setQty(rs.getInt(3));
				p.setPrice(rs.getInt(4));
				al.add(p);
			}
			return al;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return al;
	}
}