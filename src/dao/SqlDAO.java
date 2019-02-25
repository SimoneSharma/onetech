package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Cart;
import bean.Category;
import bean.Product;
import bean.User;

public class SqlDAO extends DAOFactory {

	Connection con;
	Statement st;
	ResultSet rs;
	String query;
	boolean flag = false;
	
	@Override
	public boolean checkEmail(String email) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="select user_email from one_registeration where user_email='"+email+"' ";
			rs = st.executeQuery(query);
			if(rs.next())
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}    

	@Override
	public User getEmail(User user) {
		
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="select * from user_info where user_email='"+user.getEmail()+"' ";
			rs = st.executeQuery(query);
			if(rs.next())
			 user = new User();
			 user.setEmail(rs.getString("user_email"));
			 user.setPass(rs.getString("password"));
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}    

	@Override
	public boolean insertOtp(User user) {
		
		try {
			con = DbConnection.getConnection();
			st = con.createStatement(); 
			query="insert into otp(email,otp) value('"+user.getEmail()+"','"+user.getOtp()+"')";
			int r = st.executeUpdate(query);
			if(r>0) 
				flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean updateOtp(User user) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="update otp set otp='"+user.getOtp()+"' where email='"+user.getEmail()+"' ";
			int r = st.executeUpdate(query);
			if(r>0) {
				flag =true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteOtp(String email) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="delete from otp where email='"+email+"' ";
			int r = st.executeUpdate(query);
			if(r>0) {
				flag =true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	@Override
	public boolean checkOtp(String otp,String email) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="select * from otp where email='"+email+"' and otp='"+otp+"'";
			System.out.println(query);
			rs = st.executeQuery(query);
			if(rs.next())
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean register(User user) {
		
		try {        
			con = DbConnection.getConnection();
			st = con.createStatement(); 
			query="insert into one_registeration(user_name,user_email,password,status,phone_no) values('"+user.getName()+"','"+user.getEmail()+"','"+user.getPass()+"','pending','"+user.getPhone()+"')";
			System.out.println(query);
			int r = st.executeUpdate(query);
			if(r>0) {
				flag=true;
			} 
			else
				user =null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public boolean updateRegister(User user) {
		
		try {        
			con = DbConnection.getConnection();
			st = con.createStatement(); 
			query="update one_registeration set status='active' where user_email='"+user.getEmail()+"'";
			System.out.println(query);
			int r = st.executeUpdate(query);
			if(r>0) {
				flag=true;
			} 
			else
				user =null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	@Override
	public User checkLogin(User user) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="select * from one_registeration where user_email='"+user.getEmail()+"' and password='"+user.getPass()+"'";
			rs = st.executeQuery(query);
			if(rs.next()) {
				user = new User();
				user.setUsr_id(rs.getInt("one_registeration_id"));
				user.setName(rs.getString("user_name"));
				user.setEmail(rs.getString("user_email"));
				user.setPhone(rs.getString("phone_no"));
				user.setStatus(rs.getString("status"));
			}
			else
				user =null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean addProduct(Product product) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="insert into product(product_name,category,price,stock,qty,image,offer) value('"+product.getProduct_name()+"','"+product.getCategory()+"','"+product.getPrice()+"','"+product.getStock()+"','"+product.getQty()+"','"+product.getImage()+"','"+product.getOffer()+"')";

			int res = st.executeUpdate(query);
			if(res>0)
				flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public ArrayList<Product> getAllProduct() {
		ArrayList<Product> product_list = new ArrayList<Product>();
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="select * from product order by id desc";
			rs = st.executeQuery(query);
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getString("price"));
				product.setQty(rs.getString("qty"));
				product.setStock(rs.getString("stock"));
				product.setOffer(rs.getString("offer"));
				product.setImage(rs.getString("image"));
				product_list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product_list;
	}

	@Override
	public boolean deleteProduct(String product_id) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="delete from product where id="+product_id;
			System.out.println(query);
			int res = st.executeUpdate(query);
			if(res>0)
				flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public Product getProduct(Integer id) {
		Product product=null;
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="select * from product where id="+id;

			rs = st.executeQuery(query);
			if(rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getString("price"));
				product.setQty(rs.getString("qty"));
				product.setStock(rs.getString("stock"));
				product.setOffer(rs.getString("offer"));
				product.setImage(rs.getString("image"));
				product.setImage2(rs.getString("image2"));
				product.setImage3(rs.getString("image3"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean updateProduct(Product product) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="update product set product_name='"+product.getProduct_name()+"', category='"+product.getCategory()+"', price='"+product.getPrice()+"', stock='"+product.getStock()+"', qty='"+product.getQty()+"' where id="+product.getId();
			int res = st.executeUpdate(query);
			if(res>0)
				flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@Override
	public boolean addCategory(String category_name) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="insert into category(category_name) value('"+category_name+"')";

			int res = st.executeUpdate(query);
			if(res>0)
				flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@Override
	public ArrayList<Category> getAllCategory() {
		ArrayList<Category> allcategory = new ArrayList<Category>();
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="select * from category order by category_id desc";
			rs = st.executeQuery(query);
			while(rs.next()) {
				Category category = new Category();
				category.setCategory(rs.getString("category_name"));
				allcategory.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allcategory;
	}
	
	@Override
	public boolean checkCategory(String category_name) {
		try {
			con = DbConnection.getConnection();
			st = con.createStatement();
			query="select * from category where category_name='"+category_name+"' ";
			rs = st.executeQuery(query);
			if(rs.next()) {

				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertCart(Cart cart) {
		
		try {
			Connection con = DbConnection.getConnection();
			st = con.createStatement();
			query="insert into cart(product_id,user_id) value('"+cart.getProduct_id()+"','"+cart.getUser_id()+"')";
			int i = st.executeUpdate(query);
			if(i>0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Integer cartProduct(Integer user_id) {
		Integer count = null;
		try {
			Connection con = DbConnection.getConnection();
			st = con.createStatement();
			query="select count(idcart) from cart where user_id="+user_id;
			System.out.println(query);
			rs = st.executeQuery(query);
			while(rs.next()) {
				count = rs.getInt(1); 
				System.out.println(count);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public ArrayList<String> getProductId(Integer usr_id) {
		ArrayList<String> idlist = new ArrayList<String>();
		try {
			Connection con = DbConnection.getConnection();
			st = con.createStatement();
			query="select product_id from cart where user_id="+usr_id;
			System.out.println(query);
			rs = st.executeQuery(query);
			while(rs.next()) {
				idlist.add(rs.getString("product_id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return idlist;
	}

	@Override
	public Product getProductById(Object prod_id) {
		
		Product product = null;
		try {
			Connection con = DbConnection.getConnection();
			st = con.createStatement();
			query="select * from product where id="+prod_id;
			rs = st.executeQuery(query);
			if(rs.next()) {
			    product = new Product();
				product.setId(rs.getInt(1));
				System.out.println(product.getId());
				product.setProduct_name(rs.getString("product_name"));
				product.setCategory(rs.getString("Category"));
				product.setPrice(rs.getString("price"));
				product.setStock(rs.getString("stock"));
				product.setQty(rs.getString("qty"));
				product.setImage(rs.getString("image"));
				product.setImage2(rs.getString("image2"));
				product.setImage3(rs.getString("image3"));
				product.setOffer(rs.getString("offer"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean deleteCartProd(Integer cart) {

		try {
			Connection con = DbConnection.getConnection();
			st = con.createStatement();
			query="delete from cart where product_id="+cart;
			System.out.println(query);
			int i = st.executeUpdate(query);
			if(i>0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public Integer totalPrice(Object usr_id) {
		int total=0;
		try {
			Connection con = DbConnection.getConnection();
			st = con.createStatement();
			query="select price from product where id="+usr_id;
			rs = st.executeQuery(query);
			if(rs.next()) {
				 total = Integer.parseInt(rs.getString("price"));
				 System.out.println("this is total "+total);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return total;
	}
}


