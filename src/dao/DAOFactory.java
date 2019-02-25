package dao;

import java.util.ArrayList;

import bean.Cart;
import bean.Category;
import bean.Product;
import bean.User;

public abstract class DAOFactory {

	public static DAOFactory getDao() {
		DAOFactory dao = new SqlDAO();
		return dao;
	}
	public abstract boolean register(User user);
	public abstract boolean updateRegister(User user);
	public abstract User checkLogin(User user);
	public abstract boolean addProduct(Product product);
	public abstract boolean updateProduct(Product product);
	public abstract ArrayList<Product> getAllProduct();
	public abstract Product getProduct(Integer id);
	public abstract boolean deleteProduct(String product_id);
	public abstract boolean checkEmail(String email);
	public abstract User getEmail(User user);
	public abstract boolean insertOtp(User user);
	public abstract boolean checkOtp(String otp,String email);
	public abstract boolean updateOtp(User user);
	public abstract boolean deleteOtp(String email);
	public abstract boolean addCategory(String category_name);
	public abstract ArrayList<Category> getAllCategory();
	public abstract boolean checkCategory(String category_name);
	public abstract boolean insertCart(Cart cart);
	public abstract Integer cartProduct(Integer id);
	public abstract ArrayList getProductId(Integer usr_id);
	public abstract Product getProductById(Object prod_id);
	public abstract boolean deleteCartProd(Integer cart);
	public abstract Integer totalPrice(Object usr_id);

}
