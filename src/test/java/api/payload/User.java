
package api.payload;
import com.fasterxml.jackson.annotation.JsonProperty;
public class User 
{
	private int id;

@JsonProperty("Username")	
private String Username;
@JsonProperty("FirstName")
private String FirstName;
@JsonProperty("Lastname")
private String Lastname;
private String Email;
private String Password;
private String Phone;
int UserStatus = 0;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return Username;
}
public void setUsername(String username) {
	Username = username;
}
public String getFirstName() {
	return FirstName;
}
public void setFirstName(String firstName) {
	FirstName = firstName;
}
public String getLastname() {
	return Lastname;
}
public void setLastname(String lastname) {
	Lastname = lastname;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public String getPhone() {
	return Phone;
}
public void setPhone(String phone) {
	Phone = phone;
}
public int getUserStatus() {
	return UserStatus;
}
public void setUserStatus(int userStatus) {
	UserStatus = userStatus;
}

}
